package com.example.lx.solarfragment.fragment;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lx.solarfragment.AddTaskActivity;
import com.example.lx.solarfragment.MainActivity;
import com.example.lx.solarfragment.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.lx.solarfragment.StartTimeActivity;
import com.example.lx.solarfragment.UpdateTaskActivity;
import com.example.lx.solarfragment.bean.Task;

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
import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {
    private List<Task> tasks = new ArrayList<>();
    private TextView textName;
    private TextView textTime;
    private View view;
    private int userId;
    private CustomAdapter customAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.secondfragment_layout,container,false);
        Log.e("userId",userId+"");
        //给添加任务加一个监听器实现页面跳转到AddTaskActivity
        ImageView Add=view.findViewById(R.id.jiahao);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //页面跳转

                //1. 创建Intent对象

                Intent intent = new Intent();

                //2. 指定跳转路线

                intent.setClass(getActivity(),

                        AddTaskActivity.class);

                //3. 进行跳转

                startActivity(intent);
            }
        });
        //显示业务列表
        Log.e("userId",userId+"");
        showTask();
        return view;
    }


    public void setData(int data) {
        userId = data;
    }

   public void showTask(){
        new Thread(){
            @Override
            public void run() {
                String path="http://10.7.89.187:8080/Solar/ShowTaskServlet";
                try {
                    URL url=new URL(path);
                    HttpURLConnection connection= null;
                    try {
                        connection = (HttpURLConnection) url.openConnection();
                        //发送post请求
                        connection.setRequestProperty("contentType","utf-8");
                        connection.setRequestMethod("POST");
                        InputStream is=connection.getInputStream();
                        final Message message = Message.obtain();
                        InputStreamReader inputStreamReader = new InputStreamReader(is);
                        BufferedReader reader = new BufferedReader(inputStreamReader);
                        String res = reader.readLine();
                        //解析Json格式

                        try {
                            JSONArray  array = new JSONArray(res);
                            for (int i = 0; i < array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                Task task= new Task();
                                task.setTaskName(object.getString("taskName"));
                                task.setDateTime(object.getInt("taskTime"));
                                task.setTaskId(object.getInt("taskId"));
                                task.setTaskState(object.getString("taskState"));

                                tasks.add(task);}
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("1111","1111");
                        Log.e("TaskList",tasks.toString());
                        message.what=1;
                        handler.sendMessage(message);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
    //当某个界面返回响应信息时调用
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 200){
            Task task = (Task) data.getSerializableExtra("responseTask");
            int index = data.getIntExtra("index",0);
            tasks.set(index,task);
            //更新ListView的数据集
            customAdapter.notifyDataSetChanged();
        }
    }

    public class CustomAdapter extends BaseAdapter{
        private Context context;
        private int itemLayout;
        private List<Task> tasks=new ArrayList<>();


        public CustomAdapter(Context context, int itemLayout, List<Task> tasks) {
            this.context = context;
            this.itemLayout = itemLayout;
            this.tasks = tasks;

        }

        @Override
        public int getCount() {
            return tasks.size();
        }

        @Override
        public Object getItem(int position) {
            return tasks.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            //每加载一条item就调用一次
            if(null==convertView){
                LayoutInflater layoutInflater=LayoutInflater.from(context);
                convertView=layoutInflater.inflate(itemLayout,null);
            }
            //获取每个Item的控件对象，并且设置显示的内容
            textName = convertView.findViewById(R.id.task_name);
            textTime=convertView.findViewById(R.id.task_time);

            textName.setText(tasks.get(position).getTaskName());
            textTime.setText(tasks.get(position).getDateTime()+"分钟");
//            Log.e("Name",tasks.get(position).getTaskName());
//            Log.e("名字",tasks.get(position).getTaskState());

            if((tasks.get(position).getTaskState().equals("完成"))){
                Log.e("11111","IF");
                textName.setPaintFlags(textName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                //textName.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                notifyDataSetChanged();}
            //textName .getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG );
            else {
                Log.e("11111","Else");
                //取消划线    jia上这一句才能正确显示！！！！
                textName.setPaintFlags(textName.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                notifyDataSetChanged();}
            return convertView;
        }

    }
    //定义Handler
     Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 1:

                    final ListView listView  = view.findViewById(R.id.lv_tasks);
                    //创建自定义Adapter对象
                  customAdapter = new CustomAdapter(getActivity(),
                            R.layout.list_item,tasks);
                    //绑定adapter

                    listView.setAdapter(customAdapter);
                    //item点击事件
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                            final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

                            //正极按钮
                            builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    AlertDialog.Builder builder1=new AlertDialog.Builder(getActivity());
                                    builder1.setMessage("确定删除吗？");
                                    //设置提示标题
                                    builder1.setTitle("提示");
                                    builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();

                                            //根据TaskId去数据库中删
                                            //启动一个子线程将数据库中数据也删除
                                            new Thread(){
                                                @Override
                                                public void run() {
                                                    String path="http://10.7.89.187:8080/Solar/DeleteTaskServlet";
                                                    Log.e("删除","shanshanchu");
                                                    try {
                                                        URL url=new URL(path);
                                                        HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                                                        //发送post请求
                                                        connection.setRequestProperty("contentType","utf-8");
                                                        connection.setRequestMethod("POST");
                                                        //把TaskID传过去，根据taskID在数据中删除数据
                                                        OutputStream os=connection.getOutputStream();
                                                        OutputStreamWriter opw=new OutputStreamWriter(os);
                                                        BufferedWriter writer=new BufferedWriter(opw);
                                                        JSONObject js=new JSONObject();
                                                        js.put("TaskId",tasks.get(position).getTaskId());
                                                        writer.write(String.valueOf(js));
                                                        writer.flush();
                                                        writer.close();
                                                        //请求
                                                        connection.connect();
                                                        //获得响应
                                                        InputStream is=connection.getInputStream();
                                                        InputStreamReader reader=new InputStreamReader(is);
                                                        BufferedReader bufferedReader=new BufferedReader(reader);
                                                    } catch (MalformedURLException e) {
                                                        e.printStackTrace();
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }.start();
                                          //  showTask();
                                         

                                        }
                                    });
                                    builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    Log.e("删除","111111");
                                    builder1.setCancelable(false);
                                    AlertDialog dialog1=builder1.create();
                                    dialog1.setCancelable(false);
                                    dialog1.show();
                                }
                            });
                            //中
                            builder.setNeutralButton("开始", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                    //页面跳转

                                    //1. 创建Intent对象

                                    Intent intent = new Intent();

                                    //2. 指定跳转路线

                                    intent.setClass(getActivity(),

                                            StartTimeActivity.class);
                                    //  封装    携带数据到另一个页面
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("TaskId",tasks.get(position).getTaskId());
                                    bundle.putString("TaskName",tasks.get(position).getTaskName());
                                    bundle.putInt("TaskTime",tasks.get(position).getDateTime());
                                    intent.putExtras(bundle);
                                    Log.e("TaskId", String.valueOf(tasks.get(position).getTaskId()));

                                    //3. 进行跳转

                                    startActivity(intent);

                                }
                            });
                            //负极按钮
                            builder.setNegativeButton("编辑", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    //获取每个Item的控件对象的taskID--->传到另个一个页面



                                    //页面跳转

                                    //1. 创建Intent对象

                                    Intent intent = new Intent();

                                    //2. 指定跳转路线
                                    intent.setClass(getActivity(),UpdateTaskActivity.class);
                                    intent.putExtra("Task",tasks.get(position));
                                    intent.putExtra("index",position);

                                    startActivityForResult(intent, 0);

                                    //intent.setClass(getActivity(),

                                           // UpdateTaskActivity.class);
                                    //  封装    携带数据到另一个页面

                                    //Log.e("TaskId", String.valueOf(tasks.get(position).getTaskId()));

                                    //3. 进行跳转

                                   // startActivity(intent);

                                }
                            });
                            builder.setCancelable(false);//弹出框不可以按返回取消
                            AlertDialog dialog=builder.create();//创建对话框
                            dialog.setCancelable(false);
                            dialog.show();//弹出
                        }
                    });
                    break;

            }}
    };



}
