package com.example.administrator.its_gs_mvp.mvp.presenter;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.bean.TrafficLightBean;
import com.example.administrator.its_gs_mvp.mvp.TrafiicLightContract;
import com.example.administrator.its_gs_mvp.mvp.mpdel.TrafficLightModel;
import com.example.administrator.its_gs_mvp.mvp.presenter.base.BasePresenterImpl;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xww on 2018/4/18 0018.
 */

public class TrafiicLightPresenterImpl extends BasePresenterImpl<TrafiicLightContract.View>
        implements TrafiicLightContract.Presenter, TrafiicLightContract.Model {

    private TrafficLightModel trafficLightModel;
    private List<TrafficLightBean> trafficLightBeanList;

    public TrafiicLightPresenterImpl() {
        trafficLightBeanList = new ArrayList<>();
        trafficLightModel = new TrafficLightModel();
    }

    @Override
    public void onCreate() {
        /**
         * 调用此方法去获取数据
         */
        mView.addTrafficLightID();
    }

    @Override
    public void onDestory() {
        trafficLightModel = null;
        trafficLightBeanList.clear();
        trafficLightBeanList = null;
    }

    @Override
    public void onCallbackGetTrafficLightConfigInfo(JSONObject jsonObject) {
        try {
            if (jsonObject.getInt("code") == 1) {
                /**
                 * 服务器返回数据，进行解析
                 */
                TrafficLightBean bean = new Gson().fromJson(jsonObject.toString(), TrafficLightBean.class);
                if (bean != null) {
                    trafficLightBeanList.add(bean);
                    /**
                     * 进行排序
                     */
                    SetSortTrafficLight();
                    /**
                     * 设置显示
                     */
                    mView.setAdapter(trafficLightBeanList);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCallbackSetTrafficLightConfig(JSONObject jsonObject) {
        Log.i("------------->", "onCallbackSetTrafficLightConfig: " + jsonObject.toString());
        try {
            if (jsonObject.getInt("code") == 1) {
                Log.i("-------------返回---size", ": " + trafficLightBeanList.size());
                trafficLightBeanList.clear(); /** 清空Recyclerview适配器之前保存的数据 **/
                mView.addTrafficLightID();
                mView.clearTrafficLightIDlist();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTrafficLightId() {
        /**
         * 让Model去获取网络数据
         */
        for (int i = 1; i < 6; i++) {
            trafficLightModel.getTrafficLightConfig(i, this);
        }
    }

    /**
     * 比较类
     */
    private Comparator<TrafficLightBean> comparatorTrafficLight;
    private int Position = 0;

    /**
     * 对红绿灯按照规则进行排序方法
     */
    private void SetSortTrafficLight() {
        switch (Position) {
            case 0:
                comparatorTrafficLight = new Comparator<TrafficLightBean>() {
                    @Override
                    public int compare(TrafficLightBean tl1, TrafficLightBean tl2) {
                        int num1 = tl1.getData().get(0).getTrafficID();
                        int num2 = tl2.getData().get(0).getTrafficID();
                        if (num1 > num2) {
                            return 1;
                        } else if (num1 == num2) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                };
                break;
            case 1:
                comparatorTrafficLight = new Comparator<TrafficLightBean>() {
                    @Override
                    public int compare(TrafficLightBean tl1, TrafficLightBean tl2) {
                        int num1 = tl1.getData().get(0).getTrafficID();
                        int num2 = tl2.getData().get(0).getTrafficID();
                        if (num1 > num2) {
                            return -1;
                        } else if (num1 == num2) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                };
                break;
            case 2:
                comparatorTrafficLight = new Comparator<TrafficLightBean>() {
                    @Override
                    public int compare(TrafficLightBean tl1, TrafficLightBean tl2) {
                        int num1 = tl1.getData().get(0).getRedTime();
                        int num2 = tl2.getData().get(0).getRedTime();
                        if (num1 > num2) {
                            return 1;
                        } else if (num1 == num2) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                };
                break;
            case 3:
                comparatorTrafficLight = new Comparator<TrafficLightBean>() {
                    @Override
                    public int compare(TrafficLightBean tl1, TrafficLightBean tl2) {
                        int num1 = tl1.getData().get(0).getRedTime();
                        int num2 = tl2.getData().get(0).getRedTime();
                        if (num1 > num2) {
                            return -1;
                        } else if (num1 == num2) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                };
                break;
            case 4:
                comparatorTrafficLight = new Comparator<TrafficLightBean>() {
                    @Override
                    public int compare(TrafficLightBean tl1, TrafficLightBean tl2) {
                        int num1 = tl1.getData().get(0).getGreenTime();
                        int num2 = tl2.getData().get(0).getGreenTime();
                        if (num1 > num2) {
                            return 1;
                        } else if (num1 == num2) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                };
                break;
            case 5:
                comparatorTrafficLight = new Comparator<TrafficLightBean>() {
                    @Override
                    public int compare(TrafficLightBean tl1, TrafficLightBean tl2) {
                        int num1 = tl1.getData().get(0).getGreenTime();
                        int num2 = tl2.getData().get(0).getGreenTime();
                        if (num1 > num2) {
                            return -1;
                        } else if (num1 == num2) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                };
                break;
            case 6:
                comparatorTrafficLight = new Comparator<TrafficLightBean>() {
                    @Override
                    public int compare(TrafficLightBean tl1, TrafficLightBean tl2) {
                        int num1 = tl1.getData().get(0).getYellowTime();
                        int num2 = tl2.getData().get(0).getYellowTime();
                        if (num1 > num2) {
                            return 1;
                        } else if (num1 == num2) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                };
                break;
            case 7:
                comparatorTrafficLight = new Comparator<TrafficLightBean>() {
                    @Override
                    public int compare(TrafficLightBean tl1, TrafficLightBean tl2) {
                        int num1 = tl1.getData().get(0).getYellowTime();
                        int num2 = tl2.getData().get(0).getYellowTime();
                        if (num1 > num2) {
                            return -1;
                        } else if (num1 == num2) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                };
                break;
        }
        Collections.sort(trafficLightBeanList, comparatorTrafficLight);
    }

    @Override
    public void getSpinerItemSelected(int position) {
        trafficLightBeanList.clear(); /** 清空Recyclerview适配器之前保存的数据 **/
        mView.addTrafficLightID();/** 重新从网络获取数据 **/
        Position = position;
    }

    @Override
    public void showTrafficLightConfigSettingDialog(final List<Integer> trafficLightId) {
        if (trafficLightId.size() == 0) {
            Toast.makeText(mView.getContext(), "请选择红绿灯编号", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i("------------id--size", ": " + trafficLightId.size());
        final AlertDialog dialog = new AlertDialog.Builder(mView.getContext()).create();
        View TLConfig = LayoutInflater.from(mView.getContext()).inflate(R.layout.dialog_trafficlight_config_setting, null);
        final EditText edtRedTime = TLConfig.findViewById(R.id.edt_redTime);
        final EditText edtGreedTime = TLConfig.findViewById(R.id.edt_greenTime);
        final EditText edtYellowTime = TLConfig.findViewById(R.id.edt_yellowTime);
        Button btnConfirm = TLConfig.findViewById(R.id.btn_confirm);
        Button btnCancel = TLConfig.findViewById(R.id.btn_cancel);
        dialog.setView(TLConfig);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String redTime = edtRedTime.getText().toString().trim();
                final String greenTime = edtGreedTime.getText().toString().trim();
                final String yellowTime = edtYellowTime.getText().toString().trim();
                if (TextUtils.isEmpty(redTime) || TextUtils.isEmpty(greenTime) || TextUtils.isEmpty(yellowTime)) {
                    Toast.makeText(mView.getContext(), "红绿灯配置信息不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    for (int i = 0; i < trafficLightId.size(); i++) {
                        trafficLightModel.setTrafficLightConfig(trafficLightId.get(i),
                                Integer.valueOf(redTime),
                                Integer.valueOf(greenTime),
                                Integer.valueOf(yellowTime), TrafiicLightPresenterImpl.this);
                    }
                    dialog.dismiss();
                }
            }
        });
    }
}
