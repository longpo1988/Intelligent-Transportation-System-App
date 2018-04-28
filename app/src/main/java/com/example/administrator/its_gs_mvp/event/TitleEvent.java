package com.example.administrator.its_gs_mvp.event;

/**
 * Created by xww on 2018/4/27 0027.
 */

public class TitleEvent {
    private String barTitle;

    public TitleEvent(String barTitle) {
        this.barTitle = barTitle;
    }

    public String getBarTitle() {
        return barTitle;
    }
}
