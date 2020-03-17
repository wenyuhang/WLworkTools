package com.hyperx.wlworktools.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hyperx.wlworktools.R;
import com.wl.uilib.utils.CacheUtils;
import com.wl.uilib.utils.EncryptUtil;
import com.wl.uilib.utils.L;
import com.wl.uilib.utils.StringUtil;
import com.wl.uilib.utils.T;

import org.reactivestreams.Subscriber;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Rxjava使用
 */
public class RxjavaActivity extends AppCompatActivity {

    private boolean flag;
    private String dataurl = "http://www.baidu.com";
    private String data = "{\n" +
            "    \"msg\": \"令牌验证失败\",\n" +
            "    \"code\": 401\n" +
            "}";
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        tvContent = findViewById(R.id.tv_content);
        tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.s(RxjavaActivity.this,"123567987");
            }
        });
        //在onCreate之后进行赋值
//        if (!flag){
//            boolean b = CacheUtils.WriteStringToFile("123456789", CacheUtils.getCacheDirPath(this) + EncryptUtil.getInstance().md5Encode(dataurl), false);
//            L.d(RxjavaActivity.class,"Write_Result："+b);
//            flag = true;
//        }else {
//            String s = CacheUtils.ReadStringFromFile(CacheUtils.getCacheDirPath(this) + EncryptUtil.getInstance().md5Encode(dataurl));
//            L.d(RxjavaActivity.class,"Read_Result："+s);
//            flag = false;
//        }
        test();
    }

    /**
     * 基础使用
     *
     * @param view
     */
    public void btn_run(View view) {
        // 步骤1：创建被观察者 Observable & 发送事件
        // 在主线程创建被观察者 Observable 对象
        // 所以生产事件的线程是：主线程
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                L.d(RxjavaActivity.class, "subscribe");
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onComplete();
            }
        });
        // 步骤2：创建观察者 Observer 并 定义响应事件行为
        // 在主线程创建观察者 Observer 对象
        // 所以接收 & 响应事件的线程是：主线程
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                L.d(RxjavaActivity.class, "onSubscribe");

            }

            @Override
            public void onNext(String s) {
                L.d(RxjavaActivity.class, s);
            }

            @Override
            public void onError(Throwable e) {
                L.d(RxjavaActivity.class, "onError");
            }

            @Override
            public void onComplete() {
                L.d(RxjavaActivity.class, "onComplete");
            }
        };

        // 步骤3：通过订阅（subscribe）连接观察者和被观察者
        observable.subscribe(observer);
    }

    /**
     * 线程调度
     *
     * @param view
     */
    public void btn_disp(View view) {
        // 使用说明
        // Observable.subscribeOn（Schedulers.Thread）：指定被观察者 发送事件的线程（传入RxJava内置的线程类型）
        // Observable.observeOn（Schedulers.Thread）：指定观察者 接收 & 响应事件的线程（传入RxJava内置的线程类型）

        // 实例使用
        // 步骤1：创建被观察者 Observable & 发送事件
        // 在主线程创建被观察者 Observable 对象
        // 所以生产事件的线程是：主线程
        Observable<Boolean> observable = Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                L.d(RxjavaActivity.class, "subscribe");
                boolean b = CacheUtils.WriteStringToFile(data, CacheUtils.getCacheDirPath(RxjavaActivity.this) + EncryptUtil.getInstance().md5Encode(dataurl), false);
                emitter.onNext(b);
                emitter.onComplete();
            }
        });
        // 步骤2：创建观察者 Observer 并 定义响应事件行为
        // 在主线程创建观察者 Observer 对象
        // 所以接收 & 响应事件的线程是：主线程
        Observer<Boolean> observer = new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
                L.d(RxjavaActivity.class, "onSubscribe");

            }

            @Override
            public void onNext(Boolean s) {
                L.d(RxjavaActivity.class, "====" + s);
            }

            @Override
            public void onError(Throwable e) {
                L.d(RxjavaActivity.class, "onError");
            }

            @Override
            public void onComplete() {
                L.d(RxjavaActivity.class, "onComplete");
            }
        };
        // 步骤3：通过订阅（subscribe）连接观察者和被观察者
        observable.subscribeOn(Schedulers.newThread()) // 1. 指定被观察者 生产事件的线程
                .observeOn(AndroidSchedulers.mainThread())  // 2. 指定观察者 接收 & 响应事件的线程
                .subscribe(observer); // 3. 最后再通过订阅（subscribe）连接观察者和被观察者

    }

    private void test() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                L.d(RxjavaActivity.class, "subscribe");
                int b = 0;
                for (int i = 0; i <10000 ; i++) {
                    CacheUtils.WriteStringToFile(data, CacheUtils.getCacheDirPath(RxjavaActivity.this) + EncryptUtil.getInstance().md5Encode(dataurl), false);
                    b = i;
                }
                emitter.onNext(String.valueOf(b));
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String aBoolean) {
                        L.d(RxjavaActivity.class,"==="+aBoolean);
//                        if (aBoolean){
//                            tvContent.setText("操作成功");
//                        }else {
//                            tvContent.setText("操作失败");
//                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
