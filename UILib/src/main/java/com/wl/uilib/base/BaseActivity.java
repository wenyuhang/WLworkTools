package com.wl.uilib.base;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.gyf.immersionbar.ImmersionBar;
import com.wl.uilib.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by WYH on 2017/8/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AppManager.getInstance().addActivity(this);

        //转场动画
        overridePendingTransition( R.anim.slide_left_in,R.anim.slide_left_out);

        setContentView(getLayoutId());

        bind = ButterKnife.bind(this);
        //初始化沉浸式
        initImmersionBar();

        init();
    }

    @Override
    public void finish() {
        super.finish();
        //退场动画
        overridePendingTransition( R.anim.slide_right_in ,R.anim.slide_right_out );
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).navigationBarColor(R.color.white_a_color).init();
    }


    /**
     * 加载布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化数据
     *
     * @return
     */
    public abstract void init();


    /**
     * 老人机字体适配
     *
     * @return
     */
    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration newConfig = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();

        if (resources != null && newConfig.fontScale != 1) {
            newConfig.fontScale = 1;
            if (Build.VERSION.SDK_INT >= 17) {
                Context configurationContext = createConfigurationContext(newConfig);
                resources = configurationContext.getResources();
                displayMetrics.scaledDensity = displayMetrics.density * newConfig.fontScale;
            } else {
                resources.updateConfiguration(newConfig, displayMetrics);
            }
        }
        return resources;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
//        AppManager.getInstance().removeActivity(this);
    }

}
