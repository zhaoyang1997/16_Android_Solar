package com.example.lx.solarfragment.adapter;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lx.solarfragment.R;
import com.example.lx.solarfragment.bean.Picture;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<Picture> pictures=new ArrayList<>();
    private Context context;
    private int itemLayout;

    public MyAdapter() {
    }

    public MyAdapter(List<Picture> pictures, Context context, int itemLayout) {
        this.pictures = pictures;
        this.context = context;
        this.itemLayout = itemLayout;
    }

    @Override
    public int getCount() {
        return pictures.size();
    }

    @Override
    public Object getItem(int position) {
        return pictures.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //每加载一条item 就调用一次
        if(null==convertView){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(itemLayout,null);
        }
        //获取每个控件对象，并且设置显示的内容
        ImageView image=convertView.findViewById(R.id.iv_deimage);
        image.setImageBitmap(pictures.get(position).getImageurl());


        Button btn=convertView.findViewById(R.id.btn);
        btn.setText("应用");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }
}
