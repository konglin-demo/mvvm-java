package com.test.tsp.base.convert;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 *  @auth : kongl
 *  @date : 2020/04/23
 *  @describtion : 自定义retrofit gson转换
 *                 由于返回的data Json 串的格式可能出现不一致的效果，可以使用自定义的json 串的方式统一处理data的格式
 */
public class CustomGsonConvertFactory extends Converter.Factory {
    private final Gson mgson;
    public static CustomGsonConvertFactory create(){
        return create(new Gson());
    }

    public static CustomGsonConvertFactory create(Gson gson){
        return new CustomGsonConvertFactory(gson);
    }

    private CustomGsonConvertFactory(Gson gson){
        if (null == gson){
            throw new NullPointerException("gosn is null");
        }
        this.mgson = gson;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter( Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit ) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter( Type type, Annotation[] annotations, Retrofit retrofit ) {
        TypeAdapter<?> adapter = mgson.getAdapter(TypeToken.get(type));

        return new CustomResponseConvert<>(mgson, adapter, type);
//        return super.responseBodyConverter(type, annotations, retrofit);
    }
}
