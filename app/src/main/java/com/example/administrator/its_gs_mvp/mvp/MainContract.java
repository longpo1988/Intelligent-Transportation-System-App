package com.example.administrator.its_gs_mvp.mvp;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.example.administrator.its_gs_mvp.mvp.mpdel.CallBack;
import com.example.administrator.its_gs_mvp.mvp.presenter.IBasePresenter;
import com.example.administrator.its_gs_mvp.mvp.view.IBaseView;

/**
 * 主界面 契约
 * Created by xww on 2018/4/8 0008.
 */

public class MainContract {
    public interface View<T> extends IBaseView {
        /**
         * 设置侧拉选项
         */
        void setItems(T data);
    }

    public interface Presenter extends IBasePresenter<View> {
        /**
         * @param activity
         * @param drawerLayout
         * @param toolbar
         * @return
         */
        ActionBarDrawerToggle initToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar);
    }

    public interface Model {
        /**
         * 加载侧拉选项数据
         */
        void getItems(CallBack callBack);
    }
}
