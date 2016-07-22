package com.kbq.myframe.view.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.kbq.myframe.R;
import com.kbq.myframe.application.AppComponent;
import com.kbq.myframe.databinding.ActivityLoginBinding;
import com.kbq.myframe.model.LoginModel;
import com.kbq.myframe.model.api.ApiService;
import com.kbq.myframe.model.bean.IpInfo;
import com.kbq.myframe.model.bean.User;
import com.kbq.myframe.model.bean.view.LoginViewBean;
import com.kbq.myframe.presenter.LoginPresenter;
import com.kbq.myframe.view.activity.component.DaggerLoginActivityComponent;
import com.kbq.myframe.view.activity.module.LoginActivityModule;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity{
    private static final String TAG = "LoginActivity";
    @Inject
    User user;
    @Inject
    LoginPresenter loginPresenter;
    @Inject
    IpInfo ipInfo;
    @Inject
    ApiService apiService;
    @Inject
    LoginModel loginModel;
    @Inject
    Context context;
    @Inject
    LoginViewBean loginViewBean;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

//        loginBinding.setUser(user);
        loginBinding.setLoginViewBean(loginViewBean);
        loginBinding.setLoginPresenter(loginPresenter);
        loginBinding.setIpInfo(ipInfo);
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginActivityComponent.builder()
                .appComponent(appComponent)
                .loginActivityModule(new LoginActivityModule(this))
                .build()
                .inject(this);
    }
}
