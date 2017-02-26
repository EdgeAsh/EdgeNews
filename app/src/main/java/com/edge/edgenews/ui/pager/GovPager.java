package com.edge.edgenews.ui.pager;

import android.app.Activity;
import android.widget.TextView;

import com.edge.edgenews.base.BasePager;

/**
 * Created by edge on 25/02/2017.
 */
public class GovPager extends BasePager {

    public GovPager(Activity activity) {
        super(activity);
    }
    @Override
    public void initData() {
        tv_titlebar.setText("政务");
        TextView view = new TextView(mActivity);
        view.setText("政务");
        view.setTextSize(20);
        fl_basepager.addView(view);
    }
}
