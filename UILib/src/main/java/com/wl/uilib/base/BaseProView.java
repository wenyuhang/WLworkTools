package com.wl.uilib.base;


/**
 * Created by wyh on 2017/9/21.
 */

public interface BaseProView<T> {
    void setPresenter(T t);
    void showMsg(String msg);
    void showProgress();
    void cancelProgress();
}
