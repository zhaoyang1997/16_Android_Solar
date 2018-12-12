package com.example.lx.solarfragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lx.solarfragment.bean.Task;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateTaskActivity extends AppCompatActivity {
    private EditText taskName;
    private EditText taskTime;
    private Button finish;
    private String Tname;
    private int Ttime;
    private String Year;
    private String Month;
    private String Day;
    private int  TaskId;
    private String TaskState;
    private String TaskName;
    private int TaskTime;
    private  Bundle bundle;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        taskName = findViewById(R.id.taskname);
        taskTime = findViewById(R.id.tasktime);
        finish = findViewById(R.id.finish);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateTaskActivity.this.finish();
            }
        });
        final Task task3=(Task) getIntent().getSerializableExtra("Task");
        taskName.setText(task3.getTaskName());
        taskTime.setText(task3.getDateTime()+"");

        TaskState=task3.getTaskState();
        Log.e("任务状态",TaskState);
        TaskId=task3.getTaskId();
        TaskName=task3.getTaskName();
        TaskTime=task3.getDateTime();

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Tname = taskName.getText().toString();
                Ttime = Integer.parseInt(taskTime.getText().toString());//获取int类型的值
                Year = getYear();
                Month = getMonth();
                Day = getDay();

                UpdateTask();

                Intent intent = new Intent();
                Task task2 = new Task(Tname, Ttime,TaskState);
                intent.putExtra("responseTask", task2);
                intent.putExtra("index", getIntent().getIntExtra("index", 0));
                //返回响应的数据
                setResult(200, intent);
                //结束当前的Activity
                finish();
            }
        });
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

    //根据TaskId修改内容
    public void UpdateTask(){
        new Thread(){
            @Override
            public void run() {
                String path="http://10.7.89.187:8080/Solar/UpdateTaskServlet";
                try {
                    URL url=new URL(path);
                    HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("contentType","utf-8");
                    connection.setRequestMethod("POST");
                    OutputStream os=connection.getOutputStream();
                    OutputStreamWriter opw=new OutputStreamWriter(os);
                    BufferedWriter writer=new BufferedWriter(opw);
                    //构建Message对象
                    final Message message = Message.obtain();

                    //判断如果修改的内容和时间为空时，给出弹框
                    if(Tname.equals("")|| Ttime==0){
                        message.what=2;
                        handler.sendMessage(message);
                    }else{
                        //根据TaskId去数据库中改相应修改的内容
                        JSONObject js=new JSONObject();
                        js.put("TaskId",TaskId);
                        js.put("Tname",Tname);
                        js.put("Ttime",Ttime);
                        js.put("Tyear",Year);
                        js.put("Tmonth",Month);
                        js.put("Tday",Day);
                        js.put("Tstate",TaskState);
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
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
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
                    UpdateTaskActivity.this.finish();
                    break;
                case 2:

                    //创建Toast对象
                    Toast toast = Toast.makeText(UpdateTaskActivity.this,
                            "任务名称或任务时长不能为空",
                            Toast.LENGTH_LONG);
                    //设置Toast显示的位置
                    toast.setGravity(Gravity.BOTTOM, 0,0);
                    //显示Toast
                    toast.show();
                    break;
            }}
    };
}