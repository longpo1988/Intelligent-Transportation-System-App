package com.example.administrator.its_gs_mvp.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.its_gs_mvp.mvp.presenter.base.BasePresenterImpl;

/**
 * Fragment 基类
 *
 * @Created by xww on 2018/4/8 0008.
 */

public abstract class BaseFragmentImpl<V extends IBaseView, T extends BasePresenterImpl<V>> extends Fragment
        implements IBaseView {

    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = initPresenter();
        mPresenter.attachView((V) this);
        return inflater.inflate(setLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        mPresenter.onCreate();
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView(); //释放引用
        }
        super.onDestroy();
    }

    protected abstract T initPresenter();

    protected abstract int setLayoutId();

    protected abstract void initView();
}
