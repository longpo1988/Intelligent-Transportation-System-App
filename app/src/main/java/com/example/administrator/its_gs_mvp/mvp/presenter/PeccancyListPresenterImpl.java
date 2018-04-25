package com.example.administrator.its_gs_mvp.mvp.presenter;

import android.text.TextUtils;

import com.example.administrator.its_gs_mvp.bean.PeccancyCodeBean;
import com.example.administrator.its_gs_mvp.db.CarPeccancy;
import com.example.administrator.its_gs_mvp.db.PeccancyCard;
import com.example.administrator.its_gs_mvp.mvp.PeccancyListContract;
import com.example.administrator.its_gs_mvp.mvp.mpdel.PeccancyListModel;
import com.example.administrator.its_gs_mvp.mvp.presenter.base.BasePresenterImpl;
import com.example.administrator.its_gs_mvp.util.LoadingDialog;
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
    private boolean isCardRefreshed;

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
        isCardRefreshed = false;
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
    }

    @Override
    public void onCallbackPeccancyCode(JSONObject jsonObject) {
        scoreSum = 0;
        moneySum = 0;
        pScoreList.clear();
        pMoneyList.clear();
        pRemarksList.clear();
        pCountSum = pCodeList.size();
        try {
            if (jsonObject.getInt("code") == 1) {
                LoadingDialog.disDialog();
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
                /**
                 * 首先判断此车牌号未存在数据库中--添加违章卡片到数据库
                 */
                List<PeccancyCard> peccancyCardList = DataSupport
                        .where("carNumber=?", carNumber)
                        .find(PeccancyCard.class);
                if (peccancyCardList.size() == 0) {
                    PeccancyCard peccancyCard = new PeccancyCard(carNumber, pCountSum, scoreSum, moneySum);
                    peccancyCard.save();
                }
                /**
                 * 添加违章卡片的数据
                 * @isCardRefreshed 需要刷新适配器时调用
                 */
                if (isCardRefreshed) {
                    List<Map<String, Object>> cardInfo = new ArrayList<>();
                    if (DataSupport.count(PeccancyCard.class) > 0) {
                        List<PeccancyCard> peccancyList = DataSupport.findAll(PeccancyCard.class);
                        for (int i = 0; i < peccancyList.size(); i++) {
                            Map<String, Object> cardMap = new HashMap<>();
                            cardMap.put("carNumber", peccancyList.get(i).getCarNumber());
                            cardMap.put("pCount", peccancyList.get(i).getpCount());
                            cardMap.put("pScoreSum", peccancyList.get(i).getpScoreSum());
                            cardMap.put("pMoneySum", peccancyList.get(i).getpMoneySum());
                            cardInfo.add(cardMap);
                        }
                    }
                    /**
                     * 判断数据不为空，设置到适配器进行显示
                     */
                    if (cardInfo.size() != 0) {
                        mView.setCardAdapter(cardInfo);
                    }
                }
                /**
                 * 添加违章卡片详情的数据
                 */
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
                    /**
                     * 判断数据不为空，设置到适配器进行显示
                     */
                    if (cardDetailInfo.size() != 0) {
                        mView.setCardDetailAdapter(cardDetailInfo);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCarNumber(String carNumber, boolean isCardRefresh) {
        if (TextUtils.isEmpty(carNumber)) {
            return;
        } else {
            isCardRefreshed = isCardRefresh;
            pCodeList.clear();//违章代码
            pTimeList.clear();//违章时间
            pAddrList.clear();//违章地点
            this.carNumber = carNumber;
            if (DataSupport.count(CarPeccancy.class) != 0) {
                List<CarPeccancy> carPeccancyList = DataSupport
                        .where("carNumber=?", carNumber)
                        .find(CarPeccancy.class);
                for (int i = 0; i < carPeccancyList.size(); i++) {
                    CarPeccancy bean = carPeccancyList.get(i);
                    pCodeList.add(bean.getpCode());
                    pTimeList.add(bean.getpTime());
                    pAddrList.add(bean.getpAddr());
                }
            }

            LoadingDialog.showDialog(mView.getContext());
            peccancyListModel.getPeccancyCode(this);
        }
    }
}
