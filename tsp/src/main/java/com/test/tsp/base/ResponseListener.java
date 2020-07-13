package com.test.tsp.base;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 *  @auth : kongl
 *  @date : 2020/04/23
 *  @describtion : 网络请求rajava返回的实体类
 *                 IResponseListener提供给终端或者service的数据返回监听器
 */
public class ResponseListener implements Observer<BaseResponseEntity> {
    private static final String TAG = "ResponseEntity";
    private IResponseListener mListener;
    private CompositeDisposable mCompositeDisposable;

    public ResponseListener( IResponseListener mListener) {
        this.mListener = mListener;
    }

    // todo 请求后销毁页面的消去disposable的问题。
    @Override
    public void onSubscribe( Disposable d ) {
        Log.i(TAG,"onSubscribe" + d);
        if (null == mCompositeDisposable){
            mCompositeDisposable = new CompositeDisposable();
            mCompositeDisposable.add(d);
        }
        mListener.onRequestStart();
    }

    @Override
    public void onNext( BaseResponseEntity baseResponseEntity ) {
        Log.i(TAG,"onNext : " + baseResponseEntity.getData().toString()) ;
        mListener.onSuccess(baseResponseEntity.getData().toString());
    }

    @Override
    public void onError(Throwable e) {
        Log.i(TAG,"onError" + e.getMessage());
        mListener.onError(e.getMessage());
        cancelDisposable();
    }

    @Override
    public void onComplete() {
        Log.i(TAG,"onCompleted");
        mListener.onRequestEnd();
        cancelDisposable();
    }

    private void cancelDisposable(){
        if (null != mCompositeDisposable && !mCompositeDisposable.isDisposed()){
            mCompositeDisposable.dispose();
        }
    }
}
