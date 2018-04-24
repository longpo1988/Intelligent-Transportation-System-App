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
import com.github.mikephil.charting.charts.BarChart;
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
 * 生活助手 - 温度
 *
 * @Created by xww on 2018/4/13 0013.
 */

public class Fragment_Life_Temperature extends BaseFragment {

    @BindView(R.id.lineChart_Temperature)
    LineChart lineChartTemperature;

    private List<Integer> tempList;
    private List<String> timeList;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_life_temperature;
    }

    @Override
    protected void initView() {
        tempList = new ArrayList<>();
        timeList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            timeList.add("");
            tempList.add(0);
        }
        lineChartTemperature.setDrawGridBackground(false);
        lineChartTemperature.setDescription("");
        lineChartTemperature.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChartTemperature.getXAxis().setDrawAxisLine(false);
        lineChartTemperature.getXAxis().setDrawGridLines(false);
        lineChartTemperature.getXAxis().setTextSize(18);
        lineChartTemperature.getAxisLeft().setDrawAxisLine(false);
        lineChartTemperature.getAxisLeft().setGridColor(Color.parseColor("#DEDEDE"));
        lineChartTemperature.getAxisLeft().setTextSize(18);
        lineChartTemperature.getAxisRight().setEnabled(false);
        lineChartTemperature.getLegend().setEnabled(false);
        lineChartTemperature.getAxisLeft().setValueFormatter(new LargeValueFormatter());
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
        tempList.add(0, event.getTemp());
        tempList.remove(20);
        if (time >= 57) {
            time = 0;
        }
        for (int i = 0; i < tempList.size(); i++) {
            time += 3;
            xLable.add("" + time);
            yLable.add(new BarEntry(tempList.get(i), i));
        }
        LineDataSet dataSet = new LineDataSet(yLable, "");
        dataSet.setColor(Color.parseColor("#B0B0B0"));
        dataSet.setCircleColorHole(Color.parseColor("#B0B0B0"));
        dataSet.setCircleColor(Color.parseColor("#B0B0B0"));
        dataSet.setValueFormatter(new LargeValueFormatter());
        LineData lineData = new LineData(xLable, dataSet);
        lineChartTemperature.setData(lineData);
        lineChartTemperature.invalidate();
    }
}
