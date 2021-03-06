package com.test.aap;

import android.os.Bundle;

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
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button mEnterThridBtn;
    private Button mBack;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        Log.i("FirstFragment","newInstance ");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onHiddenChanged( boolean hidden ) {
        super.onHiddenChanged(hidden);
        Log.i("FirstFragment","hidden :  " + String.valueOf(hidden));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("FirstFragment","onResume=========");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("FirstFragment","onPause=========");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("FirstFragment","onDestroy=========");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("FirstFragment","onCreateView=========");
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindView();
        Log.i("FirstFragment","onActivityCreated=========");
    }

    private void bindView(){
        mBack = (Button)getView().findViewById(R.id.back);
        mEnterThridBtn = (Button)getView().findViewById(R.id.enter_third_fragment);
        mBack.setOnClickListener(this);
        mEnterThridBtn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_firstFragment_to_thirdFragment));
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBack)){
            NavHostFragment.findNavController(this).navigateUp();
        }

        if (v.equals(mEnterThridBtn)){
//            NavHostFragment.findNavController(this).navigate(R.id.action_firstFragment_to_thirdFragment);
            FirstFragmentDirections.ActionFirstFragmentToThirdFragment action = FirstFragmentDirections.actionFirstFragmentToThirdFragment().setEnter("hello");
            NavHostFragment.findNavController(this).navigate(action);
        }
    }
    
}
