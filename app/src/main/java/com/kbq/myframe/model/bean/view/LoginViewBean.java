package com.kbq.myframe.model.bean.view;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.kbq.myframe.BR;
import com.kbq.myframe.model.bean.User;

/**
 * Created by KBQ on 16/7/22.
 * 登录界面所有熟悉的实体类
 */
public class LoginViewBean extends BaseObservable{
    private User user;
    private boolean isShowProgress = false;
    private String message;
    private boolean isLoginSuccess = false;

    public LoginViewBean(User user) {
        this.user = user;
    }

    @Bindable
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        notifyPropertyChanged(BR.user);
    }
    @Bindable
    public boolean isShowProgress() {
        return isShowProgress;
    }

    public void setShowProgress(boolean showProgress) {
        isShowProgress = showProgress;
        notifyPropertyChanged(BR.showProgress);
    }
    @Bindable
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }
    @Bindable
    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
        notifyPropertyChanged(BR.loginSuccess);
    }
}
