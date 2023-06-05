package com.example.smart_garden.Light;

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

import com.example.smart_garden.MainActivity;
import com.example.smart_garden.R;
import com.example.smart_garden.Warter.WaterAutomaticActivity;
import com.example.smart_garden.Warter.WaterScheduleActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class LightAutomaticActivity extends AppCompatActivity {
    private Button btn_back,btn_CapNhat,btn_MucAS_1225,btn_MucAS_50;
    private DatabaseReference databaseRef;
    private String treeID;
    private ToggleButton btn_DenTuDong;
    Intent intent;

    private TextView tv_MucAS;
    private EditText edit_MucDoAS;
    Double MucAS;
    String id_quan_ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_automatic);
        intent = getIntent();
        treeID = intent.getStringExtra("TreeID");
        init();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        getTree();

        overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_left);

    }

    public void init() {
        btn_back = findViewById(R.id.btn_back);
        btn_DenTuDong=findViewById(R.id.btn_DenTuDong);
        tv_MucAS=findViewById(R.id.tv_MucAS);
        edit_MucDoAS=findViewById(R.id.edit_MucDoAS);
        btn_CapNhat=findViewById(R.id.btn_CapNhat);
        btn_MucAS_1225=findViewById(R.id.btn_MucAS_1225);
        btn_MucAS_50=findViewById(R.id.btn_MucAS_50);
        setonclick();
    }
    private void setonclick(){
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(LightAutomaticActivity.this, LightScheduleActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });
        btn_DenTuDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateCheDo();
            }
        });
        btn_CapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( edit_MucDoAS.equals(null)){
                    Toast.makeText(LightAutomaticActivity.this,"Vui lòng điền mức độ sáng!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Double MucDoAm = Double.parseDouble( edit_MucDoAS.getText().toString());
                    UpdateQuanLy(MucDoAm);
//                    tv_MucDoAm.setText(String.valueOf(MucDoAm)+" %");
                    Toast.makeText(LightAutomaticActivity.this,"Đã cập nhật mức độ sáng",Toast.LENGTH_LONG).show();
                    edit_MucDoAS.setText("");
                }
            }
        });
        btn_MucAS_50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UpdateQuanLy(50.0);
//                tv_MucDoAm.setText("20%");
                Toast.makeText(LightAutomaticActivity.this,"Đã cập nhật mức độ sáng",Toast.LENGTH_LONG).show();
            }
        });
        btn_MucAS_1225.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateQuanLy(1225.0);
                Toast.makeText(LightAutomaticActivity.this,"Đã cập nhật mức độ sáng",Toast.LENGTH_LONG).show();
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
                    MucAS = treeSnapshot.child("muc_AS").getValue(Double.class);
                    Boolean Aotu= treeSnapshot.child("batDen_TuDong").getValue(Boolean.class);
//                    Toast.makeText(WaterAutomaticActivity.this,"id quanly "+ id,Toast.LENGTH_LONG).show();
                    // Cập nhật các thuộc tính trong giao diện người dùng
                    if(Aotu==true)
                    {
                        btn_DenTuDong.setText("Bật");
                    }
                    else
                    {
                        btn_DenTuDong.setText("Tắt");
                    }

                    tv_MucAS.setText(String.valueOf(MucAS)+" Lux");
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
    public void UpdateQuanLy(Double AS) {

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
                        treeSnapshot.getRef().child("muc_AS").setValue(AS);
                        // Cập nhật các thuộc tính trong giao diện người dùng
                        tv_MucAS.setText(String.valueOf(AS)+" %");

                    }
                    catch (NumberFormatException e) {
                        // Handle the exception by setting a default value for newMucDoAm
                        Toast.makeText(LightAutomaticActivity.this, "Vui lòng nhập Mức độ ánh sáng!", Toast.LENGTH_LONG).show();

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
                Log.w(TAG, "Lỗi khi đọc dữ liệu từ Firebase.", databaseError.toException());
                Toast.makeText(LightAutomaticActivity.this, "Không thể đọc dữ liệu từ Firebase. Vui lòng thử lại sau.", Toast.LENGTH_LONG).show();
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
                        if (btn_DenTuDong.isChecked()) {

                            treeSnapshot.getRef().child("batDen_TuDong").setValue(true);
                            treeSnapshot.getRef().child("batDen_Gio").setValue(false);
                            treeSnapshot.getRef().child("batDen_ThuCong").setValue(false);
                            Toast.makeText(LightAutomaticActivity.this, "Đã bật chế độ chiếu sáng theo theo thông số!", Toast.LENGTH_LONG).show();

                        } else {
                            treeSnapshot.getRef().child("batDen_TuDong").setValue(false);
                            Toast.makeText(LightAutomaticActivity.this, "Đã tắt chế độ chiếu sáng tự động theo thông số!", Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (NumberFormatException e) {
                        // Handle the exception by setting a default value for newMucDoAm
                        Toast.makeText(LightAutomaticActivity.this, "Vui lòng nhập Mức độ sáng!", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
                Log.w(TAG, "Lỗi khi đọc dữ liệu từ Firebase.", databaseError.toException());
                Toast.makeText(LightAutomaticActivity.this, "Không thể đọc dữ liệu từ Firebase. Vui lòng thử lại sau.", Toast.LENGTH_LONG).show();
            }
        });
    }
}