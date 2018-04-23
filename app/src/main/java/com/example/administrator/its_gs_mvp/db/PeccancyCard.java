package com.example.administrator.its_gs_mvp.db;

import org.litepal.crud.DataSupport;

/**
 * 违章详情-违章卡片
 *
 * @Created by xww on 2018/4/23 0023.
 */

public class PeccancyCard  extends DataSupport{
    private String carNumber;//车牌号
    private int pCount;//违章次数
    private int pScoreSum;//违章扣分总和
    private int pMoneySum;//违章罚款总和

    public PeccancyCard(String carNumber, int pCount, int pScoreSum, int pMoneySum) {
        this.carNumber = carNumber;
        this.pCount = pCount;
        this.pScoreSum = pScoreSum;
        this.pMoneySum = pMoneySum;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setpCount(int pCount) {
        this.pCount = pCount;
    }

    public void setpScoreSum(int pScoreSum) {
        this.pScoreSum = pScoreSum;
    }

    public void setpMoneySum(int pMoneySum) {
        this.pMoneySum = pMoneySum;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public int getpCount() {
        return pCount;
    }

    public int getpScoreSum() {
        return pScoreSum;
    }

    public int getpMoneySum() {
        return pMoneySum;
    }
}
