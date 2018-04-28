package com.example.administrator.its_gs_mvp.ui.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.adapter.PeccancyCardAdapter;
import com.example.administrator.its_gs_mvp.adapter.PeccancyCardDetailAdapter;
import com.example.administrator.its_gs_mvp.adapter.base.BaseAdapter;
import com.example.administrator.its_gs_mvp.adapter.base.BaseAdapterItemListener;
import com.example.administrator.its_gs_mvp.db.PeccancyCard;
import com.example.administrator.its_gs_mvp.event.TitleEvent;
import com.example.administrator.its_gs_mvp.mvp.PeccancyListContract;
import com.example.administrator.its_gs_mvp.mvp.presenter.PeccancyListPresenterImpl;
import com.example.administrator.its_gs_mvp.mvp.view.BaseFragmentImpl;

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
        mPresenter.addCarNumber(carNumber, true);
        rvPeccancyCard.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPeccancyCardDetail.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @OnClick(R.id.addCard)
    public void addCarPeccancyCard() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainContent, new Fragment_Peccancy());
        transaction.commit();
    }

    @Override
    public void setCardAdapter(final List<Map<String, Object>> cardInfo) {
        final PeccancyCardAdapter adapter = new PeccancyCardAdapter(getContext(), cardInfo);
        rvPeccancyCard.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String carnumber = cardInfo.get(position).get("carNumber").toString();
                carNumber = carnumber;
                mPresenter.addCarNumber(carNumber, false);
            }
        });
        adapter.setOnClickListener(new BaseAdapterItemListener.onClickListener() {
            @Override
            public void onClick(int position) {
                String carNumber = cardInfo.get(position).get("carNumber").toString();
                cardInfo.remove(position);
                adapter.notifyItemRemoved(position);
                DataSupport.deleteAll(PeccancyCard.class, "carNumber=?", carNumber);
            }
        });
    }

    @Override
    public void setCardDetailAdapter(List<Map<String, Object>> cardDetailInfo) {
        final PeccancyCardDetailAdapter adapter = new PeccancyCardDetailAdapter(getContext(), cardDetailInfo);
        rvPeccancyCardDetail.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                EventBus.getDefault().post(new TitleEvent("监控抓拍"));
                /**
                 * 添加到返回栈中
                 */
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.hide(Fragment_PeccancyList.this);
                transaction.add(R.id.mainContent, new Fragment_PeccancyPhoto(), "photo");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
