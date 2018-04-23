package com.example.administrator.its_gs_mvp.mvp;

import com.example.administrator.its_gs_mvp.mvp.presenter.base.IBasePresenter;
import com.example.administrator.its_gs_mvp.mvp.view.IBaseView;

import org.json.JSONObject;

/**
 * Created by xww on 2018/4/20 0020.
 */

public class CarPeccancyContract {
    public interface View extends IBaseView {

    }

    public interface Presenter extends IBasePresenter<View> {
        boolean hasPeccancy(String carNumber);
    }

    public interface Model {
        void onCallbackGetCarAllPeccancy(JSONObject jsonObject);
    }
}
