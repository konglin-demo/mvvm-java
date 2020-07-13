package com.test.tsp.base;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author kongl
 * @date 2020/7/2
 * @describtion 多个应用拥有不同的拦截器，采用继承复用的方式，将共通的拦截器写在此类中实现，
 *              通过继承,实现自己应用的拦截器。
 */
public class CommonInterceptor {
    /**
     * 日志拦截器
     *
     * @return 日志拦截器
     */
    public static HttpLoggingInterceptor logInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
