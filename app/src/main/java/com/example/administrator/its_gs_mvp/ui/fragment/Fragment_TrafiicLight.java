package com.example.administrator.its_gs_mvp.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.adapter.TrafficLightAdapter;
import com.example.administrator.its_gs_mvp.adapter.base.BaseAdapterItemListener;
import com.example.administrator.its_gs_mvp.bean.TrafficLightBean;
import com.example.administrator.its_gs_mvp.mvp.TrafiicLightContract;
import com.example.administrator.its_gs_mvp.mvp.presenter.TrafiicLightPresenterImpl;
import com.example.administrator.its_gs_mvp.mvp.view.BaseFragmentImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 红绿灯管理
 *
 * @Created by xww on 2018/4/18 0018.
 */

public class Fragment_TrafiicLight extends BaseFragmentImpl<TrafiicLightContract.View, TrafiicLightPresenterImpl>
        implements TrafiicLightContract.View {

    @BindView(R.id.sp_TrafficLight)
    Spinner spTrafficLight;
    @BindView(R.id.btn_Search)
    Button btnSearch;
    @BindView(R.id.btn_setTrafficLightAll)
    Button btnSetTrafficLightAll;
    @BindView(R.id.rv_TrafficLight)
    RecyclerView rvTrafficLight;

    @Override
    protected TrafiicLightPresenterImpl initPresenter() {
        return new TrafiicLightPresenterImpl();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_trafficlight;
    }

    @Override
    protected void initView() {
        trafficLightIdListMore = new ArrayList<>();
        rvTrafficLight.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setAdapter(final List<TrafficLightBean> trafficLightBeanList) {
        TrafficLightAdapter adapter = new TrafficLightAdapter(getContext(), trafficLightBeanList);
        rvTrafficLight.setAdapter(adapter);
        adapter.setOnCheckedListener(new BaseAdapterItemListener.onCheckedListener() {
            @Override
            public void onChecked(boolean isSelected, int position) {
                final int trafficLightID = trafficLightBeanList.get(position).getData().get(0).getTrafficID();
                if (isSelected) {
                    trafficLightIdListMore.add(trafficLightID);
                } else {
                    for (int i = 0; i < trafficLightIdListMore.size(); i++) {
                        if (trafficLightID == trafficLightIdListMore.get(i)) {
                            trafficLightIdListMore.remove(i);
                        }
                    }
                }
            }
        });
        adapter.setOnClickListener(new BaseAdapterItemListener.onClickListener() {
            @Override
            public void onClick(int position) {
                List<Integer> trafficLightIdList = new ArrayList<>();
                int trafficLightID = trafficLightBeanList.get(position).getData().get(0).getTrafficID();
                trafficLightIdList.add(trafficLightID);
                mPresenter.showTrafficLightConfigSettingDialog(trafficLightIdList);
            }
        });
    }

    @Override
    public void clearTrafficLightIDlist() {
        trafficLightIdListMore.clear();
    }

    @OnClick(R.id.btn_Search)
    public void onSerachTrafficLightInfo() {   /** 查询*/
        mPresenter.getSpinerItemSelected(spTrafficLight.getSelectedItemPosition());
    }

    private List<Integer> trafficLightIdListMore;

    @OnClick(R.id.btn_setTrafficLightAll)
    public void setTrafficLightConfigMore() {    /** 批量设置*/
        mPresenter.showTrafficLightConfigSettingDialog(trafficLightIdListMore);
    }
}
