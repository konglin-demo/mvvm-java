package com.test.aap;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FourthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FourthFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "FourthFragment";
    private Button mBack;

    public FourthFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static FourthFragment newInstance() {
        FourthFragment fragment = new FourthFragment();
        return fragment;
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
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy=========");
    }

    @Override
    public void onHiddenChanged( boolean hidden ) {
        super.onHiddenChanged(hidden);
        Log.i(TAG,"onHiddenChanged=========" + String.valueOf(hidden));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBack = (Button)view.findViewById(R.id.back);
//        mBack.setOnClickListener(this);
        mBack.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_fourthFragment_to_mainFragment));
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBack)){
//            Navigation.findNavController(getView()).navigateUp();
        }
    }

    @Override
    public void onPrimaryNavigationFragmentChanged(boolean isPrimaryNavigationFragment) {
        super.onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment);
        Log.i("FourthFragment", "onPrimaryNavigationFragmentChanged : " + String.valueOf(isPrimaryNavigationFragment) );
    }
}
