package com.example.smart_garden.Warter;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_garden.AddTreeActivity;
import com.example.smart_garden.Light.LightScheduleActivity;
import com.example.smart_garden.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class WaterAutomaticActivity extends AppCompatActivity {
    private Button btn_back,btn_CapNhat,btn_DoAm50,btn_DoAm20;
    private String treeID;
    private TextView tv_MucDoAm;
    private ToggleButton btn_TuoiTuDong;
    private EditText edit_MucDoAm;
    Intent intent;
    Double MucDoAM;
    String id_quan_ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_automatic);
        intent = getIntent();
        treeID = intent.getStringExtra("TreeID");
        init();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        getTree();

        overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_left);


    }

    public void init() {
        btn_back = findViewById(R.id.btn_back);
        btn_TuoiTuDong=findViewById(R.id.btn_TuoiTuDong);
        tv_MucDoAm=findViewById(R.id.tv_MucDoAm);
        edit_MucDoAm=findViewById(R.id.edit_MucDoAm);
        btn_CapNhat=findViewById(R.id.btn_CapNhat);
        btn_DoAm50=findViewById(R.id.btn_DoAm50);
        btn_DoAm20=findViewById(R.id.btn_DoAm20);
        setonclick();
    }
    private void setonclick(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(WaterAutomaticActivity.this, WaterScheduleActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });
        btn_TuoiTuDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateCheDo();
            }
        });
        btn_CapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit_MucDoAm.equals(null)){
                    Toast.makeText(WaterAutomaticActivity.this,"Vui lòng điền mức độ ẩm!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Double MucDoAm = Double.parseDouble(edit_MucDoAm.getText().toString());
                    UpdateQuanLy(MucDoAm);
//                    tv_MucDoAm.setText(String.valueOf(MucDoAm)+" %");
                    Toast.makeText(WaterAutomaticActivity.this,"Đã cập nhật mức độ ẩm",Toast.LENGTH_LONG).show();
                    edit_MucDoAm.setText("");
                }
            }
        });
        btn_DoAm20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UpdateQuanLy(20.0);
//                tv_MucDoAm.setText("20%");
                Toast.makeText(WaterAutomaticActivity.this,"Đã cập nhật mức độ ẩm",Toast.LENGTH_LONG).show();
            }
        });
        btn_DoAm50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateQuanLy(50.0);
                Toast.makeText(WaterAutomaticActivity.this,"Đã cập nhật mức độ ẩm",Toast.LENGTH_LONG).show();
            }
        });
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
                    MucDoAM = treeSnapshot.child("muc_DoAm").getValue(Double.class);
                    Boolean clock = treeSnapshot.child("tuoiNuoc_TuDong").getValue(Boolean.class);
//                    Toast.makeText(WaterAutomaticActivity.this,"id quanly "+ id,Toast.LENGTH_LONG).show();
                    // Cập nhật các thuộc tính trong giao diện người dùng
                    if(clock==true)
                    {
                        btn_TuoiTuDong.setText("Bật");
                    }
                    else
                    {
                        btn_TuoiTuDong.setText("Tắt");
                    }

                    tv_MucDoAm.setText(String.valueOf(MucDoAM)+" %");
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
    public void UpdateQuanLy(Double DoAm) {

        // Lấy đường dẫn đến bảng Tree và tìm các node có "id_Tree" bằng treeID
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("quan_ly");
        Query treeData = databaseRef.orderByChild("id_quanLy").equalTo(id_quan_ly);

        // Đăng ký một listener để theo dõi thay đổi giá trị trên database
//        ValueEventListener valueSingleEventListener = new ValueEventListener() {
        treeData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Lặp qua từng node trả về và lấy dữ liệu tương ứng
                for (DataSnapshot treeSnapshot : dataSnapshot.getChildren()) {
                    // Thay đổi giá trị của id_quan_ly
                    try{
//                        Double newMucDoAm = Double.parseDouble(edit_MucDoAm.getText().toString());
                        treeSnapshot.getRef().child("muc_DoAm").setValue(DoAm);
                        // Cập nhật các thuộc tính trong giao diện người dùng
                        tv_MucDoAm.setText(String.valueOf(DoAm)+" %");

                    }
                    catch (NumberFormatException e) {
                        // Handle the exception by setting a default value for newMucDoAm
                        Toast.makeText(WaterAutomaticActivity.this, "Vui lòng nhập Mức độ ẩm!", Toast.LENGTH_LONG).show();

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
                Log.w(TAG, "Lỗi khi đọc dữ liệu từ Firebase.", databaseError.toException());
                Toast.makeText(WaterAutomaticActivity.this, "Không thể đọc dữ liệu từ Firebase. Vui lòng thử lại sau.", Toast.LENGTH_LONG).show();
            }
        });
        // Đăng ký listener và query vào DatabaseReference để thực hiện thao tác cập nhật dữ liệu
//        treeData.removeEventListener(valueEventListener);
//        treeData.addValueEventListener(valueEventListener);
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
                        if (btn_TuoiTuDong.isChecked()) {

                            treeSnapshot.getRef().child("tuoiNuoc_TuDong").setValue(true);
                            treeSnapshot.getRef().child("tuoiNuoc_Gio").setValue(false);
                            treeSnapshot.getRef().child("tuoiNuoc").setValue(false);
                            Toast.makeText(WaterAutomaticActivity.this, "Đã bật chế độ tưới theo theo thông số!", Toast.LENGTH_LONG).show();

                        } else {
                            treeSnapshot.getRef().child("tuoiNuoc_TuDong").setValue(false);
                            Toast.makeText(WaterAutomaticActivity.this, "Đã tắt chế độ tưới tự động theo thông số!", Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (NumberFormatException e) {
                        // Handle the exception by setting a default value for newMucDoAm
                        Toast.makeText(WaterAutomaticActivity.this, "Vui lòng nhập Mức độ ẩm!", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
                Log.w(TAG, "Lỗi khi đọc dữ liệu từ Firebase.", databaseError.toException());
                Toast.makeText(WaterAutomaticActivity.this, "Không thể đọc dữ liệu từ Firebase. Vui lòng thử lại sau.", Toast.LENGTH_LONG).show();
            }
        });
    }
}