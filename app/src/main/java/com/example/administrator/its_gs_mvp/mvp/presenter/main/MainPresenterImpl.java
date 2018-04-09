package com.example.administrator.its_gs_mvp.mvp.presenter.main;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.mvp.MainContract;
import com.example.administrator.its_gs_mvp.mvp.mpdel.CallBack;
import com.example.administrator.its_gs_mvp.mvp.mpdel.MainModel;
import com.example.administrator.its_gs_mvp.mvp.presenter.BasePresenterImpl;

/**
 * Created by xww on 2018/4/8 0008.
 */

public class MainPresenterImpl extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter
        , CallBack {

    private MainModel mainModel;

    public MainPresenterImpl() {
        mainModel = new MainModel();
    }

    @Override
    public void onCreate() {
        mainModel.getItems(this);
    }

    @Override
    public void onDestory() {

    }

    @Override
    public void onFinished(Object data) {
        mView.setItems(data);
    }


    @Override
    public ActionBarDrawerToggle initToggle(Activity activity, final DrawerLayout drawerLayout, Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar,
                R.string.open, R.string.close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View content = drawerLayout.getChildAt(0); //获得主界面View
                if (drawerView.getTag().equals("left")) {  //判断是否是左菜单
                    int offset = (int) (drawerView.getWidth() * slideOffset);
                    content.setTranslationX(offset);
                }
            }
        };
        toggle.syncState();
        return toggle;
    }
}
