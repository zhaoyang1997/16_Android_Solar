package com.example.lx.solarfragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lx.solarfragment.adapter.CustomAdapter;
import com.example.lx.solarfragment.bean.Goods;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class IntegralActivity extends AppCompatActivity {

    private ImageView ivImage;
    private TextView tvScore;
    List<Goods> list = new ArrayList<>();
    private Button btnBack;
    private String lname;
    private int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        userId=getIntent().getIntExtra("userId",0);
        lname=getIntent().getStringExtra("username");
        btnBack=findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntegralActivity.this.finish();

            }
        });
        ivImage = findViewById(R.id.iv_image);
        tvScore = findViewById(R.id.tv_score);
        ScoreDetailTask scoreDetailTask = new ScoreDetailTask();
        scoreDetailTask.execute();



    }


   public class ScoreDetailTask extends AsyncTask {

        @Override
        protected List<Goods> doInBackground(Object[] objects) {

            try {
                URL url = new URL("http://10.7.89.189:8080/Solar/ScoreDetailServlet");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("contentType","UTF-8");

                InputStream is = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String res = reader.readLine();
                //解析JSon格式
                try {
                    JSONObject object = new JSONObject(res);
                    //头像
                    String user_image = "http://10.7.89.189.8080"+object.getString("user_image");
                    Bitmap bitmap= BitmapFactory.decodeStream(new URL(user_image).openStream());
                    ivImage.setImageBitmap(bitmap);

                    //积分
                    int score = object.getInt("user_score");
                    tvScore.setText(String.valueOf(score));

                    //任务和番茄
                    int num1 = object.getInt("num1");
                    int num2 = object.getInt("num2");
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
            //创建自定义Adapter对象
            CustomAdapter customAdapter = new CustomAdapter(IntegralActivity.this,list,R.layout.activity_list);
            //绑定adapter
            listView.setAdapter(customAdapter);
            super.onPostExecute(o);
        }
    }
}
