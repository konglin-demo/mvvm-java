package com.test.tsp.api.test.retrofit;
import com.test.tsp.base.BaseRetrofit;
import com.test.tsp.base.BaseRetrofitFactory;

/**
 * @author kongl
 * @date 2020/7/2
 * @describtion 实现自己的retrofit类，可以创建retrofit对象也可以创建请求的接口API的对象
 */
public class TestRetroiftFactory extends BaseRetrofitFactory {

    private TestRetrofit mTestRetrofit = null;
    @Override
    public <T extends BaseRetrofit> T createRetrofit( Class<T> clz) {
        String className = clz.getName();
        try {
            mTestRetrofit = (TestRetrofit)Class.forName(className).newInstance();
            mTestRetrofit.initRetrofit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (T)mTestRetrofit;
    }

}
