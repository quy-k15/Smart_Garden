package com.example.smart_garden.Light;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_garden.MainActivity;
import com.example.smart_garden.R;
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
    private Button btn_back;
    private DatabaseReference databaseRef;
    private String treeID, Name;
    Intent intent;
    private ToggleButton btn_DenTheoGio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_clock);
        intent = getIntent();
        treeID = intent.getStringExtra("TreeID");
        Name = intent.getStringExtra("Name");

        init();
//        getThong_So();
//        getTree();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_left);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(LightClockActivity.this, LightScheduleActivity.class);
                startActivity(it1);
            }
        });
        btn_DenTheoGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_DenTheoGio.isChecked())
                {
                    DatabaseReference databaseRef = database.getReference().child("thong_so").child("Den_Tu_Dong");
                    databaseRef.setValue(true);

                }
                else
                {
                    DatabaseReference databaseRef = database.getReference().child("thong_so").child("Den_Tu_Dong");
                    databaseRef.setValue(false);
                }
            }
        });
    }

    public void init() {
        btn_back = findViewById(R.id.btn_back);
        btn_DenTheoGio=findViewById(R.id.btn_DenTheoGio);
    }

//    public void getThong_So() {
//    // Lấy đường dẫn đến bảng Tree và tìm các node có "id_Tree" bằng treeID
//    databaseRef = FirebaseDatabase.getInstance().getReference().child("Tree");
//    Query treeData = databaseRef.orderByChild("id_Tree").equalTo(treeID);
//
//    // Đăng ký một listener để theo dõi thay đổi giá trị trên database
//    ValueEventListener valueEventListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            // Lặp qua từng node trả về và lấy dữ liệu tương ứng
//            for (DataSnapshot treeSnapshot : dataSnapshot.getChildren()) {
//                String ID = treeSnapshot.child("id_Tree").getValue(String.class);
//                Double DoAmValue = treeSnapshot.child("doAm").getValue(Double.class);
//                Double AnhSangValue = treeSnapshot.child("doSang").getValue(Double.class);
//                Double NhietDoValue = treeSnapshot.child("nhietDo").getValue(Double.class);
//
//                // Cập nhật các thuộc tính trong giao diện người dùng
//                tv_DoAm.setText(String.valueOf(DoAmValue) + "%");
//                tv_AnhSang.setText(String.valueOf(AnhSangValue) + " Lux");
//                tv_NhietDo.setText(String.valueOf(NhietDoValue) + " °C");
//                Log.d(TAG, "DataSnapshot: " + dataSnapshot);
//
//            }
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//            // Xử lý khi có lỗi xảy ra
//        }
//    };
//    // Đăng ký listener với đường dẫn của bảng Tree trong Firebase Realtime Database
//    treeData.addValueEventListener(valueEventListener);;
//    }
}