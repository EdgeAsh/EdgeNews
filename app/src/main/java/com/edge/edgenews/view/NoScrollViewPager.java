package com.edge.edgenews.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 自定义ViewPager,重写onTouchEvent()方法，禁止ViewPager滑动
 * Created by edge on 26/02/2017.
 */
public class NoScrollViewPager extends ViewPager{
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 重写父类方法，返回true禁止滑动事件
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
