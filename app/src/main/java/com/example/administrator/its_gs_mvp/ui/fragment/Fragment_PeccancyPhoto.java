package com.example.administrator.its_gs_mvp.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.event.FragmentEvent;
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
    @BindView(R.id.btn_Back)
    Button btnBack;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_peccancyphoto;
    }

    @Override
    protected void initView() {
    }

    @OnClick({R.id.btn_Back, R.id.img_1, R.id.img_2, R.id.img_3, R.id.img_4})
    public void onBack(View view) {
        switch (view.getId()) {
            case R.id.btn_Back:
                EventBus.getDefault().post(new FragmentEvent(new Fragment_PeccancyList(), "违章详情"));
                break;
            case R.id.img_1:
                Fragment_PeccancyPhotoDetail.Flag = 1;
                EventBus.getDefault().post(new FragmentEvent(new Fragment_PeccancyPhotoDetail(), "照片详情"));
                break;
            case R.id.img_2:
                Fragment_PeccancyPhotoDetail.Flag = 2;
                EventBus.getDefault().post(new FragmentEvent(new Fragment_PeccancyPhotoDetail(), "照片详情"));
                break;
            case R.id.img_3:
                Fragment_PeccancyPhotoDetail.Flag = 3;
                EventBus.getDefault().post(new FragmentEvent(new Fragment_PeccancyPhotoDetail(), "照片详情"));
                break;
            case R.id.img_4:
                Fragment_PeccancyPhotoDetail.Flag = 4;
                EventBus.getDefault().post(new FragmentEvent(new Fragment_PeccancyPhotoDetail(), "照片详情"));
                break;
        }
    }
}

