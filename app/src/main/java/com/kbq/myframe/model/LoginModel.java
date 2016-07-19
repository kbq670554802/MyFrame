package com.kbq.myframe.model;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.kbq.myframe.model.bean.IpInfo;
import com.kbq.myframe.model.bean.User;
import com.kbq.myframe.model.response.IpInfoResponse;
import com.kbq.myframe.utils.RetrofitUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by KBQ on 16/7/19.
 */
public class LoginModel {
    private static final String TAG = "LoginModel";


    /**
     * 模拟登陆
     */
    public void login(final User user) {
        user.setShowProgress(true);
        Log.i(TAG, "用户名：" + user.getUserName() + "  密码:" + user.getPassWord());
        new Handler().postDelayed(new Thread() {
            @Override
            public void run() {
                boolean error = false;
                String message = "键入信息:" + user.getUserName() + "_" + user.getPassWord();
                if (TextUtils.isEmpty(user.getUserName())) {
                    error = true;
                    message += "   用户名错误!";
                } else if (TextUtils.isEmpty(user.getPassWord())) {
                    error = true;
                    message += "   密码错误!";
                } else if (!error) {
                    message += "   登录成功!";
                    user.setLoginSuccess(true);
                }
                user.setMessage(message);
                user.setShowProgress(false);
            }
        }, 2000);
    }

    /**
     * 根据IP查询IP基本信息
     */
    public void getIpInfo(final IpInfo ipInfo) {
        RetrofitUtils.getInstance().apiServiceSub.getIpInfo(ipInfo.getIp())
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
