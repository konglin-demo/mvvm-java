package com.test.tsp.api.test;

import android.util.Log;

import com.test.tsp.api.test.retrofit.ITestServiceApi;
import com.test.tsp.base.IResponseListener;
import com.test.tsp.base.ObservableTransformerHelper;
import com.test.tsp.base.ResponseListener;
import com.test.tsp.api.test.retrofit.TestRetrofit;
import com.test.tsp.api.test.retrofit.TestRetroiftFactory;
import com.test.tsp.util.RxUtil;

import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *  auth : kongl
 *  date : 2020/04/23
 *  describtion : retrofit 实现网络请求 没有做成单例，可以实现使一套应用多次实例化请求
 *                retrofit 返回数据的entity tsp不进行封装，使其更加有灵活性。
 */
public class TestRequestMethod implements ITestRequstMethod {
    private static final String TAG = "TestRequestMethod";
    TestRetrofit mTestRetrofit;
    ITestServiceApi mRequestApi;

    public TestRequestMethod() {
//        TestRetroiftFactory mTestRetroiftFactory = new TestRetroiftFactory();
//        mTestRetrofit = mTestRetroiftFactory.createRetrofit(TestRetrofit.class);

        mRequestApi = new TestRetroiftFactory().createRetrofit(TestRetrofit.class)
                .createApi(ITestServiceApi.class);
    }

    /**
     *  retrofit请求方式
     */
    @Override
    public void getName() {
        mRequestApi.getIpMsg().enqueue(new Callback<ITestModel>() {
            @Override
            public void onResponse(Call<ITestModel> call, Response<ITestModel> response) {
                //请求处理,输出结果
                Log.i(TAG,response.body().toString());
                response.body().onTest();
            }

            @Override
            public void onFailure(Call<ITestModel> call, Throwable t) {
                Log.i(TAG,"连接失败");
            }
        });
    }
    /**
     * retrofit + rxjava 请求方式
     */
    @Override
    public void getRxName( IResponseListener listener ) {
        mRequestApi.getRxMsg()
                .compose(ObservableTransformerHelper.ObserveOnMainThread())
                .subscribe(new ResponseListener(listener));
    }

    @Override
    public void getList( IResponseListener listener ) {
        assert RxUtil.getLifecycleProvider() != null;
        mRequestApi.getList()
                .compose(ObservableTransformerHelper.ObserveOnMainThread())
                .subscribe((Consumer<? super Object>) new ResponseListener(listener));
    }
}
