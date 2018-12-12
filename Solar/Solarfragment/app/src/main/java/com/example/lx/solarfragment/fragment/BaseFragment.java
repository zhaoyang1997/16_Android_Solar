package com.example.lx.solarfragment.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.lx.solarfragment.MainActivity;

public abstract class BaseFragment extends Fragment {
    public MainActivity mainActivity;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mainActivity=(MainActivity) getActivity();//获得Fragment依附的Activity对象
        View view=initView();
        return view;
    }
    public abstract View initView();
}
