package com.edge.edgenews.ui.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.edge.edgenews.base.BasePager;

/**
 * Created by edge on 25/02/2017.
 */
public class HomePager extends BasePager {

    public HomePager(Activity activity) {
        super(activity);
        ib_titlebar.setVisibility(View.GONE);
    }
    @Override
    public void initData() {
        tv_titlebar.setText("主页");
        TextView view = new TextView(mActivity);
        view.setText("主页");
        view.setTextSize(20);
        view.setTextColor(Color.RED);
        view.setGravity(Gravity.CENTER);  //并无效果
        fl_basepager.addView(view);
    }
}
