package com.example.lx.solarfragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lx.solarfragment.ClockActivity;
import com.example.lx.solarfragment.MyActivity;
import com.example.lx.solarfragment.R;

public class FirstFragment extends Fragment {

    public static int NUMBER;
    private Spinner spinner1;
    private String number;
    private Button btnMore;
    private Button btnStart;
    private int userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.firstfragment_layout,container,false);

        btnMore=view.findViewById(R.id.btn_more);
        btnStart=view.findViewById(R.id.start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number=(String)spinner1.getSelectedItem();
                NUMBER = Integer.parseInt(number);
                Intent intent=new Intent(getContext(), ClockActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });


        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setClass(getContext(),MyActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.next_in,R.anim.next_out);
            }
        });
        spinner1= (Spinner) view.findViewById(R.id.spinner1);
        String[] arr={"1","2","3","4","5","6"};
        //创建ArrayAdapter对象
        ArrayAdapter adapter=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,arr);
        spinner1.setAdapter(adapter);

        return view;
    }

    public void setData(int data) {
        userId = data;
    }

}

