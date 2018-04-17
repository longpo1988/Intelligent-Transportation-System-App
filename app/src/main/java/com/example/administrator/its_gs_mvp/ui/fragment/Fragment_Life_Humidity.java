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
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
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
 * 生活助手 - 相对湿度
 *
 * @Created by xww on 2018/4/13 0013.
 */

public class Fragment_Life_Humidity extends BaseFragment {

    @BindView(R.id.lineChart_Humidity)
    LineChart lineChartHumidity;

    private List<Integer> humdList;
    private List<String> timeList;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_life_humidity;
    }

    @Override
    protected void initView() {
        humdList = new ArrayList<>();
        timeList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            timeList.add("");
            humdList.add(0);
        }
        lineChartHumidity.setDrawGridBackground(false);
        lineChartHumidity.setDescription("");
        lineChartHumidity.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChartHumidity.getXAxis().setDrawAxisLine(false);
        lineChartHumidity.getXAxis().setDrawGridLines(false);
        lineChartHumidity.getXAxis().setTextSize(18);
        lineChartHumidity.getAxisLeft().setDrawAxisLine(false);
        lineChartHumidity.getAxisLeft().setGridColor(Color.parseColor("#DEDEDE"));
        lineChartHumidity.getAxisLeft().setTextSize(18);
        lineChartHumidity.getAxisRight().setEnabled(false);
        lineChartHumidity.getLegend().setEnabled(false);
        lineChartHumidity.getAxisLeft().setValueFormatter(new LargeValueFormatter());
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
        humdList.add(0, event.getHumd());
        humdList.remove(20);
        if (time >= 57) {
            time = 0;
        }
        for (int i = 0; i < humdList.size(); i++) {
            time += 3;
            xLable.add("" + time);
            yLable.add(new BarEntry(humdList.get(i), i));
        }
        LineDataSet dataSet = new LineDataSet(yLable, "");
        dataSet.setColor(Color.parseColor("#B0B0B0"));
        dataSet.setCircleColorHole(Color.parseColor("#B0B0B0"));
        dataSet.setCircleColor(Color.parseColor("#B0B0B0"));
        dataSet.setValueFormatter(new LargeValueFormatter());
        LineData lineData = new LineData(xLable, dataSet);
        lineChartHumidity.setData(lineData);
        lineChartHumidity.invalidate();
    }
}
