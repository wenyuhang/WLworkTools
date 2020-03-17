package com.hyperx.wlworktools.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.wl.uilib.utils.L;

/**
 * Created by WYH
 * on2020/1/14
 */
public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        L.d("TAG", width + "========" + widthMode  + "<===" + MeasureSpec.EXACTLY);
        L.d("TAG", height + "=======" + heightMode + "<===" + MeasureSpec.EXACTLY);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
