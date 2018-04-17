package com.example.administrator.its_gs_mvp.ui.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.mvp.LoginContract;
import com.example.administrator.its_gs_mvp.mvp.presenter.LoginPresenterImpl;
import com.example.administrator.its_gs_mvp.mvp.view.BaseActivityImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录注册界面
 *
 * @Created by xww on 2018/4/9 0009.
 */

public class LoginActivity extends BaseActivityImpl<LoginContract.View, LoginPresenterImpl>
        implements LoginContract.View {

    @BindView(R.id.llayout_netSet)
    LinearLayout llayoutNetSet;
    @BindView(R.id.edt_user)
    EditText edtUser;
    @BindView(R.id.edt_pwd)
    EditText edtPwd;
    @BindView(R.id.cb_auto)
    CheckBox cbAuto;
    @BindView(R.id.cb_remember)
    CheckBox cbRemember;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected LoginPresenterImpl initPresenter() {
        return new LoginPresenterImpl();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {}

    @OnClick(R.id.btn_login)
    public void doLogin() {
        final String _userName = edtUser.getText().toString().trim();
        final String _userPwd = edtPwd.getText().toString().trim();
        mPresenter.loginTest(_userName, _userPwd);
    }

    @OnClick(R.id.llayout_netSet)
    public void ipSettings() {
        mPresenter.saveIPandPORT();
    }

    @Override
    public void loginSucceed() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
}
