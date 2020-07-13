package com.test.tsp.base.convert;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.test.tsp.base.BaseResponseEntity;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 *  @auth : kongl
 *  @date : 2020/04/23
 *  @describtion : 自定义retrofit gson转换，由于返回的data Json 串的格式可能出现不一致的效果
 *                  ，可以使用自定义的json 串的方式统一处理data的格式，方案则是用
 *                 {@link CustomResponseConvert}实现{@link Converter<ResponseBody,T>}使其重写conver进行转换。
 *
 */
public class CustomResponseConvert<T> implements Converter<ResponseBody,T> {

    private final Gson mGson;
    private TypeAdapter<T> mAdapter;
    private Type mType;

    public CustomResponseConvert( Gson gson, TypeAdapter<T> adapter, Type mType ) {
        this.mGson = gson;
        this.mAdapter = adapter;
        this.mType = mType;
    }

    @Override
    public T convert( ResponseBody value ) throws IOException {
        BaseResponseEntity baseResponseEntity = new BaseResponseEntity();
        try {
            String body = value.string();
            JSONObject json = new JSONObject(body);
            int ret = json.optInt("errorCode");
            String msg = json.optString("errorMsg", "");

            if (ret == 200) {
                return mGson.fromJson(body, mType);
            } else {
                baseResponseEntity.setErrorCode(ret);
                baseResponseEntity.setErrorMsg(msg);
                baseResponseEntity.setData(json.opt("data"));

                return (T) baseResponseEntity;
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }finally {
            value.close();
        }
    }
}
