package com.edge.edgenews.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.edge.edgenews.R;
import com.edge.edgenews.ui.activity.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 *  5个页面共有的内容单独提取让子类继承
 * Created by edge on 25/02/2017.
 */
public abstract class BasePager {
    public Activity mActivity;
    public View rootView;
    public TextView tv_titlebar;
    public ImageButton ib_titlebar;
    public FrameLayout fl_basepager;

    public BasePager(Activity activity) {
        this.mActivity = activity;
        initView();
    }
    // 初始化布局
    public void initView() {
        rootView = View.inflate(mActivity, R.layout.layout_basepager,null);
        tv_titlebar = (TextView) rootView.findViewById(R.id.tv_titlebar);
        ib_titlebar = (ImageButton) rootView.findViewById(R.id.ib_titlebar);
        fl_basepager = (FrameLayout) rootView.findViewById(R.id.fl_basepager);
        // 给titlebar的图片按钮设置点击，控制slidingMenu
        ib_titlebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });
    }

    /**
     * 侧边栏展开或关闭
     */
    private void toggle() {
        // 获取SlidingMenu对象
        SlidingMenu slidingMenu = ((MainActivity)mActivity).getSlidingMenu();
        slidingMenu.toggle();// 开关slidingMenu状态，如果开就关，如果关就开
    }


    /**
     * 初始化各页面数据
     */
    public abstract void initData();

}
