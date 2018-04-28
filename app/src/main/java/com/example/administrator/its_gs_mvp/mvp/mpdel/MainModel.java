package com.example.administrator.its_gs_mvp.mvp.mpdel;

import com.example.administrator.its_gs_mvp.R;
import com.example.administrator.its_gs_mvp.mvp.MainContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created by xww on 2018/4/8 0008.
 */

public class MainModel implements MainContract.Model {
    private final String titles[] = {"账户管理", "公交查询", "红绿灯管理", "车辆违章", "路况查询",
            "生活助手", "数据分析", "个人中心", "地铁查询", "高速路况", "旅行助手", "创意"};
    private final int icons[] = {R.drawable.ic_list_item_lefticon, R.drawable.ic_list_item_lefticon,
            R.drawable.ic_list_item_lefticon, R.drawable.ic_list_item_lefticon, R.drawable.ic_list_item_lefticon,
            R.drawable.ic_list_item_lefticon, R.drawable.ic_list_item_lefticon, R.drawable.ic_list_item_lefticon,
            R.drawable.ic_list_item_lefticon, R.drawable.ic_list_item_lefticon, R.drawable.ic_list_item_lefticon,
            R.drawable.ic_list_item_lefticon};

    private List<Map<String, Object>> getDrawerItems() {
        List _mapListItems = new ArrayList();
        for (int i = 0; i < titles.length; i++) {
            Map _mapItem = new HashMap();
            _mapItem.put("icon", icons[i]);
            _mapItem.put("name", titles[i]);
            _mapListItems.add(_mapItem);
        }
        return _mapListItems;
    }

    @Override
    public void getItems(CallBack callBack) {
        callBack.onFinished(getDrawerItems());
    }
}
