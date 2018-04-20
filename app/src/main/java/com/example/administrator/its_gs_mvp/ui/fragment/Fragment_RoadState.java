package com.example.administrator.its_gs_mvp.ui.fragment;

import android.graphics.Color;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.bean.SenseBean;
import com.example.administrator.its_gs_mvp.mvp.RoadStateContract;
import com.example.administrator.its_gs_mvp.mvp.presenter.RoadStatePresenterImpl;
import com.example.administrator.its_gs_mvp.mvp.view.BaseFragmentImpl;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 道路状态
 *
 * @Created by xww on 2018/4/19 0019.
 */

public class Fragment_RoadState extends BaseFragmentImpl<RoadStateContract.View, RoadStatePresenterImpl>
        implements RoadStateContract.View {

    @BindView(R.id.tv_kuaisuTop)
    TextView tvKuaisuTop;
    @BindView(R.id.tv_kuaisuLeft1)
    TextView tvKuaisuLeft1;
    @BindView(R.id.tv_kuaisuLeft2)
    TextView tvKuaisuLeft2;
    @BindView(R.id.tv_kuaisuBottom)
    TextView tvKuaisuBottom;
    @BindView(R.id.tv_gaosu)
    TextView tv_gaosu;
    @BindView(R.id.tv_xueyuan)
    TextView tvXueyuan;
    @BindView(R.id.tv_xingfu)
    TextView tvXingfu;
    @BindView(R.id.tv_yiyuan)
    TextView tvYiyuan;
    @BindView(R.id.tv_zhongyang)
    TextView tvZhongyang;
    @BindView(R.id.tv_tingchechang)
    TextView tvTingchechang;
    @BindView(R.id.tv_lianxiang)
    TextView tvLianxiang;
    @BindView(R.id.tv_Year)
    TextView tvYear;
    @BindView(R.id.tv_Week)
    TextView tvWeek;
    @BindView(R.id.btn_Refreshing)
    ImageButton btnRefreshing;
    @BindView(R.id.tv_Temp)
    TextView tvTemp;
    @BindView(R.id.tv_Humd)
    TextView tvHumd;
    @BindView(R.id.tv_PM25)
    TextView tvPM25;
    @BindView(R.id.iv_pliceMan)
    ImageView ivPliceMan;
    @BindView(R.id.iv_pliceGirl)
    ImageView ivPliceGirl;

    @Override
    protected RoadStatePresenterImpl initPresenter() {
        return new RoadStatePresenterImpl();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_roadstate;
    }

    @Override
    protected void initView() {
    }

    @OnClick(R.id.btn_Refreshing)
    public void onRefreshing() {
        tvYear.setText(mPresenter.getYear());
        tvWeek.setText(mPresenter.getWeek());
        mPresenter.getSensor();
    }

    private String[] colors = {"", "#6ab82e", "#ece93a", "#f49b25", "#e33532", "#b01e23"};

    @Override
    public void setRoadStatusColor(int roadID, int level) {
        switch (roadID) {
            case 1:
                tvKuaisuBottom.setBackgroundColor(Color.parseColor(colors[level]));
                tvKuaisuTop.setBackgroundColor(Color.parseColor(colors[level]));
                tvKuaisuLeft1.setBackgroundColor(Color.parseColor(colors[level]));
                tvKuaisuLeft2.setBackgroundColor(Color.parseColor(colors[level]));
                break;
            case 2:
                tv_gaosu.setBackgroundColor(Color.parseColor(colors[level]));
                break;
            case 3:
                tvTingchechang.setBackgroundColor(Color.parseColor(colors[level]));
                break;
            case 4:
                tvXueyuan.setBackgroundColor(Color.parseColor(colors[level]));
                break;
            case 5:
                tvXingfu.setBackgroundColor(Color.parseColor(colors[level]));
                break;
            case 6:
                tvLianxiang.setBackgroundColor(Color.parseColor(colors[level]));
                break;
            case 7:
                tvYiyuan.setBackgroundColor(Color.parseColor(colors[level]));
                break;
            case 8:
                tvZhongyang.setBackgroundColor(Color.parseColor(colors[level]));
                break;
        }
    }

    @Override
    public void setImgAnimationPolice(int count) {
        if (count % 2 == 0) {
            ivPliceMan.setImageResource(R.drawable.img_roadstate_police_man_1);
            ivPliceGirl.setImageResource(R.drawable.img_roadstate_police_girl_1);
        } else {
            ivPliceMan.setImageResource(R.drawable.img_roadstate_police_man_2);
            ivPliceGirl.setImageResource(R.drawable.img_roadstate_police_girl_2);
        }
    }

    @Override
    public void updateSensor(SenseBean bean) {
        int temp = bean.getData().get(0).getTemperature();
        int humd = bean.getData().get(0).getHumidity();
        int pm25 = bean.getData().get(0).getPm25();
        tvTemp.setText("温度：" + temp + "°C");
        tvHumd.setText("相对湿度：" + humd + "%");
        tvPM25.setText("PM2.5：" + pm25 + "ug/m3");
    }
}
