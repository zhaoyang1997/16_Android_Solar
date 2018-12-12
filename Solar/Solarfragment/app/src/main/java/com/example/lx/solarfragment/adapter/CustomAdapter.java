package com.example.lx.solarfragment.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lx.solarfragment.IntegralActivity;
import com.example.lx.solarfragment.R;
import com.example.lx.solarfragment.bean.Goods;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private IntegralActivity context;    // 上下文环境
    private int item_layout_id; // 声明列表项的布局
    private List<Goods> list = new ArrayList<>();

    public CustomAdapter(){}

    public CustomAdapter(IntegralActivity context, List<Goods> list, int item_layout_id) {
        this.context = context;
        this.list = list;
        this.item_layout_id = item_layout_id;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(item_layout_id,null);
        }

        TextView tvTaskName = convertView.findViewById(R.id.tv_task_name);
        tvTaskName.setText(list.get(position).getName());
        TextView tvGTaskScore = convertView.findViewById(R.id.tv_task_score);
        tvGTaskScore.setText(list.get(position).getScore());

        return convertView;

    }
}
