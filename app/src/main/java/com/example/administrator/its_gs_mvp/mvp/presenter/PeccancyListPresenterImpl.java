package com.example.administrator.its_gs_mvp.mvp.presenter;

import android.text.TextUtils;

import com.example.administrator.its_gs_mvp.bean.PeccancyCodeBean;
import com.example.administrator.its_gs_mvp.db.CarPeccancy;
import com.example.administrator.its_gs_mvp.db.PeccancyCard;
import com.example.administrator.its_gs_mvp.mvp.PeccancyListContract;
import com.example.administrator.its_gs_mvp.mvp.mpdel.PeccancyListModel;
import com.example.administrator.its_gs_mvp.mvp.presenter.base.BasePresenterImpl;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created by xww on 2018/4/21 0021.
 */

public class PeccancyListPresenterImpl extends BasePresenterImpl<PeccancyListContract.View>
        implements PeccancyListContract.Presenter, PeccancyListContract.Model {

    private String carNumber;

    private PeccancyListModel peccancyListModel;

    private List<String> pCodeList;//违章代码
    private List<String> pTimeList;//违章时间
    private List<String> pAddrList;//违章地点
    private List<String> pRemarksList;//违章标注
    private List<Integer> pScoreList;//违章扣分
    private List<Integer> pMoneyList;//违章罚款

    private int pCountSum;//违章次数总和
    private int scoreSum;//违章扣分总和
    private int moneySum;//违章罚款总和

    public PeccancyListPresenterImpl() {
        pCodeList = new ArrayList<>();
        pTimeList = new ArrayList<>();
        pAddrList = new ArrayList<>();
        pScoreList = new ArrayList<>();
        pMoneyList = new ArrayList<>();
        pRemarksList = new ArrayList<>();
        peccancyListModel = new PeccancyListModel();
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestory() {
        peccancyListModel = null;
        pCodeList = null;
        pTimeList = null;
        pAddrList = null;
        pScoreList = null;
        pMoneyList = null;
        pRemarksList = null;
    }

    @Override
    public void onCallbackPeccancyCode(JSONObject jsonObject) {
        scoreSum = 0;
        moneySum = 0;
        pCountSum = pCodeList.size();
        try {
            if (jsonObject.getInt("code") == 1) {
                PeccancyCodeBean bean = new Gson().fromJson(jsonObject.toString(), PeccancyCodeBean.class);
                if (bean != null) {
                    List<PeccancyCodeBean.DataBean> codeBean = bean.getData();
                    for (int i = 0; i < pCodeList.size(); i++) {
                        for (int j = 0; j < codeBean.size(); j++) {
                            if (pCodeList.get(i).equals(codeBean.get(j).getPCode())) {
                                int _score = codeBean.get(j).getPScore();
                                int _money = codeBean.get(j).getPMoney();
                                String _remarks = codeBean.get(j).getPRemarks();
                                scoreSum += _score;
                                moneySum += _money;
                                pScoreList.add(_score);
                                pMoneyList.add(_money);
                                pRemarksList.add(_remarks);
                            }
                        }
                    }
                }
                saveToDataBase();
                addCardData();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加违章卡片到数据库
     */
    private void saveToDataBase() {
        List<PeccancyCard> peccancyCardList = DataSupport
                .where("carNumber=?", carNumber)
                .find(PeccancyCard.class);
        if (peccancyCardList.size() == 0) {
            PeccancyCard peccancyCard = new PeccancyCard(carNumber, pCountSum, scoreSum, moneySum);
            peccancyCard.save();
        }
    }

    @Override
    public void addCarNumber(String carNumber) {
        if (TextUtils.isEmpty(carNumber)) {
           return;
        }else {
            pCodeList.clear();//违章代码
            pTimeList.clear();//违章时间
            pAddrList.clear();//违章地点
            this.carNumber = carNumber;
            List<CarPeccancy> carPeccancyList = DataSupport
                    .where("carNumber=?", carNumber)
                    .find(CarPeccancy.class);
            for (int i = 0; i < carPeccancyList.size(); i++) {
                CarPeccancy bean = carPeccancyList.get(i);
                pCodeList.add(bean.getpCode());
                pTimeList.add(bean.getpTime());
                pAddrList.add(bean.getpAddr());
            }
            peccancyListModel.getPeccancyCode(this);
        }
    }

    private void addCardData() {
        List<Map<String, Object>> cardInfo = new ArrayList<>();
        if (DataSupport.count(PeccancyCard.class) > 0) {
            List<PeccancyCard> peccancyCardList = DataSupport.findAll(PeccancyCard.class);
            for (int i = 0; i < peccancyCardList.size(); i++) {
                Map<String, Object> cardMap = new HashMap<>();
                cardMap.put("carNumber", peccancyCardList.get(i).getCarNumber());
                cardMap.put("pCount", peccancyCardList.get(i).getpCount());
                cardMap.put("pScoreSum", peccancyCardList.get(i).getpScoreSum());
                cardMap.put("pMoneySum", peccancyCardList.get(i).getpMoneySum());
                cardInfo.add(cardMap);
            }
        }
        if (cardInfo.size() != 0) {
            mView.setCardAdapter(cardInfo);
        }
    }

    @Override
    public void addCardDetailData() {
        List<Map<String, Object>> cardDetailInfo;
        if (pCodeList == null) {
            return;
        } else {
            cardDetailInfo = new ArrayList<>();
            for (int i = 0; i < pCodeList.size(); i++) {
                Map<String, Object> cardDetailMap = new HashMap<>();
                cardDetailMap.put("pTime", pTimeList.get(i));
                cardDetailMap.put("pAddr", pAddrList.get(i));
                cardDetailMap.put("pRemarks", pRemarksList.get(i));
                cardDetailMap.put("pScore", pScoreList.get(i));
                cardDetailMap.put("pMoney", pMoneyList.get(i));
                cardDetailInfo.add(cardDetailMap);
            }
        }
        if (cardDetailInfo.size() != 0) {
            mView.setCardDetailAdapter(cardDetailInfo);
        }
    }
}
