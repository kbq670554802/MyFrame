package com.kbq.myframe.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kbq.myframe.R;
import com.kbq.myframe.databinding.ActivityLoginBinding;
import com.kbq.myframe.model.bean.IpInfo;
import com.kbq.myframe.model.bean.User;
import com.kbq.myframe.presenter.LoginPresenter;
import com.kbq.myframe.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView{
    private static final String TAG = "LoginActivity";
    private User user;
    private LoginPresenter loginPresenter;
    private IpInfo ipInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        ActivityLoginBinding loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        user = new User();
        ipInfo = new IpInfo();
        loginPresenter = new LoginPresenter(this,user,ipInfo);


        loginBinding.setUser(user);
        loginBinding.setLoginPresenter(loginPresenter);
        loginBinding.setIpInfo(ipInfo);
    }

    @Override
    public void showProgress() {
        Log.i(TAG, "showProgress: ");
    }

    @Override
    public void hideProgress() {
        Log.i(TAG, "hideProgress: ");
    }

    @Override
    public void userNameError() {
        Log.i(TAG, "userNameError: ");
    }

    @Override
    public void passWordError() {
        Log.i(TAG, "passWordError: ");
    }

    @Override
    public void loginSuccess() {
        Log.i(TAG, "loginSuccess: ");
    }
}
