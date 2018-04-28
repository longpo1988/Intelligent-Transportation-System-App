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
    public static String URL = "http://"
            + savedNet.getString("ip", "localhost") + ":"
            + savedNet.getString("port", "8080")
            + "/ITSservice/";

    public final static String USER_LOGIN = URL + "userLogin.do";//登录接口
    public final static String WEATHER_INFO = URL + "GetWeather.do";//天气接口
    public final static String ENVIONMENT_SENSE = URL + "GetSensor.do";//环境传感器接口
    public final static String GETTRAFFICLIGHTCONFIG = URL + "GetTrafficLightConfig.do";//获取红绿灯配置信息接口
    public final static String SETTRAFFICLIGHTCONFIG = URL + "SetTrafficLightConfig.do";//设置红绿灯配置信息接口
    public final static String GETROADSTATUS = URL + "GetRoadStatus.do";//获取道路状态接口
    public final static String GETALLCARPECCANCY = URL + "GetAllCarPeccancy.do";//获取所有车辆违章接口
    public final static String GETPECCANCYCODE = URL + "GetPeccancyCode.do";//获取违章代码接口
    public final static String GETTRAVELINFO = URL + "GetTravelInfo.do";//获取旅游信息接口
}
