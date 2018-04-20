package com.example.administrator.its_gs_mvp.mvp;

import com.example.administrator.its_gs_mvp.bean.SenseBean;
import com.example.administrator.its_gs_mvp.mvp.presenter.base.IBasePresenter;
import com.example.administrator.its_gs_mvp.mvp.view.IBaseView;

import org.json.JSONObject;

/**
 * @Created by xww on 2018/4/19 0019.
 */

public class RoadStateContract {
    public interface View extends IBaseView {
        void setRoadStatusColor(int roadID, int level);

        void setImgAnimationPolice(int count);

        void updateSensor(SenseBean bean);
    }

    public interface Presenter extends IBasePresenter<View> {
        String getYear();

        String getWeek();

        void getSensor();
    }

    public interface Model {
        void onCallbackGetRoadStatus(JSONObject jsonObject);

        void onCallbackGetSensor(JSONObject jsonObject);
    }
}
