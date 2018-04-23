package com.example.administrator.its_gs_mvp.db;

import org.litepal.crud.DataSupport;

/**
 * 车辆违章 表
 *
 * @Created by xww on 2018/4/20 0020.
 */

public class CarPeccancy extends DataSupport{
    private String carNumber;
    private String pCode;
    private String pTime;
    private String pAddr;

    public CarPeccancy(String carNumber, String pCode, String pTime, String pAddr) {
        this.carNumber = carNumber;
        this.pCode = pCode;
        this.pTime = pTime;
        this.pAddr = pAddr;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public void setpTime(String pTime) {
        this.pTime = pTime;
    }

    public void setpAddr(String pAddr) {
        this.pAddr = pAddr;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getpCode() {
        return pCode;
    }

    public String getpTime() {
        return pTime;
    }

    public String getpAddr() {
        return pAddr;
    }
}
