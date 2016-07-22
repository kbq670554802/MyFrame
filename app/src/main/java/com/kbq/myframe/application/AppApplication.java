package com.kbq.myframe.application;

import android.app.Application;

import com.kbq.myframe.model.api.module.ApiServiceModule;

/**
 * Created by KBQ on 16/7/19.
 * 自定义Application
 */
public class AppApplication extends Application {
    private AppComponent appComponent;
    private static AppApplication instance;

    public static AppApplication get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化appComponent
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
