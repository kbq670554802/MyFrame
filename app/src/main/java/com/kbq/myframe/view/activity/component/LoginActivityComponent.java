package com.kbq.myframe.view.activity.component;

import com.kbq.myframe.application.AppComponent;
import com.kbq.myframe.view.activity.ActivityScope;
import com.kbq.myframe.view.activity.LoginActivity;
import com.kbq.myframe.view.activity.module.LoginActivityModule;

import dagger.Component;

/**
 * Created by KBQ on 16/7/19.
 */
@ActivityScope
@Component(modules = LoginActivityModule.class,dependencies = AppComponent.class)
public interface LoginActivityComponent {
    void inject(LoginActivity loginActivity);
}
