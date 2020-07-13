package com.test.tsp.api.test.retrofit;


import com.test.tsp.api.test.ITestModel;
import com.test.tsp.base.BaseResponseEntity;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 *  auth : kongl
 *  date : 2020/04/23
 *  describtion : retrofit 请求类
 *
 *               retrofit请求采用的注解，注解分为HTTP请求方法注解类、标记类注解和参数类注解。
 *               HTTP请求方法注解类，有8种，分别是POST、GET、HEARD、DELETE、PUT、TRACE、OPTIONS、PATCH.
 *               标记类注解 ：FormUrlEncoded、Multipart、Streaming
 *               参数类注解 ：Header(添加不固定的头)、 Headers(添加固定的头)、 Body、 Path、 Field、FieldMap、 Part、 PartMap、 Query和QueryMap.
 *               POST一般需要FormUrlEncoded 搭配Body或者Field/FieldMap GET 一般使用Query和QueryMap.使用需要根据服务端
 *               提供的规则来定。
 */
public interface ITestServiceApi {

    @GET("wxarticle/chapters/json")
    Call<ITestModel> getIpMsg();

    @GET("wxarticle/chapters/json")
    Observable<BaseResponseEntity> getRxMsg();

    @GET("wxarticle/list/408/1/json")
    Observable<BaseResponseEntity> getList();
}
