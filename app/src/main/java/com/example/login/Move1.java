package com.example.login;

        import android.app.Activity;
        import android.os.Bundle;

public class Move1 extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Move2 view = new Move2(this, "杨过");
        setContentView(view);
    }
}
