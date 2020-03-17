package com.hyperx.wlworktools.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wl.uilib.utils.L;

import java.util.ArrayList;

/**
 * Created by WYH
 * on2020/3/9
 */
public class CusRectView extends View{

    private Paint paint;
    private ArrayList<CoorBean> rectList = new ArrayList<CoorBean>();

    public CusRectView(Context context) {
        super(context);
        init();
    }

    public CusRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CusRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
    }


    public void upDataDraw(ArrayList<CoorBean> list){
        if (list!=null&&list.size()>0){
            rectList =  list;
            postInvalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i <rectList.size(); i++) {
            CoorBean coorBean = rectList.get(i);

            L.d(CusRectView.class,coorBean.getX()+"==="+coorBean.getY()+"==="+(coorBean.getX()+coorBean.getWidth())+"==="+(coorBean.getY()+coorBean.getHeight()));
            RectF rect = new RectF( coorBean.getX(), coorBean.getY(),coorBean.getX()+coorBean.getWidth(),coorBean.getY()+coorBean.getHeight());
            canvas.drawRect(rect,paint);
            canvas.drawText(coorBean.getName(),coorBean.getX()+(coorBean.getWidth()/2),coorBean.getY()+(coorBean.getHeight()/2),paint);
        }

    }
}
