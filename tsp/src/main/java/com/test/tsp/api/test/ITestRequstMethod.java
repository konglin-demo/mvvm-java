package com.test.tsp.api.test;

import com.test.tsp.base.IResponseListener;

/**
 *  auth : kongl
 *  date : 2020/04/23
 *  describtion : 网络请求接口
 */
public interface ITestRequstMethod {
    void getName();

    void getRxName( IResponseListener listener);

    void getList(IResponseListener listener);
}
