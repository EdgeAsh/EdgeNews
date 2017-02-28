package com.edge.edgenews.ui.pager.menudetail;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.edge.edgenews.base.BaseMenuDetailPager;

/**
 * Created by edge on 27/02/2017.
 */
public class TabDetailPager extends BaseMenuDetailPager{

    private String mText;
    private TextView view;

    public TabDetailPager(Activity activity, String title) {
        super(activity);
        mText = title;
    }

    @Override
    public View initView() {
        view = new TextView(mActivity);
        view.setText("新闻菜单详情-页签");
        view.setTextSize(20);
        view.setGravity(Gravity.CENTER);

        return view;

    }

    @Override
    public void initData() {
        view.setText("新闻菜单详情-"+mText);
    }
}
