package com.example.administrator.its_gs_mvp.mvp.presenter.base;

import com.example.administrator.its_gs_mvp.mvp.view.IBaseView;

/**
 * @Created by xww on 2018/4/8 0008.
 */

public abstract class BasePresenterImpl<V extends IBaseView> implements IBasePresenter<V> {
    public V mView;

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    /**
     * 视图创建之后所需的数据
     */
    public abstract void onCreate();

    /**
     * 视图摧毁，释放引用
     */
    public abstract void onDestory();
}
