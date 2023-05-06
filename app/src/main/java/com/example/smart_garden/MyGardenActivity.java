package com.example.smart_garden;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyGardenActivity extends AppCompatActivity {
    private Button btn_back;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseInstance;
    TextView month,day,year, tv_DoAm,tv_AnhSang,tv_NhietDo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_garden);
        init();

        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        String[] splitDate = formattedDate.split(",");

        month.setText(splitDate[1]+"/");
        day.setText(splitDate[0]+"/");
        year.setText(splitDate[2]);


        getThong_So();

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
        tv_DoAm=findViewById(R.id.tv_DoAm);
        tv_AnhSang=findViewById(R.id.tv_AnhSang);
        tv_NhietDo=findViewById(R.id.tv_NhietDo);
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

                Double soilMoistureValue = dataSnapshot.child("Do_Am").getValue(Double.class);
                double soilMoisture = soilMoistureValue != null ? soilMoistureValue.doubleValue() : 0.0;
                Double lightIntensityValue = dataSnapshot.child("Do_Sang").getValue(Double.class);
                double lightIntensity= lightIntensityValue != null ? lightIntensityValue.doubleValue() : 0.0;
                Double NhietDoValue = dataSnapshot.child("Nhiet_Do").getValue(Double.class);
                double NhietDo= NhietDoValue != null ? NhietDoValue.doubleValue() : 0.0;

                // Cập nhật các thuộc tính trong giao diện người dùng
                tv_DoAm.setText(String.valueOf(soilMoisture));
                tv_AnhSang.setText(String.valueOf(lightIntensity));
                tv_NhietDo.setText(String.valueOf(NhietDo));
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
