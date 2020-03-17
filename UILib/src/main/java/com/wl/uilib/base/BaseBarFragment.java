package com.wl.uilib.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.SimpleImmersionFragment;
import com.wl.uilib.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wyh on 2017/9/21.
 */

public abstract class BaseBarFragment extends SimpleImmersionFragment {
    protected Bundle bundle;
    private Unbinder bind;

    protected String mTag = this.getClass().getSimpleName();

    protected Activity mActivity;
    protected View mRootView;


    Toolbar toolbar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), null);
        bind = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        toolbar = view.findViewById(R.id.toolbar);
//        ImmersionBar.setTitleBar(mActivity, toolbar);
//        init(view,savedInstanceState);
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).keyboardEnable(true).init();
    }


    /**
     * 加载布局
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     * @param view
     */
    protected abstract void init(View view,Bundle savedInstanceState);


    /**
     * 加载数据
     */
    protected  void loadData(){};


    /**
     * 该方法在Fragment可见时调用，可以在该方法中刷新数据
     */
    protected void onShow(){}

    /**
     * 该方法在Fragment隐藏时调用，可以做数据保存
     */
    protected void onHidden(){}


    /**
     * 当Fragment发生变化是调用 true为隐藏
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            onHidden();
        }else {
            onShow();
        }
    }

    public void setBundle(Bundle bundle){
        this.bundle = bundle;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
}
