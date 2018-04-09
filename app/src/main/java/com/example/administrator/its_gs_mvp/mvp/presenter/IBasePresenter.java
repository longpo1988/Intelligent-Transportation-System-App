package com.example.administrator.its_gs_mvp.mvp.presenter;

import com.example.administrator.its_gs_mvp.mvp.view.IBaseView;

/**
 * Created by xww on 2018/4/8 0008.
 */

public interface IBasePresenter<V extends IBaseView> {
    /**
     * 绑定View
     */
    void attachView(V view);

    /**
     * 解绑View
     */
    void detachView();
}
