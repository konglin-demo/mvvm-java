package com.mxnavi.hase.hongqi.datarepository.response;

import androidx.lifecycle.MutableLiveData;
import com.test.tsp.util.JsonUtil;

/**
 *  @auth : kongl
 *  @date : 2020/07/06
 *  @describtion : tsp请求返回List类型数据{@link DataListResponse} 是 {@link DataResponse}的子类
 */
public class DataListResponse<U> extends DataResponse<U> {

    private Object U;

    public DataListResponse( MutableLiveData<U> mutableLiveData, MutableLiveData<String> liveCodeLiveData ) {
        super(mutableLiveData, liveCodeLiveData);
    }

    @Override
    public void onSuccess( String data ) {
        if (null != getResultMultiData()){
            getResultMultiData().postValue((U)JsonUtil.parseArray(data,U));
        }
    }
}
