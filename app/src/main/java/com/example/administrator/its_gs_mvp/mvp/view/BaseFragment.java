package com.example.administrator.its_gs_mvp.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xww on 2018/4/13 0013.
 */

public abstract class BaseFragment extends Fragment {
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(setLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected abstract int setLayoutId();

    protected abstract void initView();
}
