package com.example.administrator.its_gs_mvp.mvp.mpdel;

import com.example.administrator.its_gs_mvp.http.HttpUtil;
import com.example.administrator.its_gs_mvp.http.ServerURL;
import com.example.administrator.its_gs_mvp.mvp.TravelContract;

import org.json.JSONObject;

/**
 * Created by xww on 2018/4/25 0025.
 */

public class TravelModel {
    HttpUtil http = HttpUtil.getInstance();

    public void getTravelInfo(final TravelContract.Model callback) {
        http.Request(ServerURL.GETTRAVELINFO, null, new CallBack.VolleyCallback() {
            @Override
            public void onSucceed(JSONObject jsonObject) {
                callback.TravelInfo(jsonObject);
            }
        });
    }
}
