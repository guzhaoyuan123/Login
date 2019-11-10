package com.example.login;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.lang.reflect.Method;


public class Text1Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //创建选项菜单OptionMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }



        //    处理选项菜单的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_groupchat:
                Toast.makeText(Text1Menu.this,"点击了发起群聊",Toast.LENGTH_LONG).show();
                break;
            case R.id.item_addfriend:
                Toast.makeText(Text1Menu.this,"点击了添加朋友",Toast.LENGTH_LONG).show();
                break;
            case R.id.item_saoyis:
                Toast.makeText(Text1Menu.this,"点击了扫一扫",Toast.LENGTH_LONG).show();
                break;
            case R.id.item_shoufukuan:
                Toast.makeText(Text1Menu.this,"点击了收付款",Toast.LENGTH_LONG).show();
                break;
            case R.id.item_help:
                Toast.makeText(Text1Menu.this,"点击了帮助",Toast.LENGTH_LONG).show();
                break;
                default:
                    super.onOptionsItemSelected(item);
                    break;
        }
        return true;
    }

    //显示头像
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }



}