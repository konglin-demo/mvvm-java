package com.test.tsp.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public final class JsonUtil {
    /**
     * @param text  json
     * @param t    类型
     * @param <T>   类
     * @return 结果
     */
    public static <T> T parseObject( String text, T t) {
        return new Gson().fromJson(text,new TypeToken<T>() {}.getType());
    }

    /**
     * @param text  json
     * @param t    类型
     * @param <T>   类
     * @return 结果
     */
    public static <T> List<T> parseArray( String text, T t ) {
        return new Gson().fromJson(text,new TypeToken<List<T>>() {}.getType());
    }
}