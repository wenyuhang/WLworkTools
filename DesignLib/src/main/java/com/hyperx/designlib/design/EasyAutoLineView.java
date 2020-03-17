package com.hyperx.designlib.design;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wyh on 2019/3/4
 * 自定义标签
 */
public class EasyAutoLineView extends ViewGroup {

    public EasyAutoLineView(Context context) {
        super(context);
        init(context);
    }

    public EasyAutoLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EasyAutoLineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context);
    }



    private void init(Context context){
        childViewList = new ArrayList<List<View>>();
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);  //获取系统测量的宽度数据
        int widthMode = MeasureSpec.getMode(widthMeasureSpec); //获取XML中宽度的模式  一共有三种模式

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);//同上
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int lineHeight = 0 ;
        int lineWidth = 0 ;

        int width = 0 ;
        int height = 0 ;

        int childCount = getChildCount();


        // 对子孩子进行循环，测量每个子孩子的宽高，同时计算父布局一行能够放多少个子控件
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();

            int childWidth = childView.getMeasuredWidth() + params.leftMargin + params.rightMargin ;

            int childHeight  = childView.getMeasuredHeight() + params.topMargin + params.bottomMargin ;

            if ((lineWidth + childWidth ) > widthSize - getPaddingLeft() - getPaddingRight() ) {
                width = Math.max(width, lineWidth);
                lineWidth = childWidth ;
                height += lineHeight ;
                lineHeight = childHeight;
            }else {
                lineWidth += childWidth ;
                lineHeight = Math.max(lineHeight, childHeight);
            }

            if (i  == childCount-1) {
                width = Math.max(width, lineWidth);
                height += lineHeight ;
            }
        }

        height += getPaddingTop() + getPaddingBottom() ;

        setMeasuredDimension(
                widthMode == MeasureSpec.EXACTLY?widthSize:width,
                heightMode == MeasureSpec.EXACTLY?heightSize:height); // 设置布局的宽高
    }


    private List<List<View>> childViewList ;
    private List<Integer> lineHeight = new ArrayList<Integer>() ;


    @Override
    protected void onLayout(boolean a, int l, int t, int r, int b) {

        childViewList.clear();

        int childCount = getChildCount() ;
        int width = getWidth();
        int lineWidth = 0 ;
        int lineHeight = 0 ;

        List<View> lineViews = new ArrayList<View>();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();

            int childWidth = childView.getMeasuredWidth() + params.leftMargin + params.rightMargin ;
            int childHeight = childView.getMeasuredHeight() + params.topMargin +  params.bottomMargin  ;

            if (lineWidth + childWidth > width - getPaddingLeft() - getPaddingRight()) {

                childViewList.add(lineViews);
                lineViews = new ArrayList<View>();

                if (i == 0 ) {
                    lineHeight += getPaddingTop() ;
                }else if (i== childCount - 1) {
                    lineHeight += getPaddingBottom() ;
                }
                this.lineHeight.add(lineHeight);

                lineHeight = 0 ;
                lineWidth = 0 ;
            }

            lineWidth += childWidth;
            lineHeight = Math.max(lineHeight, childHeight) ;

            lineViews.add(childView);
        }

        childViewList.add(lineViews);
        this.lineHeight.add(lineHeight);

        int left = getPaddingLeft() ;
        int top = getPaddingTop();

        for (int i = 0; i < childViewList.size(); i++) {
            lineViews = childViewList.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View childView = lineViews.get(j);

                MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();

                int lc = left + params.leftMargin ;
                int tc = top + params.topMargin ;
                int rc = lc + childView.getMeasuredWidth()  ;
                int bc = tc + childView.getMeasuredHeight() ;

                childView.layout(lc,tc,rc,bc);

                left += params.leftMargin + childView.getMeasuredWidth() + params.rightMargin ;
            }

            left =  getPaddingLeft() ;
            top += this.lineHeight.get(i) ;
        }

    }


    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }


}