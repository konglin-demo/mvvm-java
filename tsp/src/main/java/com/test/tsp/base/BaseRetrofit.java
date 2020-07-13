package com.test.tsp.base;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 *  @auth : kongl
 *  @date : 2020/07/02
 *  @describtion : 结合okhttp3.0 + retrofit2.0进行网络请求
 *                 BaseRetrofit创建api对象的成员方法,继承{@link BaseRetrofitClient}获取
 *                 {@link OkHttpClient.Builder}对象
 */
public abstract class BaseRetrofit extends BaseRetrofitClient {
    private Retrofit mRetrofit;

    /**
     *  初始化retrofit对象
     */
    public void initRetrofit(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(getGsonConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initOkHttpClientBuilder().build())
                .build();
    }
    /**
     * 创建请求的api对象
     *
     * @param clz class_name.class
     * @param <U> class_name
     * @return    class object
     */
    public <U> U createApi( Class<U> clz ){
        return (U)mRetrofit.create(clz);
    }

    /**
     *  设置base url
     *
     * @return
     */
    protected abstract String getBaseUrl();

    /**
     *  设置 获取的gson,可以根据自己的服务端返回数据结构进行自己的转换
     * @return
     */
    protected abstract Converter.Factory getGsonConverterFactory();

}
