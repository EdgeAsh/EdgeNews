package com.edge.edgenews.ui.pager.menudetail;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edge.edgenews.R;
import com.edge.edgenews.base.BaseMenuDetailPager;
import com.edge.edgenews.domain.NewsMenuData;
import com.edge.edgenews.domain.NewsTab;
import com.edge.edgenews.global.Constants;
import com.edge.edgenews.utils.CacheUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by edge on 27/02/2017.
 */
public class TabDetailPager extends BaseMenuDetailPager{
    // 页签分类的网络信息
    private NewsMenuData.NewsTabData mTabData;

    //新闻列表的url
    private String mUrl;
    private NewsTab mNewsTab;//网络返回的新闻列表信息
    private ArrayList<NewsTab.TopNews> mTopNews; // 网络返回的新闻头条信息
    private TabDetailAdapter mTabDetailAdapter; // 头条新闻的数据适配器

    @ViewInject(R.id.vp_tab_detail)
    private ViewPager vp_tab_detail;
    @ViewInject(R.id.lv_tab_detail)
    private ListView lv_tab_detail;

    public TabDetailPager(Activity activity, NewsMenuData.NewsTabData tabData) {
        super(activity);
        mTabData = tabData;
        mUrl = Constants.URL.URL_BASE+tabData.url;
    }

    @Override
    public View initView() {
        View pager_tab_detail = View.inflate(mActivity, R.layout.pager_tab_detail,null);

        ViewUtils.inject(this,pager_tab_detail);
        
        return pager_tab_detail;
    }

    @Override
    public void initData() {

        String cache = CacheUtil.getCache(mUrl,mActivity);
        if(!TextUtils.isEmpty(cache)){
            // 如果缓存不为空，解析缓存中的数据
            pocessResult(cache);
        }
        getDataFromServer();
    }

    private void getDataFromServer() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpMethod.GET, mUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                pocessResult(result);

                // 将数据写入缓存
                CacheUtil.setCache(mUrl,result,mActivity);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
                Toast.makeText(mActivity,"数据请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void pocessResult(String result) {
        Gson gson = new Gson();
        mNewsTab = gson.fromJson(result, NewsTab.class);
//        System.out.println("新闻数据解析解果"+newsTab);
        // 初始化头条新闻
        mTopNews = mNewsTab.data.topnews;
        mTabDetailAdapter = new TabDetailAdapter();
        vp_tab_detail.setAdapter(mTabDetailAdapter);
    }

    class TabDetailAdapter extends PagerAdapter {
        BitmapUtils mBitmapUtils;
        public TabDetailAdapter(){
            // 初始化xUtils中的图片加载工具
            mBitmapUtils = new BitmapUtils(mActivity);
            // 设置默认加载图片
            mBitmapUtils.configDefaultLoadingImage(R.mipmap.pic_item_list_default);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = new ImageView(mActivity);
            view.setScaleType(ImageView.ScaleType.FIT_XY);// 设置图片填充效果，填充父窗体
            // 获取图片链接，下载图片，将图片设置给ImageView，内存溢出问题被BitMapUtils已解决，图片缓存
            mBitmapUtils.display(view,mTopNews.get(position).topimage);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mTopNews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }
    }
}
