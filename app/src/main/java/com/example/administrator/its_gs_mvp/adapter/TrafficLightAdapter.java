package com.example.administrator.its_gs_mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.bean.TrafficLightBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xww on 2018/4/18 0018.
 */

public class TrafficLightAdapter extends RecyclerView.Adapter<TrafficLightAdapter.ViewHolder> {

    private List<TrafficLightBean> beanList;
    private BaseAdapterItemListener.onClickListener onClickListener;
    private BaseAdapterItemListener.onCheckedListener onCheckedListener;

    public void setOnClickListener(BaseAdapterItemListener.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnCheckedListener(BaseAdapterItemListener.onCheckedListener onCheckedListener) {
        this.onCheckedListener = onCheckedListener;
    }

    public TrafficLightAdapter(List<TrafficLightBean> beanList) {
        this.beanList = beanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_trafficlight_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tlRoadNum.setText("" + beanList.get(position).getData().get(0).getTrafficID());
        holder.tlRedTime.setText("" + beanList.get(position).getData().get(0).getRedTime());
        holder.tlGreenTime.setText("" + beanList.get(position).getData().get(0).getGreenTime());
        holder.tlYellowTime.setText("" + beanList.get(position).getData().get(0).getYellowTime());
        holder.tlSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                onCheckedListener.onChecked(b,position);
            }
        });
        holder.tlSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tl_roadNum)
        TextView tlRoadNum;
        @BindView(R.id.tl_redTime)
        TextView tlRedTime;
        @BindView(R.id.tl_greenTime)
        TextView tlGreenTime;
        @BindView(R.id.tl_yellowTime)
        TextView tlYellowTime;
        @BindView(R.id.tl_Selected)
        CheckBox tlSelected;
        @BindView(R.id.tl_Setting)
        Button tlSetting;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
