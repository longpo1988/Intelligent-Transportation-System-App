package com.example.administrator.its_gs_mvp.bean;

import java.util.List;

/**
 * 违章代码 实体类
 *
 * @Created by xww on 2018/4/21 0021.
 */

public class PeccancyCodeBean {

    /**
     * data : [{"pScore":12,"pCode":"1001A","pMoney":200,"pRemarks":"驾驶机动车在环城高速路中超速行驶"},{"pScore":14,"pCode":"1002A","pMoney":300,"pRemarks":"驾驶机动车在环城快速路中不按规定车道行驶"},{"pScore":6,"pCode":"1003A","pMoney":150,"pRemarks":"驾驶机动车在幸福路红绿灯路口闯红灯行为被拍摄"},{"pScore":4,"pCode":"1004A","pMoney":100,"pRemarks":"驾驶机动车在医院路中超载过ETC"},{"pScore":16,"pCode":"1005A","pMoney":500,"pRemarks":"驾驶机动车在联想路中因为醉酒驾驶被交警拦截"},{"pScore":2,"pCode":"1006A","pMoney":100,"pRemarks":"驾驶机动车在停车场周围随意停车导致交通堵塞"}]
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
         * pScore : 12
         * pCode : 1001A
         * pMoney : 200
         * pRemarks : 驾驶机动车在环城高速路中超速行驶
         */

        private int pScore;
        private String pCode;
        private int pMoney;
        private String pRemarks;

        public int getPScore() {
            return pScore;
        }

        public void setPScore(int pScore) {
            this.pScore = pScore;
        }

        public String getPCode() {
            return pCode;
        }

        public void setPCode(String pCode) {
            this.pCode = pCode;
        }

        public int getPMoney() {
            return pMoney;
        }

        public void setPMoney(int pMoney) {
            this.pMoney = pMoney;
        }

        public String getPRemarks() {
            return pRemarks;
        }

        public void setPRemarks(String pRemarks) {
            this.pRemarks = pRemarks;
        }
    }
}
