package com.hyperx.wlworktools.activitys;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.azhon.appupdate.config.UpdateConfiguration;
import com.azhon.appupdate.listener.OnButtonClickListener;
import com.azhon.appupdate.listener.OnDownloadListener;
import com.azhon.appupdate.manager.DownloadManager;
import com.hyperx.wlworktools.R;

import java.io.File;


/**
 * 版本更新
 */
public class UpdateActivity extends AppCompatActivity implements OnButtonClickListener {


    private String url = "https://f29addac654be01c67d351d1b4282d53.dd.cdntips.com/imtt.dd.qq.com/16891/DC501F04BBAA458C9DC33008EFED5E7F.apk?mkey=5d6d132d73c4febb&f=0c2f&fsname=com.estrongs.android.pop_4.2.0.2.1_10027.apk&csr=1bbd&cip=115.196.216.78&proto=https";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }

    public void btn_update(View view){
        startUpdate();
    }

    private void startUpdate() {
        /*
         * 整个库允许配置的内容
         * 非必选
         */
        UpdateConfiguration configuration = new UpdateConfiguration()
                //输出错误日志
                .setEnableLog(true)
                //设置自定义的下载
                //.setHttpManager()
                //下载完成自动跳动安装页面
                .setJumpInstallPage(true)
                //设置对话框背景图片 (图片规范参照demo中的示例图)
                //.setDialogImage(R.drawable.ic_dialog)
                //设置按钮的颜色
                //.setDialogButtonColor(Color.parseColor("#E743DA"))
                //设置按钮的文字颜色
                .setDialogButtonTextColor(Color.WHITE)
                //设置是否显示通知栏进度
                .setShowNotification(true)
                //设置是否提示后台下载toast
                .setShowBgdToast(false)
                //设置强制更新
                .setForcedUpgrade(false)
                //设置对话框按钮的点击监听
                .setButtonClickListener(this)
                //设置下载过程的监听
                .setOnDownloadListener(new OnDownloadListener() {
                    @Override
                    public void start() {

                    }

                    @Override
                    public void downloading(int max, int progress) {

                    }

                    @Override
                    public void done(File apk) {

                    }

                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void error(Exception e) {

                    }
                });

        DownloadManager manager = DownloadManager.getInstance(this);
        manager.setApkName("hyperx.apk")
                .setApkUrl(url)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setShowNewerToast(true)
                .setConfiguration(configuration)
//                .setDownloadPath(Environment.getExternalStorageDirectory() + "/AppUpdate")
                .setApkVersionCode(3)
                .setApkVersionName("1.0.1")
                .setApkSize("28.5")
                .setAuthorities(getPackageName())
                .setApkDescription("1.支持断点下载/\n2.支持Android N/\n3.支持Android O/\n4.支持Android P/\n5.支持自定义下载过程/\n6.支持 设备>=Android M 动态权限的申请/\n7.支持通知栏进度条展示")
                .download();
    }

    @Override
    public void onButtonClick(int id) {

    }
}
