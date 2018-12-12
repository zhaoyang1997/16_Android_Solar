package com.example.lx.solarfragment.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lx.solarfragment.MartActivity;
import com.example.lx.solarfragment.MyActivity;
import com.example.lx.solarfragment.R;
import com.example.lx.solarfragment.bean.MyImg;

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
import java.util.List;

public class MartAdapter extends BaseAdapter {

    private String boughtState;
    int imageId;
    int userId;
    String imageUrl;
    String imageScore;
    private TextView buttonBuy;
    private List<MyImg> myImgs;
    private int itemMart;
    private MartActivity context;
    private Handler handler = new Handler();

    public MartAdapter(){}

    public MartAdapter(MartActivity context, int itemMart, List<MyImg> myImgs){
        this.context=context;
        this.itemMart = itemMart;
        this.myImgs = myImgs;
    }

    @Override
    public int getCount() {
        return myImgs.size();
    }

    @Override
    public Object getItem(int position) {
        return myImgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(null == convertView){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(itemMart,null);
        }

        final ImageView ivBgImg = convertView.findViewById(R.id.iv_bgimg);
        ivBgImg.setImageBitmap(myImgs.get(position).getBitmap());

        final View finalConvertView = convertView;
        /**
         * 给每个item中的图片设置点击事件
         */
        ivBgImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonBuy = finalConvertView.findViewById(R.id.btn_buy_state);
                imageId = myImgs.get(position).getId();
                imageScore = myImgs.get(position).getPrice();
                imageUrl = myImgs.get(position).getImageUrl();
                userId = MyActivity.USER_ID;
                BuyStateTask buyStateTask = new BuyStateTask();
                buyStateTask.execute();

            }
        });

        TextView tvPrice = convertView.findViewById(R.id.tv_price);
        tvPrice.setText(myImgs.get(position).getPrice());

        return convertView;
    }

    /**
     * 检查当前商品是否已被当前在线用户购买  数据验证线程
     */
    public class BuyStateTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {

                URL url = new URL("http://10.7.89.78:8080/data_get/BuyStateServlet");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("contentType", "UTF-8");
                OutputStream os = connection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
                BufferedWriter writer = new BufferedWriter(outputStreamWriter);
                JSONObject object = new JSONObject();
                object.put("user_id",userId);
                object.put("image_id",imageId);
                writer.write(String.valueOf(object));
                connection.connect();
                writer.flush();
                writer.close();

                InputStream is = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String res = reader.readLine();
                Log.e("res", res);
                JSONObject objectJSON = new JSONObject(res);
                boughtState = objectJSON.getString("bought_state");

                reader.close();
                inputStreamReader.close();
                is.close();


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

            /**
             * 如果当前壁纸没有被该用户购买，显示兑换，可进行兑换操作
             */
            if(boughtState.equals("NotBought")){
                buttonBuy.setText("兑换");
                Log.e("State@@@",boughtState);
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() { // 5秒后执行该方法
                        // handler自带方法实现定时器
                        try {
                            buttonBuy.setVisibility(View.GONE); // 隐藏
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                handler.postDelayed(runnable,2000);
                BtnChange();

            }

            /**
             *如果当前壁纸没有被该用户购买，显示已兑换，不可以进行操作
             */
            if(boughtState.equals("AlreadyBought")){
                buttonBuy.setText("已兑换");
                Log.e("State@@@",boughtState);
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() { // 5秒后执行该方法
                        // handler自带方法实现定时器
                        try {
                            buttonBuy.setVisibility(View.GONE); // 隐藏
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                handler.postDelayed(runnable,1000);
            }

            buttonBuy.setVisibility(View.VISIBLE);


        }
    }

    /**
     * 用户确定购买后（并且条件允许时），更改数据库buy表的数据，信息提示，操作
     */

    public void BtnChange(){
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder checkBuilder = new AlertDialog.Builder(context);
                checkBuilder.setTitle("确定");
                checkBuilder.setMessage("您确定要进行兑换吗？");
                checkBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int userScore = MyActivity.USER_SCORE;
                        int score = Integer.parseInt(imageScore);
                        if(userScore < score){
                            /**
                             * 积分不足，不允许继续购买操作并弹出信息提示
                             */
                            Toast toastTip = Toast.makeText(context," 积分不足 ",Toast.LENGTH_SHORT);
                            toastTip.setGravity(Gravity.CENTER, 0, 0);
                            toastTip.show( );
                        }else {
                            BuyUpdateTask buyUpdateTask = new BuyUpdateTask();
                            buyUpdateTask.execute();
                        }


                    }
                });
                checkBuilder.setNegativeButton("取消", null);
                AlertDialog checkAlert = checkBuilder.create( );
                checkAlert.show();



            }
        });

    }

    /**
     * 确定购买并允许购买时，进行数据库数据修改更新的线程
     */
    public class BuyUpdateTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {

                URL url = new URL("http://10.7.89.78:8080/data_get/BuyUpdateServlet");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("contentType", "UTF-8");
                OutputStream os = connection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
                BufferedWriter writer = new BufferedWriter(outputStreamWriter);
                JSONObject object = new JSONObject();
                object.put("user_id",userId);
                object.put("image_id",imageId);
                object.put("score",imageScore);
                object.put("imageurl",imageUrl);
                writer.write(String.valueOf(object));
                connection.connect();
                writer.flush();
                writer.close();

                InputStream is = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(inputStreamReader);

                reader.close();
                inputStreamReader.close();
                is.close();


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

        }
    }
}
