package com.app.goldendays_android.application;

import android.app.Application;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by 王立强 on 2016/11/7.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        //初始化
        OkGo.init(this);

        try {
            //全局参数设置
            OkGo.getInstance()


                    //设置超时时间，默认60秒
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)
                    //设置全局缓存，默认无缓存
                    .setCacheMode(CacheMode.NO_CACHE)
                    //设置全局缓存时间，默认永不过期
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}