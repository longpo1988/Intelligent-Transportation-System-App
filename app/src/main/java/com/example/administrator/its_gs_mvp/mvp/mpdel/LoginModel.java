package com.example.administrator.its_gs_mvp.mvp.mpdel;

import com.example.administrator.its_gs_mvp.http.HttpUtil;
import com.example.administrator.its_gs_mvp.http.ServerURL;
import com.example.administrator.its_gs_mvp.mvp.LoginContract;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Created by xww on 2018/4/9 0009.
 */

public class LoginModel {
    private HttpUtil http = HttpUtil.getInstance();

    public void startLogin(String user, String pwd, final LoginContract.Model loginListener) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("UserName", user);
            obj.put("UserPwd", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        http.Request(ServerURL.USER_LOGIN, obj, new CallBack.VolleyCallback() {
            @Override
            public void onSucceed(JSONObject jsonObject) {
                loginListener.loginResponse(jsonObject);
                http = null;
            }
        });
    }
}
