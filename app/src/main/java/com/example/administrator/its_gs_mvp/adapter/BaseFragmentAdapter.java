package com.example.administrator.its_gs_mvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @Created by xww on 2018/4/13 0013.
 */

public class BaseFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> viewList;

    public BaseFragmentAdapter(FragmentManager fm, List<Fragment> viewList) {
        super(fm);
        this.viewList = viewList;
    }

    @Override
    public Fragment getItem(int position) {
        return viewList.get(position);
    }

    @Override
    public int getCount() {
        return viewList.size();
    }
}
