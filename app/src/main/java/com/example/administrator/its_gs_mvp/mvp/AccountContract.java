package com.example.administrator.its_gs_mvp.mvp;

import com.example.administrator.its_gs_mvp.mvp.presenter.base.IBasePresenter;
import com.example.administrator.its_gs_mvp.mvp.view.IBaseView;

/**
 * @Created by xww on 2018/4/9 0009.
 */

public class AccountContract {
    public interface View extends IBaseView {

    }

    public interface Presenter extends IBasePresenter<View> {

    }

    public interface Model {

    }
}
