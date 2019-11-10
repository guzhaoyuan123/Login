package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class FunctionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:// 切换登录界面
                Intent intent = new Intent(FunctionActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.life:
                showLifeCycle();
                break;
            case R.id.del1:
                sendSimpleValue1();
                break;
            case R.id.del2:
                sendSimpleValue2();
                break;
            case R.id.take1:
                sendArray();
                break;
            case R.id.take2:
                sendObject();
                break;
            case R.id.take3:
                sendObjiectSimple();
                break;
            case R.id.return1:
                sentReturn();
                break;
            case R.id.call:
                sendPhone();
                break;
            case R.id.message:
                sendSMS();
                break;
            case R.id.email:
                sendEmail();
                break;
            case R.id.http:
                break;
            case R.id.clock:
                break;
            case R.id.qi:
                break;
            case R.id.show:
                break;
        }
    }

    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        String[] receivers = {"2469754120@qq.com"};
        String[] subjects = {"test"};
        String body = "测试Email";

        intent.putExtra(Intent.EXTRA_EMAIL,receivers);
        intent.putExtra(Intent.EXTRA_SUBJECT,subjects);
        intent.putExtra(Intent.EXTRA_TEXT,body);
        intent.setType("text/plain");

        startActivity(Intent.createChooser(intent,"Select email application."));
    }

    private void sendSMS() {
        Uri uri = Uri.parse("smsto:5556");
        Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
        startActivity(intent);
    }

    private void sendPhone() {
        Uri uri = Uri.parse("tel:19805026167");
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    private void sentReturn() {
        Intent intent = new Intent(FunctionActivity.this,IntentActivity.class);
        intent.putExtra("flag",6);

        intent.putExtra("send","发送值，等待返回...");
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode == 100 && requestCode ==Activity.RESULT_OK){
           if (data!=null){
               String returnData = data.getStringExtra("username");
               TextView tvReturn = findViewById(R.id.tv_data);
               tvReturn.setText(returnData!=null?returnData:"为获取到值");
           }
       }

    }

    private void sendObjiectSimple() {
        //1. 创建Intent
        Intent intent = new Intent(FunctionActivity.this,IntentActivity.class);
        //2. 通过Bundle获取数组或列表
        Bundle bundle = new Bundle();
        String[] clss = {"移动1813","移动1811","移动1823"};
        String[] name = {"张三","李四","王二麻"};
        String[] age = {"20","19","20"};
        bundle.putStringArray("clss",clss);
        bundle.putStringArray("name",name);
        bundle.putStringArray("age",age);

        intent.putExtras(bundle);
        //3. 启动Activity
        startActivity(intent);

    }

    private void sendObject() {
        //1. 创建Intent
        Intent intent = new Intent(FunctionActivity.this,IntentActivity.class);
        //2. 传递简单类型数据
       Bundle bundle = new Bundle();
       User user = new User("张三", 20,80.5f);
       bundle.putSerializable("user",user);
       intent.putExtras(bundle);
        //3. 启动Activity
        startActivity(intent);
    }

    private void sendArray() {
        //1. 创建Intent
        Intent intent = new Intent(FunctionActivity.this,IntentActivity.class);
        //2. 通过Bundle获取数组或列表
        Bundle bundle = new Bundle();
      //列表
        ArrayList<String> cls = new ArrayList<>();
        cls.add("移动1813");
        cls.add("移动1811");
        cls.add("移动1823");
      //数组
        String[] clss = {"移动1813","移动1811","移动1823"};

        bundle.putStringArrayList("cls",cls);
        bundle.putStringArray("clss",clss);

        intent.putExtras(bundle);
        //3. 启动Activity
        startActivity(intent);
    }


    private void sendSimpleValue1() {
        //1. 创建Intent
        Intent intent = new Intent(FunctionActivity.this,IntentActivity.class);
        //2. 传递简单类型数据
        intent.putExtra("班级","移动1813");
        //3. 启动Activity
        intent.putExtra("age",19);
        startActivity(intent);
    }

    private void sendSimpleValue2() {
        //1. 创建Intent
        Intent intent = new Intent(FunctionActivity.this,IntentActivity.class);
        //2. 传递简单类型数据
        Bundle bundle = new Bundle();
        bundle.putString("班级","移动1813");
        bundle.putInt("age",19);
        intent.putExtras(bundle);
        //3. 启动Activity
        startActivity(intent);

    }

    private void showLifeCycle() {
        Intent intent = new Intent(FunctionActivity.this,LifeCycleActivity.class);
        startActivity(intent);
    }
}
