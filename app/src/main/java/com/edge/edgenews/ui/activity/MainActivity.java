package com.edge.edgenews.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.edge.edgenews.R;
import com.edge.edgenews.ui.fragment.ContentFragment;
import com.edge.edgenews.ui.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * SlidingMenu使用流程
 * 1,引入SlidingMenu库
 * 2,Activity继承SlidingFragmentActivity
 * 3,onCreate改为public
 * 4,setBehindContentView,设置基本侧边栏效果
 * 5,获取SlidingMenu,深层定制
 */
public class MainActivity extends SlidingFragmentActivity {

    static final String TAG_CONTENT = "TAG_CONTENT";
    static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉标题,必须在setContentView前执行
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // 填充侧边栏
        setBehindContentView(R.layout.left_menu);
        // 获取SlidingMenu，深层定制
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindOffset(700);

        initFragment();
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        // 获取fragment管理器
        FragmentManager fm = getSupportFragmentManager();
        // 开启事物
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_left_menu,new LeftMenuFragment(),TAG_LEFT_MENU);  // 填充FrameLayout
        transaction.replace(R.id.fl_content,new ContentFragment(),TAG_CONTENT);
        transaction.commit();  // 提交事务
    }




}
