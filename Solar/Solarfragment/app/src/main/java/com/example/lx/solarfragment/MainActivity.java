package com.example.lx.solarfragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lx.solarfragment.bean.Task;
import com.example.lx.solarfragment.fragment.FifthFragment;
import com.example.lx.solarfragment.fragment.FirstFragment;
import com.example.lx.solarfragment.fragment.ForthFragment;
import com.example.lx.solarfragment.fragment.SecondFragment;
import com.example.lx.solarfragment.fragment.SixthFragment;
import com.example.lx.solarfragment.fragment.ThirdFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends FragmentActivity {
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private ForthFragment forthFragment;
    private FifthFragment fifthFragment;
    private SixthFragment sixthFragment;
    private FragmentManager fragmentManager;
    private LinearLayout tab1;
    private LinearLayout tab2;
    private LinearLayout tab3;
    private Fragment currentFragment=new Fragment();
    private FragmentTransaction ft;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userId=getIntent().getIntExtra("userId",1);
        //获取视图组件对象
        Log.e("textview",""+userId);
        finViews();
        tab1.bringToFront();
        tab2.bringToFront();
        tab3.bringToFront();
        //绑定点击事件监听器
        setLinstener();
        firstFragment=new FirstFragment();
        secondFragment=new SecondFragment();
        thirdFragment=new ThirdFragment();
        forthFragment=new ForthFragment();
        fifthFragment=new FifthFragment();
        sixthFragment=new SixthFragment();
        fragmentManager= getSupportFragmentManager();
        ft=fragmentManager.beginTransaction();
        imageView1=findViewById(R.id.image1);
        imageView2=findViewById(R.id.image2);
        imageView3=findViewById(R.id.image3);
        /*
         * 添加fragment 设置fragment的tag值
         * */
        ft.add(R.id.content,thirdFragment,"fragment3").hide(thirdFragment)
                .add(R.id.content,forthFragment,"fragment4").hide(forthFragment)
                .add(R.id.content,fifthFragment,"fragment5").hide(fifthFragment)
                .add(R.id.content,sixthFragment,"fragment6").hide(sixthFragment)
                .commit();
        firstFragment.setData(userId);
        secondFragment.setData(userId);
        thirdFragment.setData(userId);
        forthFragment.setData(userId);
        fifthFragment.setData(userId);
        sixthFragment.setData(userId);
        showFragment(firstFragment);
        imageView1.setImageResource(R.drawable.index);
        imageView2.setImageResource(R.drawable.strategy_out);
        imageView3.setImageResource(R.drawable.quotation_out);

    }

    /*
     * 主Activity控制不同的fragment
     * */
    public void switchFragment(String fromTag, String toTag) {
        Fragment from = fragmentManager.findFragmentByTag(fromTag);
        Fragment to = fragmentManager.findFragmentByTag(toTag);
        if (currentFragment != to) {
            currentFragment = to;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!to.isAdded()) {//判断是否被添加到了Activity里面去了
                transaction.hide(from).add(R.id.content, to).commit();
            } else {
                transaction.hide(from).show(to).commit();
            }
        }

    }

    /*
     * 获取tab
     * */
    private void finViews(){
        tab1=findViewById(R.id.tab1);
        tab2=findViewById(R.id.tab2);
        tab3=findViewById(R.id.tab3);
    }

    /*
     *给tab添加点击事件
     */

    private void setLinstener(){
        TabClickLinstener linstener=new TabClickLinstener();
        tab1.setOnClickListener(linstener);
        tab2.setOnClickListener(linstener);
        tab3.setOnClickListener(linstener);
    }

    /*
     *  tab的点击监听器
     * */
    private class TabClickLinstener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tab1:
                    showFragment(firstFragment);
                    imageView1.setImageResource(R.drawable.index);
                    imageView2.setImageResource(R.drawable.strategy_out);
                    imageView3.setImageResource(R.drawable.quotation_out);
                    break;
                case R.id.tab2:
                    showFragment(secondFragment);
                    imageView1.setImageResource(R.drawable.index_out);
                    imageView2.setImageResource(R.drawable.strategy);
                    imageView3.setImageResource(R.drawable.quotation_out);
                    break;
                case R.id.tab3:
                    ThirdFragment.Sum sum=new ThirdFragment.Sum();
                    sum.execute();
                    FifthFragment.Sum sum2=new FifthFragment.Sum();
                    sum2.execute();
                    SixthFragment.Sum sum3=new SixthFragment.Sum();
                    sum3.execute();
                    showFragment(thirdFragment);
                    imageView1.setImageResource(R.drawable.index_out);;
                    imageView2.setImageResource(R.drawable.strategy_out);
                    imageView3.setImageResource(R.drawable.quotation);
                    break;
            }
        }
    }


    /*
     * 自定义fragmenttabhost
     * */
    private void showFragment(Fragment fragment){
        //创建Fragment事务
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        if(fragment != currentFragment){
            //隐藏正在显示的Fragment
            transaction.hide(currentFragment);
            //添加Fragment
            if(!fragment.isAdded()) {   //判断fragment是否添加过 因为只能添加一次
                transaction.add(R.id.content, fragment);
            }
            //显示Fragment
            transaction.show(fragment);
            //提交事务
            transaction.commit();
            currentFragment=fragment;
        }
    }

}
