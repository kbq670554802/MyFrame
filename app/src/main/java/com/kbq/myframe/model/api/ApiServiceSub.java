package com.kbq.myframe.model.api;

import com.kbq.myframe.model.response.IpInfoResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by KBQ on 16/7/15.
 * API类
 */
public interface ApiServiceSub {
    @GET("service/getIpInfo.php")
    Observable<IpInfoResponse> getIpInfo(@Query("ip") String ip);
}
