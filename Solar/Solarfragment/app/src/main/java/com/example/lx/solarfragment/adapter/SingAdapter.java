package com.example.lx.solarfragment.adapter;

import android.widget.BaseAdapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lx.solarfragment.R;
import com.example.lx.solarfragment.bean.Sing;

import java.util.ArrayList;
import java.util.List;

public class SingAdapter extends BaseAdapter {
    private List<Sing> sings=new ArrayList<>();
    private Context context;
    private int itemLayout;

    public SingAdapter() {
    }

    public SingAdapter(List<Sing> sings, Context context, int itemLayout) {
        this.sings = sings;
        this.context = context;
        this.itemLayout = itemLayout;
    }

    @Override
    public int getCount() {return sings.size();}

    @Override
    public Object getItem(int position) {
        return sings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //每加载一条item 就调用一次
        if(null==convertView){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(itemLayout,null);
        }
        //获取每个控件对象，并且设置显示的内容

        TextView name=convertView.findViewById(R.id.tv_singname);
        name.setText(sings.get(position).getSingname());

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.example.lx.solarfragment","com.example.lx.solarfragment.SettingActivity");
                intent.putExtra("name",sings.get(position).getSingname());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
