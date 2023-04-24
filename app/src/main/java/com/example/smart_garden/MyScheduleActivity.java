package com.example.smart_garden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MyScheduleActivity extends AppCompatActivity {
    private Button btn_back,btn_LichTuoiNuoc,btn_LichThapDen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);

        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        init();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(MyScheduleActivity.this,MainActivity.class);
                startActivity(it1);
            }
        });
        btn_LichTuoiNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it2 = new Intent(MyScheduleActivity.this,ScheduleWaterActivity.class);
                startActivity(it2);
            }
        });
        btn_LichThapDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it3 = new Intent(MyScheduleActivity.this,ScheduleLightActivity.class);
                startActivity(it3);
            }
        });
    }
    public void init()
    {
        btn_back=findViewById(R.id.btn_back);
        btn_LichTuoiNuoc=findViewById(R.id.btn_LichTuoiNuoc);
        btn_LichThapDen=findViewById(R.id.btn_LichThapDen);
    }
}
