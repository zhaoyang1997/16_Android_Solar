package com.example.lx.solarfragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lx.solarfragment.adapter.CustomAdapter;
import com.example.lx.solarfragment.bean.Goods;

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
import java.util.ArrayList;
import java.util.List;


public class IntegralActivity extends AppCompatActivity {

    private TextView tvScore;
    List<Goods> list = new ArrayList<>();
    private ImageView btnBack;
    private String lname;
    private int userId;
    private int score;
    private int num1;
    private int num2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        userId=getIntent().getIntExtra("userId",0);
        lname=getIntent().getStringExtra("username");

        btnBack = findViewById(R.id.iv_back_eft);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntegralActivity.this.finish();
            }
        });

        tvScore = findViewById(R.id.tv_score);
        ScoreDetailTask scoreDetailTask = new ScoreDetailTask();
        scoreDetailTask.execute();



    }


   public class ScoreDetailTask extends AsyncTask {

        @Override
        protected List<Goods> doInBackground(Object[] objects) {

            try {
                URL url = new URL("http://10.7.89.37:8080/Solar/ScoreDetailServlet");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("contentType","UTF-8");

                OutputStream os=connection.getOutputStream();
                OutputStreamWriter opw=new OutputStreamWriter(os);
                BufferedWriter writer=new BufferedWriter(opw);
                JSONObject js=new JSONObject();
                try {
                    js.put("userId",userId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                writer.write(String.valueOf(js));
                writer.flush();
                writer.close();

                connection.connect();
                InputStream is = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String res = reader.readLine();
                //解析JSon格式
                try {
                    JSONObject object = new JSONObject(res);
                    //积分
                    score = object.getInt("user_score");

                    //任务和番茄
                    num1 = object.getInt("num1");
                    num2 = object.getInt("num2");
                    for(int i=0;i<num1;i++){
                        Goods goods = new Goods();
                        goods.setName(object.getString("task_name["+i+"]"));
                        goods.setScore(object.getString("task_score["+i+"]"));
                        list.add(goods);
                    }
                    for(int j=0;j<num2;j++){
                        Goods goods = new Goods();
                        goods.setName(object.getString("tomato_name["+j+"]"));
                        goods.setScore(object.getString("tomato_score["+j+"]"));
                        list.add(goods);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(Object o) {
            ListView listView = findViewById(R.id.lv_task);
            Log.e("11","11");
            tvScore.setText(String.valueOf(score));
            //创建自定义Adapter对象
            CustomAdapter customAdapter = new CustomAdapter(IntegralActivity.this,list,R.layout.activity_list);
            //绑定adapter
            listView.setAdapter(customAdapter);
            super.onPostExecute(o);
        }
    }
}
