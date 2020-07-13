package com.test.aap;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment implements View.OnClickListener {

    private Button mBack;
    public SecondFragment() {
        // Required empty public constructor
    }


    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Log.i("SecondFragment","newInstance ");
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated( @NonNull View view, @Nullable Bundle savedInstanceState ) {
        super.onViewCreated(view, savedInstanceState);
        mBack = (Button) getView().findViewById(R.id.back);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick( View v ) {
        if (v.equals(mBack)){
            Navigation.findNavController(getView()).popBackStack();
        }
    }
}
