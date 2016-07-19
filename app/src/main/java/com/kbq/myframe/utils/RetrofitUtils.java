package com.kbq.myframe.utils;

import com.kbq.myframe.app.Constant;
import com.kbq.myframe.model.api.ApiServiceSub;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by KBQ on 16/7/18.
 * Http工具类
 */
public class RetrofitUtils {
    private Retrofit retrofitSub;
    public ApiServiceSub apiServiceSub;

    public static RetrofitUtils getInstance(){
        return SingletonHolder.instance;
    }

    /**
     * 私有的构造函数
     */
    private RetrofitUtils(){
        this.retrofitSub = new Retrofit.Builder()
                .baseUrl(Constant.APISERVER_SUB)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        this.apiServiceSub = retrofitSub.create(ApiServiceSub.class);
    }

    /**
     * 内部类实现单例模式
     * 延迟加载,减少内存开销
     */
    private static class SingletonHolder{
        private static RetrofitUtils instance = new RetrofitUtils();
    }

}
