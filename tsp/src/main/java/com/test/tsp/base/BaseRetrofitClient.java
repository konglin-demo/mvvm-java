package com.test.tsp.base;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 *  @auth : kongl
 *  @date : 2020/07/02
 *  @describtion : 网络请求框架 okHttp3.0
 *                 自定义拦截器
 */
public abstract class BaseRetrofitClient {
    private static final long READ_TIME_OUT = 20;
    private static final long WRITE_TIME_OUT = 5;
    private static final long CONNECT_TIME_OUT = 15;

    protected OkHttpClient.Builder initOkHttpClientBuilder() {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        okhttpBuilder.addInterceptor(CommonInterceptor.logInterceptor())
                .retryOnConnectionFailure(true)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);

        // todo 做缓存处理
        if (null != getInterceptors()) {
            if (!getInterceptors().isEmpty()) {
                for (Interceptor interceptor : getInterceptors()) {
                    if (null == interceptor) {
                        continue;
                    }
                    okhttpBuilder.addInterceptor(interceptor);
                }
            }
        }
        return okhttpBuilder;
    }

    /**
     *  设置拦截器
     * @return
     */
    protected abstract List<Interceptor> getInterceptors();
}
