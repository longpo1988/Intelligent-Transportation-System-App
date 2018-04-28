package com.example.administrator.its_gs_mvp.mvp;

import com.example.administrator.its_gs_mvp.bean.TravelBean;
import com.example.administrator.its_gs_mvp.mvp.presenter.base.IBasePresenter;
import com.example.administrator.its_gs_mvp.mvp.view.IBaseView;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by xww on 2018/4/25 0025.
 */

public class TravelContract {
    public interface View extends IBaseView {
        void setAdapter(List<TravelBean.DataBean> data);
    }

    public interface Presenter extends IBasePresenter<View> {
    }

    public interface Model {
        void TravelInfo(JSONObject jsonObject);
    }
}
