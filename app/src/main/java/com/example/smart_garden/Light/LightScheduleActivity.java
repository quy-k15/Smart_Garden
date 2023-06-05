package com.example.smart_garden.Light;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_garden.MyScheduleActivity;
import com.example.smart_garden.R;
import com.example.smart_garden.Warter.WaterClockActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LightScheduleActivity extends AppCompatActivity {
    private Button btn_back,btn_ASTuDong,btn_ASTheoGio,btn_ASThuCong;
    private TextView tv_ApDungTuDong,tv_ApDungGio,tv_ApDungThuCong;
    private Intent intent;
    private String treeID, id_quan_ly;
    private boolean TuDong,Gio,ThuCong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_light);
        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        init();
//        getThong_So();
        intent = getIntent();
        treeID = intent.getStringExtra("TreeID");
        getTree();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(LightScheduleActivity.this, MyScheduleActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });
        btn_ASTuDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(LightScheduleActivity.this,LightAutomaticActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });
        btn_ASTheoGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(LightScheduleActivity.this,LightClockActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });
        btn_ASThuCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(LightScheduleActivity.this,LightManuallyActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });



    }
    public void init()
    {
        btn_back=findViewById(R.id.btn_back);
        btn_ASTuDong=findViewById(R.id.btn_ASTuDong);
        btn_ASTheoGio=findViewById(R.id.btn_ASTheoGio);
        btn_ASThuCong=findViewById(R.id.btn_ASThuCong);

        tv_ApDungTuDong=findViewById(R.id.tv_ApDungTuDong);
        tv_ApDungGio=findViewById(R.id.tv_ApDungGio);
        tv_ApDungThuCong=findViewById(R.id.tv_ApDungThuCong);

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
                    TuDong = treeSnapshot.child("batDen_TuDong").getValue(Boolean.class);
                    Gio= treeSnapshot.child("batDen_Gio").getValue(Boolean.class);
                    ThuCong= treeSnapshot.child("batDen_ThuCong").getValue(Boolean.class);
//                    Toast.makeText(WaterAutomaticActivity.this,"id quanly "+ id,Toast.LENGTH_LONG).show();
                    // Cập nhật các thuộc tính trong giao diện người dùng

//                    Toast.makeText(WaterAutomaticActivity.this,"Muc do am "+ MucDoAM,Toast.LENGTH_LONG).show();

                    if(TuDong==true)
                    {
                        tv_ApDungTuDong.setText("Đang áp dụng");
                    }
                    else {
                        tv_ApDungTuDong.setText("Không áp dụng");
                    }
                    if(Gio==true)
                    {
                        tv_ApDungGio.setText("Đang áp dụng");
                    }
                    else{
                        tv_ApDungGio.setText("Không áp dụng");
                    }
                    if(ThuCong==true)
                    {
                        tv_ApDungThuCong.setText("Đang áp dụng");
                    }
                    else{
                        tv_ApDungThuCong.setText("Không áp dụng");
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
