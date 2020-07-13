package com.test.tsp.api.test.retrofit;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.test.tsp.base.BaseResponseEntity;
import com.test.tsp.base.BaseRetrofit;
import com.test.tsp.base.convert.CustomGsonConvertFactory;

import java.util.List;

import okhttp3.Interceptor;
import retrofit2.Converter;

/**
 *  auth : kongl
 *  date : 2020/04/23
 *  describtion : 网络请求框架{@link TestRetrofit} 中 getInterceptors定义自己应用的拦截器
 *                getGsonConverterFactory 自定义转换的bean
 */
public class TestRetrofit extends BaseRetrofit {
    @Override
    public String getBaseUrl() {
        return "https://www.wanandroid.com/";
    }

    @Override
    protected List<Interceptor> getInterceptors() {
        return null;
    }

    @Override
    protected Converter.Factory getGsonConverterFactory() {
//        return GsonConverterFactory.create(new GsonBuilder().registerTypeAdapter(BaseResponseEntity.class,getJsonDeserializer()).create());
        return CustomGsonConvertFactory.create();
    }

    /**
     *  可以使用 return GsonConverterFactory.create(new GsonBuilder()
     *  .registerTypeAdapter(BaseResponseEntity.class,getJsonDeserializer()).create());来进行json的数据串
     *  前提是data的格式是统一的。
     */
    private JsonDeserializer<BaseResponseEntity> getJsonDeserializer(){
        return ( json, typeOfT, context ) -> new Gson().fromJson(json,BaseResponseEntity.class);
    }
}
