package com.example.lx.solarfragment;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
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
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.lx.solarfragment.fragment.FirstFragment;

import org.json.JSONArray;
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

public class LoginActivity extends AppCompatActivity {

    private EditText loginname;
    private EditText pwd;
    private Button   login;
    private String lname;
    private String password;
    private Button btnBack;
    private int userId;
    private Button btnForget;
    private Button btnNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnBack=findViewById(R.id.back);
        btnForget=findViewById(R.id.btn_forget);
        btnNew=findViewById(R.id.newregister);
        btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,ForgetActivity.class);
                startActivity(intent);
            }
        });
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               LoginActivity.this.finish();
            }
        });
        loginname=findViewById(R.id.login_name);
        pwd=findViewById(R.id.password);
        login=findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginTask loginTask=new LoginTask();
                loginTask.execute();
            }
        });
       // login();
    }

//    public  void login(){
//        loginname=findViewById(R.id.login_name);
//        pwd=findViewById(R.id.password);
//        login=findViewById(R.id.login);
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoginTask loginTask=new LoginTask();
//                loginTask.execute();
//            }
//        });
//    }

    public class LoginTask extends AsyncTask{
        String rs;
        protected void onPostExecute(Object o) {

            //如果登录名或密码为空时
            if (password.length()==0 || lname.length()==0) {
                //创建Toast对象
                Toast toast = Toast.makeText(LoginActivity.this,
                        "密码或用户不能为空",
                        Toast.LENGTH_LONG);
                //设置Toast显示的位置
                toast.setGravity(Gravity.BOTTOM, 0,0);
                //显示Toast
                toast.show();}
            rs=null;
            super.onPostExecute(o);
        }




        @Override
        protected Object doInBackground(Object[] objects) {
            String path="http://10.7.89.187:8080/Solar/loginServlet";
            try {
                URL url=new URL(path);
                try {
                    HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                    //发送post请求
                    connection.setRequestProperty("contentType","utf-8");
                    connection.setRequestMethod("POST");
                    OutputStream os=connection.getOutputStream();
                    OutputStreamWriter opw=new OutputStreamWriter(os);
                    BufferedWriter writer=new BufferedWriter(opw);
                    lname=loginname.getText().toString();
                    password=pwd.getText().toString();
                    if(lname.length()!=0 && password.length()!=0){
                        JSONObject object=new JSONObject();
                        object.put("username",lname);
                        object.put("password",password);
                        Log.e("person",String.valueOf(object));
                        writer.write(String.valueOf(object));
                        writer.flush();
                        writer.close();
                        //请求
                        connection.connect();
                        Message message=Message.obtain();
                        //获得响应
                        InputStream is=connection.getInputStream();
                        InputStreamReader reader=new InputStreamReader(is);
                        BufferedReader bufferedReader=new BufferedReader(reader);
                        String res=bufferedReader.readLine();

                        if(res==null){
                            message.what=1;
                            handler.sendMessage(message);
                        }else{

                            try {

                                JSONObject object1 = new JSONObject(res);
                                userId=object1.getInt("userId");
                                Log.e("userId",String.valueOf(userId));
                                message.what=2;
                                handler.sendMessage(message);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }}
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }


        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what){
                    case 1:
                        //创建Toast对象
                        Toast toast1 = Toast.makeText(LoginActivity.this,
                                "密码或用户输入错误",
                                Toast.LENGTH_LONG);
                        //设置Toast显示的位置
                        toast1.setGravity(Gravity.BOTTOM, 0,0);
                        //显示Toast
                        toast1.show();
                        break;
                    case 2:
                        //创建Toast对象
                        Toast toast= Toast.makeText(LoginActivity.this,
                                "登录成功！",
                                Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM, 0,0);
                        // 显示Toast
                        toast.show();
                        //  封装    携带数据到另一个页面
                        Intent intent=new Intent(LoginActivity.this,MyActivity.class);
                        intent.putExtra("userName",lname);
                        intent.putExtra("userId",userId);
                        startActivity(intent);
                        break;

                }
            }
        };

    }
}
