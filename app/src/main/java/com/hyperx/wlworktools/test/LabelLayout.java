package com.hyperx.wlworktools.test;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyperx.wlworktools.R;
import com.wl.uilib.utils.L;

/**
 * 自定义标签流水型布局
 * create by wxy on 2020/1/13
 */


public class LabelLayout extends ViewGroup {

    //特殊字体属性
    //字体大小
    private int spTextSize = 10;
    //字体颜色
    private int spTextColor = R.color.white_a_color;

    //普通字体属性
    //字体大小
    private int orTextSize = 14;
    //字体颜色
    private int orTextColor = R.color.gray_a_color;

    //行高
    private int lineHeight = 10;

    private Context context;
    private float width;
    private float lastWidth;
    //特殊字体textview
    private TextView specialView;
    //特殊字体textview paint
    private Paint specialPaint;
    //普通字体textview
    private TextView ordinaryView;
    //特殊字体textview paint
    private Paint ordinaryPaint;
    private int maxLineHeight;


    public LabelLayout(Context context) {
        super(context);
        init(context);
    }

    public LabelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LabelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     */
    private void init(Context context) {
        this.context = context;
        maxLineHeight = orTextSize > spTextSize ? orTextSize * 4 : spTextSize * 4;
        //特殊字体
        specialView = new TextView(this.context);
        specialView.setTextSize(spTextSize);
        specialPaint = specialView.getPaint();

        //普通字体
        ordinaryView = new TextView(this.context);
        ordinaryView.setTextSize(orTextSize);
        ordinaryPaint = ordinaryView.getPaint();

    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (width > 0 && mode == MeasureSpec.EXACTLY) {
            this.width = width;
            lastWidth = width;
            startCalc();
        }
        int count = getChildCount();
        int l = 0;
        float totalHeight = lineHeight;
        int viweHeight = 0;
        int maxLineHeight = 0;
        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);
            if (v.getVisibility() != View.GONE) {
                measureChild(v, widthMeasureSpec, heightMeasureSpec);
                View childAt = getChildAt(i);
                int viewWidth = childAt.getMeasuredWidth();
                viweHeight = childAt.getMeasuredHeight();
                if (viewWidth > width - l) {
                    totalHeight += maxLineHeight + lineHeight;
                    l = 0;
                    maxLineHeight = 0;
                }

                if (maxLineHeight == 0) {
                    maxLineHeight = viweHeight;
                }


                if (viweHeight > maxLineHeight) {
                    maxLineHeight = viweHeight;
                }
                if (width - l > 0) {
                    l += viewWidth;
                }
            }
        }

        setMeasuredDimension(width, (int) totalHeight + viweHeight);
    }

    /**
     * 排布
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        float totalHeight = lineHeight;

        if (childCount == 0) {
            return;
        }
        l = 0;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int viewWidth = childAt.getMeasuredWidth();
            int viewHeight = childAt.getMeasuredHeight();


            if (viewWidth > width - l) {
                totalHeight += maxLineHeight + lineHeight;
                l = 0;
                maxLineHeight = orTextSize > spTextSize ? orTextSize * 4 : spTextSize * 4;
            }


            if (viewHeight > maxLineHeight) {
                maxLineHeight = viewHeight;
                t = (int) totalHeight;
                b = (int) totalHeight + viewHeight;
            } else {
                t = (int) totalHeight + ((maxLineHeight - viewHeight) / 2);
                b = (int) totalHeight + viewHeight + ((maxLineHeight - viewHeight) / 2);
            }

            childAt.layout(l, t, l + viewWidth, b);

            if (width - l > 0) {
                l += (viewWidth) ;
            }

        }

    }

    private String data;
    private boolean isStart;

    public void upData(String data) {
        this.data = data;
    }

    /**
     * creat view
     */
    private void startCalc() {
        if (null == data || data.length() == 0) return;
        if (isStart) return;
        String[] split = split(data);
        for (int i = 0; i < split.length; i++) {
            String str = split[i];
            if (null != str && str.length() > 0) {
                calc(str.contains("~"), str);
            }
        }
        invalidate();
//        requestLayout();
        isStart = true;
    }


    /**
     * 计算数据
     */
    private void calc(boolean flag, String data) {
        //特殊字体
        if (flag) {
            data = data.replace("~", "");
            addSpecialText(data);
//            Log.d("TAG-main","特殊字体："+data);
            //普通字体
        } else {
            addOrdinaryText(data);
//            Log.d("TAG-main","普通字体："+data);
        }
    }

    /**
     * 拆分字体
     */
    private String[] split(String data) {
        data = data.replaceAll("/#", "&");
        data = data.replaceAll("#/", "~&");
        String[] split = data.split("&");
        return split;
    }

    /**
     * 添加普通字体
     *
     * @param data
     */
    private void addOrdinaryText(String data) {

        float v = ordinaryPaint.measureText(data);
        //如果字符串宽度小于剩余宽度 直接创建一个textview
        if (v <= lastWidth) {
            lastWidth = lastWidth - v;
            creatView(data);

            //如果字符串宽度大于剩余宽度 需要拆分字符串
        } else {

            String str = getOrdinaryStr(data);
            lastWidth = width;
            creatView(str);


            addOrdinaryText(data.substring(str.length(), data.length()));
        }

    }


    /**
     * 添加特殊字体
     *
     * @param data
     */
    private void addSpecialText(String data) {
        float v = specialPaint.measureText(data)+20;
        //如果字符串宽度小于剩余宽度 直接创建一个textview
        if (v <= lastWidth) {
            lastWidth = lastWidth - v;
            creatSpView(data);

            //如果字符串宽度大于剩余宽度 需要拆分字符串
        } else {

            String str = getSpecialStr(data);
            lastWidth = width;
            creatSpView(str);



            addSpecialText(data.substring(str.length(), data.length()));
        }
    }


    /**
     * 获取剩余控件特殊字体可填入的内容
     *
     * @param data
     * @return
     */
    private String getSpecialStr(String data) {

        String str = "";
        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            str += chars[i];
            if (specialPaint.measureText(str) >= lastWidth) {
                if (i <= chars.length) {
                    str = data.substring(0, i);
                }
                break;
            }
        }

        return str;
    }

    /**
     * 获取剩余控件特殊字体可填入的内容
     *
     * @param data
     * @return
     */
    private String getOrdinaryStr(String data) {

        String str = "";
        char[] chars = data.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            str += chars[i];
            if (ordinaryPaint.measureText(str) >= lastWidth) {
                if (i <= chars.length) {
                    str = data.substring(0, i);
                }
                break;
            }
        }
        return str;
    }


    /**
     * 创建普通字体textview
     *
     * @param data
     */
    private void creatView(String data) {
        if (null == data || data.length() == 0) return;
        int width = (int) ordinaryPaint.measureText(data);
        ordinaryView = new TextView(this.context);
        ordinaryView.setTextSize(orTextSize);
        ordinaryView.setGravity(Gravity.CENTER);
        ordinaryView.setTextColor(getResources().getColor(orTextColor));
        ordinaryView.setWidth(width);
        ordinaryView.setText(data);
        addView(ordinaryView);
    }

    /**
     * 创建特殊字体textview
     *
     * @param data
     */
    private void creatSpView(String data) {
        if (null == data || data.length() == 0) return;
        int width = (int) specialPaint.measureText(data)+18;
        specialView = new TextView(this.context);
        specialView.setTextSize(spTextSize);
        specialView.setGravity(Gravity.CENTER);
        specialView.setTextColor(getResources().getColor(spTextColor));
        specialView.setBackgroundColor(getResources().getColor(R.color.black_d_color));
        specialView.setWidth(width);
        specialView.setHeight(maxLineHeight);
        specialView.setText(data);
        addView(specialView);
    }
}
