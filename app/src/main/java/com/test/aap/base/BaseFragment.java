package com.test.aap.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 * set view click event and create viewmodel object
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private ViewModelProvider mFragmentProvider;
    private ViewModelProvider mActivityProvider;

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
        View view = LayoutInflater.from(getContext()).inflate(getLayoutRes(),container,false);
        bindView(view);
        bindListener();
        return view;
    }

    @Override
    public void onViewCreated( @NonNull View view, @Nullable Bundle savedInstanceState ) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        bindViewModel();
    }

    protected <T extends ViewModel> T getFragmentViewModel(Class<T> clz){
        if (null == mFragmentProvider){
            mFragmentProvider = new ViewModelProvider(this);
        }
        return mFragmentProvider.get(clz);
    }

    protected <T extends ViewModel> T getActivityViewModel(Class<T> clz){
        if (null == mActivityProvider){
            mActivityProvider = new ViewModelProvider(this);
        }
        return mActivityProvider.get(clz);
    }

    protected NavController nav(){
        return Navigation.findNavController(getView());
    }

    protected abstract int  getLayoutRes();

    protected abstract void initData();

    protected abstract void bindView(View view);

    protected abstract void bindListener();

    protected abstract void bindViewModel();

    protected abstract void initViewModel();

}
