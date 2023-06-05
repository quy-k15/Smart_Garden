package com.example.smart_garden;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_garden.Light.LightScheduleActivity;
import com.example.smart_garden.Warter.WaterScheduleActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MyScheduleActivity extends AppCompatActivity {
    private Button btn_back,btn_LichTuoiNuoc,btn_LichThapDen;
    private TextView LichThapDen,LichTuoiNuoc;
    private Intent intent;
    private String treeID,id_quan_ly;
    private Boolean AS_TuDong,AS_Gio,AS_ThuCong,DoAm_TuDong,DoAM_Gio,DoAm_ThuCong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);

        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        init();
        intent = getIntent();
        treeID = intent.getStringExtra("TreeID");
        getTree();
//        Toast.makeText(MyScheduleActivity.this,"ID tree"+treeID,Toast.LENGTH_LONG).show();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(MyScheduleActivity.this,ParameterScheduleActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });
        btn_LichTuoiNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it2 = new Intent(MyScheduleActivity.this, WaterScheduleActivity.class);
                it2.putExtra("TreeID", treeID);
                startActivity(it2);
            }
        });
        btn_LichThapDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it3 = new Intent(MyScheduleActivity.this,LightScheduleActivity.class);
                it3.putExtra("TreeID", treeID);
                startActivity(it3);
            }
        });

    }

    public void init()
    {
        btn_back=findViewById(R.id.btn_back);
        btn_LichTuoiNuoc=findViewById(R.id.btn_LichTuoiNuoc);
        btn_LichThapDen=findViewById(R.id.btn_LichThapDen);
        LichTuoiNuoc=findViewById(R.id.LichTuoiNuoc);
        LichThapDen=findViewById(R.id.LichThapDen);

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
                getQuanLy();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        };
        treeData.addValueEventListener(valueEventListener);
    }
    public void getQuanLy() {
        // Lấy đường dẫn đến bảng Tree và tìm các node có "id_Tree" bằng treeID
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("quan_ly");
        Query quanlyData = databaseRef.orderByChild("id_quanLy").equalTo(id_quan_ly);
        // Đăng ký một listener để theo dõi thay đổi giá trị trên database
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Lặp qua từng node trả về và lấy dữ liệu tương ứng
                for (DataSnapshot treeSnapshot : dataSnapshot.getChildren()) {
                    String id = treeSnapshot.child("id_quanLy").getValue(String.class);
                    AS_TuDong = treeSnapshot.child("batDen_TuDong").getValue(Boolean.class);
                    AS_Gio= treeSnapshot.child("batDen_Gio").getValue(Boolean.class);
                    AS_ThuCong= treeSnapshot.child("batDen_ThuCong").getValue(Boolean.class);

                    DoAm_TuDong = treeSnapshot.child("tuoiNuoc_TuDong").getValue(Boolean.class);
                    DoAM_Gio= treeSnapshot.child("tuoiNuoc_Gio").getValue(Boolean.class);
                    DoAm_ThuCong= treeSnapshot.child("tuoiNuoc_ThuCong").getValue(Boolean.class);



                    if(AS_TuDong==true)
                    {
                        LichThapDen.setText("Tự động thắp đèn theo chế độ dựa trên thông số");
                    }
                    else if(AS_Gio==true)
                    {
                        LichThapDen.setText("Tự động thắp đèn theo chế độ hẹn giờ");
                    }
                    else if(AS_ThuCong==true)
                    {
                        LichThapDen.setText("Thắp đèn theo chế độ thủ công ");
                    }
                    else{
                        LichThapDen.setText("Chưa chọn chế độ");
                    }

                    if(DoAm_TuDong==true)
                    {
                        LichTuoiNuoc.setText("Tự động thắp đèn theo chế độ dựa trên thông số");
                    }
                    else if(DoAM_Gio==true)
                    {
                        LichTuoiNuoc.setText("Tự động thắp đèn theo chế độ hẹn giờ");
                    }
                    else if(DoAm_ThuCong==true)
                    {
                        LichTuoiNuoc.setText("Thắp đèn theo chế độ thủ công ");
                    }
                    else{
                        LichTuoiNuoc.setText("Chưa chọn chế độ");
                    }


                }





            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        };
        quanlyData.addValueEventListener(valueEventListener);
    }



}
