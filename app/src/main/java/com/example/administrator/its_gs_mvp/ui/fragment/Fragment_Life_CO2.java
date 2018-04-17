package com.example.administrator.its_gs_mvp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.event.SenseEvent;
import com.example.administrator.its_gs_mvp.mvp.presenter.base.BasePresenterImpl;
import com.example.administrator.its_gs_mvp.mvp.view.BaseFragment;
import com.example.administrator.its_gs_mvp.mvp.view.BaseFragmentImpl;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 生活助手 - 二氧化碳
 *
 * @Created by xww on 2018/4/13 0013.
 */

public class Fragment_Life_CO2 extends BaseFragment {

    @BindView(R.id.lineChart_CO2)
    LineChart lineChartCO2;

    private List<Integer> co2List;
    private List<String> timeList;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_life_co2;
    }

    @Override
    protected void initView() {
        co2List = new ArrayList<>();
        timeList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            timeList.add("");
            co2List.add(0);
        }
        lineChartCO2.setBackgroundColor(Color.parseColor("#52C6F7"));
        lineChartCO2.getLegend().setEnabled(false);
        lineChartCO2.setDescription("");
        lineChartCO2.setDrawGridBackground(false);
        lineChartCO2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChartCO2.getXAxis().setTextColor(Color.WHITE);
        lineChartCO2.getXAxis().setDrawGridLines(false);
        lineChartCO2.getXAxis().setDrawAxisLine(false);
        lineChartCO2.getXAxis().setTextSize(18);
        lineChartCO2.getAxisLeft().setDrawAxisLine(false);
        lineChartCO2.getAxisLeft().setTextColor(Color.WHITE);
        lineChartCO2.getAxisLeft().setTextSize(18);
        lineChartCO2.getAxisLeft().setGridColor(Color.WHITE);
        lineChartCO2.getAxisRight().setEnabled(false);
        lineChartCO2.getAxisLeft().setValueFormatter(new LargeValueFormatter());
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
        List<String> xLable = new ArrayList<>();
        List<Entry> yLable = new ArrayList<>();
        co2List.add(0, event.getCO2());
        co2List.remove(20);
        if (time >= 57) {
            time = 0;
        }
        for (int i = 0; i < co2List.size(); i++) {
            time += 3;
            xLable.add("" + time);
            yLable.add(new BarEntry(co2List.get(i), i));
        }
        LineDataSet dataSet = new LineDataSet(yLable, "");
        dataSet.setColor(Color.WHITE);
        dataSet.setCircleColorHole(Color.WHITE);
        dataSet.setCircleColor(Color.WHITE);
        dataSet.setDrawValues(false);
        LineData lineData = new LineData(xLable, dataSet);
        lineChartCO2.setData(lineData);
        lineChartCO2.invalidate();
    }
}
