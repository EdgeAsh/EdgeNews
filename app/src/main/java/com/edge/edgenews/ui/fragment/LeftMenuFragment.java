package com.edge.edgenews.ui.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.edge.edgenews.R;
import com.edge.edgenews.base.BaseFragment;
import com.edge.edgenews.domain.NewsMenuData;
import com.edge.edgenews.domain.NewsMenuData.NewsData;
import com.edge.edgenews.ui.activity.MainActivity;
import com.edge.edgenews.ui.pager.NewsPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by edge on 25/02/2017.
 */
public class LeftMenuFragment extends BaseFragment{
    /**
     * 初始化布局，子类必须实现
     */
    // 用于存储NewsPager传递来的json数据
    private ArrayList<NewsData> data;

    private int mCurrentPosition; // 记录当前条目位置

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
        final LeftMenuAdapter leftMenuAdapter = new LeftMenuAdapter();
        lv_left_menu.setAdapter(leftMenuAdapter);

        lv_left_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                mCurrentPosition = position;   // 记录当前条目
                leftMenuAdapter.notifyDataSetChanged();

                // 通知NewsPager切换页面
                setNewsMenuDetailPager(position);
                // 侧边栏点击事件结束，自动隐藏
                toggle();
            }
        });
    }

    private void toggle() {
        // 获取SlidingMenu对象
        SlidingMenu slidingMenu = ((MainActivity)mActivity).getSlidingMenu();
        slidingMenu.toggle();// 开关slidingMenu状态，如果开就关，如果关就开
    }

    private void setNewsMenuDetailPager(int position) {
        // 获取NewsPager对象
        // 1,获取MainActivity对象
        // 2,通过MainActivity获取ContentFragment对象
        // 3,通过ContentFragment获取NewsPager对象
        ContentFragment contentFragment = ((MainActivity)mActivity).getContentFragment();
        NewsPager newsPager = contentFragment.getNewsPager();

        // 填充NewsMenuDetailPager
        newsPager.setCurrentMenuPager(position);
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
            // 设置条目中字体和图片颜色
            if(mCurrentPosition == position) {
                //  当前条目与被选中的条目相同，设置条目的图片和字体颜色为红色
                tv_left_menu_item.setEnabled(true);
            }else {
                //  其它条目为白色
                tv_left_menu_item.setEnabled(false);
            }
            return view;
        }
    }
}
