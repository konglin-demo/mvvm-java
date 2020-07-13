package com.test.tsp.base;

import com.google.gson.annotations.SerializedName;


public class BaseResponseEntity<T> {

    @SerializedName("errorCode")
    private int mErrorCode;

    @SerializedName("errorMsg")
    private String mErrorMsg;

    @SerializedName("datas")
    private T mData;

    public int getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode( int errorCode ) {
        this.mErrorCode = errorCode;
    }

    public String getErrorMsg() {
        return mErrorMsg;
    }

    public void setErrorMsg( String errorMsg ) {
        this.mErrorMsg = errorMsg;
    }

    public T getData() {
        return mData;
    }

    public void setData( T data ) {
        this.mData = data;
    }
}
