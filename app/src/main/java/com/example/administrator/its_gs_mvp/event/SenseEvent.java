package com.example.administrator.its_gs_mvp.event;

/**
 * 生活助手：折线图数据 EventBus事件
 *
 * @Created by xww on 2018/4/12 0012.
 */

public class SenseEvent {
    private int Temp;
    private int Humd;
    private int CO2;
    private int PM25;
    private int Light;

    public SenseEvent(int temp, int humd, int CO2, int PM25, int light) {
        Temp = temp;
        Humd = humd;
        this.CO2 = CO2;
        this.PM25 = PM25;
        Light = light;
    }

    public void setTemp(int temp) {
        Temp = temp;
    }

    public void setHumd(int humd) {
        Humd = humd;
    }

    public void setCO2(int CO2) {
        this.CO2 = CO2;
    }

    public void setPM25(int PM25) {
        this.PM25 = PM25;
    }

    public void setLight(int light) {
        Light = light;
    }

    public int getTemp() {
        return Temp;
    }

    public int getHumd() {
        return Humd;
    }

    public int getCO2() {
        return CO2;
    }

    public int getPM25() {
        return PM25;
    }

    public int getLight() {
        return Light;
    }
}
