package com.example.administrator.its_gs_mvp.mvp.mpdel;

import com.example.administrator.its_gs_mvp.http.HttpUtil;
import com.example.administrator.its_gs_mvp.http.ServerURL;
import com.example.administrator.its_gs_mvp.mvp.CarPeccancyContract;

import org.json.JSONObject;

/**
 * Created by xww on 2018/4/20 0020.
 */

public class CarPeccancyModel {
    private HttpUtil http = HttpUtil.getInstance();

    public void getAllCarPeccancy(final CarPeccancyContract.Model callback) {
        http.Request(ServerURL.GETALLCARPECCANCY, null, new CallBack.VolleyCallback() {
            @Override
            public void onSucceed(JSONObject jsonObject) {
                callback.onCallbackGetCarAllPeccancy(jsonObject);
            }
        });
    }
}
