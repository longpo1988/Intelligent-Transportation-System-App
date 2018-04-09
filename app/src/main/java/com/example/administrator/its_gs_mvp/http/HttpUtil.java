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

import java.io.IOException;
import java.lang.reflect.Method;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by xww on 2018/4/9 0009.
 */

public class HttpUtil {

    private static HttpUtil instance;
    private OkHttpClient client;
    private Handler handler;

    private RequestQueue queue;

    public HttpUtil() {
        client = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
        queue = Volley.newRequestQueue(App.context);
    }

    public static HttpUtil getInstance() {
        if (instance == null) {
            synchronized (HttpUtil.class) {
                instance = new HttpUtil();
            }
        }
        return instance;
    }

    public void sendPostRequest(String url, RequestBody params, final CallBack.RespCallback callback) {
        Log.i("---------->url", ": " + url);
        final Request request = new Request.Builder()
                .url(url)
                .post(params)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSucceed(response);
                    }
                });
            }
        });
    }

    public void Request(String url, JSONObject obj, final CallBack.VolleyCallback callback) {
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
