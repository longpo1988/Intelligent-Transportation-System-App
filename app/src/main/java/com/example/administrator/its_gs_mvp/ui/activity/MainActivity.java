package com.example.administrator.its_gs_mvp.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.mvp.MainContract;
import com.example.administrator.its_gs_mvp.mvp.presenter.MainPresenterImpl;
import com.example.administrator.its_gs_mvp.mvp.view.BaseActivityImpl;
import com.example.administrator.its_gs_mvp.ui.fragment.Fragment_Account;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主界面
 *
 * @author xww
 */
public class MainActivity extends BaseActivityImpl<MainContract.View, MainPresenterImpl> implements
        MainContract.View {

    @BindView(R.id.tv_Title)
    TextView tvTitle;
    @BindView(R.id.tb_Main)
    Toolbar tbMain;
    @BindView(R.id.mainContent)
    FrameLayout mainContent;
    @BindView(R.id.lv_Main)
    ListView lvMain;
    @BindView(R.id.drawer_Main)
    DrawerLayout drawerMain;

    @Override
    protected MainPresenterImpl initPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tbMain.setTitle("");
        setSupportActionBar(tbMain);
        drawerMain.setScrimColor(Color.TRANSPARENT);
        drawerMain.addDrawerListener(mPresenter.initToggle(this, drawerMain, tbMain));
    }

    @Override
    public void setItems(final Object data) {
        lvMain.setAdapter(new SimpleAdapter(this, (List<? extends Map<String, ?>>) data, R.layout.list_item_main,
                new String[]{"icon", "name"}, new int[]{R.id.item_main_img, R.id.item_main_name}));

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String _name = ((List<? extends Map<String, ?>>) data).get(i).get("name").toString();
                tvTitle.setText(_name);
                if (_name.equals("账户管理")) {
                    rePlace(new Fragment_Account());
                } else if (_name.equals("公交查询")) {
                    rePlace(new Fragment_Account());
                }else if (_name.equals("红绿灯管理")) {
                    rePlace(new Fragment_Account());
                }else if (_name.equals("车辆违章")) {
                    rePlace(new Fragment_Account());
                }else if (_name.equals("路况查询")) {
                    rePlace(new Fragment_Account());
                }else if (_name.equals("生活助手")) {
                    rePlace(new Fragment_Account());
                }else if (_name.equals("数据分析")) {
                    rePlace(new Fragment_Account());
                }else if (_name.equals("个人中心")) {
                    rePlace(new Fragment_Account());
                }else if (_name.equals("创意")) {
                    rePlace(new Fragment_Account());
                }
            }
        });

    }

    /**
     * 切换碎片
     */
    private void rePlace(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContent, fragment).commit();
    }
}
