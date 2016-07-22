package com.kbq.myframe.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.kbq.myframe.application.AppApplication;
import com.kbq.myframe.application.AppComponent;
import com.kbq.myframe.model.LoginModel;

/**
 * Created by KBQ on 16/7/19.
 * BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(AppApplication.get().getAppComponent());
    }

    /**
     * 设置Component的其他依赖
     * @param appComponent 全局的Component
     */
    protected abstract void setupActivityComponent(AppComponent appComponent);


}
