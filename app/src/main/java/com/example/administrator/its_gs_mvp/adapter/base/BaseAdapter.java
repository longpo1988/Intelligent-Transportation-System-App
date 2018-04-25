package com.example.administrator.its_gs_mvp.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 通用RecyclerView适配器
 *
 * @Created by xww on 2018/4/24 0024.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private List<T> mData;
    protected onItemClickListener onItemClickListener;
    protected onItemLongClickListener onItemLongClickListener;

    public BaseAdapter(Context Context, List<T> Data) {
        mContext = Context;
        mData = Data;
    }

    public void updateData(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        /**
         * 重新绑定数据，position位置刷新（只有调用含有 changed 方法才能刷新）
         */
        notifyItemRangeChanged(position, mData.size() - position);
    }

    public List<T> getData() {
        return mData;
    }

    /**
     * 绑定数据
     *
     * @param context
     * @param holder
     * @param position
     * @param t
     */
    protected abstract void onBindData(Context context, BaseViewHolder holder, int position, T t);

    /**
     * 绑定布局
     *
     * @return
     */
    protected abstract int initLayout();

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(initLayout(), parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        onBindData(mContext, holder, position, mData.get(position));
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, position);
                }
            });
        }
        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClickListener.onItemLongClick(view, position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface onItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setOnItemLongClickListener(onItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }
}
