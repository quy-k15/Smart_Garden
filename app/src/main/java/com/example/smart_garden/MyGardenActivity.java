package com.example.smart_garden;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyGardenActivity extends AppCompatActivity {
    private Button btn_back;
    TextView month,day,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_garden);
        init();

        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        String[] splitDate = formattedDate.split(",");

        month.setText(splitDate[1]);
        day.setText(splitDate[0]);
        year.setText(splitDate[2]);

        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(MyGardenActivity.this,MainActivity.class);
                startActivity(it1);
            }
        });
    }
    public void init()
    {
        btn_back=findViewById(R.id.btn_back);
        day=findViewById(R.id.tv_day);
        month=findViewById(R.id.tv_month);
        year=findViewById(R.id.tv_year);
    }
}
