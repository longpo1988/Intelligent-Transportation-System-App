package com.example.administrator.its_gs_mvp.mvp;

import com.example.administrator.its_gs_mvp.mvp.presenter.base.IBasePresenter;
import com.example.administrator.its_gs_mvp.mvp.view.IBaseView;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by xww on 2018/4/21 0021.
 */

public class PeccancyListContract {
    public interface View extends IBaseView {
        void setCardAdapter(List<Map<String, Object>> cardInfo);

        void setCardDetailAdapter(List<Map<String, Object>> cardDetailInfo);
    }

    public interface Presenter extends IBasePresenter<View> {
        /**
         * 添加需要查询的车牌号
         *
         * @param carNumber
         */
        void addCarNumber(String carNumber, boolean isCardRefresh);
    }

    public interface Model {
        void onCallbackPeccancyCode(JSONObject jsonObject);
    }
}
