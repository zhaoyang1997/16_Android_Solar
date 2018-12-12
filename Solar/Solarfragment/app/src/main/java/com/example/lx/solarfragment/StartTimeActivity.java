package com.example.lx.solarfragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lx.solarfragment.fragment.SecondFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StartTimeActivity extends AppCompatActivity {

    private ImageView backLeft;
    private LinearLayout countDown;
    // 倒计时
    private int fCount = 0;
    private TextView minutesTv, secondsTv;
    private long mMin;
    private long mSecond = 00;// 天 ,小时,分钟,秒
    private boolean isRun = true;
    private MyThread myThread;
    private Thread thread;
    private boolean flag;
    private int mCount=10;
    private int  TaskId;
    private String TaskName;
    private int TaskTime;
    private  Bundle bundle;
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what==1) {
                computeTime();
                minutesTv.setText(mMin+"");
                secondsTv.setText(mSecond+"");
                if (mMin==0&&mSecond==0) {
                    //countDown.setVisibility(View.GONE);
                    //跳转-->去数据库中改任务状态为完成《--根据taskId

                    new Thread(){
                        @Override
                        public void run() {
                            String path="http://10.7.89.187:8080/Solar/UpdateTaskStateServlet";
                            try {
                                URL url =new URL(path);
                                HttpURLConnection Connection=(HttpURLConnection) url.openConnection();
                                Connection.setRequestProperty("contentType","utf-8");
                                Connection.setRequestMethod("POST");
                                //把TaskID传过去，根据taskID在数据中改状态
                                OutputStream os=Connection.getOutputStream();
                                OutputStreamWriter opw=new OutputStreamWriter(os);
                                BufferedWriter writer=new BufferedWriter(opw);
                                JSONObject js=new JSONObject();
                                js.put("TaskId",TaskId);
                                js.put("TaskTime",TaskTime);
                                js.put("userId",1);
                                writer.write(String.valueOf(js));
                                writer.flush();
                                writer.close();
                                //请求
                                Connection.connect();
                                //获得响应
                                InputStream is=Connection.getInputStream();
                                InputStreamReader reader=new InputStreamReader(is);
                                BufferedReader bufferedReader=new BufferedReader(reader);

                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


                    }.start();
//完成任务
                   StartTimeActivity.this.finish();



                }
            }
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_time);
        //监听返回
        backLeft = findViewById(R.id.clock_back_left);
        backLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartTimeActivity.this.finish();
            }
        });
        //获得任务的名字/时间/Id
        final Intent i = getIntent();
        bundle=i.getExtras();
        TaskId=bundle.getInt("TaskId");
        TaskName=bundle.getString("TaskName");
        TextView textView=findViewById(R.id.task_name1);
        textView.setText(TaskName);
        TaskTime= bundle.getInt("TaskTime");

        Log.e("时间", String.valueOf(bundle.getInt("TaskTime")));


        myThread = new MyThread();
        thread = new Thread(myThread);

        countDown = (LinearLayout) findViewById(R.id.count_time_layout);
        minutesTv = (TextView) findViewById(R.id.minutes_tv);
        secondsTv = (TextView) findViewById(R.id.seconds_tv);
        //fCount++;
//        if(fCount == 5){
//            Intent intent = new Intent();
//            intent.setClass(StartTimeCountActivity.this,MainActivity.class);
//            startActivity(intent);
//        }
        flag=false;
        thread.interrupt();
        mMin=TaskTime;
        mSecond = 00;
        minutesTv.setText(mMin+"");
        secondsTv.setText(mSecond+"0");
        thread = new Thread(myThread);
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //开始计时器或者是重启计时器，设置标记为true
        minutesTv.setTextColor(Color.rgb(0, 0, 0));
        secondsTv.setTextColor(Color.rgb(0,0,0));
        flag=true;
        //直接进行倒计时

        thread.start();

        //监听暂停和开始
        Button button = findViewById(R.id.zanting);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                if(!thread.isAlive()){
                    //开始计时器或者是重启计时器，设置标记为true
                    minutesTv.setTextColor(Color.rgb(0, 0, 0));
                    secondsTv.setTextColor(Color.rgb(0,0,0));
                    flag=true;
                    //判断是否是第一次启动，如果是不是第一次启动，那么状态就是Thread.State.TERMINATED
                    //不是的话，就需要重新的初始化，因为之前的已经结束了。
                    //并且要判断这个mCount 是否为-1，如果是的话，说名上一次的计时已经完成了，那么要重新设置。
                    if(thread.getState()==Thread.State.TERMINATED){
                        thread = new Thread(myThread);
                        if(mCount==-1) mCount=10;
                        thread.start();
                    }else{
                        thread.start();
                    }
                }else {
                    //暂停计时器，设置标记为false
                    flag=false;
                    minutesTv.setTextColor(R.color.text_gray_ccc);
                    secondsTv.setTextColor(R.color.text_gray_ccc);
                    //不可以使用 stop 方法，会报错，java.lang.UnsupportedOperationException
                    //mThread.stop();
                }
            }
        });
//鉴定中止按钮
        Button stop=findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //给出列表弹框
                final String[] items = new String[]{"放弃当前计时", "提前完成计时", "取消"};//创建item
                AlertDialog alertDialog3 = new AlertDialog.Builder(StartTimeActivity.this)
                        .setTitle("请选择选择您的选项")
                        .setIcon(R.mipmap.ic_launcher)
                        .setItems(items, new DialogInterface.OnClickListener() {//添加列表
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(items[i].equals("放弃当前计时")){
                                    //1. 创建Intent对象

                                    Intent intent = new Intent();

                                    //2. 指定跳转路线

                                    intent.setClass(StartTimeActivity.this,

                                            SecondFragment.class);
                                    //3. 进行跳转

                                    startActivity(intent);

                                }else if(items[i].equals("提前完成计时")){
                                    new Thread(){
                                        @Override
                                        public void run() {
                                            String path="http://10.7.90.220:8080/Solar/UpdateTaskStateServlet";
                                            try {
                                                URL url =new URL(path);
                                                HttpURLConnection Connection=(HttpURLConnection) url.openConnection();
                                                Connection.setRequestProperty("contentType","utf-8");
                                                Connection.setRequestMethod("POST");
                                                //把TaskID传过去，根据taskID在数据中改状态
                                                OutputStream os=Connection.getOutputStream();
                                                OutputStreamWriter opw=new OutputStreamWriter(os);
                                                BufferedWriter writer=new BufferedWriter(opw);
                                                JSONObject js=new JSONObject();
                                                js.put("TaskId",TaskId);
                                                js.put("TaskTime",TaskTime);
                                                js.put("userId",1);
                                                writer.write(String.valueOf(js));
                                                writer.flush();
                                                writer.close();
                                                //请求
                                                Connection.connect();

                                                //获得响应
                                                InputStream is=Connection.getInputStream();
                                                InputStreamReader reader=new InputStreamReader(is);
                                                BufferedReader bufferedReader=new BufferedReader(reader);

                                            } catch (MalformedURLException e) {
                                                e.printStackTrace();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }


                                    }.start();
                                    //完成任务
                                    //1. 创建Intent对象

                                    Intent intent = new Intent();

                                    //2. 指定跳转路线

                                    intent.setClass(StartTimeActivity.this,

                                            SecondFragment.class);
                                    //3. 进行跳转

                                    startActivity(intent);

                                }else{
                                    //继续执行倒计时！！！
                                    dialogInterface.dismiss();
                                }
                            }
                        })
                        .create();
                alertDialog3.show();

            }
        });
        Button begin = findViewById(R.id.btn_begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fCount++;
//                if(fCount == 5){
//                    Intent intent = new Intent();
//                    intent.setClass(StartTimeCountActivity.this,MainActivity.class);
//                    startActivity(intent);
//                }
                flag=false;
                thread.interrupt();
                mMin=TaskTime;
                mSecond = 00;
                minutesTv.setText(mMin+"");
                secondsTv.setText(mSecond+"0");
                thread = new Thread(myThread);
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private class MyThread implements Runnable{

        @Override
        public void run() {
            while (flag&&mCount>=0) {
                try {
                    Thread.sleep(1000); // sleep 1000ms
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
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 25;
            }
        }
    }
}
