package com.example.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etUsername;
    private EditText etPassword;
    private TextView wangjimima;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.获取用户名、密码和按钮这几个控件
        etUsername = findViewById(R.id.ev_userName);
        etPassword = findViewById(R.id.ev_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        wangjimima = findViewById(R.id.btn_forget_pwd);
        wangjimima.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_forget_pwd:
                Toast.makeText(MainActivity.this, "忘记密码", Toast.LENGTH_LONG).show();
                break;
        }
    }


    private void login() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();
        if (username.equals("1") && password.equals("1")) {
            Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(MainActivity.this, TextViewactivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
        }

    }

    //2.监听按钮的点击事件
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //3.处理按钮的点击事件
//                //3.1获取用户名和密码的值
//                String username = etUsername.getText().toString().trim();
//                String password = etPassword.getText().toString();
//                if(username.equals("cwf")&&password.equals("123456")) {
//                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG).show();
//
//                }else{
//                    Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
//                }
//
//
//            }
//        });
}
