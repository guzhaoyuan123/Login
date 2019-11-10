package com.example.login;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Calculator extends AppCompatActivity implements View.OnClickListener {

    EditText et_input;
    boolean clr_flag;    //判断et编辑文本框中是否清空

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        //实例化对象

        //给按钮设置的点击事件
    }

    @Override
    public void onClick(View v) {
        String str=et_input.getText().toString();
        switch (v.getId()){
            case   R.id.btn_0:
            case   R.id.btn_1:
            case   R.id.btn_2:
            case   R.id.btn_3:
            case   R.id.btn_4:
            case   R.id.btn_5:
            case   R.id.btn_6:
            case   R.id.btn_7:
            case   R.id.btn_8:
            case   R.id.btn_9:
            case   R.id.btn_pt:
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    et_input.setText("");
                }
                et_input.setText(str+((Button)v).getText());
                break;
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_mul:
            case R.id.btn_div:
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    et_input.setText("");
                }
                if(str.contains("+")||str.contains("-")||str.contains("×")||str.contains("÷")) {
                    str=str.substring(0,str.indexOf(" "));
                }
                et_input.setText(str+" "+((Button)v).getText()+" ");
                break;
            case R.id.btn_clr:
                if(clr_flag)
                    clr_flag=false;
                str="";
                et_input.setText("");
                break;
            case R.id.btn_del: //判断是否为空，然后在进行删除
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    et_input.setText("");
                }
                else if(str!=null&&!str.equals("")){
                    et_input.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.btn_eq: //单独运算最后结果
                getResult();//调用下面的方法
                break;
        }
    }

    private void getResult() {
        String exp=et_input.getText().toString();
        if(exp==null||exp.equals("")) return ;
        //因为没有运算符所以不用运算
        if(!exp.contains(" ")){
            return ;
        }
        if(clr_flag){
            clr_flag=false;
            return;
        }
        clr_flag=true;
        //截取运算符前面的字符串
        String s1=exp.substring(0,exp.indexOf(" "));
        //截取的运算符
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        //截取运算符后面的字符串
        String s2=exp.substring(exp.indexOf(" ")+3);
        double cnt=0;
        if(!s1.equals("")&&!s2.equals("")){
            double d1=Double.parseDouble(s1);
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d1+d2;
            }
            if(op.equals("-")){
                cnt=d1-d2;
            }
            if(op.equals("×")){
                cnt=d1*d2;
            }
            if(op.equals("÷")){
                if(d2==0) cnt=0;
                else cnt=d1/d2;
            }
            if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        //如果s1是空    s2不是空  就执行下一步
        else if(!s1.equals("")&&s2.equals("")){
            double d1=Double.parseDouble(s1);
            if(op.equals("+")){
                cnt=d1;
            }
            if(op.equals("-")){
                cnt=d1;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!s1.contains(".")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        //如果s1是空    s2不是空  就执行下一步
        else if(s1.equals("")&&!s2.equals("")){
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d2;
            }
            if(op.equals("-")){
                cnt=0-d2;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!s2.contains(".")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        else {
            et_input.setText("");
        }
    }
}




