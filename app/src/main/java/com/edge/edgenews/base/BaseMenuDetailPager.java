package com.edge.edgenews.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by edge on 27/02/2017.
 */
public abstract class BaseMenuDetailPager {

    public Activity mActivity;

    public View mRootView;
    public BaseMenuDetailPager(Activity activity){
        mActivity = activity;
        mRootView = initView();
    }

    /**
     * 初始化布局，返回跟节点
     */
    public abstract View initView();

    public void initData() {

    }
}
