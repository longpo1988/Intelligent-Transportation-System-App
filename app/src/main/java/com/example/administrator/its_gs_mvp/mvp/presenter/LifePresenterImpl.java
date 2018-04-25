package com.example.administrator.its_gs_mvp.mvp.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.bean.SenseBean;
import com.example.administrator.its_gs_mvp.bean.WeatherBean;
import com.example.administrator.its_gs_mvp.event.SenseEvent;
import com.example.administrator.its_gs_mvp.mvp.LifeContract;
import com.example.administrator.its_gs_mvp.mvp.mpdel.CallBack;
import com.example.administrator.its_gs_mvp.mvp.mpdel.LifeModel;
import com.example.administrator.its_gs_mvp.mvp.presenter.base.BasePresenterImpl;
import com.example.administrator.its_gs_mvp.util.LoadingDialog;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xww on 2018/4/10 0010.
 */

public class LifePresenterImpl extends BasePresenterImpl<LifeContract.View>
        implements LifeContract.Presenter, LifeContract.Model, CallBack {

    private LifeModel lifeModel;
    private Timer timer;

    private int mTemp;//今天温度
    private String mAreaTemp;//今天温度范围

    public LifePresenterImpl() {
        timer = new Timer();
        lifeModel = new LifeModel();
    }

    @Override
    public void onCreate() {
        lifeModel.getFragmentList(this);
        LoadingDialog.showDialog(mView.getContext());
        lifeModel.getWeather(this);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                lifeModel.getEnvSense(LifePresenterImpl.this);
            }
        }, 1, 3000);
    }

    @Override
    public void onDestory() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    @Override
    public void onFinished(Object data) {
        mView.setAdapter((List<Fragment>) data);
    }

    /**
     * 获取天气温度数据
     */
    @Override
    public void getWeather(JSONObject jsonObject) {
        try {
            if (jsonObject.getInt("code") == 1) {
                LoadingDialog.disDialog();
                /**
                 * bean.get(0) 获取的是今天温度
                 * bean.get(1) 获取的是未来今天的温度范围
                 */
                WeatherBean bean = new Gson().fromJson(jsonObject.toString(), WeatherBean.class);
                if (bean != null) {
                    mTemp = bean.getData().get(0).getTodayTemp();
                    /**
                     * get(1).get(1) 获取的是第二天的数据
                     */
                    mAreaTemp = bean.getData().get(1).getDetail().get(1).getTempRange();
                    /**
                     设置今天温度数据
                     */
                    if (mView != null) {
                        mView.setTodayTemperture(mTemp, mAreaTemp);
                    }

                    List<Integer> tempMax = new ArrayList<>();
                    List<Integer> tempMin = new ArrayList<>();
                    List<WeatherBean.DataBean.DetailBean> rows_detailsList = bean.getData().get(1).getDetail();
                    for (int i = 0; i < rows_detailsList.size(); i++) {
                        String _areaTemp = rows_detailsList.get(i).getTempRange();
                        String[] _Temp = _areaTemp.split("~");
                        int _min = Integer.valueOf(_Temp[0]);
                        int _max = Integer.valueOf(_Temp[1]);
                        tempMin.add(_min);
                        tempMax.add(_max);
                    }
                    /**
                     设置未来几天温度折线图数据
                     */
                    if (mView != null) {
                        mView.setWeekTempLine(tempMax, tempMin);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取环境传感器数据
     */
    @Override
    public void getSense(JSONObject jsonObject) {
        try {
            if (jsonObject.getInt("code") == 1) {
                SenseBean bean = new Gson().fromJson(jsonObject.toString(), SenseBean.class);
                if (bean != null) {
                    int temp = bean.getData().get(0).getTemperature();
                    int humd = bean.getData().get(0).getHumidity();
                    int co2 = bean.getData().get(0).getCo2();
                    int light = bean.getData().get(0).getLightIntensity();
                    int pm = bean.getData().get(0).getPm25();

                    setLivingIndex(temp, humd, co2, light, pm);
                    selectMaxVaule(temp, humd, co2, light, pm);

                    EventBus.getDefault().post(new SenseEvent(temp, humd, co2, pm, light));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据传感器的值，提供今日生活指数
     */
    private void setLivingIndex(int temp, int humd, int co2, int light, int pm) {
        String tempIndex = null;
        String humdIndex = null;
        String co2Index = null;
        String lightIndex = null;
        String pmIndex = null;
        String tempTips = null;
        String humdTips = null;
        String co2Tips = null;
        String lightTips = null;
        String pmTips = null;

        /**
         紫外线指数及建议
         */
        if (light >= 0 && light < 1000) {
            lightIndex = "弱(" + light + ")";
            lightTips = "辐射较弱，涂擦SPF12~15、PA+护肤品";
        } else if (light >= 1000 && light <= 3000) {
            lightIndex = "中等(" + light + ")";
            lightTips = "涂擦 SPF 大于 15、PA+防晒护肤品";
        } else if (light > 3000) {
            lightIndex = "强(" + light + ")";
            lightTips = "尽量减少外出，需要涂抹高倍数防晒霜";
        }

        /**
         感冒指数及建议
         */
        if (temp < 8) {
            humdIndex = "较易发(" + temp + ")";
            humdTips = "温度低，风较大，较易发生感冒，注意防护";
        } else if (temp >= 8) {
            humdIndex = "少发(" + temp + ")";
            humdTips = "无明显降温，感冒机率较低";
        }

        /**
         穿衣指数及建议
         */
        if (temp < 12) {
            tempIndex = "冷(" + temp + ")";
            tempTips = "建议穿长袖衬衫、单裤等服装";
        } else if (temp >= 12 && temp <= 21) {
            tempIndex = "舒适(" + temp + ")";
            tempTips = "建议穿短袖衬衫、单裤等服装";
        } else if (temp > 21) {
            tempIndex = "热(" + temp + ")";
            tempTips = "适合穿 T 恤、短薄外套等夏季服装";
        }

        /**
         运动指数及建议
         */
        if (co2 >= 0 && co2 < 3000) {
            co2Index = "适宜(" + co2 + ")";
            co2Tips = "气候适宜，推荐您进行户外运动";
        } else if (co2 >= 3000 && co2 <= 6000) {
            co2Index = "中(" + co2 + ")";
            co2Tips = "易感人群应适当减少室外活动";
        } else if (co2 > 6000) {
            co2Index = "较不宜(" + co2 + ")";
            co2Tips = "空气氧气含量低，请在室内进行休闲运动";
        }

        /**
         空气污染扩散指数及建议
         */
        if (pm >= 0 && pm < 30) {
            pmIndex = "优(" + pm + ")";
            pmTips = "空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气";
        } else if (pm >= 30 && pm <= 100) {
            pmIndex = "良(" + pm + ")";
            pmTips = "易感人群应适当减少室外活动";
        } else if (pm > 100) {
            pmIndex = "污染(" + pm + ")";
            pmTips = "空气质量差，不适合户外活动";
        }

        if (!TextUtils.isEmpty(tempIndex) || !TextUtils.isEmpty(humdIndex) || !TextUtils.isEmpty(co2Index) ||
                !TextUtils.isEmpty(lightIndex) || !TextUtils.isEmpty(pmIndex) || !TextUtils.isEmpty(tempTips) ||
                !TextUtils.isEmpty(humdTips) || !TextUtils.isEmpty(co2Tips) || !TextUtils.isEmpty(lightTips) ||
                !TextUtils.isEmpty(pmTips)) {
            if (mView != null) {
                mView.setLivingIndex(tempIndex, humdIndex, co2Index, lightIndex, pmIndex,
                        tempTips, humdTips, co2Tips, lightTips, pmTips);
            }
        }
    }

    @Override
    public void onRefreshingTemp() {
        LoadingDialog.showDialog(mView.getContext());
        lifeModel.getWeather(this);
    }

    private int maxTemp;
    private int minTemp;
    private int maxPM25;
    private int maxHumd;
    private int maxCO2;
    private int time;

    private void selectMaxVaule(int temp, int humd, int co2, int light, int pm) {
        if (time > 60) {
            time = 0;
            maxTemp = -1;
            minTemp = -1;
            maxPM25 = -1;
            maxHumd = -1;
            maxCO2 = -1;
        } else {
            time++;
            if (temp > maxTemp) {
                maxTemp = temp;
            }
            if (temp < minTemp) {
                minTemp = temp;
            }
            if (humd > maxHumd) {
                maxHumd = humd;
            }
            if (co2 > maxCO2) {
                maxCO2 = co2;
            }
            if (pm > maxPM25) {
                maxPM25 = pm;
            }
        }
        if (mView != null) {
            mView.setOneMinuteMaxValue(maxTemp, minTemp, maxCO2, maxPM25, maxHumd);
        }
    }


    @Override
    public void setLineChartChanging(int position, TextView airQuality, TextView temp, TextView humd,
                                     TextView co2, ViewPager vpLife) {
        switch (position) {
            case 0:
                vpLife.setCurrentItem(0);
                airQuality.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life));
                temp.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life_null));
                humd.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life_null));
                co2.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life_null));
                break;
            case 1:
                vpLife.setCurrentItem(1);
                airQuality.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life_null));
                temp.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life));
                humd.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life_null));
                co2.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life_null));
                break;
            case 2:
                vpLife.setCurrentItem(2);
                airQuality.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life_null));
                temp.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life_null));
                humd.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life));
                co2.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life_null));
                break;
            case 3:
                vpLife.setCurrentItem(3);
                airQuality.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life_null));
                temp.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life_null));
                humd.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life_null));
                co2.setBackgroundDrawable(ContextCompat.getDrawable(mView.getContext(), R.drawable.border_life_2));
                break;
        }
    }
}
