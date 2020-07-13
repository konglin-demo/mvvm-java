package com.mxnavi.hase.hongqi.datarepository.response;

import androidx.lifecycle.MutableLiveData;

import com.test.tsp.base.IResponseListener;
import com.test.tsp.util.JsonUtil;

/**
 *  @auth : kongl
 *  @date : 2020/07/06
 *  @describtion : tsp请求返回object类型数据{@link DataResponse} 实现了{@link IResponseListener}
 */
public class DataResponse<T> implements IResponseListener {
    public static final String START_REQUEST  = "开始请求";
    public static final String STOP_REQUEST  = "取消请求";

    private MutableLiveData<T> mMutableLiveData;
    private MutableLiveData<String> mLiveCodeLiveData;
    private Object T;

    public DataResponse( MutableLiveData<T> mutableLiveData, MutableLiveData<String> liveCodeLiveData) {
        this.mMutableLiveData = mutableLiveData;
        this.mLiveCodeLiveData = liveCodeLiveData;
    }

    @Override
    public void onRequestStart() {
        if (null != mLiveCodeLiveData){
            mLiveCodeLiveData.postValue(START_REQUEST);
        }
    }

    @Override
    public void onSuccess( String data ) {
        if(null != mMutableLiveData){
            mMutableLiveData.postValue((T) JsonUtil.parseObject(data,T));
        }
    }

    @Override
    public void onError( String errorMsg ) {
        if (null != mLiveCodeLiveData){
            mLiveCodeLiveData.postValue(errorMsg);
        }
    }

    @Override
    public void onRequestEnd() {
        if (null != mLiveCodeLiveData){
            mLiveCodeLiveData.postValue(STOP_REQUEST);
        }
    }

    protected MutableLiveData<T> getResultMultiData(){
        return null == mMutableLiveData ? null : mMutableLiveData;
    }
}
