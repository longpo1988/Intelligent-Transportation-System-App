package com.example.administrator.its_gs_mvp.ui.fragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.event.SenseEvent;
import com.example.administrator.its_gs_mvp.mvp.LifeContract;
import com.example.administrator.its_gs_mvp.mvp.presenter.base.BasePresenterImpl;
import com.example.administrator.its_gs_mvp.mvp.view.BaseFragment;
import com.example.administrator.its_gs_mvp.mvp.view.BaseFragmentImpl;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 生活助手 - 空气质量
 *
 * @Created by xww on 2018/4/13 0013.
 */

public class Fragment_Life_AirQuality extends BaseFragment {

    @BindView(R.id.barChart_PM)
    BarChart barChartPM;

    private List<Integer> pm25List;
    private List<String> timeList;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_life_air_quality;
    }

    @Override
    protected void initView() {
        pm25List = new ArrayList<>();
        timeList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            timeList.add("");
            pm25List.add(0);
        }
        barChartPM.setDescription("");
        barChartPM.setDrawGridBackground(false);
        barChartPM.getAxisRight().setEnabled(false);
        barChartPM.getAxisLeft().setDrawAxisLine(false);
        barChartPM.getAxisLeft().setGridColor(Color.parseColor("#DEDEDE"));
        barChartPM.getAxisLeft().setTextSize(18);
        barChartPM.getLegend().setEnabled(false);
        barChartPM.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChartPM.getXAxis().setTextSize(18);
        barChartPM.getXAxis().setDrawGridLines(false);
        barChartPM.getXAxis().setDrawAxisLine(false);
        barChartPM.getAxisLeft().setValueFormatter(new LargeValueFormatter());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    int time;

    @Subscribe
    public void recivedEvent(SenseEvent event) {
        ArrayList<BarEntry> yLable = new ArrayList<>();
        ArrayList<String> xLable = new ArrayList<>();
        pm25List.add(0, event.getPM25());
        pm25List.remove(20);
        if (time >= 57) {
            time = 0;
        }
        for (int i = 0; i < pm25List.size(); i++) {
            time += 3;
            xLable.add("" + time);
            yLable.add(new BarEntry(pm25List.get(i), i));
        }
        BarDataSet dataSet = new BarDataSet(yLable, "");
        dataSet.setColor(Color.parseColor("#B0B0B0"));
        dataSet.setBarShadowColor(Color.BLACK);
        dataSet.setBarSpacePercent(50);
        dataSet.setValueFormatter(new LargeValueFormatter());
        BarData barData = new BarData(xLable, dataSet);
        barChartPM.setData(barData);
        barChartPM.invalidate();
    }

}
