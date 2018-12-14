package com.example.lx.solarfragment.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lx.solarfragment.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FifthFragment extends BaseFragment {
    private Button btn;
    private Button btnWeek;
    private Button btnYear;
    private Button btnMonth;
    private static LineChart chart;
    private TextView textView;
    private View view;
    private static int userId;
    private static TextView tv1;
    private static TextView tv2;
    private static TextView tv3;

    @SuppressLint("ResourceAsColor")
    public View initView(){
        if(view==null){
            view=View.inflate(mainActivity, R.layout.thirdfragment_layout,null);
            tv1=view.findViewById(R.id.tv1);
            tv2=view.findViewById(R.id.tv2);
            tv3=view.findViewById(R.id.tv3);
            btn=(Button) view.findViewById(R.id.btn2);
            btnMonth=view.findViewById(R.id.month);
            btnMonth.setBackgroundResource(R.drawable.sun3);
            Button btnTime=view.findViewById(R.id.btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainActivity.switchFragment("fragment5","fragment4");
                }
            });
            textView=view.findViewById(R.id.title1);
            textView.setText("过去一个月");
            btnWeek=(Button) view.findViewById(R.id.week);
            btnYear=view.findViewById(R.id.year);
            btnWeek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainActivity.switchFragment("fragment5","fragment3");
                }
            });
            btnYear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainActivity.switchFragment("fragment5","fragment6");
                }
            });
            chart=view.findViewById(R.id.linechart);
        }
        return view;
    }

    public void setData(int data) {
        userId = data;
    }

    private static String getYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String s = sdf.format(new Date());
        return s;
    }
    private static String getMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String s = sdf.format(new Date());
        return s;
    }
    private static String getDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String s = sdf.format(new Date());
        return s;
    }
    //连接数据库
    public static class Sum extends AsyncTask {
        private List<Entry> entries = new ArrayList<Entry>();
        private String sumTomato;
        private String sumTask;
        private String sumTaskNotFinish;
        protected Object doInBackground(Object[] objects){
            URL url= null;
            try {
                url = new URL("http://10.7.89.187:8080/Solar/SumServlet");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("contentType","UTF-8");
                OutputStream os=connection.getOutputStream();
                OutputStreamWriter opw=new OutputStreamWriter(os);
                BufferedWriter writer=new BufferedWriter(opw);
                writer.write("year="+getYear()+"&month="+getMonth()+"&day="+getDay()+"&num=2&userId="+userId);
                writer.flush();
                writer.close();
                connection.connect();
                InputStream is = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String res = reader.readLine();
                //解析Json格式
                JSONArray array = new JSONArray(res);
                for (int i = 0; i < (array.length()-3); i++) {
                    JSONObject object = array.getJSONObject(i);
                    String sum = object.getString("sum");
                    int sum1 = Integer.parseInt(sum);
                    Log.e("sum", sum1 + "");
                    entries.add(new Entry(i, sum1));

                }
                JSONObject object1 = array.getJSONObject((array.length()-3));
                sumTomato=object1.getString("sumTomato");
                JSONObject object2 = array.getJSONObject((array.length()-2));
                sumTask=object2.getString("sumTask");
                JSONObject object3 = array.getJSONObject((array.length()-1));
                sumTaskNotFinish=object3.getString("sumTaskNotFinish");
                reader.close();
                inputStreamReader.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return entries;

        }
        protected void onPostExecute(Object o) {
            LineDataSet dataSet=new LineDataSet(entries,"Label");
            dataSet.setColor(Color.GREEN);
            dataSet.setCircleRadius(3f);
            dataSet.setLineWidth(1f);
            LineData lineData=new LineData(dataSet);
            //添加的图表中
            chart.setData(lineData);
            //获取x轴
            XAxis xAxis=chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            //获取Y轴右边的轴线
            YAxis rightAxis=chart.getAxisRight();
            rightAxis.setEnabled(false);
            //绘制图表
            chart.invalidate();

            tv1.setText("专注了"+sumTomato+"个番茄时间");
            tv2.setText("完成了"+sumTask+"个任务");
            tv3.setText("未完成"+sumTaskNotFinish+"个任务");
        }

    }


}
