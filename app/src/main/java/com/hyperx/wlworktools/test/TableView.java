package com.hyperx.wlworktools.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by WYH
 * on2020/3/13
 */
public class TableView extends View{

    private Paint paint;

    public TableView(Context context) {
        super(context);
        init();
    }

    public TableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int view_height = getHeight();
        int view_width = getWidth();
        int table_width = getHeight()/10;
        for (int i = 0; i <11 ; i++) {
            canvas.drawLine(table_width*i,0,table_width*i,view_height,paint);
            canvas.drawLine(0,table_width*i,view_width,table_width*i,paint);
        }

    }
}
