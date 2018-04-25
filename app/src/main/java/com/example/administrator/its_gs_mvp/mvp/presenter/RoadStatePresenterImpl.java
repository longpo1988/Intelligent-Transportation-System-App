package com.example.administrator.its_gs_mvp.mvp.presenter;

import com.example.administrator.its_gs_mvp.bean.RoadStatusBean;
import com.example.administrator.its_gs_mvp.bean.SenseBean;
import com.example.administrator.its_gs_mvp.mvp.RoadStateContract;
import com.example.administrator.its_gs_mvp.mvp.mpdel.RoadStateModel;
import com.example.administrator.its_gs_mvp.mvp.presenter.base.BasePresenterImpl;
import com.example.administrator.its_gs_mvp.util.LoadingDialog;
import com.example.administrator.its_gs_mvp.util.TimeUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Created by xww on 2018/4/19 0019.
 */

public class RoadStatePresenterImpl extends BasePresenterImpl<RoadStateContract.View>
        implements RoadStateContract.Presenter, RoadStateContract.Model {

    private RoadStateModel roadStateModel;
    private Timer timer;
    private int Count;

    public RoadStatePresenterImpl() {
        timer = new Timer();
        roadStateModel = new RoadStateModel();
    }

    @Override
    public void onCreate() {
        /**
         * 获取1~8号道路
         */
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 1; i < 9; i++) {
                    roadStateModel.getRoadStatus(i, RoadStatePresenterImpl.this);
                }
                mView.setImgAnimationPolice(Count);
                Count++;
            }
        }, 0, 3000);
    }

    @Override
    public void onDestory() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    @Override
    public String getYear() {
        return TimeUtil.getYear();
    }

    @Override
    public String getWeek() {
        return TimeUtil.getWeek();
    }

    @Override
    public void getSensor() {
        LoadingDialog.showDialog(mView.getContext());
        roadStateModel.getEnvSense(this);
    }

    @Override
    public void onCallbackGetRoadStatus(JSONObject jsonObject) {
        try {
            if (jsonObject.getInt("code") == 1) {
                RoadStatusBean bean = new Gson().fromJson(jsonObject.toString(), RoadStatusBean.class);
                if (bean != null) {
                    int roadID = bean.getData().get(0).getRoadId();
                    int level = bean.getData().get(0).getLevel();
                    if (mView != null) {
                        mView.setRoadStatusColor(roadID, level);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCallbackGetSensor(JSONObject jsonObject) {
        try {
            if (jsonObject.getInt("code") == 1) {
                LoadingDialog.disDialog();
                SenseBean bean = new Gson().fromJson(jsonObject.toString(), SenseBean.class);
                if (bean != null) {
                    if (mView != null) {
                        mView.updateSensor(bean);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
