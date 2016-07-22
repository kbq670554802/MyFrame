package com.kbq.myframe.model;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.kbq.myframe.model.api.ApiService;
import com.kbq.myframe.model.bean.IpInfo;
import com.kbq.myframe.model.bean.view.LoginViewBean;
import com.kbq.myframe.model.response.IpInfoResponse;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by KBQ on 16/7/19.
 * 用户登录业务处理类
 */
public class LoginModel {
    private static final String TAG = "LoginModel";
    private ApiService apiService;

    public LoginModel(ApiService apiService) {
        this.apiService = apiService;
    }

    /**
     * 模拟登陆
     */
    public void login(final LoginViewBean loginViewBean) {
        loginViewBean.setShowProgress(true);
        Log.i(TAG, "用户名：" + loginViewBean.getUser().getUserName() + "  密码:" + loginViewBean.getUser().getPassWord());
        new Handler().postDelayed(new Thread() {
            @Override
            public void run() {
                boolean error = false;
                String message = "键入信息:" + loginViewBean.getUser().getUserName() + "_" + loginViewBean.getUser().getPassWord();
                if (TextUtils.isEmpty(loginViewBean.getUser().getUserName())) {
                    error = true;
                    message += "   用户名错误!";
                } else if (TextUtils.isEmpty(loginViewBean.getUser().getPassWord())) {
                    error = true;
                    message += "   密码错误!";
                } else if (!error) {
                    message += "   登录成功!";
                    loginViewBean.setLoginSuccess(true);
                }
                loginViewBean.setMessage(message);
                loginViewBean.setShowProgress(false);
            }
        }, 2000);
    }

    /**
     * 根据IP查询IP基本信息
     */
    public void getIpInfo(final IpInfo ipInfo) {
        apiService.getIpInfo(ipInfo.getIp())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IpInfoResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(IpInfoResponse ipInfoResponse) {
                        ipInfo.setCountry(ipInfoResponse.data.country);
//                        ipInfo = ipInfoResponse.data;
                        Log.i(TAG, "onNext: " + ipInfoResponse.data.ip);
                    }
                });
    }
}
