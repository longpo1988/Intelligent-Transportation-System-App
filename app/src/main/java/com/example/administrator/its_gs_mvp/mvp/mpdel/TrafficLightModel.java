package com.example.administrator.its_gs_mvp.mvp.mpdel;

import com.example.administrator.its_gs_mvp.http.HttpUtil;
import com.example.administrator.its_gs_mvp.http.ServerURL;
import com.example.administrator.its_gs_mvp.mvp.TrafiicLightContract;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Created by xww on 2018/4/18 0018.
 */

public class TrafficLightModel {
    private HttpUtil http = HttpUtil.getInstance();

    public void getTrafficLightConfig(int TrafficLightId, final TrafiicLightContract.Model callback) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("TrafficLightId", TrafficLightId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        http.Request(ServerURL.GETTRAFFICLIGHTCONFIG, obj, new CallBack.VolleyCallback() {
            @Override
            public void onSucceed(JSONObject jsonObject) {
                callback.onCallbackGetTrafficLightConfigInfo(jsonObject);
            }
        });
    }

    public void setTrafficLightConfig(int TrafficLightId, int redTime, int greenTime, int yellowTime,
                                      final TrafiicLightContract.Model callback) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("TrafficLightId", TrafficLightId);
            obj.put("redTime", redTime);
            obj.put("greenTime", greenTime);
            obj.put("yellowTime", yellowTime);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        http.Request(ServerURL.SETTRAFFICLIGHTCONFIG, obj, new CallBack.VolleyCallback() {
            @Override
            public void onSucceed(JSONObject jsonObject) {
                callback.onCallbackSetTrafficLightConfig(jsonObject);
            }
        });
    }
}
