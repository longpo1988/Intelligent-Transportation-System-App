package com.example.administrator.its_gs_mvp.mvp.presenter;

import android.util.Log;

import com.example.administrator.its_gs_mvp.bean.TravelBean;
import com.example.administrator.its_gs_mvp.mvp.TravelContract;
import com.example.administrator.its_gs_mvp.mvp.mpdel.TravelModel;
import com.example.administrator.its_gs_mvp.mvp.presenter.base.BasePresenterImpl;
import com.example.administrator.its_gs_mvp.util.LoadingDialog;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by xww on 2018/4/25 0025.
 */

public class TravelPresenterImpl extends BasePresenterImpl<TravelContract.View>
        implements TravelContract.Presenter, TravelContract.Model {

    private TravelModel travelModel;

    public TravelPresenterImpl() {
        travelModel = new TravelModel();
    }

    @Override
    public void onCreate() {
        LoadingDialog.showDialog(mView.getContext());
        travelModel.getTravelInfo(this);
    }

    @Override
    public void onDestory() {

    }

    @Override
    public void TravelInfo(JSONObject jsonObject) {
        try {
            if (jsonObject.getInt("code") == 1) {
                TravelBean bean = new Gson().fromJson(jsonObject.toString(), TravelBean.class);
                if (mView != null) {
                    mView.setAdapter(bean.getData());
                    LoadingDialog.disDialog();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
