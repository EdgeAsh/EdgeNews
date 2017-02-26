package com.edge.edgenews.ui.fragment;

import android.view.View;

import com.edge.edgenews.R;
import com.edge.edgenews.base.BaseFragment;

/**
 * Created by edge on 25/02/2017.
 */
public class LeftMenuFragment extends BaseFragment{
    /**
     * 初始化布局，子类必须实现
     *
     * @return
     */
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu,null);
        return view;
    }
}
