package com.example.administrator.its_gs_mvp.ui.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.event.TitleEvent;
import com.example.administrator.its_gs_mvp.mvp.view.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 监控抓拍
 *
 * @Created by xww on 2018/4/23 0023.
 */

public class Fragment_PeccancyPhoto extends BaseFragment {

    @BindView(R.id.img_1)
    ImageView img1;
    @BindView(R.id.img_2)
    ImageView img2;
    @BindView(R.id.img_3)
    ImageView img3;
    @BindView(R.id.img_4)
    ImageView img4;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_peccancyphoto;
    }

    @Override
    protected void initView() {
    }

    @OnClick({R.id.img_1, R.id.img_2, R.id.img_3, R.id.img_4})
    public void onBack(View view) {
        switch (view.getId()) {
            case R.id.img_1:
                Fragment_PeccancyPhotoDetail.Flag = 1;
                break;
            case R.id.img_2:
                Fragment_PeccancyPhotoDetail.Flag = 2;
                break;
            case R.id.img_3:
                Fragment_PeccancyPhotoDetail.Flag = 3;
                break;
            case R.id.img_4:
                Fragment_PeccancyPhotoDetail.Flag = 4;
                break;
        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(Fragment_PeccancyPhoto.this);
        transaction.add(R.id.mainContent, new Fragment_PeccancyPhotoDetail(), "photo");
        transaction.addToBackStack(null);
        transaction.commit();
        EventBus.getDefault().post(new TitleEvent("照片详情"));
    }

    public static boolean onKeyDown(int keycode, KeyEvent keyEvent) {
        EventBus.getDefault().post(new TitleEvent("违章详情"));
        return true;
    }
}

