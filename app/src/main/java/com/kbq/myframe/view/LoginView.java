package com.kbq.myframe.view;

/**
 * Created by KBQ on 16/7/15.
 * 登录View的接口，实现类为对应登录界面的Activity
 */
public interface LoginView {
    void showProgress();
    void hideProgress();
    void userNameError();
    void passWordError();
    void loginSuccess();

}
