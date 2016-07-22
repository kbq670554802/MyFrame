package com.kbq.myframe.application;

import android.app.Application;
import android.content.Context;

import com.kbq.myframe.model.BaseModel;
import com.kbq.myframe.model.LoginModel;
import com.kbq.myframe.model.api.ApiService;
import com.kbq.myframe.model.api.module.ApiServiceModule;
import com.kbq.myframe.model.bean.IpInfo;
import com.kbq.myframe.model.bean.User;
import com.kbq.myframe.presenter.LoginPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by KBQ on 16/7/19.
 */
@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface AppComponent {
    void inject(AppApplication appApplication);
    void inject(LoginPresenter appApplication);

    Application getApplication();
    Context getContext();

    User getUser();
    IpInfo getIpInfo();

    ApiService getApiService();

}
