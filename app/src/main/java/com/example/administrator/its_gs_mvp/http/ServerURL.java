package com.example.administrator.its_gs_mvp.http;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.its_gs_mvp.App;

/**
 * 服务器接口
 *
 * @Created by xww on 2018/4/9 0009.
 */

public class ServerURL {
    public static SharedPreferences savedNet = App.context.getSharedPreferences("Setting", Context.MODE_PRIVATE);
    private static String URL = "http://"
            + savedNet.getString("ip", "") + ":"
            + savedNet.getString("port", "")
            + "/transportservice/action/";

    public final static String USER_LOGIN = URL + "user_login.do";//登录接口
}
