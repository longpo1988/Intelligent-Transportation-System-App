package com.example.administrator.its_gs_mvp.event;

import android.support.v4.app.Fragment;

/**
 * 车联违章：替换子碎片 EventBus事件
 *
 * @Created by xww on 2018/4/21 0021.
 */

public class FragmentEvent {
    private Fragment fragment;
    private String title;

    public FragmentEvent(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
