package com.example.lx.solarfragment.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lx.solarfragment.MainActivity;
import com.example.lx.solarfragment.R;

public class ForthFragment extends BaseFragment {
    private Button btn;
    private View view;
    private int userId;
    public View initView(){
        if(view==null){
            view=View.inflate(mainActivity,R.layout.forthfragment_layout,null);
            Button btn1=view.findViewById(R.id.btn4);
            btn=(Button) view.findViewById(R.id.btn3);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainActivity.switchFragment("fragment4","fragment3");
                }
            });
        }
        return view;
    }

    public void setData(int data) {
        userId = data;
    }
}
