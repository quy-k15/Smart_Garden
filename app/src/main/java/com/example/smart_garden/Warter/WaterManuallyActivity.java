package com.example.smart_garden.Warter;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_garden.Light.LightScheduleActivity;
import com.example.smart_garden.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class WaterManuallyActivity extends AppCompatActivity {
    private Button btn_back,btn_TuoiNuoc,btn_TatNuoc;
    private ToggleButton btn_TuoiThuCong;
    private String treeID,id_quan_ly;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_manually);
        intent = getIntent();
        treeID = intent.getStringExtra("TreeID");
        init();
        getTree();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_left);
    }
    public void setonlick(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(WaterManuallyActivity.this, WaterScheduleActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });
        btn_TuoiThuCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateCheDo();
            }
        });
        btn_TuoiNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateQuanLy(true);
            }
        });
        btn_TatNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateQuanLy(false);
            }
        });
    }
    public void init() {
        btn_back = findViewById(R.id.btn_back);
        btn_TuoiThuCong=findViewById(R.id.btn_TuoiThuCong);
        btn_TuoiNuoc=findViewById(R.id.btn_TuoiNuoc);
        btn_TatNuoc=findViewById(R.id.btn_TatNuoc);
        setonlick();
    }public void getTree() {
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
                    Boolean manully = treeSnapshot.child("tuoiNuoc_ThuCong").getValue(Boolean.class);
//                    Toast.makeText(WaterAutomaticActivity.this,"id quanly "+ id,Toast.LENGTH_LONG).show();
                    // Cập nhật các thuộc tính trong giao diện người dùng
                    if(manully==true)
                    {
                        btn_TuoiThuCong.setText("Bật");
                    }
                    else
                    {
                        btn_TuoiThuCong.setText("Tắt");
                    }
//                    Toast.makeText(WaterAutomaticActivity.this,"Muc do am "+ MucDoAM,Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        };
        quanlyData.addValueEventListener(valueEventListener);
    }
    public void UpdateQuanLy(boolean tuoinuoc)
    {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("quan_ly");
        Query treeData = databaseRef.orderByChild("id_quanLy").equalTo(id_quan_ly);

        treeData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Lặp qua từng node trả về và lấy dữ liệu tương ứng
                for (DataSnapshot treeSnapshot : dataSnapshot.getChildren()) {
                    // Thay đổi giá trị của id_quan_ly
                    try{
//                        Double newMucDoAm = Double.parseDouble(edit_MucDoAm.getText().toString());
                        treeSnapshot.getRef().child("tuoiNuoc").setValue(tuoinuoc);
                    }
                    catch (NumberFormatException e) {
                        // Handle the exception by setting a default value for newMucDoAm
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
                Log.w(TAG, "Lỗi khi đọc dữ liệu từ Firebase.", databaseError.toException());
                Toast.makeText(WaterManuallyActivity.this, "Không thể đọc dữ liệu từ Firebase. Vui lòng thử lại sau.", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void UpdateCheDo() {

        // Lấy đường dẫn đến bảng Tree và tìm các node có "id_Tree" bằng treeID
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("quan_ly");
        Query treeData = databaseRef.orderByChild("id_quanLy").equalTo(id_quan_ly);

        treeData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Lặp qua từng node trả về và lấy dữ liệu tương ứng
                for (DataSnapshot treeSnapshot : dataSnapshot.getChildren()) {
                    // Thay đổi giá trị của id_quan_ly
                    try{
//                        Double newMucDoAm = Double.parseDouble(edit_MucDoAm.getText().toString());
                        if (btn_TuoiThuCong.isChecked()) {
                            treeSnapshot.getRef().child("tuoiNuoc_TuDong").setValue(false);
                            treeSnapshot.getRef().child("tuoiNuoc_Gio").setValue(false);
                            treeSnapshot.getRef().child("tuoiNuoc_ThuCong").setValue(true);
                            Toast.makeText(WaterManuallyActivity.this, "Đã bật chế độ tưới thủ công!", Toast.LENGTH_LONG).show();

                        } else {
                            treeSnapshot.getRef().child("tuoiNuoc_ThuCong").setValue(false);
                            Toast.makeText(WaterManuallyActivity.this, "Đã tắt chế độ tưới thủ công!", Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (NumberFormatException e) {
                        // Handle the exception by setting a default value for newMucDoAm
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
                Log.w(TAG, "Lỗi khi đọc dữ liệu từ Firebase.", databaseError.toException());
                Toast.makeText(WaterManuallyActivity.this, "Không thể đọc dữ liệu từ Firebase. Vui lòng thử lại sau.", Toast.LENGTH_LONG).show();
            }
        });
    }


}