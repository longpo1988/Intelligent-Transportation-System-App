package com.example.administrator.its_gs_mvp.mvp;

import com.example.administrator.its_gs_mvp.mvp.presenter.IBasePresenter;
import com.example.administrator.its_gs_mvp.mvp.view.IBaseView;

import org.json.JSONObject;

import okhttp3.Response;

/**
 * Created by xww on 2018/4/9 0009.
 */

public class LoginContract {
    public interface View extends IBaseView {

    }

    public interface Presenter extends IBasePresenter<View> {
        void loginTest(String user, String pwd);
    }

    public interface Model {
        void loginResponse(Response response);

        void loginResponse(JSONObject jsonObject);
    }
}
