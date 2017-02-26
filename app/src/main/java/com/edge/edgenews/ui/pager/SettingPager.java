package com.edge.edgenews.ui.pager;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.edge.edgenews.base.BasePager;

/**
 * Created by edge on 25/02/2017.
 */
public class SettingPager extends BasePager {

    public SettingPager(Activity activity) {
        super(activity);
        ib_titlebar.setVisibility(View.GONE);
    }
    @Override
    public void initData() {
        tv_titlebar.setText("设置");
        TextView view = new TextView(mActivity);
        view.setText("设置");
        view.setTextSize(20);
        view.setGravity(Gravity.CENTER);
        fl_basepager.addView(view);
    }
}
