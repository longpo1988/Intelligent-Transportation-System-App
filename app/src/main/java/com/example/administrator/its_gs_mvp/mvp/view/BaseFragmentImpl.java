package com.example.administrator.its_gs_mvp.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.its_gs_mvp.mvp.presenter.base.BasePresenterImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment 基类
 *
 * @Created by xww on 2018/4/8 0008.
 */

public abstract class BaseFragmentImpl<V extends IBaseView, T extends BasePresenterImpl<V>> extends Fragment
        implements IBaseView {

    protected T mPresenter;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = initPresenter();
        mPresenter.attachView((V) this);
        View rootView = inflater.inflate(setLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onCreate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView(); //释放引用
            mPresenter.onDestory();
            mPresenter = null;
        }
    }

    protected abstract T initPresenter();

    protected abstract int setLayoutId();

    protected abstract void initView();
}
