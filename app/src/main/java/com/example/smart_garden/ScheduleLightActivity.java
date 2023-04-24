package com.example.smart_garden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleLightActivity extends AppCompatActivity {
    private Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_light);

        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        init();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(ScheduleLightActivity.this,MyScheduleActivity.class);
                startActivity(it1);
            }
        });
    }
    public void init()
    {
        btn_back=findViewById(R.id.btn_back);
    }
}
