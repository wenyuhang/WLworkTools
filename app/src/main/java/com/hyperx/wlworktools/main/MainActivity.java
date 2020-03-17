package com.hyperx.wlworktools.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hyperx.wlworktools.R;
import com.hyperx.wlworktools.activitys.CacheUtilsActivity;
import com.hyperx.wlworktools.activitys.CusGroupActivity;
import com.hyperx.wlworktools.activitys.PreventDoubleClickActivity;
import com.hyperx.wlworktools.activitys.RxjavaActivity;
import com.hyperx.wlworktools.activitys.SwipeBackActivity;
import com.hyperx.wlworktools.activitys.UpdateActivity;
import com.wl.uilib.utils.T;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 滑动返回
     * @param view
     */
    public void jumpSwipeBack(View view){
     startActivity(new Intent(MainActivity.this, SwipeBackActivity.class));
    }

    /**
     * Rxjava  操作
     * @param view
     */
    public void jumpRxjava(View view){
        startActivity(new Intent(MainActivity.this, RxjavaActivity.class));
    }

    /**
     * 防止重复点击的
     * @param view
     */
    public void jumpDoubleClick(View view){
        startActivity(new Intent(MainActivity.this, PreventDoubleClickActivity.class));
    }

    /**
     * 版本更新
     * @param view
     */
    public void jumpUpdate(View view){
        startActivity(new Intent(MainActivity.this, UpdateActivity.class));
    }

    /**
     * 缓存
     * @param view
     */
    public void jumpCache(View view){
        startActivity(new Intent(MainActivity.this, CacheUtilsActivity.class));
    }

    /**
     * 自定义viewgroup
     * @param view
     */
    public void jumpCusGroup(View view){
        startActivity(new Intent(MainActivity.this, CusGroupActivity.class));
    }
}
