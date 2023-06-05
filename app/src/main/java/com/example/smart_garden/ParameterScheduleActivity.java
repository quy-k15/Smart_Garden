package com.example.smart_garden;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_garden.Warter.WaterAutomaticActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ParameterScheduleActivity extends AppCompatActivity {

    private Button btn_back, btn_ThongSo,btn_plants,bt_Delete;
    private Intent intent;
    private String treeID,id_quan_ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter_schedule);

        intent = getIntent();
        treeID = intent.getStringExtra("TreeID");
//        Toast.makeText(ParameterScheduleActivity.this,"ID tree"+treeID,Toast.LENGTH_LONG).show();

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello World");
        init();
        getTree();
        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        String[] splitDate = formattedDate.split(",");


        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(ParameterScheduleActivity.this,MyGardenActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });
        btn_plants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(ParameterScheduleActivity.this,MyScheduleActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });

        btn_ThongSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it2 = new Intent(ParameterScheduleActivity.this,AboutTreeActivity.class);
                it2.putExtra("TreeID", treeID);
                startActivity(it2);
            }
        });
        bt_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteTree();
                DeleteQuanLy();
                Intent it1 = new Intent(ParameterScheduleActivity.this,MyGardenActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });
    }
    public void init()
    {
        btn_ThongSo=findViewById(R.id.btn_ThongSo);
        btn_plants=findViewById(R.id.btn_plants);
        btn_back=findViewById(R.id.btn_back);
        bt_Delete=findViewById(R.id.bt_Delete);

    }
    public void getTree() {
        // Lấy đường dẫn đến bảng Tree và tìm các node có "id_Tree" bằng treeID
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("Tree");
        Query treeData = databaseRef.orderByChild("id_Tree").equalTo(treeID);

        // Đăng ký một listener để theo dõi thay đổi giá trị trên database
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Lặp qua từng node trả về và lấy dữ liệu tương ứng
                for (DataSnapshot treeSnapshot : dataSnapshot.getChildren()) {
                    id_quan_ly = treeSnapshot.child("id_quanLy").getValue(String.class);
//                    Toast.makeText(WaterAutomaticActivity.this,"ID quan ly"+id_quan_ly,Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        };
        treeData.addValueEventListener(valueEventListener);
    }
    public void DeleteTree() {
        // Lấy đường dẫn đến bảng Tree và tìm các node có "id_Tree" bằng treeID
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("Tree");
        Query treeData = databaseRef.orderByChild("id_Tree").equalTo(treeID);

        // Đăng ký một listener để theo dõi thay đổi giá trị trên database
        // Xóa đối tượng nhận được từ câu truy vấn
        treeData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot quanLySnapshot : dataSnapshot.getChildren()) {
                    quanLySnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra

            }
        });
    }
    public void DeleteQuanLy() {
        // Lấy đường dẫn đến bảng Quan_ly và tìm các node có "id_quanLy" bằng id_quan_ly
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("quan_ly");
        Query quanLyData = databaseRef.orderByChild("id_quanLy").equalTo(id_quan_ly);

        // Xóa đối tượng nhận được từ câu truy vấn
        quanLyData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot quanLySnapshot : dataSnapshot.getChildren()) {
                    quanLySnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra

            }
        });
    }
}