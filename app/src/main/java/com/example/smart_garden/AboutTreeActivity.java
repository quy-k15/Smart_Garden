package com.example.smart_garden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class AboutTreeActivity extends AppCompatActivity {
    private Button btn_back;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String treeID;
    Intent intent;
    TextView month,day,year, tv_DoAm,tv_AnhSang,tv_NhietDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_tree);
        intent = getIntent();
        treeID = intent.getStringExtra("TreeID");
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
                Intent it1 = new Intent(AboutTreeActivity.this,MainActivity.class);
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
//        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("thong_so");
        Query treedata = FirebaseDatabase.getInstance().getReference().child("Tree").orderByChild("ID_Tree").equalTo(treeID);
        // Đăng ký một listener để theo dõi thay đổi giá trị trên database
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Lấy giá trị mới từ database và cập nhật vào UI
                Double DoAmValue = dataSnapshot.child("Do_Am").getValue(Double.class);
                Double AnhSangValue = dataSnapshot.child("Do_Sang").getValue(Double.class);
                Double NhietDoValue = dataSnapshot.child("Nhiet_Do").getValue(Double.class);
                // Cập nhật các thuộc tính trong giao diện người dùng
                tv_DoAm.setText(String.valueOf(DoAmValue) + "%");
                tv_AnhSang.setText(String.valueOf(AnhSangValue)+" Lux");
                tv_NhietDo.setText(String.valueOf(NhietDoValue)+" °C");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        };
        // Đăng ký listener với đường dẫn của bảng ThongSo trong Firebase Realtime Database
        treedata.addValueEventListener(valueEventListener);
    }




}
