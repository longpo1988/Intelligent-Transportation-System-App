package com.example.administrator.its_gs_mvp.mvp;

import com.example.administrator.its_gs_mvp.mvp.presenter.base.IBasePresenter;
import com.example.administrator.its_gs_mvp.mvp.view.IBaseView;

import org.json.JSONObject;

/**
 * @Created by xww on 2018/4/9 0009.
 */

public class LoginContract {
    public interface View extends IBaseView {
        /**
         * 登录成功
         */
        void loginSucceed();
    }

    public interface Presenter extends IBasePresenter<View> {
        /**
         * 登录信息检查
         */
        void loginTest(String user, String pwd);

        /**
         * 保存ip和port
         */
        void saveIPandPORT();
    }

    public interface Model {
        /**
         * 登录业务逻辑处理
         */
        void loginResponse(JSONObject jsonObject);
    }
}
