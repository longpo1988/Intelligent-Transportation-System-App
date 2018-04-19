package com.example.administrator.its_gs_mvp.adapter;

/**
 * recylerview 点击事件
 *
 * @Created by xww on 2018/4/19 0019.
 */

public interface BaseAdapterItemListener {
    /**
     * item 长按点击
     */
    interface onLongClickListener {
        void onLongClick(int position);
    }

    /**
     * item button 点击
     */
    interface onClickListener {
        void onClick(int position);
    }

    /**
     * item checkbox 选择状态
     */
    interface onCheckedListener {
        void onChecked(boolean isSelected, int position);
    }
}
