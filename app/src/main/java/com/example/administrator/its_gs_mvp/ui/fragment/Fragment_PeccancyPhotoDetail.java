package com.example.administrator.its_gs_mvp.ui.fragment;

import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.event.FragmentEvent;
import com.example.administrator.its_gs_mvp.mvp.view.BaseFragment;
import com.example.administrator.its_gs_mvp.ui.listener.OnTouchListener;
import com.example.administrator.its_gs_mvp.ui.listener.TEST;

import org.greenrobot.eventbus.EventBus;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 照片详情
 *
 * @Created by xww on 2018/4/23 0023.
 */

public class Fragment_PeccancyPhotoDetail extends BaseFragment {

    @BindView(R.id.img_detail)
    ImageView imgDetail;
    @BindView(R.id.btn_Back)
    Button btnBack;

    public static int Flag;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_peccancyphotodetail;
    }

    @Override
    protected void initView() {
        switch (Flag) {
            case 1:
                imgDetail.setImageResource(R.drawable.weizhang01);
                break;
            case 2:
                imgDetail.setImageResource(R.drawable.weizhang02);
                break;
            case 3:
                imgDetail.setImageResource(R.drawable.weizhang03);
                break;
            case 4:
                imgDetail.setImageResource(R.drawable.weizhang04);
                break;
        }
//        imgDetail.setOnTouchListener(new OnTouchListener(imgDetail));
        imgDetail.setOnTouchListener(new TEST(imgDetail));
    }

    @OnClick(R.id.btn_Back)
    public void onBack() {
        EventBus.getDefault().post(new FragmentEvent(new Fragment_PeccancyPhoto(), "监控抓拍"));
    }
}

