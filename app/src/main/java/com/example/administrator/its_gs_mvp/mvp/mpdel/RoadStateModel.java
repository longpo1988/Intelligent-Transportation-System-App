package com.example.administrator.its_gs_mvp.mvp.mpdel;

import com.example.administrator.its_gs_mvp.http.HttpUtil;
import com.example.administrator.its_gs_mvp.http.ServerURL;
import com.example.administrator.its_gs_mvp.mvp.LifeContract;
import com.example.administrator.its_gs_mvp.mvp.RoadStateContract;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Created by xww on 2018/4/19 0019.
 */

public class RoadStateModel {
    private HttpUtil http = HttpUtil.getInstance();

    public void getRoadStatus(int RoadId, final RoadStateContract.Model callback) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("RoadId", RoadId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        http.Request(ServerURL.GETROADSTATUS, obj, new CallBack.VolleyCallback() {
            @Override
            public void onSucceed(JSONObject jsonObject) {
                callback.onCallbackGetRoadStatus(jsonObject);
            }
        });
    }

    public void getEnvSense(final RoadStateContract.Model callback) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("UserName", "user1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        http.Request(ServerURL.ENVIONMENT_SENSE, obj, new CallBack.VolleyCallback() {
            @Override
            public void onSucceed(JSONObject jsonObject) {
                callback.onCallbackGetSensor(jsonObject);
            }
        });
    }
}
