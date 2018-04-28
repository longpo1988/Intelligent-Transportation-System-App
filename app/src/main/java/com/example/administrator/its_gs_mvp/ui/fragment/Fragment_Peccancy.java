package com.example.administrator.its_gs_mvp.ui.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.event.TitleEvent;
import com.example.administrator.its_gs_mvp.mvp.CarPeccancyContract;
import com.example.administrator.its_gs_mvp.mvp.presenter.CarPeccancyPresenterImpl;
import com.example.administrator.its_gs_mvp.mvp.view.BaseFragmentImpl;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 车辆违章
 *
 * @Created by xww on 2018/4/20 0020.
 */

public class Fragment_Peccancy extends BaseFragmentImpl<CarPeccancyContract.View, CarPeccancyPresenterImpl>
        implements CarPeccancyContract.View {

    @BindView(R.id.edt_carNumber)
    EditText edtCarNumber;
    @BindView(R.id.btn_FindPeccancy)
    Button btnFindPeccancy;

    @Override
    protected CarPeccancyPresenterImpl initPresenter() {
        return new CarPeccancyPresenterImpl();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_carpeccancy;
    }

    @Override
    protected void initView() {
    }

    @OnClick(R.id.btn_FindPeccancy)
    public void onFindCarPeccancy() {
        String edtText = edtCarNumber.getText().toString().trim();
        String CarNumber = "鲁" + edtText;
        if (TextUtils.isEmpty(edtText)) {
            Toast.makeText(getContext(), "请输入车牌号", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (mPresenter.hasPeccancy(CarNumber)) {
                /**
                 * 切换到违章详情界面
                 */
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.mainContent, new Fragment_PeccancyList());
                transaction.commit();
                EventBus.getDefault().post(new TitleEvent("违章详情"));
                /**
                 * 从数据库查询 此车牌号 所有违章信息
                 */
                Fragment_PeccancyList.carNumber = CarNumber;
            } else {
                Toast.makeText(getContext(), "此车辆暂无违章信息", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
