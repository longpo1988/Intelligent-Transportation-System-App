package com.example.administrator.its_gs_mvp.mvp.presenter.login;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.its_gs_mvp.mvp.LoginContract;
import com.example.administrator.its_gs_mvp.mvp.mpdel.LoginModel;
import com.example.administrator.its_gs_mvp.mvp.presenter.BasePresenterImpl;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by xww on 2018/4/9 0009.
 */

public class LoginPresenterImpl extends BasePresenterImpl<LoginContract.View>
        implements LoginContract.Presenter, LoginContract.Model {

    private LoginModel loginModel;

    public LoginPresenterImpl() {
        loginModel = new LoginModel();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestory() {

    }

    @Override
    public void loginTest(String user, String pwd) {
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(mView.getContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(mView.getContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            loginModel.startLogin(user, pwd, this);
        }
    }

    @Override
    public void loginResponse(Response response) {
    }

    @Override
    public void loginResponse(JSONObject jsonObject) {
        Log.i("--------------", "run: " + jsonObject.toString());
    }
}
