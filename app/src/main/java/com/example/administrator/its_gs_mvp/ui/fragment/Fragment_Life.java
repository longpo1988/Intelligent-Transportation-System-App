package com.example.administrator.its_gs_mvp.ui.fragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.adapter.base.BaseFragmentAdapter;
import com.example.administrator.its_gs_mvp.mvp.LifeContract;
import com.example.administrator.its_gs_mvp.mvp.presenter.LifePresenterImpl;
import com.example.administrator.its_gs_mvp.mvp.view.BaseFragmentImpl;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 生活助手
 *
 * @Created by xww on 2018/4/10 0010.
 */

public class Fragment_Life extends BaseFragmentImpl<LifeContract.View, LifePresenterImpl>
        implements LifeContract.View {

    @BindView(R.id.tv_currentTemperature)
    TextView tvCurrentTemperature;
    @BindView(R.id.tv_tadayTemperature)
    TextView tvTadayTemperature;
    @BindView(R.id.btn_temperatureRefresh)
    ImageButton btnTemperatureRefresh;
    @BindView(R.id.lineChart_WeekTemperature)
    LineChart lineChartWeekTemperature;
    @BindView(R.id.tv_ziwaixian)
    TextView tvZiwaixian;
    @BindView(R.id.tv_ziwaixianAdvice)
    TextView tvZiwaixianAdvice;
    @BindView(R.id.tv_ganmao)
    TextView tvGanmao;
    @BindView(R.id.tv_ganmapAdvice)
    TextView tvGanmapAdvice;
    @BindView(R.id.tv_chuangyi)
    TextView tvChuangyi;
    @BindView(R.id.tv_chuangyiAdvice)
    TextView tvChuangyiAdvice;
    @BindView(R.id.tv_yundong)
    TextView tvYundong;
    @BindView(R.id.tv_yundongAdvice)
    TextView tvYundongAdvice;
    @BindView(R.id.tv_kongqiwuran)
    TextView tvKongqiwuran;
    @BindView(R.id.tv_kongqiwuranAdvice)
    TextView tvKongqiwuranAdvice;
    @BindView(R.id.tv_MinuteMax)
    TextView tvMinuteMax;
    @BindView(R.id.btn_KongQiZhiLiang)
    TextView btnKongQiZhiLiang;
    @BindView(R.id.btn_WenDu)
    TextView btnWenDu;
    @BindView(R.id.btn_XiangDuiShiDu)
    TextView btnXiangDuiShiDu;
    @BindView(R.id.btn_ErYangHuaTan)
    TextView btnErYangHuaTan;
    @BindView(R.id.vp_Life)
    ViewPager vpLife;

    private int Position = 0;

    @Override
    protected LifePresenterImpl initPresenter() {
        return new LifePresenterImpl();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_life;
    }

    @Override
    protected void initView() {
        lineChartWeekTemperature.setDescription("");
        lineChartWeekTemperature.getAxisRight().setEnabled(false);
        lineChartWeekTemperature.getAxisLeft().setDrawLabels(false);
        lineChartWeekTemperature.getAxisLeft().setDrawAxisLine(false);
        lineChartWeekTemperature.getLegend().setEnabled(false);
        lineChartWeekTemperature.getXAxis().setDrawGridLines(false);
        lineChartWeekTemperature.setDrawGridBackground(false);
        lineChartWeekTemperature.getAxisLeft().setGridColor(Color.parseColor("#DEDEDE"));
        lineChartWeekTemperature.getXAxis().setEnabled(false);
        mPresenter.setLineChartChanging(0, btnKongQiZhiLiang, btnWenDu, btnXiangDuiShiDu, btnErYangHuaTan
                , vpLife);
    }

    @OnClick(R.id.btn_temperatureRefresh)
    public void wheatherRefreshing() {
        mPresenter.onRefreshingTemp();
    }

    @Override
    public void setAdapter(List<Fragment> fragmentList) {
        vpLife.setOffscreenPageLimit(4);
        FragmentManager manager = getChildFragmentManager();
        if (manager != null) {
            vpLife.setAdapter(new BaseFragmentAdapter(manager, fragmentList));
        }
        vpLife.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Position = position;
                mPresenter.setLineChartChanging(position, btnKongQiZhiLiang, btnWenDu, btnXiangDuiShiDu, btnErYangHuaTan
                        , vpLife);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    @Override
    public void setTodayTemperture(int cTemp, String areaTemp) {
        tvCurrentTemperature.setText(cTemp + "°");
        tvTadayTemperature.setText("今天:" + areaTemp + "°c");
    }

    @Override
    public void setWeekTempLine(List<Integer> tempMax, List<Integer> tempMin) {
        //添加数据
        List<String> xLable = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            xLable.add("");
        }
        List<Entry> yLable_Max = new ArrayList<>();
        for (int i = 0; i < tempMax.size(); i++) {
            yLable_Max.add(new Entry(tempMax.get(i), i));
        }
        List<Entry> yLable_Min = new ArrayList<>();
        for (int i = 0; i < tempMin.size(); i++) {
            yLable_Min.add(new Entry(tempMin.get(i), i));
        }

        List<LineDataSet> dataSetList = new ArrayList<>();
        //未来几天最高温度折线
        LineDataSet dataSet_Max = new LineDataSet(yLable_Max, "");
        dataSet_Max.setLineWidth(1);
        dataSet_Max.setCircleSize(5);
        dataSet_Max.setCircleColorHole(Color.parseColor("#CD8500"));
        dataSet_Max.setCircleColor(Color.parseColor("#CD8500"));
        dataSet_Max.setColor(Color.parseColor("#CD8500"));
        dataSet_Max.setValueFormatter(new LargeValueFormatter());
        //未来几天最高低度折线
        LineDataSet dataSet_Min = new LineDataSet(yLable_Min, "");
        dataSet_Min.setValueFormatter(new LargeValueFormatter());
        dataSet_Min.setColor(Color.parseColor("#104E8B"));
        dataSet_Min.setCircleColorHole(Color.parseColor("#104E8B"));
        dataSet_Min.setCircleColor(Color.parseColor("#104E8B"));
        dataSet_Min.setLineWidth(1);
        dataSet_Min.setCircleSize(5);

        dataSetList.add(dataSet_Max);
        dataSetList.add(dataSet_Min);
        LineData data = new LineData(xLable, dataSetList);
        lineChartWeekTemperature.setData(data);
        lineChartWeekTemperature.invalidate();
    }

    @Override
    public void setLivingIndex(String tempIndex, String humdIndex, String co2Index, String lightIndex,
                               String pmIndex, String tempTips, String humdTips, String co2Tips,
                               String lightTips, String pmTips) {
        tvZiwaixian.setText(lightIndex);
        tvChuangyi.setText(tempIndex);
        tvGanmao.setText(humdIndex);
        tvKongqiwuran.setText(pmIndex);
        tvYundong.setText(co2Index);
        tvZiwaixianAdvice.setText(lightTips);
        tvChuangyiAdvice.setText(tempTips);
        tvGanmapAdvice.setText(humdTips);
        tvKongqiwuranAdvice.setText(pmTips);
        tvYundongAdvice.setText(co2Tips);
    }

    @Override
    public void setOneMinuteMaxValue(int maxTemp, int minTemp, int maxCO2, int maxPM25, int maxHumd) {
        switch (Position) {
            case 0:
                tvMinuteMax.setText("过去一分钟空气质量最差值：" + maxPM25);
                break;
            case 1:
                tvMinuteMax.setText("过去一分钟最高温度：" + maxTemp + " 最低温度：" + minTemp);
                break;
            case 2:
                tvMinuteMax.setText("过去一分钟最大相对湿度：" + maxHumd);
                break;
            case 3:
                tvMinuteMax.setText("过去一分钟最大浓度：" + maxCO2);
                break;
        }
    }

    @OnClick({R.id.btn_KongQiZhiLiang, R.id.btn_WenDu, R.id.btn_XiangDuiShiDu, R.id.btn_ErYangHuaTan})
    public void setLineChartChanged(View view) {
        switch (view.getId()) {
            case R.id.btn_KongQiZhiLiang:
                Position = 0;
                break;
            case R.id.btn_WenDu:
                Position = 1;
                break;
            case R.id.btn_XiangDuiShiDu:
                Position = 2;
                break;
            case R.id.btn_ErYangHuaTan:
                Position = 3;
                break;
        }
        mPresenter.setLineChartChanging(Position, btnKongQiZhiLiang, btnWenDu, btnXiangDuiShiDu, btnErYangHuaTan
                , vpLife);
    }
}
