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

public class ScheduleLightActivity extends AppCompatActivity {
    private Button btn_back,btn_BatDen,btn_TatDen,btn_MucAS_2200,btn_MucAS_1225;
    private TextView tv_MucAS;
    private ToggleButton btn_DenTuDong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_light);

        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        init();
        getThong_So();
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
                tv_MucAS.setText("1225 Lux");
            }
        });
        btn_MucAS_2200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseRef = database.getReference().child("thong_so").child("Muc_AS");
                databaseRef.setValue(50); //Thay đổi giá trị của node "Muc_AS" thành 2200
                tv_MucAS.setText("50 Lux");
            }
        });
        btn_DenTuDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_DenTuDong.isChecked())
                {
                    DatabaseReference databaseRef = database.getReference().child("thong_so").child("Den_Tu_Dong");
                    databaseRef.setValue(true);

                }
                else
                {
                    DatabaseReference databaseRef = database.getReference().child("thong_so").child("Den_Tu_Dong");
                    databaseRef.setValue(false);
                }
            }
        });
    }
    public void init()
    {
        btn_back=findViewById(R.id.btn_back);
        btn_BatDen=findViewById(R.id.btn_BatDen);
        btn_TatDen=findViewById(R.id.btn_TatDen);
//        btn_MucAS_2200=findViewById(R.id.btn_MucAS_2200);
//        btn_MucAS_1225=findViewById(R.id.btn_MucAS_1225);
        tv_MucAS=findViewById(R.id.tv_MucAS);
        btn_DenTuDong=findViewById(R.id.btn_DenTuDong);
    }
    public void getThong_So() {
        // Lấy đường dẫn đến bảng ThongSo
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("thong_so");
        // Đăng ký một listener để theo dõi thay đổi giá trị trên database
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Lấy giá trị mới từ database và cập nhật vào UI
                Double MucAnhSangValue = dataSnapshot.child("Muc_AS").getValue(Double.class);
                // Cập nhật các thuộc tính trong giao diện người dùng
                tv_MucAS.setText(String.valueOf(MucAnhSangValue)+" Lux");
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
