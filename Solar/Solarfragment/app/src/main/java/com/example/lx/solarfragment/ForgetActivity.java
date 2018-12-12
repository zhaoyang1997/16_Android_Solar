package com.example.lx.solarfragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ForgetActivity extends AppCompatActivity {

    EditText etName,etPhone,etEmail;
    TextView tvName;
    Button button;
    private Button btnBack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        etName=findViewById(R.id.et_name);
        etPhone=findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);
        tvName = findViewById(R.id.tv_name);
        button = findViewById(R.id.btn);
        btnBack=findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgetActivity.this.finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Revise revise = new Revise();
                revise.execute();
            }
        });
    }
    //连接数据库
    public class Revise extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                URL url =new URL("http://10.7.89.189:8080/SolarServer/UserServlet");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("contentType","UTF-8");
                InputStream is = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String res = reader.readLine();
                Log.e("youxiang",res);
                JSONObject object = new JSONObject(res);
                //获取数据库数据
                String name1=object.getString("name");
                String phone1=object.getString("phone");
                String email1 = object.getString("email");
                reader.close();
                inputStreamReader.close();
                is.close();
                //获取页面数据
                String name2=etName.getText().toString();
                String phone2 = etPhone.getText().toString();
                String email2 = etEmail.getText().toString();
                Log.e("youxiang",etEmail.getText().toString());
                //判断数据是否相等
                if(name1.equals(name2)&&phone1.equals(phone2)&&email1.equals(email2)){
                    //进行页面跳转
                    Intent intent = new Intent();
                    intent.setClass(ForgetActivity.this,ReviseActivity.class);
                    startActivity(intent);
                }else {
                    tvName.setText("用户名输/手机号/密码入不正确");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
