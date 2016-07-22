package com.kbq.myframe.presenter;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.kbq.myframe.application.AppApplication;
import com.kbq.myframe.model.LoginModel;
import com.kbq.myframe.model.bean.IpInfo;
import com.kbq.myframe.model.bean.User;
import com.kbq.myframe.model.bean.view.LoginViewBean;
import com.kbq.myframe.model.response.IpInfoResponse;
import com.kbq.myframe.utils.RetrofitUtils;
import com.kbq.myframe.view.LoginView;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by KBQ on 16/7/15.
 * 用户登录Presenter实现类
 */
public class LoginPresenter {
    private static final String TAG = "LoginPresenter";
    private User user;
    private IpInfo ipInfo;
    private LoginModel loginModel;
    private LoginViewBean loginViewBean;

    public LoginPresenter(LoginViewBean loginViewBean, IpInfo ipInfo, LoginModel loginModel) {
        this.loginViewBean = loginViewBean;
        this.ipInfo = ipInfo;
        this.loginModel = loginModel;
    }

    public void login(View view) {
        loginModel.login(this.loginViewBean);
    }

    public void getIpInfo(View view){
        loginModel.getIpInfo(ipInfo);
    }
}
