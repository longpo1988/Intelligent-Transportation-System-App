package com.example.administrator.its_gs_mvp.bean;

import java.util.List;

/**
 * Created by xww on 2018/4/16 0016.
 */

public class SenseBean {
    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int humidity;
        private int lightIntensity;
        private int co2;
        private int pm25;
        private int temperature;

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public int getLightIntensity() {
            return lightIntensity;
        }

        public void setLightIntensity(int lightIntensity) {
            this.lightIntensity = lightIntensity;
        }

        public int getCo2() {
            return co2;
        }

        public void setCo2(int co2) {
            this.co2 = co2;
        }

        public int getPm25() {
            return pm25;
        }

        public void setPm25(int pm25) {
            this.pm25 = pm25;
        }

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }
    }


}
