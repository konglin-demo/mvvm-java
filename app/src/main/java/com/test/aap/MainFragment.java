package com.test.aap;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mxnavi.hase.hongqi.datarepository.bean.BeanOfficialAccount;
import com.test.aap.base.BaseFragment;
import com.test.aap.provider.MainViewModel;
import com.trello.lifecycle4.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle4.LifecycleProvider;

import java.util.List;

/**
 * A simple {@link BaseFragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends BaseFragment {
    private static final String TAG = "MainFragment";
    private Button mFirstBtn;
    private Button mSecondBtn;
    private Button mTspButton;
    private Button mTspButtonList;
    private Button mBackHome;
    private MainViewModel mMainViewModel;

    public MainFragment() {
        // Required empty public constructor
        LifecycleProvider<Lifecycle.Event> provider = AndroidLifecycle.createLifecycleProvider(this);
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void bindView( View view ) {
        mBackHome = (Button)view.findViewById(R.id.back);
        mFirstBtn = (Button)view.findViewById(R.id.enter_first_fragment);
        mSecondBtn = (Button)view.findViewById(R.id.enter_second_fragment);
        mTspButton = (Button)view.findViewById(R.id.test_tsp);
        mTspButtonList = (Button)view.findViewById(R.id.test_tsp_list);
    }

    @Override
    protected void bindListener() {
        mFirstBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_firstFragment));
        mSecondBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_secondFragment));
        mBackHome.setOnClickListener(this);
        mTspButton.setOnClickListener(this);
        mTspButtonList.setOnClickListener(this);
    }

    @Override
    protected void bindViewModel() {
        mMainViewModel.getOfficialAcccountRequest().getOfficialAccountLiveData().observe(getViewLifecycleOwner(), new Observer<List<BeanOfficialAccount>>() {
            @Override
            public void onChanged( List<BeanOfficialAccount> beanOfficialAccounts ) {
                if (null == beanOfficialAccounts){
                    return;
                }
                mMainViewModel.getOfficialAcccount().setValue(beanOfficialAccounts);
                Log.i(TAG,"bean Officail Accounts : " + beanOfficialAccounts.toString());
            }
        });

        mMainViewModel.getOfficialAcccountRequest().getRequestState().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged( String s ) {
                Log.i(TAG,"bean request state: " + s);
            }
        });
    }

    @Override
    protected void initViewModel() {
        mMainViewModel = getFragmentViewModel(MainViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,"onActivityCreated=========");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume=========");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"onPause=========");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"onDestroyView=========");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy=========");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG,"onHiddenChanged : " + String.valueOf(hidden));
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBackHome)){
            nav().navigateUp();
        }

        if (v.equals(mTspButton)){
            mMainViewModel.getOfficialAcccountRequest().getOfficialAccount();
        }

        if (v.equals(mTspButtonList)){
        }
    }
}
