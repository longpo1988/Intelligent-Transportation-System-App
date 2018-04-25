package com.example.administrator.its_gs_mvp.mvp.mpdel;

import com.example.administrator.its_gs_mvp.http.HttpUtil;
import com.example.administrator.its_gs_mvp.http.ServerURL;
import com.example.administrator.its_gs_mvp.mvp.PeccancyListContract;
import org.json.JSONObject;

/**
 * Created by xww on 2018/4/21 0021.
 */

public class PeccancyListModel {
    HttpUtil http = HttpUtil.getInstance();
    public void getPeccancyCode(final PeccancyListContract.Model callback) {
        http.Request(ServerURL.GETPECCANCYCODE, null, new CallBack.VolleyCallback() {
            @Override
            public void onSucceed(JSONObject jsonObject) {
                callback.onCallbackPeccancyCode(jsonObject);
            }
        });
    }
}
