package com.example.smart_garden.Warter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_garden.Light.LightAutomaticActivity;
import com.example.smart_garden.Light.LightClockActivity;
import com.example.smart_garden.Light.LightManuallyActivity;
import com.example.smart_garden.MyScheduleActivity;
import com.example.smart_garden.ParameterScheduleActivity;
import com.example.smart_garden.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WaterScheduleActivity extends AppCompatActivity {
    private Button btn_back,btn_TuoiTuDong,btn_TuoiTheoGio,btn_TuoiThuCong;
    private TextView tv_ApDungTuDong,tv_ApDungGio,tv_ApDungThuCong;
    private Intent intent;
    private String treeID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_water);
        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        init();
        getThong_So();
        intent = getIntent();
        treeID = intent.getStringExtra("TreeID");
        Toast.makeText(WaterScheduleActivity.this,"ID tree"+treeID,Toast.LENGTH_LONG).show();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(WaterScheduleActivity.this, MyScheduleActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });
        btn_TuoiTuDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(WaterScheduleActivity.this, WaterAutomaticActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);

            }
        });
        btn_TuoiTheoGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(WaterScheduleActivity.this, WaterClockActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);

            }
        });
        btn_TuoiThuCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(WaterScheduleActivity.this, WaterManuallyActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);

            }
        });


    }
    public void init()
    {
        btn_back=findViewById(R.id.btn_back);
        btn_TuoiTuDong=findViewById(R.id.btn_TuoiTuDong);
        btn_TuoiTheoGio=findViewById(R.id.btn_TuoiTheoGio);
        btn_TuoiThuCong=findViewById(R.id.btn_TuoiThuCong);

        tv_ApDungTuDong=findViewById(R.id.tv_ApDungTuDong);
        tv_ApDungGio=findViewById(R.id.tv_ApDungGio);
        tv_ApDungThuCong=findViewById(R.id.tv_ApDungThuCong);

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
