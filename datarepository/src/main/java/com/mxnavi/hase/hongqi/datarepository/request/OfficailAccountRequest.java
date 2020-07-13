package com.mxnavi.hase.hongqi.datarepository.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mxnavi.hase.hongqi.datarepository.DataRepository;
import com.mxnavi.hase.hongqi.datarepository.bean.BeanOfficialAccount;

import java.util.List;

/**
 *  @auth : kongl
 *  @date : 2020/07/06
 *  @describtion : 请求数据类{@link OfficailAccountRequest}, 继承{@link BaseRequest}
 *                 可以根据业务类型区分request的种类
 *                 将request作为ViewModel的成员暴露给Activity/Fragment,可以实现在viewmodel
 *                 的组合和复用.
 *                 若是业务不是很庞大，可以直接写在viewmodel做业务逻辑的处理以及数据的缓存。
 */
public class OfficailAccountRequest extends BaseRequest{
    private MutableLiveData<List<BeanOfficialAccount>> mOfficailAccount;

    // Tip:
    // 提供数据请求状态类型。
    @Override
    public MutableLiveData<String> getRequestState() {
        return DataRepository.getInstance().getResponseCode();
    }
    // Tip :
    // 向 ui 层提供的request LiveData,使用抽象的LiveData而不是MutableLiveData为了来自数据层的数据,在ui层中只读
    public LiveData<List<BeanOfficialAccount>> getOfficialAccountLiveData(){
        if (null == mOfficailAccount){
            mOfficailAccount = new MutableLiveData<>();
        }
        return mOfficailAccount;
    }

    public void getOfficialAccount(){
        DataRepository.getInstance().getRxName(mOfficailAccount);
    }
}
