package com.example.login;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.Locale;

public class Touch extends AppCompatActivity implements View.OnClickListener {
    // 1. 定义这些控件
    private EditText etIp;
    private TextView tvShowIp;
    private TextView tvXY;

    private ImageView imgView;
    private Rect rect = new Rect();  // 布局的长方形对象
    private float deltaX, deltaY;   // x、y方向上的移动偏移量

    private Button btnHang, btnNormal, btnFold, btnClear;
    private NotificationManager manager;  // 消息管理器对象

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

        // 2. 获取控件对象，并设置相关的监听事件
        ininView();

        // 3. 设置事件监听器
        // 3.1 图片的移动的Touch事件
        imgView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return moveImage(v, event);
            }
        });

        // 3.2 键盘输入的事件
        etIp = findViewById(R.id.tou_ip);
        etIp.setOnKeyListener(new View.OnKeyListener() {
            // 4. 处理监听事件
            @Override
            public boolean onKey(View v, int i, KeyEvent keyEvent) {
                switch (keyEvent.getAction()){
                    case KeyEvent.ACTION_UP:
                        String ip = etIp.getText().toString();
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
                        tvShowIp.setText(newIp);
                        break;
                    case KeyEvent.ACTION_DOWN:
                        break;
                }
                return false;
            }
        });

        // 4. 创建通知渠道
        createChannel();
    }

    // 功能：初始化布局上的所有组件，并设置监听事件
    private void ininView() {
        // 获取ip解析的两个控件
        etIp = findViewById(R.id.tou_ip);
        tvShowIp = findViewById(R.id.tou_showIP);
        tvXY = findViewById(R.id.tv_coordinate);

        // 获取ImageView控件
        imgView = findViewById(R.id.tv_touch);

        // 初始化消息的3个按钮，并设置监听事件
        btnHang = findViewById(R.id.bt_hang);
        btnNormal = findViewById(R.id.bt_normal);
        btnFold = findViewById(R.id.bt_fold);
        btnClear = findViewById(R.id.bt_clear);

        btnHang.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        btnFold.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }


    // 功能：根据MotionEvent的触发，计算ImageView的位置，并通过设置ImageView的margin移动图片
    private boolean moveImage(View v, MotionEvent event) {
        // 1. 获取触控点的坐标，并显示到TextView
        float x = event.getX() + v.getLeft();
        float y = event.getY() + v.getTop();
        String pos = "触点坐标：" + String.format(Locale.CHINA,"%.2f", x)
                + ", " + String.format(Locale.CHINA,"%.2f", y);
        tvXY.setText(pos);

        // 2. 获取可以移动的范围大小
        findViewById(R.id.relative_layout).getDrawingRect(rect);

        // 3. 获取imageView的布局参数
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v.getLayoutParams();

        // 4. 处理ACTION_DOWN、ACTION_MOVE、ACTION_UP的事件
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                deltaX = x - params.leftMargin;
                deltaY = y - params.topMargin;
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = Math.round(x - deltaX);
                int moveY = Math.round(y - deltaY);

                if(moveX >= rect.left && moveX <= rect.right
                        && moveY >= rect.top && moveY <= rect.bottom) {
                    params.leftMargin = moveX;
                    params.topMargin = moveY;
                    imgView.setLayoutParams(params);
                }
                break;
            case MotionEvent.ACTION_UP:
                imgView.performClick();
                break;
        }
        return true;
    }

    // 创建3种不同的消息渠道
    private void createChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // 悬挂通知：等级最高
            String channelId = "悬挂";
            String channelName = "悬挂通知";
            int importance = NotificationManager.IMPORTANCE_HIGH;  // 消息的重要性等级
            createNotificationChannel(channelId, channelName, importance);

            // 普通通知：等级中等
            channelId = "普通";
            channelName = "普通通知";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);

            // 折叠通知：等级最低
            channelId = "折叠";
            channelName = "折叠通知";
            importance = NotificationManager.IMPORTANCE_LOW;
            createNotificationChannel(channelId, channelName, importance);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_hang:
                sendHang();
                break;
            case R.id.bt_normal:
                sendNormal();
                break;
            case R.id.bt_fold:
                sendFold();
                break;
            case R.id.bt_clear:
                clearAllNotification();
        }
    }

    // 发送悬挂消息
    private void sendHang() {
        // 设置消息传递的动作，通过PendingIntent进行传递（自定义Intent跳转的第二个Activity需要自行定义）
        Intent intent = new Intent(Touch.this, TextViewactivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(Touch.this, 0, intent, 0);

        // 1. 创建消息构造器，设置相关的属性；通过Builder构造方法传递channelId，匹配createChannel()方法中设置的channelId
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Touch.this, "悬挂");
        builder.setSmallIcon(R.drawable.qqbeijing4)
                .setContentTitle("悬挂通知")
                .setContentText("今天中午会下大雨")
                .setWhen(System.currentTimeMillis())
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.qqbeijing2))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // 针对低于Android 8.0的设置
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setFullScreenIntent(pendingIntent, true);
        }

        // 2. 通过builder构建消息对象
        Notification notification = builder.build();

        // 3. 通过消息管理器发送消息
        if(manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);  // 消息通知是系统服务
        }
        manager.notify(1, notification); // 发送消息的id必须唯一，否则会被后面发送的消息覆盖
    }

    // 发送普通消息
    private void sendNormal() {
        // 设置消息传递的动作，通过PendingIntent进行传递（自定义Intent跳转的第二个Activity需要自行定义）
        Intent intent = new Intent(Touch.this, TextViewactivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(Touch.this,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 1. 创建消息构造器，设置相关的属性；通过Builder构造方法传递channelId，匹配createChannel()方法中设置的channelId
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Touch.this, "普通");
        builder.setSmallIcon(R.drawable.qqbeijing4)
                .setContentTitle("普通通知")
                .setContentText("今天中午会下大雨")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.qqbeijing2))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // 2. 通过builder构建消息对象
        Notification notification = builder.build();

        // 3. 通过消息管理器发送消息
        if(manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        manager.notify(2, notification);
    }

    // 发送：发送折叠消息（了解即可）
    private void sendFold() {
        // 设置消息传递的动作，通过PendingIntent进行传递（自定义Intent跳转的第二个Activity需要自行定义）
        Intent intent = new Intent(Touch.this, TextViewactivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(Touch.this, 0, intent, 0);

        // 1. 创建消息构造器，设置相关的属性；通过Builder构造方法传递channelId，匹配createChannel()方法中设置的channelId
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Touch.this, "折叠");
        builder.setSmallIcon(R.drawable.flower4)
                .setContentTitle("折叠通知")
                .setContentText("今天中午会下大雨")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.flower2))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // 2. 通过builder构建消息对象
        Notification notification = builder.build();

        // 3. 设置折叠通知展开后的布局
//        notification.bigContentView = new RemoteViews(getPackageName(), R.layout.activity_menu);

        // 4. 通过消息管理器发送消息
        if(manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        manager.notify(3, notification);
    }

    // 功能：清除所有的通知，一般不使用
    private void clearAllNotification() {
        manager.cancelAll();

        // 清除某一个通知方法：manager.cancel(id)，此处的id是notify()的id
    }

    // 功能：activity界面上的触控事件处理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            String pos = "触点坐标：" + String.format(Locale.CHINA,"%.2f", x)
                    + ", " + String.format(Locale.CHINA,"%.2f", y);
            tvXY.setText(pos);
//            Toast.makeText(this, pos, Toast.LENGTH_LONG).show();
        }
        return super.onTouchEvent(event);
    }
}
