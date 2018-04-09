package com.example.administrator.its_gs_mvp.mvp.mpdel;

import android.util.Log;

import com.example.administrator.its_gs_mvp.http.HttpUtil;
import com.example.administrator.its_gs_mvp.http.ServerURL;
import com.example.administrator.its_gs_mvp.mvp.LoginContract;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by xww on 2018/4/9 0009.
 */

public class LoginModel {
    private HttpUtil http = HttpUtil.getInstance();

    public void startLogin(String user, String pwd, final LoginContract.Model loginListener) {
//        RequestBody body = new FormBody.Builder()
//                .add("UserName", user)
//                .add("UserPwd", pwd)
//                .build();
//        http.sendPostRequest(ServerURL.USER_LOGIN, body, new CallBack.RespCallback() {
//            @Override
//            public void onSucceed(Response response) {
//                loginListener.loginResponse(response);
//            }
//        });
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
            }
        });
    }
}
