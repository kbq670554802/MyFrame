package com.kbq.myframe.model.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.kbq.myframe.BR;

/**
 * Created by KBQ on 16/7/15.
 * 登录用户实体类
 */
public class User extends BaseObservable {
    private int id;
    private String userName;
    private String passWord;
    private boolean isShowProgress = false;
    private String message;
    private boolean isLoginSuccess = false;

    @Bindable
    public int getId() {
        return id;
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    @Bindable
    public String getPassWord() {
        return passWord;
    }
    @Bindable
    public boolean isShowProgress() {
        return isShowProgress;
    }
@Bindable
    public String getMessage() {
        return message;
    }

    @Bindable public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
        notifyPropertyChanged(BR.passWord);
    }

    public void setShowProgress(boolean showProgress) {
        isShowProgress = showProgress;
        notifyPropertyChanged(BR.showProgress);
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }

    public void setLoginSuccess(boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
        notifyPropertyChanged(BR.loginSuccess);
    }
}
