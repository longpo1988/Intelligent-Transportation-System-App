package com.example.administrator.its_gs_mvp.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.its_gs_mvp.App;
import com.example.administrator.its_gs_mvp.mvp.mpdel.CallBack;

import org.json.JSONObject;

/**
 * Volley 网络框架
 *
 * @Created by xww on 2018/4/9 0009.
 */

public class HttpUtil {

    private static HttpUtil instance;
    private Handler handler;
    private RequestQueue queue;


    public HttpUtil() {
        handler = new Handler(Looper.getMainLooper());
        queue = Volley.newRequestQueue(App.context);
    }

    public static HttpUtil getInstance() {
        if (instance == null) {
            synchronized (HttpUtil.class) {
                if (instance == null) {
                    instance = new HttpUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 发送 Post 请求
     *
     * @param url      URL接口
     * @param obj      需要的Json参数
     * @param callback 回调监听
     */
    public void Request(String url, JSONObject obj, final CallBack.VolleyCallback callback) {
        Log.i("------------url", "Request: "+url);
        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.POST,
                url, obj, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                callback.onSucceed(jsonObject);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        queue.add(request);
    }
}
