package com.example.administrator.its_gs_mvp.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.adapter.BaseAdapterItemListener;
import com.example.administrator.its_gs_mvp.adapter.PeccancyCardAdapter;
import com.example.administrator.its_gs_mvp.adapter.PeccancyCardDetailAdapter;
import com.example.administrator.its_gs_mvp.db.PeccancyCard;
import com.example.administrator.its_gs_mvp.event.FragmentEvent;
import com.example.administrator.its_gs_mvp.mvp.PeccancyListContract;
import com.example.administrator.its_gs_mvp.mvp.presenter.PeccancyListPresenterImpl;
import com.example.administrator.its_gs_mvp.mvp.view.BaseFragmentImpl;
import com.google.zxing.qrcode.encoder.QRCode;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 违章详情
 *
 * @Created by xww on 2018/4/21 0021.
 */

public class Fragment_PeccancyList extends BaseFragmentImpl<PeccancyListContract.View, PeccancyListPresenterImpl>
        implements PeccancyListContract.View {

    @BindView(R.id.addCard)
    ImageView addCard;
    @BindView(R.id.rv_peccancyCard)
    RecyclerView rvPeccancyCard;
    @BindView(R.id.rv_peccancyCardDetail)
    RecyclerView rvPeccancyCardDetail;

    public static String carNumber;

    @Override
    protected PeccancyListPresenterImpl initPresenter() {
        return new PeccancyListPresenterImpl();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_peccancylist;
    }

    @Override
    protected void initView() {
        mPresenter.addCarNumber(carNumber);
        rvPeccancyCard.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPeccancyCardDetail.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @OnClick(R.id.addCard)
    public void addCarPeccancyCard() {
        /**
         * 切换到查询车辆违章界面
         */
        EventBus.getDefault().post(new FragmentEvent(new Fragment_CarPeccancy(), "车辆违章"));
    }

    @Override
    public void setCardAdapter(final List<Map<String, Object>> cardInfo) {
        final PeccancyCardAdapter peccancyCardAdapter = new PeccancyCardAdapter(cardInfo);
        rvPeccancyCard.setAdapter(peccancyCardAdapter);
        peccancyCardAdapter.setOnClickListener(new BaseAdapterItemListener.onClickListener() {
            @Override
            public void onClick(int position) {
                String carnumber = cardInfo.get(position).get("carNumber").toString();
                mPresenter.addCarNumber(carnumber);
                mPresenter.addCardDetailData();
            }
        });
        peccancyCardAdapter.setOnDeleteClickListener(new BaseAdapterItemListener.onClickListener() {
            @Override
            public void onClick(int position) {
                String carNumber = cardInfo.get(position).get("carNumber").toString();
                cardInfo.remove(position);
                peccancyCardAdapter.notifyItemRemoved(position);
                DataSupport.deleteAll(PeccancyCard.class, "carNumber=?", carNumber);
            }
        });
    }

    @Override
    public void setCardDetailAdapter(List<Map<String, Object>> cardDetailInfo) {
        PeccancyCardDetailAdapter peccancyCardDetailAdapter = new PeccancyCardDetailAdapter(cardDetailInfo);
        rvPeccancyCardDetail.setAdapter(peccancyCardDetailAdapter);
        peccancyCardDetailAdapter.setOnClickListener(new BaseAdapterItemListener.onClickListener() {
            @Override
            public void onClick(int position) {
                EventBus.getDefault().post(new FragmentEvent(new Fragment_PeccancyPhoto(), "监控抓拍"));
            }
        });
    }
}
