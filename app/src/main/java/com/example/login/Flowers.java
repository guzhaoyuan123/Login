package com.example.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Flowers extends AppCompatActivity  {

    private TextView mtv1;
    private RadioGroup flowers_line11;
    private RadioGroup flowers_line22;
    private ImageView igv_tupian;
    private int[] igv = {R.drawable.flower2, R.drawable.flower3, R.drawable.flower4,
            R.drawable.flower5, R.drawable.flower6, R.drawable.flower7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowers);
        //跑马灯
        mtv1 = findViewById(R.id.paomadneng);
        mtv1.setSelected(true);
        igv_tupian = findViewById(R.id.flowers_tupian);
        flowers_line22 = findViewById(R.id.flowers_line2);
        flowers_line11 = findViewById(R.id.flowers_line1);
        flowers_line11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == -1) {
                    return;
                }
                RadioButton btn = Flowers.this.findViewById(checkedId);
                if (!btn.isChecked()) {
                    return;
                }
                flowers_line22.clearCheck();
                if (checkedId == R.id.flowers_meihua) {
                    igv_tupian.setImageResource(igv[0]);
                }
                if (checkedId == R.id.flowers_shilanhua) {
                    igv_tupian.setImageResource(igv[1]);
                }
                if (checkedId == R.id.flowers_xiangyahua) {
                    igv_tupian.setImageResource(igv[2]);
                }
            }
        });
        flowers_line22.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == -1) {
                    return;
                }
                RadioButton btn = Flowers.this.findViewById(checkedId);
                if (!btn.isChecked()) {
                    return;
                }
                flowers_line11.clearCheck();
                if (checkedId == R.id.flowers_xiuqiuhua) {
                    igv_tupian.setImageResource(igv[3]);
                }
                if (checkedId == R.id.flowers_yulanhua) {
                    igv_tupian.setImageResource(igv[4]);
                }
                if (checkedId == R.id.flowers_mudanhua) {
                    igv_tupian.setImageResource(igv[5]);
                }
            }
        });
    }
}

