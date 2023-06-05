package com.example.smart_garden;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_garden.Model.Tree;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class AboutTreeActivity extends AppCompatActivity {
    private Button btn_back;
    private DatabaseReference databaseRef;
    private String treeID, Name;
    Intent intent;
    TextView month, day, year, tv_DoAm, tv_AnhSang, tv_NhietDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_tree);
        intent = getIntent();
        treeID = intent.getStringExtra("TreeID");
//        Toast.makeText(AboutTreeActivity.this,"ID tree"+treeID,Toast.LENGTH_LONG).show();

        init();

        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        String[] splitDate = formattedDate.split(",");

        month.setText(splitDate[1] + "/");
        day.setText(splitDate[0] + "/");
        year.setText(splitDate[2]);


        getThong_So();
//        getTree();

        overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_left);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(AboutTreeActivity.this, ParameterScheduleActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });
    }

    public void init() {
        btn_back = findViewById(R.id.btn_back);
        day = findViewById(R.id.tv_day);
        month = findViewById(R.id.tv_month);
        year = findViewById(R.id.tv_year);
        tv_DoAm = findViewById(R.id.tv_DoAm);
        tv_AnhSang = findViewById(R.id.tv_AnhSang);
        tv_NhietDo = findViewById(R.id.tv_NhietDo);
    }

    public void getThong_So() {
    // Lấy đường dẫn đến bảng Tree và tìm các node có "id_Tree" bằng treeID
    databaseRef = FirebaseDatabase.getInstance().getReference().child("Tree");
    Query treeData = databaseRef.orderByChild("id_Tree").equalTo(treeID);

    // Đăng ký một listener để theo dõi thay đổi giá trị trên database
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // Lặp qua từng node trả về và lấy dữ liệu tương ứng
            for (DataSnapshot treeSnapshot : dataSnapshot.getChildren()) {
                String ID = treeSnapshot.child("id_Tree").getValue(String.class);
                Double DoAmValue = treeSnapshot.child("doAm").getValue(Double.class);
                Double AnhSangValue = treeSnapshot.child("doSang").getValue(Double.class);
                Double NhietDoValue = treeSnapshot.child("nhietDo").getValue(Double.class);

                // Cập nhật các thuộc tính trong giao diện người dùng
                tv_DoAm.setText(String.valueOf(DoAmValue) + "%");
                tv_AnhSang.setText(String.valueOf(AnhSangValue) + " Lux");
                tv_NhietDo.setText(String.valueOf(NhietDoValue) + " °C");
                Log.d(TAG, "DataSnapshot: " + dataSnapshot);

            }
        }
 
        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Xử lý khi có lỗi xảy ra
        }
    };
    // Đăng ký listener với đường dẫn của bảng Tree trong Firebase Realtime Database
    treeData.addValueEventListener(valueEventListener);;
    }
}