package com.hyperx.wlworktools.activitys;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyperx.wlworktools.R;
import com.hyperx.wlworktools.test.CoorBean;
import com.hyperx.wlworktools.test.CusRectView;
import com.hyperx.wlworktools.test.LabelLayout;
import com.hyperx.wlworktools.test.TestAdapter;
import com.wl.uilib.utils.L;

import java.util.ArrayList;

public class CusGroupActivity extends AppCompatActivity {

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_group);
        final LabelLayout group_view = findViewById(R.id.group_view);
//        String data = "为了保证大家的学习效果，请将屏幕调成全屏，/#windows 系统：请按 F11（或 Fn + F11)；Mac 系统：请同时按 control + command + F#/。";
        String data = "/#Python#/是一种/#入门快#/、/#功能强大#/、/#高效灵活#/的编程语言，学会之后无论是想进入/#数据分析#/、/#人工智能#/、/#网站开发#/、/#网络安全#/、/#集群运维#/这些领域，还是希望掌握第一门编程语言，都可以用 Python 来开启美好未来的无限可能！";
        group_view.upData(data);

        setAdapter();

        CusRectView viewById = findViewById(R.id.rect_view);
        ArrayList<CoorBean> coorBeans = new ArrayList<>();
        coorBeans.add(new CoorBean(20,20,100,100,"9"));
        coorBeans.add(new CoorBean(50,50,100,100,"10"));
        viewById.upDataDraw(coorBeans);
    }

    private void setAdapter() {
        RecyclerView recy_view = findViewById(R.id.recy_view);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("为了保证大家的学习效果，请将屏幕调成全屏，/#windows 系统：请按 F11（或 Fn + F11)；Mac 系统：请同时按 control + command + F#/。");
            list.add("/#Python#/是一种/#入门快#/、/#功能强大#/、/#高效灵活#/的编程语言，学会之后无论是想进入/#数据分析#/、/#人工智能#/、/#网站开发#/、/#网络安全#/、/#集群运维#/这些领域，还是希望掌握第一门编程语言，都可以用 Python 来开启美好未来的无限可能！");
        }
        TestAdapter testAdapter = new TestAdapter(this, list);
        recy_view.setLayoutManager(new LinearLayoutManager(this));
        recy_view.setAdapter(testAdapter);
    }


}
