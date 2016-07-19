package com.kbq.myframe.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KBQ on 16/7/15.
 * 请求响应类基类
 * 第一层信息，包括 返回码，返回信息等
 */
public class BaseResponse {
    @SerializedName("code")
    public int code;
    @SerializedName("Message")
    public String message;
    @SerializedName("ErrorInfo")
    public String errorInfo;
}
