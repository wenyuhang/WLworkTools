package com.hyperx.wlworktools.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hyperx.wlworktools.R;
import com.wl.uilib.utils.ClickUtils;
import com.wl.uilib.utils.T;

/**
 * 防止重复点击的
 */
public class PreventDoubleClickActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevent);
        findViewById(R.id.btn_double_click).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (ClickUtils.getInstance().preventFun(v.getId()))return;
        switch (v.getId()){
            case R.id.btn_double_click:
                T.s(PreventDoubleClickActivity.this,"点击成功");
                break;
        }
    }




}
