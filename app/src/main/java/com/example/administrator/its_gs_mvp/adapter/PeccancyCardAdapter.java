package com.example.administrator.its_gs_mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.db.PeccancyCard;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xww on 2018/4/21 0021.
 */

public class PeccancyCardAdapter extends RecyclerView.Adapter<PeccancyCardAdapter.ViewHolder> {

    private List<Map<String, Object>> cardList;
    private BaseAdapterItemListener.onClickListener onClickListener;
    private BaseAdapterItemListener.onClickListener onDeleteClickListener;

    public void setOnClickListener(BaseAdapterItemListener.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnDeleteClickListener(BaseAdapterItemListener.onClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public PeccancyCardAdapter(List<Map<String, Object>> cardList) {
        this.cardList = cardList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_peccancylist_card,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvCarNumber.setText(cardList.get(position).get("carNumber").toString());
        holder.tvPCount.setText("已有违章记录共：" + cardList.get(position).get("pCount").toString() + "次");
        holder.tvScoreSum.setText("共扣：" + cardList.get(position).get("pScoreSum").toString() + "分");
        holder.tvMoneySum.setText("共罚款：" + cardList.get(position).get("pMoneySum").toString() + "元");
        holder.btnCardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteClickListener.onClick(position);
            }
        });
        holder.btnItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_CarNumber)
        TextView tvCarNumber;
        @BindView(R.id.tv_pCount)
        TextView tvPCount;
        @BindView(R.id.tv_scoreSum)
        TextView tvScoreSum;
        @BindView(R.id.tv_moneySum)
        TextView tvMoneySum;
        @BindView(R.id.btn_cardDelete)
        ImageView btnCardDelete;
        @BindView(R.id.btn_ItemView)
        LinearLayout btnItemView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
