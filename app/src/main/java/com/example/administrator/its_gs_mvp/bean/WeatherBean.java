package com.example.administrator.its_gs_mvp.bean;

import java.util.List;

/**
 * @Created by xww on 2018/4/12 0012.
 */

public class WeatherBean {

    /**
     * data : [{"todayTemp":-6},{"detail":[{"tempRange":"-32~-23","date":"2018-1-1"},{"tempRange":"-7~7","date":"2018-1-2"},{"tempRange":"-12~3","date":"2018-1-3"},{"tempRange":"-28~-16","date":"2018-1-4"},{"tempRange":"2~16","date":"2018-1-5"},{"tempRange":"-2~10","date":"2018-1-6"}]}]
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
         * todayTemp : -6
         * detail : [{"tempRange":"-32~-23","date":"2018-1-1"},{"tempRange":"-7~7","date":"2018-1-2"},{"tempRange":"-12~3","date":"2018-1-3"},{"tempRange":"-28~-16","date":"2018-1-4"},{"tempRange":"2~16","date":"2018-1-5"},{"tempRange":"-2~10","date":"2018-1-6"}]
         */

        private int todayTemp;
        private List<DetailBean> detail;

        public int getTodayTemp() {
            return todayTemp;
        }

        public void setTodayTemp(int todayTemp) {
            this.todayTemp = todayTemp;
        }

        public List<DetailBean> getDetail() {
            return detail;
        }

        public void setDetail(List<DetailBean> detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * tempRange : -32~-23
             * date : 2018-1-1
             */

            private String tempRange;
            private String date;

            public String getTempRange() {
                return tempRange;
            }

            public void setTempRange(String tempRange) {
                this.tempRange = tempRange;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
