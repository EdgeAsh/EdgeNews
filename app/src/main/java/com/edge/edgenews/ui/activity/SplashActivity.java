package com.edge.edgenews.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.edge.edgenews.R;
import com.edge.edgenews.utils.PrefUtils;

/**
 * Created by edge on 24/02/2017.
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // 设置渐变动画
        LinearLayout ll_splash = (LinearLayout) findViewById(R.id.ll_splash);

        Animation animation = new AlphaAnimation(0.1f,1);
        animation.setDuration(2000);

        ll_splash.startAnimation(animation);

        // 设置侦听，动画结束后进入主界面
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 判断是否需要进入引导界面
                boolean isGuidshow = PrefUtils.getBoolean("is_guide_show",false,getApplicationContext());
                if(isGuidshow) {
                    // 进入主界面
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }else {
                    // 进入引导姐面
                    startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                }
                // 结束闪屏界面
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
