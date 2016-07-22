package com.kbq.myframe.view.activity.module;

import com.kbq.myframe.model.LoginModel;
import com.kbq.myframe.model.api.ApiService;
import com.kbq.myframe.model.bean.IpInfo;
import com.kbq.myframe.model.bean.User;
import com.kbq.myframe.model.bean.view.LoginViewBean;
import com.kbq.myframe.presenter.LoginPresenter;
import com.kbq.myframe.view.activity.ActivityScope;
import com.kbq.myframe.view.activity.LoginActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by KBQ on 16/7/19.
 * LoginActivityModule
 */
@Module
public class LoginActivityModule {

    private LoginActivity loginActivity;

    public LoginActivityModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    @Provides
    @ActivityScope
    LoginActivity provideLoginActivity() {
        return loginActivity;
    }

    @Provides
    @ActivityScope
    public LoginPresenter provideLoginPresenter(LoginViewBean loginViewBean, IpInfo ipInfo, LoginModel loginModel) {
        return new LoginPresenter(loginViewBean, ipInfo, loginModel);
    }

    @Provides
    @ActivityScope
    public LoginModel provideLoginModel(ApiService apiService) {
        return new LoginModel(apiService);
    }

    @Provides
    @ActivityScope
    public LoginViewBean provideLoginViewBean(User user){
        return new LoginViewBean(user);
    }
}
