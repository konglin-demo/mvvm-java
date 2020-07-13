package com.mxnavi.hase.hongqi.datarepository.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 *  @auth : kongl
 *  @date : 2020/07/06
 *  @describtion : define a abstract class {@link BaseRequest}, used to request state call back.
 */
public abstract class BaseRequest {
    private MutableLiveData<String> mRequestState;

    public LiveData<String> getmRequestState() {
        if (null == mRequestState){
            mRequestState = new MutableLiveData<>();
        }
        return mRequestState;
    }

    public MutableLiveData<String> getRequestState(){
        return null;
    }
}
