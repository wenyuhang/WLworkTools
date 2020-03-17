package com.wl.uilib.utils;

import android.os.Handler;

/**
 * 单击 双击 控制类
 * Created by WYH
 * on2019/11/19
 */
public class ClickUtils {

    private  OnViewDoubleClick onListener;
    private static Handler handler = new Handler();
    private  int timeout=300;//双击间四百毫秒延时
    private int clickCount = 0;//记录连续点击次数

    private static ClickUtils clickUtils;

    public static ClickUtils getInstance() {
        if (null == clickUtils) {
            synchronized (ClickUtils.class) {
                if (null == clickUtils) {
                    clickUtils = new ClickUtils();
                }
            }
        }

        return clickUtils;
    }

    /**
     * 双击处理
     * @param listener
     * @param v
     */
    public void onDoubleClick(OnViewDoubleClick listener,final Object v) {
        if (handler==null)return;
        onListener = listener;

        clickCount++;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (clickCount == 1) {
                    onListener.onSingleClick(v);
                }else if(clickCount>=2){
                    onListener.onDoubleClick(v);
                }
                handler.removeCallbacksAndMessages(null);
                //清空handler延时，并防内存泄漏
                clickCount = 0;//计数清零
            }
        },timeout);//延时timeout后执行run方法中的代码
    }


    private int id;
    private long timeMillis;

    /**
     *  防止重复点击
     * @param id
     * @return
     */
    public boolean preventFun(int id){
        if (this.id==id&&System.currentTimeMillis()-timeMillis<2000){
            return true;
        }else {
            timeMillis = System.currentTimeMillis();
            this.id = id;
        }
        return false;
    }

    /**
     * 重置id
     */
    public void resetId(){
        this.id = 0;
    }

    public interface OnViewDoubleClick{
        void onSingleClick(Object obj);
        void onDoubleClick(Object obj);
    }

}
