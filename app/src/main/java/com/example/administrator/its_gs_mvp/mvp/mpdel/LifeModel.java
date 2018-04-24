package com.example.administrator.its_gs_mvp.mvp.mpdel;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.administrator.its_gs_mvp.http.HttpUtil;
import com.example.administrator.its_gs_mvp.http.ServerURL;
import com.example.administrator.its_gs_mvp.mvp.LifeContract;
import com.example.administrator.its_gs_mvp.ui.fragment.Fragment_Life_AirQuality;
import com.example.administrator.its_gs_mvp.ui.fragment.Fragment_Life_CO2;
import com.example.administrator.its_gs_mvp.ui.fragment.Fragment_Life_Humidity;
import com.example.administrator.its_gs_mvp.ui.fragment.Fragment_Life_Temperature;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xww on 2018/4/12 0012.
 */

public class LifeModel {
    private HttpUtil http = HttpUtil.getInstance();

    public void getWeather(final LifeContract.Model callback) {
        http.Request(ServerURL.WEATHER_INFO, null, new CallBack.VolleyCallback() {
            @Override
            public void onSucceed(JSONObject jsonObject) {
                callback.getWeather(jsonObject);
            }
        });
    }

    public void getEnvSense(final LifeContract.Model callback) {
        http.Request(ServerURL.ENVIONMENT_SENSE, null, new CallBack.VolleyCallback() {
            @Override
            public void onSucceed(JSONObject jsonObject) {
                callback.getSense(jsonObject);
            }
        });
    }

    public void getFragmentList(final CallBack callBack) {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment_Life_AirQuality());
        fragmentList.add(new Fragment_Life_Temperature());
        fragmentList.add(new Fragment_Life_Humidity());
        fragmentList.add(new Fragment_Life_CO2());
        callBack.onFinished(fragmentList);
    }
}
