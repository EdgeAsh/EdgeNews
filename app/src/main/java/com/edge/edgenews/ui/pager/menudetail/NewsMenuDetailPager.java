package com.edge.edgenews.ui.pager.menudetail;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edge.edgenews.R;
import com.edge.edgenews.base.BaseMenuDetailPager;
import com.edge.edgenews.domain.NewsMenuData;
import com.edge.edgenews.ui.activity.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;


/**
 * 新闻菜单详情-新闻
 * ViewPagerIndicator使用流程
 * 1，引入library库
 * 2，布局文件中添加ViewPagerIndicator
 * 3，将ViewPagerIndicator与ViewPager关联，重写ViewPager的getPageTitle方法，ViewPager状态侦听设给indicator
 * 4，设置activity主题样式
 * 5，进入源码修改indicator的属性，修改文字，图片等
 * Created by edge on 27/02/2017.
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager implements ViewPager.OnPageChangeListener {
    @ViewInject(R.id.vp_menu_detail_news)
    private ViewPager vp_menu_detail_news;
    @ViewInject(R.id.tab_indicator)
    private TabPageIndicator tab_indicator;

    private ArrayList<NewsMenuData.NewsTabData> mTabList;//
    private ArrayList<TabDetailPager> mTabPagers;//
    public NewsMenuDetailPager(Activity activity, ArrayList<NewsMenuData.NewsTabData> children) {
        super(activity);
        mTabList = children;
    }

    /**
     * 初始化布局，返回跟节点
     */
    @Override
    public View initView() {

        View pager_menu_detail_news = View.inflate(mActivity, R.layout.pager_menu_detail_news,null);
        ViewUtils.inject(this,pager_menu_detail_news);

        return pager_menu_detail_news;
    }

    @Override
    public void initData() {
        // 遍历mTabList,创建页签
        mTabPagers = new ArrayList<TabDetailPager>();
        for (NewsMenuData.NewsTabData tabData: mTabList) {
            // 创建一个页签
            mTabPagers.add(new TabDetailPager(mActivity,tabData.title));
        }

        vp_menu_detail_news.setAdapter(new NewsMenuDetailAdapter());

        // 设置页面切换侦听，跟据页面确定侧边栏是否可以画出
//        vp_menu_detail_news.setOnPageChangeListener(this);

        // 将ViewPager与Indicator关联，此方法在viewpager设置数据后执行
        tab_indicator.setViewPager(vp_menu_detail_news);
        tab_indicator.setOnPageChangeListener(this);// 将ViewPager的侦听设置给indicator
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 页面位置
        if(position == 0 ){
            // 第一页，可以滑动
            setSlidingMenuEnable(true);
        }else {
            // 使页签可以左右滑动，同时不会唤出侧边栏
            setSlidingMenuEnable(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    // 设置侧边栏是否可以滑
    private void setSlidingMenuEnable(boolean enable) {
        // 得到此Fragment所在的Activity，获取SlidingMenu对象，设置SlidingMenu属性
        MainActivity mMainActivity = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mMainActivity.getSlidingMenu();

        if(enable) {
            // 全屏滑动
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            // 不可滑动
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

    class NewsMenuDetailAdapter extends PagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabList.get(position).title;
        }

        @Override
        public int getCount() {
            return mTabPagers.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager tabDetailPager =mTabPagers.get(position);
            container.addView(tabDetailPager.mRootView);
            tabDetailPager.initData();//初始化数据
            return tabDetailPager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    @OnClick(R.id.iv_news_cate_arr)
    public void nextPage(View view){ //滑动到下一页
        int currentItem = vp_menu_detail_news.getCurrentItem();
        currentItem++;
        vp_menu_detail_news.setCurrentItem(currentItem);
    }
}
