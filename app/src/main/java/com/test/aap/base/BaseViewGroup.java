package com.test.aap.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public abstract class BaseViewGroup extends LinearLayout {

    private Context mContext;
    public BaseViewGroup( Context context ) {
        super(context);
        mContext = context;
        createView();
    }

    public BaseViewGroup( Context context, @Nullable AttributeSet attrs ) {
        super(context, attrs);
        mContext = context;
        createView();
    }

    public BaseViewGroup( Context context, @Nullable AttributeSet attrs, int defStyleAttr ) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        createView();
    }

    public BaseViewGroup( Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes ) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        createView();
    }

    private View createView(){
        View view  = LayoutInflater.from(mContext).inflate(getLayoutResId(),this,false);
        bindView(view);
        return view;
    }

    protected abstract int getLayoutResId();
    protected abstract void bindView(View view);
}
