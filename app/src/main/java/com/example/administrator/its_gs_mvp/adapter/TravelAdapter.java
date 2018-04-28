package com.example.administrator.its_gs_mvp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.bean.TravelBean;
import com.example.administrator.its_gs_mvp.http.ServerURL;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;

import java.util.List;

/**
 * Created by xww on 2018/4/27 0027.
 */

public class TravelAdapter extends BaseRecyclerAdapter<TravelBean.DataBean> {
    private Context context;

    public TravelAdapter(Context context, List<TravelBean.DataBean> data, int layoutResId) {
        super(context, data, layoutResId);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TravelBean.DataBean item) {
        helper.setText(R.id.tv_travel_name, item.getName());
        helper.setText(R.id.tv_travel_price, "票价：￥" + item.getPrice() + "元");
        ImageView imageView = helper.getView(R.id.img_travel_pic);
        Glide.with(context).load(ServerURL.URL + item.getImg()).into(imageView);
    }
}
