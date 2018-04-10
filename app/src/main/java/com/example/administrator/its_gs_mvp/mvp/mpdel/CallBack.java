package com.example.administrator.its_gs_mvp.mvp.mpdel;

import org.json.JSONObject;

/**
 * @Created by xww on 2018/4/8 0008.
 */

public interface CallBack<T> {
    /**
     * 本地加载数据 泛型
     */
    void onFinished(T data);

    /**
     * volley 请求回调
     */
    interface VolleyCallback {
        void onSucceed(JSONObject jsonObject);
    }
}
