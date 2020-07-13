package com.test.tsp.base;

public interface IResponseListener {
    void onRequestStart();
    void onSuccess(String data);
    void onError(String errorMsg);
    void onRequestEnd();
}
