package com.example.administrator.its_gs_mvp.ui.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.event.TitleEvent;
import com.example.administrator.its_gs_mvp.mvp.MainContract;
import com.example.administrator.its_gs_mvp.mvp.presenter.MainPresenterImpl;
import com.example.administrator.its_gs_mvp.mvp.view.BaseActivityImpl;
import com.example.administrator.its_gs_mvp.ui.fragment.Fragment_Account;
import com.example.administrator.its_gs_mvp.ui.fragment.Fragment_Peccancy;
import com.example.administrator.its_gs_mvp.ui.fragment.Fragment_Life;
import com.example.administrator.its_gs_mvp.ui.fragment.Fragment_PeccancyPhoto;
import com.example.administrator.its_gs_mvp.ui.fragment.Fragment_PeccancyPhotoDetail;
import com.example.administrator.its_gs_mvp.ui.fragment.Fragment_RoadState;
import com.example.administrator.its_gs_mvp.ui.fragment.Fragment_TrafiicLight;
import com.example.administrator.its_gs_mvp.ui.fragment.Fragment_Travel;
import com.example.administrator.its_gs_mvp.ui.fragment.Fragment_Travel_Detail;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

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
        /**
         * 设置全屏显示，隐藏状态栏
         */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return new MainPresenterImpl();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tbMain.setTitle("");
        setSupportActionBar(tbMain);
        drawerMain.setScrimColor(Color.TRANSPARENT);
        drawerMain.addDrawerListener(mPresenter.initToggle(this, drawerMain, tbMain));
        EventBus.getDefault().register(this);
    }

    @Override
    public void setItems(final Object data) {
        lvMain.setAdapter(new SimpleAdapter(this, (List<? extends Map<String, ?>>) data, R.layout.list_main_item,
                new String[]{"icon", "name"}, new int[]{R.id.item_main_img, R.id.item_main_name}));

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String _name = ((List<? extends Map<String, ?>>) data).get(i).get("name").toString();
                tvTitle.setText(_name);
                if (drawerMain.isDrawerOpen(GravityCompat.START)) {
                    drawerMain.closeDrawer(GravityCompat.START);
                }
                if (_name.equals("账户管理")) {
                    rePlace(new Fragment_Account());
                } else if (_name.equals("公交查询")) {
                    rePlace(new Fragment_Account());
                } else if (_name.equals("红绿灯管理")) { //完成
                    rePlace(new Fragment_TrafiicLight());
                } else if (_name.equals("车辆违章")) { //完成
                    rePlace(new Fragment_Peccancy());
                } else if (_name.equals("路况查询")) { //完成
                    rePlace(new Fragment_RoadState());
                } else if (_name.equals("生活助手")) { //完成
                    rePlace(new Fragment_Life());
                } else if (_name.equals("数据分析")) {
                    rePlace(new Fragment_Account());
                } else if (_name.equals("个人中心")) {

                } else if (_name.equals("地铁查询")) {

                } else if (_name.equals("高速路况")) {

                } else if (_name.equals("旅行助手")) {
                    rePlace(new Fragment_Travel());
                } else if (_name.equals("创意")) {

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 更改标题
     */
    @Subscribe
    public void rePlaceChild(TitleEvent event) {
        tvTitle.setText(event.getBarTitle());
    }

    /**
     * 切换碎片
     */
    private void rePlace(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContent, fragment).commit();
    }

    private Fragment getFragment() {
        FragmentManager manager = getSupportFragmentManager();
        List<Fragment> fragmentList = manager.getFragments();
        for (Fragment fragment : fragmentList) {
            if (fragment.isVisible()) {
                return fragment;
            }
        }
        return null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (getFragment() instanceof Fragment_Travel_Detail) {
            Fragment_Travel_Detail.onKeyDown(keyCode, event);
        } else if (getFragment() instanceof Fragment_PeccancyPhoto) {
            Fragment_PeccancyPhoto.onKeyDown(keyCode, event);
        } else if (getFragment() instanceof Fragment_PeccancyPhotoDetail) {
            Fragment_PeccancyPhotoDetail.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
