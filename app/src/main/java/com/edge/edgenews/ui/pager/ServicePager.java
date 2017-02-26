package com.edge.edgenews.ui.pager;

import android.app.Activity;
import android.view.Gravity;
import android.widget.TextView;

import com.edge.edgenews.base.BasePager;

/**
 * Created by edge on 25/02/2017.
 */
public class ServicePager extends BasePager {

    public ServicePager(Activity activity) {
        super(activity);
    }
    @Override
    public void initData() {
        tv_titlebar.setText("服务");
        TextView view = new TextView(mActivity);
        view.setText("服务");
        view.setTextSize(20);
        view.setGravity(Gravity.CENTER);
        fl_basepager.addView(view);
    }
}
