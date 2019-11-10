package com.example.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class IntentActivity extends AppCompatActivity {
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);


        initView(); //  初始化控件对象
        initData();// 处理传递过来的数据
    }


    //功能：完成从其他地方获取的数据，并显示在相应的控件中
    private void initData() {
        //1.获取intent对象
        Intent intent = getIntent();
        //2.获取传递的数据，根据name属性的值匹配
        //2.1 通过Intent获取
//        String classmate = intent.getStringExtra("班级");
//        int age = intent.getIntExtra("age",0);
//        String result = "获取的数据：" +"\n"+ classmate + ","+"\n"+ + age;

        //2.2
        String result = "";
        Bundle bundle = intent.getExtras();
//        if (bundle!=null){
//            String classmate = bundle.getString("班级");
//            int age = bundle.getInt("age");
//            result = "获取的数据："+classmate+","+age;
//        }

        //2.3 通过Bundle获取数组或列表
//        if (bundle!=null){
//          ArrayList<String> cls = bundle.getStringArrayList("cls");
//          String[] clss = bundle.getStringArray("clss");
//          if (cls != null && !cls.isEmpty()){
//              result = "获取的数据："+"\n";
//              for (String  s : cls){
//                  result += s + "\n";
//              }
//              for (String s : clss){
//                  result += s + ",";
//              }
//              result = result.substring(0,result.length()-1);//去掉最后的那个逗号
//          }
//
//        }


        //2.4获取自定义对象
//        if (bundle !=null){
//            User user = (User) bundle.getSerializable("user");
//            if (user != null){
//                result += user; //会调用User类的toString（）
//            }
//        }

        //2.4 传递数组对象
//        if (bundle!=null){
//            String[] clss = bundle.getStringArray("clss");
//            String[] name = bundle.getStringArray("name");
//            String[] age = bundle.getStringArray("age");
//            for (String s : clss){
//                result += s + ",";
//            }
//            for (String s : name){
//                result += s + ",";
//            }
//            for (String s : age){
//                result += s + ",";
//            }
//
//        }

//        //3.将获取的数据显示到TextView
        tvData.setText(result);

    }

    // 功能：完成控件的获取，事件监听事件
    private void initView() {
        tvData = findViewById(R.id.tv_data);
    }
}
