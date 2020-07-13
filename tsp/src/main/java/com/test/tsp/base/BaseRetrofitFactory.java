package com.test.tsp.base;

/**
 *  @auth : kongl
 *  @date : 2020/04/23
 *  @describtion : 网络请求框架
 *                使用retrofit + rxjava 实现网络请求。
 *                采用设计模式中创建型中的工厂模式做类的反射，可实现扩展多个应用使用一套网络请求
 *                抽象工厂模式使retrofit被可以被创建多个
 */
public abstract class BaseRetrofitFactory {
    public abstract <T extends BaseRetrofit> T createRetrofit( Class<T> clz);
}
