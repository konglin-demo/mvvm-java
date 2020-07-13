package com.test.tsp.base;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 *  @auth : kongl
 *  @date : 2020/04/23
 *  @describtion : 切换线程类
 *
 */
public class ObservableTransformerHelper {

    public static <T> ObservableTransformer<T,T> ObserveOnMainThread(){
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
