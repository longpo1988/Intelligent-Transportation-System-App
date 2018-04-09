package com.example.administrator.its_gs_mvp.ui.activity;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.mvp.LoginContract;
import com.example.administrator.its_gs_mvp.mvp.presenter.login.LoginPresenterImpl;
import com.example.administrator.its_gs_mvp.mvp.view.BaseActivityImpl;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class LoginActivity extends BaseActivityImpl<LoginContract.View, LoginPresenterImpl>
        implements LoginContract.View {

    @Override
    protected LoginPresenterImpl initPresenter() {
        return new LoginPresenterImpl();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }
}
