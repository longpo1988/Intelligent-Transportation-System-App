package com.example.administrator.its_gs_mvp.mvp;

import com.example.administrator.its_gs_mvp.bean.TrafficLightBean;
import com.example.administrator.its_gs_mvp.mvp.presenter.base.IBasePresenter;
import com.example.administrator.its_gs_mvp.mvp.view.IBaseView;

import org.json.JSONObject;

import java.util.List;

/**
 * @Created by xww on 2018/4/18 0018.
 */

public class TrafiicLightContract {
    public interface View extends IBaseView {

        void setAdapter(List<TrafficLightBean> trafficLightBeanList);

        void clearTrafficLightIDlist();
    }

    public interface Presenter extends IBasePresenter<View> {
        /**
         * 依据Spainer设置排序
         *
         * @param position Spainer 的Item选项
         */
        void getSpinerItemSelected(int position);


        /**
         * 弹出红绿灯配置信息 dialog
         *
         * @param trafficLightId 需要配置红绿灯的 ID 集合
         */
        void showTrafficLightConfigSettingDialog(List<Integer> trafficLightId);
    }

    public interface Model {
        void onCallbackGetTrafficLightConfigInfo(JSONObject jsonObject);

        void onCallbackSetTrafficLightConfig(JSONObject jsonObject);
    }
}
