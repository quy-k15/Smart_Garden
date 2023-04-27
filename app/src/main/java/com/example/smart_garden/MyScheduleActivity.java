package com.example.smart_garden;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyScheduleActivity extends AppCompatActivity {
    private Button btn_back,btn_LichTuoiNuoc,btn_LichThapDen;
    private TextView tv_MucDoAm,tv_MucAs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);

        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        init();
        getThong_So();
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
        tv_MucDoAm=findViewById(R.id.tv_MucDoAm);
        tv_MucAs=findViewById(R.id.tv_MucAs);
    }
    public void getThong_So() {


        // Lấy đường dẫn đến bảng ThongSo
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("thong_so");

        // Đăng ký một listener để theo dõi thay đổi giá trị trên database
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Lấy giá trị mới từ database và cập nhật vào UI
//                double soilMoisture = dataSnapshot.child("soil_moisture").getValue(Double.class);

                Double Muc_DoAm = dataSnapshot.child("Muc_DoAm").getValue(Double.class);
                Double Muc_AS = dataSnapshot.child("Muc_AS").getValue(Double.class);

                // Cập nhật các thuộc tính trong giao diện người dùng
                tv_MucDoAm.setText(String.valueOf(Muc_DoAm));
                tv_MucAs.setText(String.valueOf(Muc_AS));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        };

        // Đăng ký listener với đường dẫn của bảng ThongSo trong Firebase Realtime Database
        databaseRef.addValueEventListener(valueEventListener);
    }

}
