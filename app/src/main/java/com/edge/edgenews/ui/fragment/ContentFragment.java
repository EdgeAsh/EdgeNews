package com.edge.edgenews.ui.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.edge.edgenews.R;
import com.edge.edgenews.base.BaseFragment;
import com.edge.edgenews.base.BasePager;
import com.edge.edgenews.ui.activity.MainActivity;
import com.edge.edgenews.ui.pager.GovPager;
import com.edge.edgenews.ui.pager.HomePager;
import com.edge.edgenews.ui.pager.NewsPager;
import com.edge.edgenews.ui.pager.ServicePager;
import com.edge.edgenews.ui.pager.SettingPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edge on 25/02/2017.
 */
public class ContentFragment extends BaseFragment {
    @ViewInject(R.id.vp_content)
    private ViewPager vp_content;
    @ViewInject(R.id.rg_content)
    private RadioGroup rg_content;

    private List<BasePager> mPagers;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content,null);
        ViewUtils.inject(this,view); // 注入view和事件
//        vp_content = (ViewPager) view.findViewById(R.id.vp_content);
        return view;
    }

    @Override
    public void initData() {
        // 添加5个标签也
        mPagers = new ArrayList<BasePager>();
        mPagers.add(new HomePager(getActivity()));
        mPagers.add(new NewsPager(getActivity()));
        mPagers.add(new GovPager(getActivity()));
        mPagers.add(new ServicePager(getActivity()));
        mPagers.add(new SettingPager(getActivity()));
        // 设置适配器
        vp_content.setAdapter(new ContentAdapter());

        // 设置RadioGroup点击侦听
        rg_content.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch (checkId){
                    case R.id.rb_home:
                        //进入首页
//                        vp_content.setCurrentItem(0);
                        vp_content.setCurrentItem(0,false);//跳转页面时无滑动效果
                        setSlidingMenuEnable(false); // 首页slidingMenu不可用
                        mPagers.get(0).initData(); // 在此请求数据
                        break;
                    case R.id.rb_news:
                        //进入新闻也
                        vp_content.setCurrentItem(1,false);
                        setSlidingMenuEnable(true);
                        mPagers.get(1).initData(); // 在此请求数据
                        break;
                    case R.id.rb_gov:
                        //进入政务
                        vp_content.setCurrentItem(2,false);
                        setSlidingMenuEnable(true);
                        mPagers.get(2).initData(); // 在此请求数据
                        break;
                    case R.id.rb_service:
                        //进入服务
                        vp_content.setCurrentItem(3,false);
                        setSlidingMenuEnable(true);
                        mPagers.get(3).initData(); // 在此请求数据
                        break;
                    case R.id.rb_setting:
                        //进入设置
                        vp_content.setCurrentItem(4,false);
                        setSlidingMenuEnable(false); // 设置页slidingMenu不可用
                        mPagers.get(4).initData(); // 在此请求数据
                        break;
                }
            }
        });
        mPagers.get(0).initData();
        setSlidingMenuEnable(false); // 进入首页就使slidingMenu失效，无需再点击主页
    }

    class ContentAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = mPagers.get(position);
            container.addView(basePager.rootView); //将页面布局添加到容器中
//            basePager.initData(); // 初始化数据，不要在这使用，为了节省资源
            return basePager.rootView;
        }


        @Override
        public int getCount() {
            return mPagers.size();
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private void setSlidingMenuEnable(boolean enable) {
        // 得到此Fragment所在的Activity，获取SlidingMenu对象，设置SlidingMenu属性
        MainActivity mMainActivity = (MainActivity) getActivity();
        SlidingMenu slidingMenu = mMainActivity.getSlidingMenu();

        if(enable) {
            // 全屏滑动
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            // 不可滑动
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
}
