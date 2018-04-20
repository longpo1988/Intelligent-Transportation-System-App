package com.example.administrator.its_gs_mvp.mvp;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.administrator.its_gs_mvp.mvp.presenter.base.IBasePresenter;
import com.example.administrator.its_gs_mvp.mvp.view.IBaseView;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by xww on 2018/4/10 0010.
 */

public class LifeContract {
    public interface View extends IBaseView {
        void setAdapter(List<Fragment> fragmentList);

        void setTodayTemperture(int cTemp, String areaTemp);

        void setWeekTempLine(List<Integer> tempMax, List<Integer> tempMin);

        /**
         * 生活指数
         */
        void setLivingIndex(String tempIndex, String humdIndex, String co2Index, String lightIndex, String pmIndex,
                            String tempTips, String humdTips, String co2Tips, String lightTips, String pmTips);

        void setOneMinuteMaxValue(int maxTemp, int minTemp, int maxCO2, int maxPM25, int maxHumd);

    }

    public interface Presenter extends IBasePresenter<View> {
        void onRefreshingTemp();
        /**
         * @param position   viewPager的position
         * @param airQuality 空气质量textview点击按钮
         * @param temp
         * @param humd
         * @param co2
         * @param vpLife
         */
        void setLineChartChanging(int position, TextView airQuality, TextView temp, TextView humd,
                                  TextView co2, ViewPager vpLife);
    }

    public interface Model {
        void getWeather(JSONObject jsonObject);

        void getSense(JSONObject jsonObject);
    }
}
