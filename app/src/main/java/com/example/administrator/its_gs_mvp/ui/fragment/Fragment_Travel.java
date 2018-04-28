package com.example.administrator.its_gs_mvp.ui.fragment;

import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.adapter.TravelAdapter;
import com.example.administrator.its_gs_mvp.bean.TravelBean;
import com.example.administrator.its_gs_mvp.event.TitleEvent;
import com.example.administrator.its_gs_mvp.event.TravelData;
import com.example.administrator.its_gs_mvp.http.ServerURL;
import com.example.administrator.its_gs_mvp.mvp.TravelContract;
import com.example.administrator.its_gs_mvp.mvp.presenter.TravelPresenterImpl;
import com.example.administrator.its_gs_mvp.mvp.view.BaseFragmentImpl;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.github.library.view.LoadType;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

/**
 * 旅行助手
 *
 * @Created by xww on 2018/4/25 0025.
 */

public class Fragment_Travel extends BaseFragmentImpl<TravelContract.View, TravelPresenterImpl>
        implements TravelContract.View {

    @BindView(R.id.rv_travel)
    RecyclerView rvTravel;

    private int mTotle;

    @Override
    protected TravelPresenterImpl initPresenter() {
        return new TravelPresenterImpl();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_travel;
    }

    @Override
    protected void initView() {
        rvTravel.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }



    @Override
    public void setAdapter(final List<TravelBean.DataBean> data) {
        mTotle = data.size();
        final TravelAdapter adapter = new TravelAdapter(getContext(), data, R.layout.recy_travel_item);
        rvTravel.setAdapter(adapter);

        adapter.setLoadMoreType(LoadType.CUBES);
        adapter.openLoadAnimation();
        if (adapter.getData().size() >= mTotle) {
            adapter.addNoMoreView();
        }
        adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final String url = ServerURL.URL + data.get(position).getImg();
                final String name = data.get(position).getName();
                final String phone = data.get(position).getContact();
                final String evaluate = data.get(position).getEvaluate();
                final String introduce = data.get(position).getIntroduce();
                final String price = data.get(position).getPrice();

                /**
                 * 添加到返回栈中
                 */
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.hide(Fragment_Travel.this);
                transaction.add(R.id.mainContent, new Fragment_Travel_Detail(), "detail");
                transaction.addToBackStack(null);
                transaction.commit();

                EventBus.getDefault().post(new TitleEvent("景点介绍"));

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new TravelData(name, introduce, evaluate, price, phone, url));
                    }
                }, 1000);
            }
        });
    }
}
