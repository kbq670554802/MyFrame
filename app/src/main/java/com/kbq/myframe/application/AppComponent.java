package com.kbq.myframe.application;

import android.app.Application;
import android.content.Context;

import com.kbq.myframe.model.api.ApiService;
import com.kbq.myframe.model.api.module.ApiServiceModule;
import com.kbq.myframe.model.bean.IpInfo;
import com.kbq.myframe.model.bean.User;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by KBQ on 16/7/19.
 * 全局的Component
 */
@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface AppComponent {
//  inject声明可以注入到哪个类中(可以多个，只要参数不同)
//    void inject(AppApplication appApplication);
//    void inject(LoginActivity loginActivity);

    Application getApplication();
    Context getContext();
    User getUser();
    IpInfo getIpInfo();
    ApiService getApiService();

}
