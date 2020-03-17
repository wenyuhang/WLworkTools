package com.wl.uilib.design;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.wl.uilib.R;


/**
 * create by wyh on 2018/9/28
 */

public class CusDialog implements View.OnClickListener{
    private static CusDialog showDialog;
    private View view;
    private boolean isOnClickDismiss = true;  //是否点击后直接关闭
    private boolean isAnim;     //是否开启动画
    private int styleId;        //进出场动画
    private int[] listenedItems = new int[]{};  // 要监听的控件id
    private OnDialogClickListener listener;
    public interface OnDialogClickListener {
        void onDialogClick(View view);
    }

    public CusDialog setOnClickDismiss(boolean isOnClickDismiss) {
        this.isOnClickDismiss= isOnClickDismiss;
        return showDialog;
    }
    public void setOnDialogClickListener(int[] ids,OnDialogClickListener listener) {
        this.listenedItems = ids;
        this.listener = listener;
        //遍历控件id,添加点击事件
        for (int id : listenedItems) {
            if(view!=null) {
                view.findViewById(id).setOnClickListener(this);
            }
        }
    }

    public CusDialog setAnim(boolean anim,int styleId) {
        isAnim = anim;
        this.styleId = styleId;
        return showDialog;
    }

    private CusDialog() {
    }

    private Dialog loadDialog;

    public static CusDialog getInsent() {
//        if (showDialog == null) {
//            synchronized (CustomDialog.class) {
//                if (showDialog == null) {
                    showDialog = new CusDialog();
//                }
//            }
//        }
        return showDialog;
    }

    /**
     * 展示Dialog
     * @param context
     * @return
     */
    public CusDialog show(Context context, int layoutId) {
        loadDialog = new Dialog(context, R.style.dialog);
        loadDialog.setCanceledOnTouchOutside(false);
        view = View.inflate(context, layoutId, null);
        loadDialog.setContentView(view);
        WindowManager windowManager = loadDialog.getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = loadDialog.getWindow().getAttributes();
        if(isAnim) {
            loadDialog.getWindow().setWindowAnimations(styleId);
        }
//        lp.width = display.getWidth()*4/5; // 设置dialog宽度为屏幕的4/5
        lp.width = display.getWidth();
        lp.height = display.getHeight()*9/10;
//        loadDialog.getWindow().setAttributes(lp);
        loadDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        loadDialog.show();

        return this;
    }

    /**
     * 获取view
     * @param id
     * @return
     */
    public View getView(int id){
        if(view!=null) {
            View childView = this.view.findViewById(id);
            return childView;
        }
        return null;
    }

    /**
     * 销毁Dialog
     * @return
     */
    public CusDialog dismiss() {

//        loadDialog.dismiss();
        loadDialog.cancel();
        return this;
    }


    /**
     * 设置dialog 关闭监听
     * @param listener
     */
    public void setDismissListener( DialogInterface.OnDismissListener listener){
        loadDialog.setOnDismissListener(listener);
    }

    @Override
    public void onClick(View view) {
        if (isOnClickDismiss){
            dismiss();
        }
        listener.onDialogClick(view);
    }
}
