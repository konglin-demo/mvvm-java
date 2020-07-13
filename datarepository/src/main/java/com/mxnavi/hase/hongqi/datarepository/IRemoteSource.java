package com.mxnavi.hase.hongqi.datarepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.mxnavi.hase.hongqi.datarepository.bean.BeanOfficialAccount;

import java.util.List;

public interface IRemoteSource {
    void getList( );
    void getRxName(@NonNull MutableLiveData<List<BeanOfficialAccount>> officialAccountLiveData );
}
