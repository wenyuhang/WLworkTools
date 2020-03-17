package com.hyperx.wlworktools.activitys;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hyperx.designlib.design.swipe_back.SwipeBackBaseActivity;
import com.hyperx.designlib.design.swipe_back.SwipeBackLayout;
import com.hyperx.wlworktools.R;

/**
 * 滑动返回
 */
public class SwipeBackActivity extends SwipeBackBaseActivity {

    private static final int VIBRATE_DURATION = 20;
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_back);
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(500);

        mSwipeBackLayout.addSwipeListener(new SwipeBackLayout.SwipeListener() {
            @Override
            public void onScrollStateChange(int state, float scrollPercent) {

            }

            @Override
            public void onEdgeTouch(int edgeFlag) {
                vibrate(VIBRATE_DURATION);
            }

            @Override
            public void onScrollOverThreshold() {
                vibrate(VIBRATE_DURATION);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    private void vibrate(long duration) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {
                0, duration
        };
        vibrator.vibrate(pattern, -1);
    }

    public void btn_back(View view){
        scrollToFinishActivity();
    }

    @Override
    public void onBackPressed() {
        scrollToFinishActivity();
    }
}
