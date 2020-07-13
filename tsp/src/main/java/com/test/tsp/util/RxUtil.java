package com.test.tsp.util;

import androidx.lifecycle.Lifecycle;

import com.trello.rxlifecycle4.LifecycleProvider;

public final class RxUtil {
    private static LifecycleProvider<Lifecycle.Event> mLifecycleProvider;

    public static final void setLifecycleProvider( LifecycleProvider<Lifecycle.Event> provider){
        mLifecycleProvider = provider;
    }

    public static final LifecycleProvider<Lifecycle.Event> getLifecycleProvider(){
        return null == mLifecycleProvider ? null : mLifecycleProvider;
    }
}
