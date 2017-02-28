package com.edge.edgenews.ui.pager;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;

import com.edge.edgenews.base.BaseMenuDetailPager;
import com.edge.edgenews.domain.NewsMenuData;
import com.edge.edgenews.global.Constants;
import com.edge.edgenews.base.BasePager;
import com.edge.edgenews.ui.activity.MainActivity;
import com.edge.edgenews.ui.fragment.LeftMenuFragment;
import com.edge.edgenews.ui.pager.menudetail.InteractMenuDetailPager;
import com.edge.edgenews.ui.pager.menudetail.NewsMenuDetailPager;
import com.edge.edgenews.ui.pager.menudetail.PhotoMenuDetailPager;
import com.edge.edgenews.ui.pager.menudetail.TopicMenuDetailPager;
import com.edge.edgenews.utils.CacheUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.util.ArrayList;

/**
 * Created by edge on 25/02/2017.
 */
public class NewsPager extends BasePager {

    // 创建集合，便于管理四个MenuDetailPager
    private ArrayList<BaseMenuDetailPager> mMenuDetailPager;
    private NewsMenuData mNewsMenuData;

    public NewsPager(Activity activity) {
        super(activity);
    }
    @Override
    public void initData() {
        tv_titlebar.setText("新闻");
        // 1，查看本地有没有缓存
        // 2，如果有，从缓存取数据
        String result = CacheUtil.getCache(Constants.URL.URL_CATEGORIES,mActivity);
        if(!TextUtils.isEmpty(result)) {
            // 有缓存,在缓存中取
            System.out.println("在缓存中取数据。。。。。。。");
            processResult(result);
        }
        // 有缓存也访问服务器，为了更新数据
        getDataFromServer();


    }

    private void getDataFromServer() {
        // 请求数据
        HttpUtils httpUtils = new HttpUtils();

        httpUtils.send(HttpMethod.GET, Constants.URL.URL_CATEGORIES, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result  = responseInfo.result;   // 获取json数据
                // 处理数据
                processResult(result);
                // 将json数据写如本地缓存
                CacheUtil.setCache(Constants.URL.URL_CATEGORIES,result,mActivity);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 处理服务器返回的json数据
     * @param result
     */
    private void processResult(String result) {
        // 使用gson框剪解析json
        Gson gson = new Gson();
        mNewsMenuData = gson.fromJson(result, NewsMenuData.class);

        // 获取LeftMenuFragment对象，传递数据
        LeftMenuFragment leftMenuFragment = ((MainActivity)mActivity).getLeftMenu();
        leftMenuFragment.setData(mNewsMenuData);
//        System.out.println("newsMenuData="+newsMenuData);

        // 初始化4ge菜单详情页
        mMenuDetailPager = new ArrayList<BaseMenuDetailPager>();
        mMenuDetailPager.add(new NewsMenuDetailPager(mActivity,mNewsMenuData.data.get(0).children));
        mMenuDetailPager.add(new TopicMenuDetailPager(mActivity));
        mMenuDetailPager.add(new PhotoMenuDetailPager(mActivity));
        mMenuDetailPager.add(new InteractMenuDetailPager(mActivity));

        // NewsPager的默认初始化页面是NewsMenuDetailPager
        setCurrentMenuPager(0);
    }

    /**
     * 给NewPager的FrameLayout填充布局
     * @param position
     */
    public void setCurrentMenuPager(int position) {
        BaseMenuDetailPager detailPager = mMenuDetailPager.get(position);
        // 初始化NewsMenuDetailPager中的数据
        detailPager.initData();
        //先移除之前的view
        fl_basepager.removeAllViews();
        fl_basepager.addView(detailPager.mRootView);

        //设置标题栏文本，随菜单详情页改变
        tv_titlebar.setText(mNewsMenuData.data.get(position).title);
    }
}
