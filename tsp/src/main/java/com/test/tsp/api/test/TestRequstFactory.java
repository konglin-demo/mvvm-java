package com.test.tsp.api.test;

import com.test.tsp.base.BaseHttpFactory;

/**
 *  @auth : kongl
 *  @date : 2020/04/23
 *  @describtion : 网络请求框架 请求框架继承{@link BaseHttpFactory} 可以用单例模式使其获取对象唯一。
 */
public class TestRequstFactory extends BaseHttpFactory {
    TestRequestMethod requestMethod = null;
    @Override
    public <T> T createHttpRequest(Class<T> clz) {

        String className = clz.getName();
        try {
            requestMethod = (TestRequestMethod)Class.forName(className).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }

        return (T)requestMethod;
    }
}
