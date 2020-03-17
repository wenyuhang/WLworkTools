package com.wl.uilib.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;


/**
 * Created by WYH on 2017/9/21.
 * Toast管理
*/

public class T {
    public static void s(Context context,String msg){
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM,0,300);
        toast.show();
    }
    public static void l(Context context,String msg){
        Toast.makeText(context,msg, Toast.LENGTH_LONG).show();
    }
}
