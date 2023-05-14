package com.example.smart_garden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ScheduleWaterActivity extends AppCompatActivity {
    private Button btn_back,btn_TuoiNuoc,btn_TatNuoc,btn_DoAm50,btn_DoAm20;
    private TextView tv_MucDoAm;
    private ToggleButton btn_TuoiTuDong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_water);

        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        init();
        getThong_So();
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
        btn_DoAm20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseRef = database.getReference().child("thong_so").child("Muc_DoAm");
                databaseRef.setValue(20);
                tv_MucDoAm.setText("20%");
            }
        });
        btn_DoAm50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseRef = database.getReference().child("thong_so").child("Muc_DoAm");
                databaseRef.setValue(50);
                tv_MucDoAm.setText("50%");
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
        btn_DoAm50=findViewById(R.id.btn_DoAm50);
        btn_DoAm20=findViewById(R.id.btn_DoAm20);
        tv_MucDoAm=findViewById(R.id.tv_MucDoAm);
        btn_TuoiTuDong=findViewById(R.id.btn_TuoiTuDong);
    }
    public void getThong_So() {
        // Lấy đường dẫn đến bảng ThongSo
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("thong_so");
        // Đăng ký một listener để theo dõi thay đổi giá trị trên database
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Lấy giá trị mới từ database và cập nhật vào UI
                Double MucDoAmValue = dataSnapshot.child("Muc_DoAm").getValue(Double.class);
                // Cập nhật các thuộc tính trong giao diện người dùng
                tv_MucDoAm.setText(String.valueOf(MucDoAmValue)+" %");
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
