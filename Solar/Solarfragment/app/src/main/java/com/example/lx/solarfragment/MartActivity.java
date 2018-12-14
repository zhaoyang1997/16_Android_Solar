package com.example.lx.solarfragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.lx.solarfragment.adapter.MartAdapter;
import com.example.lx.solarfragment.bean.Buy;
import com.example.lx.solarfragment.bean.MyImg;

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

public class MartActivity extends AppCompatActivity {

    private List<MyImg> myImgs = new ArrayList<>();
    private List<Buy> buys = new ArrayList<>();
    private ImageView MartBackLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mart);

        MartBackLeft = findViewById(R.id.iv_back_eft);
        MartBackLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MartActivity.this.finish();
            }
        });

        ImgListTask imgListTask = new ImgListTask();
        imgListTask.execute();
    }

    /**
     * 获取商城壁纸数据
     */
    public class ImgListTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                URL url = new URL("http://10.7.89.187:8080/Solar/ImgServlet");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("contentType","UTF-8");
                InputStream is = connection.getInputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer))!=-1) {
                    String res = new String(buffer,0,len);
                    //解析Json格式
                    JSONArray array = new JSONArray(res);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        MyImg myImg = new MyImg();
                        myImg.setId(object.getInt("id"));
                        String src = object.getString("image");
                        myImg.setImageUrl(src);
                        Bitmap bitmap = BitmapFactory.decodeStream(new URL(src).openStream());
                        myImg.setBitmap(bitmap);
                        myImg.setPrice(object.getString("price"));
                        myImgs.add(myImg);
                    }
                }
                Log.e("ImgList",myImgs.toString());





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
            MartAdapter myAdapter = new MartAdapter(MartActivity.this,
                    R.layout.item_mart,myImgs);
            //设置Adapter
            GridView gridView = findViewById(R.id.gv_mart);
            gridView.setAdapter(myAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
        }
    }


}
