package com.example.lx.solarfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity {
    private EditText name;
    private EditText pwd;
    private EditText qpwd;
    private EditText el;
    private  EditText pe;
    private Button register;
    private TextView newregister;
    private String username;
    private String password;
    private String email;
    private  String phone;
    private String qpassword;
    private String rs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.username);
        pwd=findViewById(R.id.password);
        el=findViewById(R.id.email);
        pe=findViewById(R.id.phone);
        qpwd=findViewById(R.id.qpassword);
        newregister=findViewById(R.id.newregister);
        register();

    }

    public  void register(){
        register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username=name.getText().toString();
                password=pwd.getText().toString();
                email=el.getText().toString();
                phone=pe.getText().toString();
                qpassword=qpwd.getText().toString();
                RegisterTask registerTask=new RegisterTask();
                registerTask.execute();
            }
        });
    }

    public class  RegisterTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            String path="http://10.7.89.187:8080/Solar/registerServlet";
            try {
                URL url=new URL(path);
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                //发送POST请求
                connection.setRequestProperty("contentType","utf-8");
                connection.setRequestMethod("POST");
                OutputStream os=connection.getOutputStream();
                OutputStreamWriter opw=new OutputStreamWriter(os);
                BufferedWriter writer=new BufferedWriter(opw);
                //构建Message对象
                final Message message = Message.obtain();
                //向服务器传送数据前，判断用户名、密码、手机号、邮箱是否为空
                if(username.equals("")|| password.equals("")||email.equals("")||phone.equals("")){
                    message.what=1;
                    handler.sendMessage(message);
                }
                //向服务器传送数据前，并且用户名、密码、手机号、邮箱均不为空时，
                else if(!(username.equals(""))&&!(password.equals(""))&&!(email.equals(""))&&!(phone.equals(""))){
                    // 判断密码长度是否大于6
                    if (password.length() < 6) {
                        message.what = 2;

                        handler.sendMessage(message);}

                    //判断电话号码格式是否正确
                    else {

                         /*
                            移动：134、135、136、137、138、139、150、151、152、157(TD)、158、159、178(新)、182、184、187、188
                            联通：130、131、132、152、155、156、185、186
                            电信：133、153、170、173、177、180、181、189、（1349卫通）
                            总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
                            */
                        String num = "^[1][3,5,8][0-9]{9}$";//"[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
                        if (phone.matches(num)) {

                            //判断邮箱格式是否正确
                            String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
                            if(email.matches(str)){
                                //验证两次输入的密码是否一致
                                if(password.equals(qpassword)){
                                    try {

                                        JSONObject js=new JSONObject();
                                        js.put("username", username);
                                        js.put("password",password);
                                        js.put("email",email);
                                        js.put("phone",phone);
                                        Log.e("person",String.valueOf(js));
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
                                        rs=bufferedReader.readLine();
                                        Log.e("返回消息",rs);
                                        //判断用户名是否可用
                                        if(rs.equals("用户名已存在，请更改用户名")){
                                            message.what=5;
                                            handler.sendMessage(message);
                                        }else{
                                            message.what=7;
                                            handler.sendMessage(message);
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    message.what=3;//密码不一致
                                    handler.sendMessage(message);
                                }
                            }else{
                                message.what=6;//邮箱格式不正确
                                handler.sendMessage(message);
                            }


                        } else {

                            message.what=4;//手机号格式不正确
                            handler.sendMessage(message);

                        }
                    }
                }



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        //定义handler

        Handler handler = new Handler(){

            @Override

            public void handleMessage(Message msg) {

                switch (msg.what){

                    case 1:

                        Toast.makeText(getApplicationContext(),

                                "用户名/密码/手机号/邮箱不能为空",

                                Toast.LENGTH_LONG)

                                .show();

                        break;

                    case 2:

                        Toast.makeText(getApplicationContext(),

                                "您输入的密码长度必须大于6个字符",

                                Toast.LENGTH_LONG)

                                .show();

                        break;

                    case 3:

                        Toast.makeText(getApplicationContext(),

                                "两次密码输入不一致，请重新输入",

                                Toast.LENGTH_LONG)

                                .show();

                        break;

                    case 4:

                        Toast.makeText(getApplicationContext(),

                                "亲，您所输入的手机号码格式有误哦~",

                                Toast.LENGTH_LONG)

                                .show();

                        break;

                    case 5:

                        Toast.makeText(getApplicationContext(),

                                "该用户名已被注册",

                                Toast.LENGTH_LONG)

                                .show();

                        break;
                    case 6:
                        Toast.makeText(getApplicationContext(),
                                "邮箱格式输入不正确",
                                Toast.LENGTH_LONG)
                                .show();
                        break;

                    case 7:

                        Log.e("chuce","chuce");
                        //页面跳转

                        //1. 创建Intent对象

                     //   Intent intent = new Intent();

                        //2. 指定跳转路线

                     //   intent.setClass(RegisterActivity.this,

                        //        LoginActivity.class);

                        //3. 进行跳转

                      //  startActivity(intent);

                        break;

                }

                super.handleMessage(msg);

            }

        };
    }
}
