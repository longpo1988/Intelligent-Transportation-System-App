package com.example.administrator.its_gs_mvp.bean;

import java.util.List;

/**
 * Created by xww on 2018/4/20 0020.
 */

public class CarPeccancyBean {

    /**
     * data : [{"carNumber":"鲁B10001","pCode":"1001A","pTime":"2015-7-17 14:35:40","pAddr":"学院路"},{"carNumber":"鲁B10002","pCode":"1003A","pTime":"2014-12-26 09:10:11","pAddr":"幸福路"},{"carNumber":"鲁B10002","pCode":"1005A","pTime":"2016-3-6 08:45:25","pAddr":"停车场"},{"carNumber":"鲁B10003","pCode":"1002A","pTime":"2017-12-27 16:43:23","pAddr":"医院路"},{"carNumber":"鲁B10004","pCode":"1004A","pTime":"2012-12-26 08:02:33","pAddr":"联想路"},{"carNumber":"鲁B10004","pCode":"1001A","pTime":"2014-4-17 21:07:55","pAddr":"环城快速路"},{"carNumber":"鲁B10004","pCode":"1006A","pTime":"2015-8-25 09:11:39","pAddr":"环城高速"},{"carNumber":"鲁B10005","pCode":"1004A","pTime":"2016-12-19 23:03:42","pAddr":"环城高速"},{"carNumber":"鲁B10005","pCode":"1002A","pTime":"2017-10-23 05:41:03","pAddr":"环城快速路"},{"carNumber":"鲁B10007","pCode":"1006A","pTime":"2013-11-11 07:33:47","pAddr":"环城高速"},{"carNumber":"鲁B10007","pCode":"1005A","pTime":"2015-07-03 04:32:56","pAddr":"环城快速路"},{"carNumber":"鲁B10007","pCode":"1004A","pTime":"2016-05-02 22:43:04","pAddr":"联想路"},{"carNumber":"鲁B10007","pCode":"1001A","pTime":"2018-01-13 21:26:50","pAddr":"幸福路"},{"carNumber":"鲁B10008","pCode":"1002A","pTime":"2007-08-24 05:23:29","pAddr":"幸福路"},{"carNumber":"鲁B10008","pCode":"1005A","pTime":"2009-07-21 18:02:24","pAddr":"联想路"},{"carNumber":"鲁B10008","pCode":"1006A","pTime":"2013-11-05 12:44:49","pAddr":"环城高速"},{"carNumber":"鲁B10008","pCode":"1003A","pTime":"2018-01-13 21:26:50","pAddr":"医院路"},{"carNumber":"鲁B10009","pCode":"1004A","pTime":"1998-02-09 08:04:22","pAddr":"联想路"},{"carNumber":"鲁B10009","pCode":"1001A","pTime":"2001-03-27 19:01:36","pAddr":"环城高速"},{"carNumber":"鲁B10009","pCode":"1005A","pTime":"2004-05-25 03:45:35","pAddr":"环城高速"},{"carNumber":"鲁B10009","pCode":"1004A","pTime":"2009-04-19 09:23:40","pAddr":"联想路"},{"carNumber":"鲁B10009","pCode":"1001A","pTime":"2011-06-15 23:35:50","pAddr":"学院路"},{"carNumber":"鲁B10009","pCode":"1006A","pTime":"2013-04-11 06:18:26","pAddr":"幸福路"},{"carNumber":"鲁B10009","pCode":"1002A","pTime":"2017-04-14 04:36:46","pAddr":"幸福路"},{"carNumber":"鲁B10010","pCode":"1006A","pTime":"2001-05-12 09:05:31","pAddr":"环城快速路"},{"carNumber":"鲁B10010","pCode":"1004A","pTime":"2008-06-25 22:55:52","pAddr":"环城快速路"},{"carNumber":"鲁B10010","pCode":"1002A","pTime":"2011-12-15 14:18:20","pAddr":"环城高速"},{"carNumber":"鲁B10010","pCode":"1006A","pTime":"20013-05-02 24:22:46","pAddr":"学院路"},{"carNumber":"鲁B10010","pCode":"1003A","pTime":"2014-11-25 01:25:33","pAddr":"医院路"}]
     * code : 1
     * msg : 成功
     */

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
        /**
         * carNumber : 鲁B10001
         * pCode : 1001A
         * pTime : 2015-7-17 14:35:40
         * pAddr : 学院路
         */

        private String carNumber;
        private String pCode;
        private String pTime;
        private String pAddr;

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public String getPCode() {
            return pCode;
        }

        public void setPCode(String pCode) {
            this.pCode = pCode;
        }

        public String getPTime() {
            return pTime;
        }

        public void setPTime(String pTime) {
            this.pTime = pTime;
        }

        public String getPAddr() {
            return pAddr;
        }

        public void setPAddr(String pAddr) {
            this.pAddr = pAddr;
        }
    }
}
