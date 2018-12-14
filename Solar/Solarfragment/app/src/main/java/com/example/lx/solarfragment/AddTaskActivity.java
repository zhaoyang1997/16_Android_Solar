package com.example.lx.solarfragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
    private EditText taskName;
    private EditText taskTime;
    private Button finish;
    private String Tname;
    private String Ttime;
    private String Year;
    private String Month;
    private String Day;
    private ImageView back;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        taskName=findViewById(R.id.taskname);
        taskTime=findViewById(R.id.tasktime);
        userId=getIntent().getIntExtra("userId",0);
        finish=findViewById(R.id.finish);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Tname=taskName.getText().toString();
                Ttime=taskTime.getText().toString();
                Year=getYear();
                Month=getMonth();
                Day=getDay();

                AddTask();

            }
        });
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTaskActivity.this.finish();
            }
        });
        //监听返回按钮

    }
    //获得点击事件的年月日
    private String getYear(){
        SimpleDateFormat sdf =new SimpleDateFormat("yyy");
        String s=sdf.format(new Date());
        return s;
    }
    private String getMonth(){
        SimpleDateFormat sdf=new SimpleDateFormat("MM");
        String s=sdf.format(new Date());
        return s;
    }
    private String getDay(){
        SimpleDateFormat sdf=new SimpleDateFormat("dd");
        String s=sdf.format(new Date());
        return s;
    }

    //添加任务
    public void AddTask(){
        new Thread(){
            @Override
            public void run() {
                String path="http://10.7.89.187:8080/Solar/AddTaskServlet";
                try {
                    URL url=new URL(path);
                    try {
                        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                        //发送POST请求
                        connection.setRequestProperty("contentType","utf-8");
                        connection.setRequestMethod("POST");
                        OutputStream os=connection.getOutputStream();
                        OutputStreamWriter opw=new OutputStreamWriter(os);
                        BufferedWriter writer=new BufferedWriter(opw);
                        //构建Message对象
                        final Message message = Message.obtain();
                        //判断如果修改的内容和时间为空时，给出弹框
                        if(Tname.equals("")|| Ttime.equals("")){
                            message.what=2;
                            handler.sendMessage(message);
                        }else{
                            JSONObject js=new JSONObject();
                            js.put("Tname",Tname);
                            js.put("Ttime",Ttime);
                            js.put("Tyear",Year);

                            js.put("Tmonth",Month);
                            js.put("Tday",Day);
                            js.put("userid",userId);

                            Log.e("Task",String.valueOf(js));
                            //将JSON封装成字符串格式
                            writer.write(String.valueOf(js));
                            writer.flush();
                            writer.close();
                            //请求
                            connection.connect();
                            //获得响应
                            InputStream is=connection.getInputStream();
                            InputStreamReader reader=new InputStreamReader(is);
                            BufferedReader bufferedReader=new BufferedReader(reader);
                            message.what=1;
                            handler.sendMessage(message);}
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
    //定义Handler
    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {

            Log.e("what","111111");
            switch (msg.what){
                case 1:
                    Intent intent = new Intent();
                    intent.setClass(AddTaskActivity.this,
                            TaskActivity.class);
                    intent.putExtra("userId",userId);
                    startActivity(intent);
                    break;
                case 2:

                    //创建Toast对象
                    Toast toast = Toast.makeText(AddTaskActivity.this,
                            "任务名称或任务时长不能为空",
                            Toast.LENGTH_LONG);
                    //设置Toast显示的位置
                    toast.setGravity(Gravity.BOTTOM, 0,0);
                    //显示Toast
                    toast.show();
                    break;
            }
            super.handleMessage(msg);
        }
    };

}