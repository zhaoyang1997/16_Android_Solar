package com.example.lx.solarfragment;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lx.solarfragment.fragment.FirstFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class ClockActivity extends AppCompatActivity {

    private int userId = MainActivity.USER_ID;
    private TomatoTask tomatoTask = new TomatoTask();
    private LinearLayout manyClock;
    private ImageView backIV;
    private int clock_count= FirstFragment.NUMBER;
    private int temp = 0;  // 记录当前点击的控件位置
    private int countFrom = 0;//共实际完成几个番茄时间
    private int clock_reduce=clock_count;//记录已经消失了几个番茄闹钟，是否是最后一个。（如果是最后一个，结束后返回上一页）

    /**
     * 动态建立的控件列表 ： 分钟/秒钟
     */

    private LinearLayout[] linearLayouts = new LinearLayout[clock_count];
    private TextView[] tvMins = new TextView[clock_count];
    private TextView[] tvSecs = new TextView[clock_count];

    //倒计时
    private long mMins[] = new long[clock_count];
    private long mSecs[] = new long[clock_count];
    private boolean isRun = true;
    private MyThread myThread;
    private Thread thread;
    private boolean flag;
    private int mCount=10;
    private Handler timeHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("flag","6");
            computeTime();
            Log.e("flag","7");
            tvMins[temp].setText(mMins[temp]+"");
            tvSecs[temp].setText(mSecs[temp]+"");
            if (mMins[temp]==0&&mSecs[temp]==0) {
                countFrom++;
                if(clock_reduce>0) {
                    linearLayouts[temp].setVisibility(View.GONE);//完成一番茄后view消失
                }else{
                    Log.e("countFrom", String.valueOf(countFrom));
                    tomatoTask.execute();
                    ClockActivity.this.finish();

                }
            }
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        manyClock = findViewById(R.id.many_clock);
        backIV = findViewById(R.id.clock_back_left);
        backIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClockActivity.this.finish();
            }
        });

        Button btnGive = findViewById(R.id.btn_give_up);
        btnGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
                ClockActivity.this.finish();
            }
        });

        userId=getIntent().getIntExtra("userId",0);

        for ( int i=0; i<clock_count; i++ ) {
            temp=i;
            mMins[temp] = 25;
            mSecs[temp] = 00;

            myThread = new MyThread();
            thread = new Thread(myThread);
            //实例化一个LinearLayout
            linearLayouts[i] = new LinearLayout(this);
            linearLayouts[i].setTag(i);
            //设置水平居中
            linearLayouts[i].setOrientation(LinearLayout.HORIZONTAL);
            //设置居中
            linearLayouts[i].setGravity(Gravity.CENTER);
            //设置背景
            final Resources resources = this.getApplicationContext().getResources();
            Drawable shapeDrawable = resources.getDrawable(R.drawable.clock_shape);
            linearLayouts[i].setBackground(shapeDrawable);
            //设置LinearLayout属性(宽和高)
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 130);
            //设置边距
            layoutParams.setMargins(40, 15, 40, 15);


            //将以上的属性赋给LinearLayout
            linearLayouts[i].setLayoutParams(layoutParams);
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(55,55);
            ivParams.setMargins(0, 0, 25, 0);
            imageView.setLayoutParams(ivParams);
            imageView.setBackground(resources.getDrawable(R.drawable.clock1));


            //实例化一个TextView / 分钟
            tvMins[i] = new TextView(this);
            //设置宽高以及权重
            LinearLayout.LayoutParams tvMinParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tvMins[i].setLayoutParams(tvMinParams);
            tvMins[i].setTextSize(30);
            tvMins[i].setText("25");

            //实例化一个TextView / 点
            TextView tvPoint = new TextView(this);
            //设置宽高以及权重
            LinearLayout.LayoutParams tvPointParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tvPoint.setLayoutParams(tvPointParams);
            tvPoint.setTextSize(30);
            tvPoint.setText(":");

            //实例化一个TextView / 秒钟
            tvSecs[i] = new TextView(this);
            //设置宽高以及权重
            LinearLayout.LayoutParams tvSecParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tvSecs[i].setLayoutParams(tvSecParams);
            tvSecs[i].setTextSize(30);
            tvSecs[i].setText("00");


            linearLayouts[i].addView(imageView);
            linearLayouts[i].addView(tvMins[i]);
            linearLayouts[i].addView(tvPoint);
            linearLayouts[i].addView(tvSecs[i]);

            manyClock.addView(linearLayouts[i]);

            linearLayouts[i].setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    int j = (Integer)v.getTag();
                    Log.e("Tag", String.valueOf(j));
                    temp = j;

                    if(!thread.isAlive()){
                        Log.e("flag","1");
                        tvMins[j].setTextColor(Color.rgb(0, 0, 0));
                        tvSecs[j].setTextColor(Color.rgb(0,0,0));
                        flag=true;
                        if(thread.getState()==Thread.State.TERMINATED){
                            Log.e("flag","2");
                            clock_reduce--;
                            Log.e("Clock_reduce", String.valueOf(clock_reduce));
                            thread = new Thread(myThread);
                            if(mCount==-1) mCount=10;
                            thread.start();
                        }else{
                            clock_reduce--;
                            Log.e("Clock_reduce", String.valueOf(clock_reduce));
                            thread.start();
                            Log.e("flag","8");
                        }
                    } else {
                        Log.e("flag","4");
                        //暂停计时器，设置标记为false
                        flag=false;
                        if(clock_reduce>0){
                            linearLayouts[temp].setVisibility(View.GONE);
                        }
                        else{
                            Log.e("countFrom", String.valueOf(countFrom));
                            tomatoTask.execute();
                            ClockActivity.this.finish();
                        }

                        tvMins[j].setTextColor(R.color.text_gray_ccc);
                        tvSecs[j].setTextColor(R.color.text_gray_ccc);
                    }

                }
            });

        }


    }


    //添加id的类
    public static class IdiUtils {

        private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

        public static int generateViewId() {
            for (; ; ) {
                final int result = sNextGeneratedId.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
                if (sNextGeneratedId.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        }
    }

    //线程类
    private class MyThread implements Runnable{

        @Override
        public void run() {
            Log.e("flag","9");

            while (flag&&mCount>=0) {
                try {
                    Thread.sleep(1000); // sleep 1000ms
                    Log.e("mMain--------", String.valueOf(mMins[temp]));
                    Message message = Message.obtain();
                    message.what = 1;
                    message.arg1=mCount;
                    timeHandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flag = false;
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecs[temp]--;
        if (mSecs[temp] < 0) {
            mMins[temp]--;
            mSecs[temp] = 59;
            if (mMins[temp] < 0) {
                mMins[temp]=24;
            }
        }
    }

    /**
     * 发送数据入数据库 countFrom
     */
    public class TomatoTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Log.e("flag----$$","1");
                URL url = new URL("http://10.7.89.78:8080/data_get/TomatoServlet");
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("contentType","UTF-8");
                Log.e("flag----$$","2");
                OutputStream os = connection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
                BufferedWriter writer = new BufferedWriter(outputStreamWriter);
                Log.e("flag----$$","3");

                JSONObject object = new JSONObject();
                object.put("user_id",userId);
                object.put("num",countFrom);
                object.put("year",getYear());
                object.put("month",getMonth());
                object.put("day",getDay());


                Log.e("flag----$$","4");
                writer.write(String.valueOf(object));
                connection.connect();
                writer.flush();
                writer.close();
                Log.e("flag----$$","5");
                InputStream is = connection.getInputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                len=is.read(buffer);
                String res = new String(buffer,0,len);

                Log.e("AAAAAAAAA",res);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }


    private String getYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String s = sdf.format(new Date());
        return s;
    }
    private String getMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String s = sdf.format(new Date());
        return s;
    }
    private String getDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String s = sdf.format(new Date());
        return s;
    }


}
