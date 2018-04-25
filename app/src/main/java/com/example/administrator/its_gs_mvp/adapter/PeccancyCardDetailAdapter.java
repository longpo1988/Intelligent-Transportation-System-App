package com.example.administrator.its_gs_mvp.adapter;

import android.content.Context;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.adapter.base.BaseAdapter;
import com.example.administrator.its_gs_mvp.adapter.base.BaseViewHolder;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class PeccancyCardDetailAdapter extends BaseAdapter<Map<String, Object>> {

    public PeccancyCardDetailAdapter(Context Context, List<Map<String, Object>> Data) {
        super(Context, Data);
    }

    @Override
    protected int initLayout() {
        return R.layout.recy_peccancylist_card_detail;
    }

    @Override
    protected void onBindData(Context context, BaseViewHolder holder, int position, Map<String, Object> stringObjectMap) {
        holder.setText(R.id.tv_detailTime, "" + stringObjectMap.get("pTime").toString());
        holder.setText(R.id.tv_detailMarks, "" + stringObjectMap.get("pRemarks").toString());
        holder.setText(R.id.tv_detailRoad, "" + stringObjectMap.get("pAddr").toString());
        holder.setText(R.id.tv_score, "扣分：" + stringObjectMap.get("pScore").toString() + "分");
        holder.setText(R.id.tv_money, "罚款：" + stringObjectMap.get("pMoney").toString() + "元");
    }
}
