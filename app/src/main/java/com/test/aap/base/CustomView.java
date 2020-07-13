package com.test.aap.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.test.aap.R;

public class CustomView extends BaseViewGroup {

    public CustomView( Context context ) {
        super(context);
    }

    public CustomView( Context context, @Nullable AttributeSet attrs ) {
        super(context, attrs);
    }

    public CustomView( Context context, @Nullable AttributeSet attrs, int defStyleAttr ) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView( Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes ) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.view_customer;
    }

    @Override
    protected void bindView( View view ) {

    }
}
