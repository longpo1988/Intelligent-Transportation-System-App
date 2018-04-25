package com.example.administrator.its_gs_mvp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.adapter.base.BaseAdapter;
import com.example.administrator.its_gs_mvp.adapter.base.BaseAdapterItemListener;
import com.example.administrator.its_gs_mvp.adapter.base.BaseViewHolder;
import com.example.administrator.its_gs_mvp.bean.TrafficLightBean;

import java.util.List;

/**
 * Created by xww on 2018/4/24 0024.
 */

public class TrafficLightAdapter extends BaseAdapter<TrafficLightBean> {
    private BaseAdapterItemListener.onClickListener onClickListener;
    private BaseAdapterItemListener.onCheckedListener onCheckedListener;

    public TrafficLightAdapter(Context Context, List<TrafficLightBean> Data) {
        super(Context, Data);
    }

    @Override
    protected int initLayout() {
        return R.layout.recy_trafficlight_item;
    }

    public void setOnClickListener(BaseAdapterItemListener.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnCheckedListener(BaseAdapterItemListener.onCheckedListener onCheckedListener) {
        this.onCheckedListener = onCheckedListener;
    }

    @Override
    protected void onBindData(Context context, BaseViewHolder holder, final int position, TrafficLightBean trafficLightBean) {
        holder.setText(R.id.tl_roadNum, "" + trafficLightBean.getData().get(0).getTrafficID());
        holder.setText(R.id.tl_redTime, "" + trafficLightBean.getData().get(0).getRedTime());
        holder.setText(R.id.tl_greenTime, "" + trafficLightBean.getData().get(0).getGreenTime());
        holder.setText(R.id.tl_yellowTime, "" + trafficLightBean.getData().get(0).getYellowTime());
        holder.getView(R.id.tl_Setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                }
            }
        });
        CheckBox checkBox = holder.getView(R.id.tl_Selected);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (onCheckedListener != null) {
                    onCheckedListener.onChecked(b, position);
                }
            }
        });
    }
}
