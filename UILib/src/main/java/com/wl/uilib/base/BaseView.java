package com.wl.uilib.base;


/**
 * Created by wyh on 2017/9/21.
 */

public interface BaseView<T> {
    void setPresenter(T t);
    void onFailed(String msg);

}
