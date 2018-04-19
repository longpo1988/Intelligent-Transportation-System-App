package com.example.administrator.its_gs_mvp.bean;

import java.util.List;

/**
 * @Created by xww on 2018/4/18 0018.
 */

public class TrafficLightBean {

    /**
     * data : [{"greenTime":5,"redTime":5,"yellowTime":5,"trafficID":1}]
     * msg : 成功
     * code : 1
     */

    private String msg;
    private int code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * greenTime : 5
         * redTime : 5
         * yellowTime : 5
         * trafficID : 1
         */

        private int greenTime;
        private int redTime;
        private int yellowTime;
        private int trafficID;

        public int getGreenTime() {
            return greenTime;
        }

        public void setGreenTime(int greenTime) {
            this.greenTime = greenTime;
        }

        public int getRedTime() {
            return redTime;
        }

        public void setRedTime(int redTime) {
            this.redTime = redTime;
        }

        public int getYellowTime() {
            return yellowTime;
        }

        public void setYellowTime(int yellowTime) {
            this.yellowTime = yellowTime;
        }

        public int getTrafficID() {
            return trafficID;
        }

        public void setTrafficID(int trafficID) {
            this.trafficID = trafficID;
        }
    }
}
