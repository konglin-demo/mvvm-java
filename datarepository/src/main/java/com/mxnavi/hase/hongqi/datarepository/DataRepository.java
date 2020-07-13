package com.mxnavi.hase.hongqi.datarepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.mxnavi.hase.hongqi.datarepository.bean.BeanOfficialAccount;
import com.mxnavi.hase.hongqi.datarepository.response.DataListResponse;
import com.test.tsp.api.test.TestRequestMethod;
import com.test.tsp.api.test.TestRequstFactory;
import com.test.tsp.base.BaseHttpFactory;

import java.util.List;

/**
 *  @auth : kongl
 *  @date : 2020/07/06
 *  @describtion : tsp请求数据类{@link DataRepository}使用单例的方式给request包下面的请求使用。
 *
 */
public class DataRepository implements IRemoteSource{
    private TestRequestMethod mRequestMethod;
    private MutableLiveData<String> mResponseCodeLiveData;

    public DataRepository( ) {
        BaseHttpFactory baseHttpFactory = new TestRequstFactory();
        mRequestMethod = baseHttpFactory.createHttpRequest(TestRequestMethod.class);
    }

    public final static DataRepository getInstance(){
        return DataHolder.INSTANCE;
    }

    private final static class DataHolder{
        private static final DataRepository INSTANCE = new DataRepository();
    }

    public MutableLiveData<String> getResponseCode(){
        if (null == mResponseCodeLiveData){
            mResponseCodeLiveData = new MutableLiveData<>();
        }
        return mResponseCodeLiveData;
    }

    @Override
    public void getList() {
//        mRequestMethod.getRxName(new DataListResponse<List<BeanOfficialAccount>>(officialAccountLiveData,mResponseCodeLiveData));
    }

    @Override
    public void getRxName(@NonNull MutableLiveData<List<BeanOfficialAccount>> officialAccountLiveData ) {
        mRequestMethod.getRxName(new DataListResponse<List<BeanOfficialAccount>>(officialAccountLiveData,mResponseCodeLiveData));
    }
}
