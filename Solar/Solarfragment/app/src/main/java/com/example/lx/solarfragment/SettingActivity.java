package com.example.lx.solarfragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    ImageView leftback;
    LinearLayout sing1;
    LinearLayout sing2;
    LinearLayout sing3;
    Switch aSwitch;
    LinearLayout image;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        leftback = findViewById(R.id.back);
        leftback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.this.finish();
            }
        });

        sing1=findViewById(R.id.sing1);
        sing2=findViewById(R.id.sing2);
        sing3=findViewById(R.id.sing3);
        aSwitch=findViewById(R.id.switch1);
        image=findViewById(R.id.image);

        userId=getIntent().getIntExtra("userId",0);
        tv1=findViewById(R.id.tv_sing1);
        tv2=findViewById(R.id.tv_sing2);
        tv3=findViewById(R.id.tv_sing3);

    //    back=findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SettingActivity.this.finish();
//            }
//        });
        Intent intent=this.getIntent();
        tv3.setText(intent.getStringExtra("name"));



        sing1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setPositiveButton("不用声音提醒", new DialogInterface.OnClickListener() {		//增加一个成功按钮,并增加点击事件
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv1.setText("不用声音提醒");
                    }
                });
                builder.setNeutralButton("震动提醒", new DialogInterface.OnClickListener() {		//增加一个成功按钮,并增加点击事件
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv1.setText("震动提醒");
                    }
                });
                builder.setNegativeButton("铃声提醒", new DialogInterface.OnClickListener() {	//增加一个中间的按钮,并增加点击事件
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv1.setText("铃声提醒");
                    }
                });
                builder.setCancelable(false);				//弹出框不可以按返回取消
                AlertDialog dialog = builder.create();		//创建对话框
                dialog.setCanceledOnTouchOutside(false);	//设置对话框失去焦点不会消息
                dialog.show();			//弹出
            }
        });

        sing2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setPositiveButton("不用声音提醒", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv2.setText("不用声音提醒");
                    }
                });
                builder.setNeutralButton("震动提醒", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv2.setText("震动提醒");
                    }
                });
                builder.setNegativeButton("铃声提醒", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv2.setText("铃声提醒");
                    }
                });
                builder.setCancelable(false);				//弹出框不可以按返回取消
                AlertDialog dialog = builder.create();		//创建对话框
                dialog.setCanceledOnTouchOutside(false);	//设置对话框失去焦点不会消息
                dialog.show();			//弹出

            }
        });

        sing3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,MySingActivity.class);
                startActivity(intent);


            }
        });
//        aSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CompoundButton.OnCheckedChangeListener listener=new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                         if(isChecked){
//                             //设置震动
//                         }
//                         else{
//                             //关闭震动
//                         }
//                    }
//                };
//                aSwitch.setOnCheckedChangeListener(listener);
//            }
//        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,MyPictureActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
    }
}
