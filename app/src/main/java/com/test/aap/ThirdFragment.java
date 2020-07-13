package com.test.aap;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "ThirdFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button mEnterFourthBtn;
    private Button mBack;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Log.i(TAG,getArguments().getString("enter"));
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        String enter = ThirdFragmentDirections.actionThirdFragmentToFourthFragment().getArguments().getString("enter");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBack)){
            NavHostFragment.findNavController(this).popBackStack();
        }

        if (v.equals(mEnterFourthBtn)){
            NavHostFragment.findNavController(this).navigate(R.id.action_thirdFragment_to_fourthFragment);
        }
    }
    private void bindView(){
        mBack = (Button)getView().findViewById(R.id.back);
        mEnterFourthBtn = (Button)getView().findViewById(R.id.enter_fourth_fragment);
        mBack.setOnClickListener(this);
//        mEnterFourthBtn.setOnClickListener(this);
        mEnterFourthBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_thirdFragment_to_fourthFragment));
    }
}
