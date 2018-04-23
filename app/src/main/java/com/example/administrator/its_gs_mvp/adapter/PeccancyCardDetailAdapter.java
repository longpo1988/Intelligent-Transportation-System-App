package com.example.administrator.its_gs_mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.administrator.its_gs_mvp.R;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Created by xww on 2018/4/21 0021.
 */

public class PeccancyCardDetailAdapter extends RecyclerView.Adapter<PeccancyCardDetailAdapter.ViewHolder> {

    private List<Map<String, Object>> cardDetailList;
    private BaseAdapterItemListener.onClickListener onClickListener;

    public void setOnClickListener(BaseAdapterItemListener.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public PeccancyCardDetailAdapter(List<Map<String, Object>> cardDetailList) {
        this.cardDetailList = cardDetailList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_peccancylist_card_detail,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvDetailTime.setText("" + cardDetailList.get(position).get("pTime").toString());
        holder.tvDetailMarks.setText("" + cardDetailList.get(position).get("pRemarks").toString());
        holder.tvDetailRoad.setText("" + cardDetailList.get(position).get("pAddr").toString());
        holder.tvScore.setText("扣分：" + cardDetailList.get(position).get("pScore").toString() + "分");
        holder.tvMoney.setText("罚款：" + cardDetailList.get(position).get("pMoney").toString() + "元");
        holder.btnLookPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardDetailList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_detailTime)
        TextView tvDetailTime;
        @BindView(R.id.tv_detailRoad)
        TextView tvDetailRoad;
        @BindView(R.id.tv_detailMarks)
        TextView tvDetailMarks;
        @BindView(R.id.tv_score)
        TextView tvScore;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.btn_LookPhoto)
        LinearLayout btnLookPhoto;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
