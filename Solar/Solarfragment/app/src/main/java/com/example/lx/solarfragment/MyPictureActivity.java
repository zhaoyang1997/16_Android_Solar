package com.example.lx.solarfragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.example.lx.solarfragment.R;
import com.example.lx.solarfragment.adapter.MyAdapter;
import com.example.lx.solarfragment.bean.Picture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyPictureActivity extends AppCompatActivity {

    private List<Picture> pictures = new ArrayList<>();
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_picture);
        userId=getIntent().getIntExtra("userId",0);
        Log.e("PictureList","oncreate()");
        ImgListTask imgListTask = new ImgListTask();
        imgListTask.execute();


        MyAdapter myAdapter=new MyAdapter(pictures,this, R.layout.view);
        //设置Adapter
        GridView gridView=findViewById(R.id.gv);
        gridView.setAdapter(myAdapter);

    }

    public class ImgListTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                URL url = new URL("http://10.7.89.189:8080/Solar/SolarSettingServlet");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("contentType", "UTF-8");
                Log.e("PictureList","is之前");
                InputStream is = connection.getInputStream();
                Log.e("PictureList","is之后");
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    String res = new String(buffer, 0, len);
                    //解析Json格式
                    JSONArray array = new JSONArray(res);
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);
                        int userid = object.getInt("userid");

                        if(userid==userId) {
                        Picture picture = new Picture();
                        picture.setUserid(userid);
                        int imageid = object.getInt("imageid");
                        picture.setImageid(imageid);
                        String src = object.getString("url");
                        Bitmap bitmap = BitmapFactory.decodeStream(new URL(src).openStream());
                        picture.setImageurl(bitmap);

                        pictures.add(picture);
                                       }
                    }
                }
                    Log.e("PictureList", pictures.toString());


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
