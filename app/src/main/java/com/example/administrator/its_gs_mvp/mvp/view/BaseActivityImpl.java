package com.example.administrator.its_gs_mvp.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.its_gs_mvp.mvp.presenter.base.BasePresenterImpl;

/**
 * Activity 基类
 *
 * @Created by xww on 2018/4/8 0008.
 */

public abstract class BaseActivityImpl<V extends IBaseView, T extends BasePresenterImpl<V>> extends AppCompatActivity
        implements IBaseView {

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.attachView((V) this);
        setContentView(setLayoutId());
        initView();
        mPresenter.onCreate();
    }

    /**
     * 实例化
     */
    protected abstract T initPresenter();

    /**
     * 视图创建之前初始所需数据
     */
    protected void init() {
    }

    protected abstract int setLayoutId();

    /**
     * 初始控件属性
     */
    protected abstract void initView();

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView(); //释放引用
            mPresenter.onDestory();
        }
        super.onDestroy();
    }
}
