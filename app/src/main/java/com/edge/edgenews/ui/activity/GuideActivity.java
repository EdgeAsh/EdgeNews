package com.edge.edgenews.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.edge.edgenews.R;
import com.edge.edgenews.utils.PrefUtils;

import java.util.ArrayList;

/**
 * Created by edge on 24/02/2017.
 */
public class GuideActivity extends Activity implements View.OnClickListener{

    int[] mImgIds = {
            R.mipmap.guide_1,
            R.mipmap.guide_2,
            R.mipmap.guide_3
    };

    ArrayList<ImageView> mImgViewList;
    private int mPointWidth;
    private Button btn_guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        final LinearLayout ll_container = (LinearLayout) findViewById(R.id.ll_container);
        final ImageView iv_red_point = (ImageView) findViewById(R.id.iv_red_point);
        btn_guide = (Button) findViewById(R.id.btn_guide);
        btn_guide.setOnClickListener(this);

        // 初始化imageView
        mImgViewList = new ArrayList<ImageView>();
        for(int i=0;i<mImgIds.length;i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setBackgroundResource(mImgIds[i]);
            mImgViewList.add(imageView);
            // 初始化原点
            ImageView pointView = new ImageView(this);
            pointView.setImageResource(R.drawable.shape_circle_default);
            // 初始化圆点布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            if(i>0){
                params.leftMargin = 20; //从第二个点开始设置边距
            }
            pointView.setLayoutParams(params);
            ll_container.addView(pointView);
        }

        ViewPager vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        vp_guide.setAdapter(new MyPagerAdapter());

        // 页面绘制结束之后，计算两个圆点间的距离
        // 视图树
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            // layout方法执行结束(位置确定)
            @Override
            public void onGlobalLayout() {
                // 移除监听
                iv_red_point.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                // 获取两个圆点间的距离
                mPointWidth = ll_container.getChildAt(1).getLeft()-ll_container.getChildAt(0).getLeft();
            }
        });



        //
        vp_guide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                // 获取小红点父节点的布局参数
//                int leftMargin = (int) (mPointWidth*positionOffset+position*mPointWidth);
//                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
//                params.leftMargin = leftMargin;
//                // 修改小红点的左边距
//                iv_red_point.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                // 获取小红点父节点的布局参数
                int leftMargin = position*mPointWidth;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
                params.leftMargin = leftMargin;
                // 修改小红点的左边距
                iv_red_point.setLayoutParams(params);
                // 设置按钮可见
                if(position==mImgIds.length-1) {
                    btn_guide.setVisibility(View.VISIBLE);
                }else {
                    btn_guide.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_guide:
                // 开始体验
                PrefUtils.putBoolean("is_guide_show",true,this);// 记录新手引导已经被显示的状态，下次启动不再展示
                // 进入主界面
                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;
        }
    }


    class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            ImageView imageView = new ImageView(getApplicationContext());
//            imageView.setBackgroundResource(mImgIds[position]);
            ImageView imageView= mImgViewList.get(position);
            container.addView(imageView);
            return imageView;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }


        @Override
        public int getCount() {
            return mImgIds.length;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public MyPagerAdapter() {
            super();
        }
    }
}
