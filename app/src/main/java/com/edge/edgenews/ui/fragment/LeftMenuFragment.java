package com.edge.edgenews.ui.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.edge.edgenews.R;
import com.edge.edgenews.base.BaseFragment;
import com.edge.edgenews.domain.NewsMenuData;
import com.edge.edgenews.domain.NewsMenuData.NewsData;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by edge on 25/02/2017.
 */
public class LeftMenuFragment extends BaseFragment{
    /**
     * 初始化布局，子类必须实现
     * @return
     */
    // 用于存储NewsPager传递来的json数据
    private ArrayList<NewsData> data;

    @ViewInject(R.id.lv_left_menu)
    private ListView lv_left_menu;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu,null);
        ViewUtils.inject(this,view);
        return view;
    }

    @Override
    public void initData() {
    }

    /**
     * 用于将在NewsPager中获取的json数据传递给LeftMenuFragment，显示
     * @param newsMenuData
     */
    public void setData(NewsMenuData newsMenuData) {
        data = newsMenuData.data;
        LeftMenuAdapter leftMenuAdapter = new LeftMenuAdapter();
        lv_left_menu.setAdapter(leftMenuAdapter);
    }

    class LeftMenuAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public NewsData getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            View view = View.inflate(getActivity(),R.layout.item_list_left_menu,null);
            TextView tv_left_menu_item = (TextView) view.findViewById(R.id.tv_left_menu_item);
            // 将data中的title显示在侧边栏中
            tv_left_menu_item.setText(getItem(position).title);
            return view;
        }
    }
}
