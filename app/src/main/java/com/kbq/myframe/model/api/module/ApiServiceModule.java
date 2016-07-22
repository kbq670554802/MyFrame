package com.kbq.myframe.model.api.module;

import android.util.Log;

import com.kbq.myframe.application.Constant;
import com.kbq.myframe.model.api.ApiService;
import com.kbq.myframe.view.activity.ActivityScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by KBQ on 16/7/20.
 */
@Module
public class ApiServiceModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(Constant.APISERVER_SUB)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}
