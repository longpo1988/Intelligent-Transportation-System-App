package com.example.administrator.its_gs_mvp.event;

/**
 * Created by xww on 2018/4/27 0027.
 */

public class TravelData {
    public String name;
    public String introduce;
    public String evaluate;
    public String price;
    public String phone;
    public String url;

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public String getPrice() {
        return price;
    }

    public String getPhone() {
        return phone;
    }

    public String getUrl() {
        return url;
    }

    public TravelData(String name, String introduce, String evaluate, String price, String phone, String url) {
        this.name = name;
        this.introduce = introduce;
        this.evaluate = evaluate;
        this.price = price;
        this.phone = phone;
        this.url = url;
    }
}
