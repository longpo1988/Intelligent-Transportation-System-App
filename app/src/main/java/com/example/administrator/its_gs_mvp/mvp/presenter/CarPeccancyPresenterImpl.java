package com.example.administrator.its_gs_mvp.mvp.presenter;

import android.text.TextUtils;

import com.example.administrator.its_gs_mvp.bean.CarPeccancyBean;
import com.example.administrator.its_gs_mvp.db.CarPeccancy;
import com.example.administrator.its_gs_mvp.mvp.CarPeccancyContract;
import com.example.administrator.its_gs_mvp.mvp.mpdel.CarPeccancyModel;
import com.example.administrator.its_gs_mvp.mvp.presenter.base.BasePresenterImpl;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

/**
 * Created by xww on 2018/4/20 0020.
 */

public class CarPeccancyPresenterImpl extends BasePresenterImpl<CarPeccancyContract.View>
        implements CarPeccancyContract.Presenter, CarPeccancyContract.Model {

    private CarPeccancyModel carPeccancyModel;
    private static CarPeccancyBean carPeccancyBean;

    public CarPeccancyPresenterImpl() {
        carPeccancyModel = new CarPeccancyModel();
    }

    @Override
    public void onCreate() {
        carPeccancyModel.getAllCarPeccancy(this);
    }

    @Override
    public void onDestory() {
        carPeccancyModel = null;
    }

    @Override
    public void onCallbackGetCarAllPeccancy(JSONObject jsonObject) {
        try {
            if (jsonObject.getInt("code") == 1) {
                CarPeccancyBean bean = new Gson().fromJson(jsonObject.toString(), CarPeccancyBean.class);
                if (bean != null) {
                    carPeccancyBean = bean;
                    /**
                     * 储存数据库
                     */
                    if (DataSupport.count(CarPeccancy.class) == 0) {
                        for (int i = 0; i < bean.getData().size(); i++) {
                            String carNumber = bean.getData().get(i).getCarNumber();
                            String pCode = bean.getData().get(i).getPCode();
                            String pAddr = bean.getData().get(i).getPAddr();
                            String pTime = bean.getData().get(i).getPTime();
                            CarPeccancy peccancy = new CarPeccancy(carNumber, pCode, pAddr, pTime);
                            peccancy.save();
                        }
                    }

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasPeccancy(String carNumber) {
        if (carPeccancyBean != null) {
            for (int i = 0; i < carPeccancyBean.getData().size(); i++) {
                String _carNumber = carPeccancyBean.getData().get(i).getCarNumber();
                if (!TextUtils.isEmpty(_carNumber)) {
                    if (carNumber.equals(_carNumber)) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
