package com.test.aap.provider;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mxnavi.hase.hongqi.datarepository.bean.BeanOfficialAccount;
import com.mxnavi.hase.hongqi.datarepository.request.OfficailAccountRequest;

import java.util.List;

/**
 *  @auth : kongl
 *  @date : 2020/07/06
 *  @describtion : 请求数据类{@link MainViewModel} is a {@link ViewModel} subclass
 *                 do necessary business logic calls and update the ~LiveData.
 */
public class MainViewModel extends ViewModel {
    private MutableLiveData<List<BeanOfficialAccount>> mOfficailAccount;
    private OfficailAccountRequest mOfficialAccountRequest;

    public final OfficailAccountRequest getOfficialAcccountRequest(){
        if (null == mOfficialAccountRequest){
            mOfficialAccountRequest = new OfficailAccountRequest();
        }
        return mOfficialAccountRequest;
    }

    public final MutableLiveData<List<BeanOfficialAccount>> getOfficialAcccount(){
        if (null == mOfficailAccount){
            mOfficailAccount = new MutableLiveData<>();
        }
        return mOfficailAccount;
    }

    /**
     *  获取缓存的数据
     */
    public void doAction(){
        mOfficailAccount.postValue(null == mOfficailAccount.getValue() ? null : mOfficailAccount.getValue());
    }
}
