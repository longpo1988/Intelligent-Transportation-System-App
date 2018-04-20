package com.example.administrator.its_gs_mvp.bean;

import java.util.List;

/**
 * @Created by xww on 2018/4/20 0020.
 */

public class RoadStatusBean {

    /**
     * data : [{"level":2,"RoadId":6}]
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
         * level : 2 拥挤程度
         * RoadId : 6
         */

        private int level;
        private int RoadId;

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getRoadId() {
            return RoadId;
        }

        public void setRoadId(int RoadId) {
            this.RoadId = RoadId;
        }
    }
}
