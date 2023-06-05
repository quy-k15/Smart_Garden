package com.example.smart_garden.Light;

import static android.content.ContentValues.TAG;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_garden.MainActivity;
import com.example.smart_garden.R;
import com.example.smart_garden.Warter.WaterClockActivity;
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

public class LightClockActivity extends AppCompatActivity {
    Intent intent;
    private ToggleButton btn_DenTheoGio;
    private Button btn_back,btn_CapNhat,btn_AS18_05, btn_AS19_01;
    private DatabaseReference databaseRef;
    private String treeID,id_quan_ly;
    private TextView edt_GioBat, edt_GioTat,GioBat,GioTat;
    private TimePickerDialog tpicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_clock);
        intent = getIntent();
        treeID = intent.getStringExtra("TreeID");
        init();
        getTree();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_left);
    }

    public void init() {
        btn_back = findViewById(R.id.btn_back);
        btn_DenTheoGio=findViewById(R.id.btn_DenTheoGio);
        btn_CapNhat=findViewById(R.id.btn_CapNhat);
        btn_AS18_05=findViewById(R.id.btn_AS18_05);
        btn_AS19_01=findViewById(R.id.btn_AS19_01);
        edt_GioBat=findViewById(R.id.edt_GioBat);
        edt_GioTat=findViewById(R.id.edt_GioTat);
        GioBat=findViewById(R.id.GioBat);
        GioTat=findViewById(R.id.GioTat);
        setonclick();
    }
    private void setonclick(){
        final Calendar calendar = Calendar.getInstance();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(LightClockActivity.this, LightScheduleActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });
        btn_DenTheoGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateCheDo();
            }
        });
        btn_CapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Bat,Tat;
                Bat=edt_GioBat.getText().toString();
                Tat=edt_GioTat.getText().toString();
                if(Bat==""||Tat==""||(Bat==""&Tat==""))
                {
                    Toast.makeText(LightClockActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    UpdateQuanLy(Bat,Tat);
                    Toast.makeText(LightClockActivity.this, "Đã cập nhật thời gian tưới!", Toast.LENGTH_LONG).show();
                }
                edt_GioBat.setText("");
                edt_GioTat.setText("");
            }
        });
        edt_GioBat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                tpicker = new TimePickerDialog(LightClockActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        edt_GioBat.setText(i + ":" + i1);
                    }
                }, hour, minute, false);
                tpicker.show();
            }
        });
        edt_GioTat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                tpicker = new TimePickerDialog(LightClockActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        edt_GioTat.setText(i + ":" + i1);
                    }
                }, hour, minute, false);
                tpicker.show();
            }
        });
        btn_AS18_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateQuanLy("18:00","05:00");
                Toast.makeText(LightClockActivity.this, "Đã cập nhật thời gian bật đèn!", Toast.LENGTH_LONG).show();
            }
        });
        btn_AS19_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateQuanLy("19:00","01:00");
                Toast.makeText(LightClockActivity.this, "Đã cập nhật thời gian bật đèn!", Toast.LENGTH_LONG).show();
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

                    String Bat = treeSnapshot.child("time_StartDen").getValue(String.class);
                    String Tat = treeSnapshot.child("time_EndDen").getValue(String.class);
                    Boolean clock = treeSnapshot.child("batDen_Gio").getValue(Boolean.class);
//                    Toast.makeText(WaterAutomaticActivity.this,"id quanly "+ id,Toast.LENGTH_LONG).show();
                    // Cập nhật các thuộc tính trong giao diện người dùng
                    if(clock==true)
                    {
                        btn_DenTheoGio.setText("Bật");
                    }
                    else
                    {
                        btn_DenTheoGio.setText("Tắt");
                    }
                    GioBat.setText(String.valueOf(Bat));
                    GioTat.setText(String.valueOf(Tat));
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
    public void UpdateQuanLy(String Bat, String Tat) {

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
                        treeSnapshot.getRef().child("time_StartDen").setValue(Bat);
                        // Cập nhật các thuộc tính trong giao diện người dùng
                        GioBat.setText(String.valueOf(Bat));
                        treeSnapshot.getRef().child("time_EndDen").setValue(Tat);
                        // Cập nhật các thuộc tính trong giao diện người dùng
                        GioBat.setText(String.valueOf(Tat));

                    }
                    catch (NumberFormatException e) {
                        // Handle the exception by setting a default value for newMucDoAm
                        Toast.makeText(LightClockActivity.this, "Vui lòng nhập Mức độ sáng!", Toast.LENGTH_LONG).show();

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
                Log.w(TAG, "Lỗi khi đọc dữ liệu từ Firebase.", databaseError.toException());
                Toast.makeText(LightClockActivity.this, "Không thể đọc dữ liệu từ Firebase. Vui lòng thử lại sau.", Toast.LENGTH_LONG).show();
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
                        if (btn_DenTheoGio.isChecked()) {
                            treeSnapshot.getRef().child("batDen_TuDong").setValue(false);
                            treeSnapshot.getRef().child("batDen_Gio").setValue(true);
                            treeSnapshot.getRef().child("batDen_ThuCong").setValue(false);
                            Toast.makeText(LightClockActivity.this, "Đã bật chế độ chiếu sáng theo giờ!", Toast.LENGTH_LONG).show();

                        } else {
                            treeSnapshot.getRef().child("batDen_Gio").setValue(false);
                            Toast.makeText(LightClockActivity.this, "Đã tắt chế độ tưới sáng theo giờ!", Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (NumberFormatException e) {
                        // Handle the exception by setting a default value for newMucDoAm
                        Toast.makeText(LightClockActivity.this, "Vui lòng nhập Mức độ sáng!", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
                Log.w(TAG, "Lỗi khi đọc dữ liệu từ Firebase.", databaseError.toException());
                Toast.makeText(LightClockActivity.this, "Không thể đọc dữ liệu từ Firebase. Vui lòng thử lại sau.", Toast.LENGTH_LONG).show();
            }
        });
    }

}