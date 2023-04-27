package com.example.smart_garden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScheduleLightActivity extends AppCompatActivity {
    private Button btn_back,btn_BatDen,btn_TatDen,btn_MucAS_2200,btn_MucAS_1225;
    private TextView tv_MucAS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_light);

        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        init();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(ScheduleLightActivity.this,MyScheduleActivity.class);
                startActivity(it1);
            }
        });
        btn_BatDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseRef = database.getReference().child("thong_so").child("BatDen");
                databaseRef.setValue(true); //Thay đổi giá trị của node "BatDen" thành true
            }
        });
        btn_TatDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseRef = database.getReference().child("thong_so").child("BatDen");
                databaseRef.setValue(false); //Thay đổi giá trị của node "BatDen" thành false
            }
        });
        btn_MucAS_1225.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseRef = database.getReference().child("thong_so").child("Muc_AS");
                databaseRef.setValue(1225); //Thay đổi giá trị của node "Muc_AS" thành 1225
                tv_MucAS.setText("1225");
            }
        });
        btn_MucAS_2200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseRef = database.getReference().child("thong_so").child("Muc_AS");
                databaseRef.setValue(2200); //Thay đổi giá trị của node "Muc_AS" thành 2200
                tv_MucAS.setText("2200");
            }
        });
    }
    public void init()
    {
        btn_back=findViewById(R.id.btn_back);
        btn_BatDen=findViewById(R.id.btn_BatDen);
        btn_TatDen=findViewById(R.id.btn_TatDen);
        btn_MucAS_2200=findViewById(R.id.btn_MucAS_2200);
        btn_MucAS_1225=findViewById(R.id.btn_MucAS_1225);
        tv_MucAS=findViewById(R.id.tv_MucAS);
    }
}
