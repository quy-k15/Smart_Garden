package com.example.smart_garden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScheduleWaterActivity extends AppCompatActivity {
    private Button btn_back,btn_TuoiNuoc,btn_TatNuoc,btn_DoAm1204,btn_DoAm2000;
    private TextView tv_MucDoAm;
    private ToggleButton btn_TuoiTuDong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_water);

        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        init();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(ScheduleWaterActivity.this,MyScheduleActivity.class);
                startActivity(it1);
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        btn_TuoiNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseRef = database.getReference().child("thong_so").child("TuoiNuoc");
                databaseRef.setValue(true); //Thay đổi giá trị của node "TuoiNuoc" thành true
            }
        });
        btn_TatNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseRef = database.getReference().child("thong_so").child("TuoiNuoc");
                databaseRef.setValue(false); //Thay đổi giá trị của node "TuoiNuoc" thành false
            }
        });
        btn_DoAm2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseRef = database.getReference().child("thong_so").child("Muc_DoAm");
                databaseRef.setValue(2000);
                tv_MucDoAm.setText("2000");
            }
        });
        btn_DoAm1204.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseRef = database.getReference().child("thong_so").child("Muc_DoAm");
                databaseRef.setValue(1204);
                tv_MucDoAm.setText("1204");
            }
        });
        btn_TuoiTuDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_TuoiTuDong.isChecked())
                {
                    DatabaseReference databaseRef = database.getReference().child("thong_so").child("Tu_Dong_Tuoi");
                    databaseRef.setValue(true);
                }
                else
                {
                    DatabaseReference databaseRef = database.getReference().child("thong_so").child("Tu_Dong_Tuoi");
                    databaseRef.setValue(false);
                }
            }
        });
    }
    public void init()
    {
        btn_back=findViewById(R.id.btn_back);
        btn_TuoiNuoc=findViewById(R.id.btn_TuoiNuoc);
        btn_TatNuoc=findViewById(R.id.btn_TatNuoc);
        btn_DoAm1204=findViewById(R.id.btn_DoAm1204);
        btn_DoAm2000=findViewById(R.id.btn_DoAm2000);
        tv_MucDoAm=findViewById(R.id.tv_MucDoAm);
        btn_TuoiTuDong=findViewById(R.id.btn_TuoiTuDong);
    }
}
