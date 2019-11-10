package com.example.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TextViewactivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_showIP;
    private EditText et_Ip;

    private EditText etUsername;
    private RadioGroup sexGroup;
    private CheckBox chkJava, chkAndroid, chkEnghlish,chkMath;
    private Button chb_bt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_viewactivity);



        etUsername = findViewById(R.id.et_name);
        sexGroup = findViewById(R.id.rg);
        chkJava = findViewById(R.id.chb_java);
        chkAndroid = findViewById(R.id.cnb_android);
        chkEnghlish = findViewById(R.id.chb_english);
        chkMath = findViewById(R.id.chn_math);
        chb_bt1 = findViewById(R.id.bt1);
        chb_bt1.setOnClickListener(this);
        et_Ip = findViewById(R.id.et_ip);
        tv_showIP = findViewById(R.id.tv_showIP);
        et_Ip.setOnKeyListener(new View.OnKeyListener() {
            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
            @Override
            public boolean onKey(View v, int i, KeyEvent keyEvent) {
                switch (keyEvent.getAction()){
                    case KeyEvent.ACTION_UP:
                        String ip = et_Ip.getText().toString();
                        String newIp = "";
                        for (i = 0; i < ip.length()/3 ; i++ ){
                            if (i*3+3<ip.length()){
                                newIp += ip.substring(i*3,Math.min(i*3+3,ip.length()))+".";
                            }else {
                                newIp += ip.substring(i*3,Math.min(i*3+3,ip.length()));
                            }
                        }
                      //  关闭软键盘
				InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
				if (imm != null && imm.isActive()) {
					imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
				}
                        tv_showIP.setText(newIp);
                        break;
                    case KeyEvent.ACTION_DOWN:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            float x = event.getX();
            float y = event.getY();
            String pos = "X轴坐标：" + x + "\nY轴坐标：" + y;
            Toast.makeText(TextViewactivity.this , pos , Toast.LENGTH_SHORT).show();
        }else if (event.getAction() == MotionEvent.ACTION_UP){
            Toast.makeText(TextViewactivity.this,"  手  指  抬  起  ",Toast.LENGTH_SHORT).show();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt1:
            getInfo();
            break;
        }
    }

    private void getInfo(){
        String username = etUsername.getText().toString().trim();
        String sex = "男";
        String favitor = "";
        int id = sexGroup.getCheckedRadioButtonId();
        if (id == R.id.rb_teenager){
            sex = "男";
        }else{

        }
       if (chkJava.isChecked()){
           favitor += "java";
       }
        if (chkAndroid.isChecked()){
            favitor += "android";
        }
        if (chkEnghlish.isChecked()){
            favitor += "英语";
        }
        if (chkMath.isChecked()){
            favitor += "高数";
        }
        String info = "姓名: " + username + "\n性别: " + sex + "\n我最喜欢的课程: "  + favitor.trim();
        Toast.makeText(TextViewactivity.this,info,Toast.LENGTH_LONG).show();

        Intent intent = new Intent(TextViewactivity.this, Calculator.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }



}
