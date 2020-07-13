package com.test.tsp.base;

/**
 *  @auth : kongl
 *  @date : 2020/04/23
 *  @describtion : 网络请求框架
 *                使用retrofit + rxjava 实现网络请求。
 *                采用设计模式中创建型中的工厂模式做类的反射，可实现扩展多个应用使用一套网络请求
 */
public abstract class BaseHttpFactory {
    public abstract <T> T createHttpRequest(Class<T> clz);
}
