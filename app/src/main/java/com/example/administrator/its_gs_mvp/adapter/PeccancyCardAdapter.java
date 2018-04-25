package com.example.administrator.its_gs_mvp.adapter;

import android.content.Context;
import android.view.View;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.adapter.base.BaseAdapter;
import com.example.administrator.its_gs_mvp.adapter.base.BaseAdapterItemListener;
import com.example.administrator.its_gs_mvp.adapter.base.BaseViewHolder;

import java.util.List;
import java.util.Map;

/**
 * @Created by xww on 2018/4/24 0024.
 */

public class PeccancyCardAdapter extends BaseAdapter<Map<String, Object>> {
    private BaseAdapterItemListener.onClickListener onClickListener;
    private BaseAdapterItemListener.onClickListener onItemClickLitener;

    public void setOnClickListener(BaseAdapterItemListener.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setonItemClickLitener(BaseAdapterItemListener.onClickListener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }

    public PeccancyCardAdapter(Context Context, List<Map<String, Object>> Data) {
        super(Context, Data);
    }

    @Override
    protected int initLayout() {
        return R.layout.recy_peccancylist_card;
    }

    @Override
    protected void onBindData(Context context, BaseViewHolder holder, final int position, Map<String, Object> data) {
        holder.setText(R.id.tv_CarNumber, data.get("carNumber").toString());
        holder.setText(R.id.tv_pCount, "已有违章记录共：" + data.get("pCount").toString() + "次");
        holder.setText(R.id.tv_scoreSum, "共扣：" + data.get("pScoreSum").toString() + "分");
        holder.setText(R.id.tv_moneySum, "共罚款：" + data.get("pMoneySum").toString() + "元");
        holder.getView(R.id.btn_cardDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(position);
            }
        });
    }
}
