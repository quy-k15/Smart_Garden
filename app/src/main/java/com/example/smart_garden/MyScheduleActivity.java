package com.example.smart_garden;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyScheduleActivity extends AppCompatActivity {
    private Button btn_back,btn_LichTuoiNuoc,btn_LichThapDen,btn_TuoiNuoc,btn_BatDen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);

        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        init();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(MyScheduleActivity.this,MainActivity.class);
                startActivity(it1);
            }
        });
        btn_LichTuoiNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it2 = new Intent(MyScheduleActivity.this,ScheduleWaterActivity.class);
                startActivity(it2);
            }
        });
        btn_LichThapDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it3 = new Intent(MyScheduleActivity.this,ScheduleLightActivity.class);
                startActivity(it3);
            }
        });
        btn_TuoiNuoc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference databaseRef = database.getReference().child("thong_so").child("TuoiNuoc");

                btn_TuoiNuoc.setText("Tắt vòi bơm");
                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("thong_so");
//                .child("TuoiNuoc");

                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Lấy giá trị mới từ database và cập nhật vào UI
//                      double soilMoisture = dataSnapshot.child("soil_moisture").getValue(Double.class);

                        Boolean Tuoi_Nuoc = dataSnapshot.child("TuoiNuoc").getValue(Boolean.class);


                        if(Tuoi_Nuoc==null )
                        {
                            Log.d("tuoi nuoc"," null");
                        }
                        else{

                            set_Tuoi_Nuoc(Tuoi_Nuoc);

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Xử lý khi có lỗi xảy ra
                    }
                };

                databaseRef.addValueEventListener(valueEventListener);


            }
        });
    }
    public void set_Tuoi_Nuoc(boolean tuoinuoc)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference().child("thong_so").child("TuoiNuoc");

        if(tuoinuoc == false) {

            databaseRef.setValue(true); //Thay đổi giá trị của node "TuoiNuoc" thành true
        } else {
            databaseRef.setValue(false); //Thay đổi giá trị của node "TuoiNuoc" thành false

        }



    }
    public void init()
    {
        btn_back=findViewById(R.id.btn_back);
        btn_LichTuoiNuoc=findViewById(R.id.btn_LichTuoiNuoc);
        btn_LichThapDen=findViewById(R.id.btn_LichThapDen);
        btn_TuoiNuoc=findViewById(R.id.btn_TuoiNuoc);
        btn_BatDen=findViewById(R.id.btn_BatDen);
    }
}
