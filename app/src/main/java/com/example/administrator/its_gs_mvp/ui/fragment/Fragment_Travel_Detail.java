package com.example.administrator.its_gs_mvp.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.event.TitleEvent;
import com.example.administrator.its_gs_mvp.event.TravelData;
import com.example.administrator.its_gs_mvp.mvp.view.BaseFragment;
import com.example.administrator.its_gs_mvp.ui.custom.ZoomImageView;
import com.example.administrator.its_gs_mvp.util.QRCodeUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 旅游信息-详细信息
 *
 * @Created by xww on 2018/4/27 0027.
 */

public class Fragment_Travel_Detail extends BaseFragment {
    @BindView(R.id.img_travel_detail)
    ZoomImageView imgTravelDetail;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_introduce)
    TextView tvIntroduce;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.btn_buy)
    Button btnBuy;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.img_qrcode)
    ZoomImageView imgQrcode;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_travel_detail;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        imgQrcode.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void setViewData(TravelData event) {
        tvName.setText(event.getName());
        tvIntroduce.setText(event.getIntroduce());
        tvPhone.setText(event.getPhone());
        phoneNumber = event.getPhone();
        tvPrice.setText("￥" + event.getPrice() + "元");
        price = "￥" + event.getPrice() + "元";
        Glide.with(getContext()).load(event.getUrl()).into(imgTravelDetail);
    }

    private String price;
    private String phoneNumber;

    @OnClick({R.id.btn_buy, R.id.tv_phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_buy:
                if (imgQrcode.getVisibility() == View.GONE) {
                    QRCodeUtil QRUtil = new QRCodeUtil();
                    Bitmap bitmap = QRUtil.createQRCode(price, 300, 300);
                    imgQrcode.setVisibility(View.VISIBLE);
                    imgQrcode.setImageBitmap(bitmap);
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
                    alphaAnimation.setDuration(2000);
                    alphaAnimation.setFillAfter(true);
                    imgQrcode.startAnimation(alphaAnimation);
                }
                Toast.makeText(getContext(), "请扫描二维码进行支付", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_phone:
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(phoneIntent);
                break;
        }
    }

    public static boolean onKeyDown(int keycode, KeyEvent keyEvent) {
        EventBus.getDefault().post(new TitleEvent("旅行助手"));
        return true;
    }
}
