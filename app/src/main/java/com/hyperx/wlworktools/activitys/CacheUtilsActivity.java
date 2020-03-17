package com.hyperx.wlworktools.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hyperx.wlworktools.R;
import com.wl.uilib.utils.CacheUtils;
import com.wl.uilib.utils.DataCleanManager;
import com.wl.uilib.utils.L;

public class CacheUtilsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_utils);
        String cacheDirPath = CacheUtils.getCacheDirPath(this);
        L.d(CacheUtilsActivity.class,cacheDirPath);
    }

    public void btn_clear_cache(View view){
        try {
            String cacheSize = DataCleanManager.getTotalCacheSize(this);
            L.d(CacheUtilsActivity.class,"cacheSize=="+cacheSize);
            DataCleanManager.clearAllCache(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
