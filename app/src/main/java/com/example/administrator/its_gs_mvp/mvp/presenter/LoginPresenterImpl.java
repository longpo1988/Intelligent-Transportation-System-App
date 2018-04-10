package com.example.administrator.its_gs_mvp.mvp.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.its_gs_mvp.App;
import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.mvp.LoginContract;
import com.example.administrator.its_gs_mvp.mvp.mpdel.LoginModel;
import com.example.administrator.its_gs_mvp.mvp.presenter.base.BasePresenterImpl;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * @Created by xww on 2018/4/9 0009.
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
        loginModel = null;
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
    public void saveIPandPORT() {
        /*
           初始化对话框
        */
        final AlertDialog dialog = new AlertDialog.Builder(mView.getContext()).create();
        View dialogView = LayoutInflater.from(mView.getContext()).inflate(R.layout.dialog_login_net_settings, null);
        final EditText edtIp = dialogView.findViewById(R.id.edit_setting_url);
        final EditText edtPort = dialogView.findViewById(R.id.edit_setting_port);
        Button btnCancel = dialogView.findViewById(R.id.cancel);
        Button btnSave = dialogView.findViewById(R.id.save);
        dialog.setView(dialogView);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        /*
           加载已保存ip和port
        */
        SharedPreferences savedNet = App.context.getSharedPreferences("Setting", Context.MODE_PRIVATE);
        String _ip = savedNet.getString("ip", "");
        String _port = savedNet.getString("port", "");
        edtIp.setText(_ip);
        edtPort.setText(_port);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip = edtIp.getText().toString().trim();
                String port = edtPort.getText().toString().trim();
                if (TextUtils.isEmpty(ip) || TextUtils.isEmpty(port)) {
                    Toast.makeText(mView.getContext(), "ip或port不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences spNet = App.context.getSharedPreferences("Setting", Context.MODE_PRIVATE);
                    spNet.edit().putString("ip", ip).commit();
                    spNet.edit().putString("port", port).commit();
                }
                dialog.dismiss();
            }
        });

    }

    @Override
    public void loginResponse(JSONObject jsonObject) {
        try {
            if (jsonObject.getString("result").equals("S")) {
                mView.loginSucceed();
                Toast.makeText(mView.getContext(), "登录成功", Toast.LENGTH_SHORT).show();
            } else if (jsonObject.getString("result").equals("F")) {
                Toast.makeText(mView.getContext(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mView.getContext(), "访问服务器失败", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
