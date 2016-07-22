package com.kbq.myframe.application;

import android.app.Application;
import android.content.Context;

import com.kbq.myframe.model.bean.IpInfo;
import com.kbq.myframe.model.bean.User;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KBQ on 16/7/19.
 * 全局的Module
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    User provideUser() {
        User user = new User();
        user.setUserName("kbq");
        return user;
    }

    @Provides
    @Singleton
    IpInfo provideIpInfo() {
        IpInfo ipInfo = new IpInfo();
        ipInfo.setIp("23.45.6.7");
        return ipInfo;
    }
}
