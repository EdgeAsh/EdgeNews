package com.edge.edgenews.ui.pager.menudetail;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.edge.edgenews.base.BaseMenuDetailPager;

/**
 * 新闻菜单详情-互动
 * Created by edge on 27/02/2017.
 */
public class InteractMenuDetailPager extends BaseMenuDetailPager {

    public InteractMenuDetailPager(Activity activity) {
        super(activity);
    }

    /**
     * 初始化布局，返回跟节点
     */
    @Override
    public View initView() {
        TextView view = new TextView(mActivity);
        view.setText("新闻菜单详情-互动");
        view.setTextSize(20);
        view.setGravity(Gravity.CENTER);

        return view;
    }
}
