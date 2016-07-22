package com.kbq.myframe.application;

import android.app.Application;
import android.content.Context;

import com.kbq.myframe.model.LoginModel;
import com.kbq.myframe.model.api.module.ApiServiceModule;

/**
 * Created by KBQ on 16/7/19.
 */
public class AppApplication extends Application {
    private AppComponent appComponent;
    private static AppApplication instance;

//    public static AppApplication get(Context context) {
//        return (AppApplication) context.getApplicationContext();
//    }
    public static AppApplication get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
