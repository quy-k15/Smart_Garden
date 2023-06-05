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

public class LightManuallyActivity extends AppCompatActivity {
    private Button btn_back;
    private ToggleButton btn_DenThuCong;
    private DatabaseReference databaseRef;
    private String treeID, Name;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_manually);
        intent = getIntent();
        treeID = intent.getStringExtra("TreeID");
        Name = intent.getStringExtra("Name");
        init();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_left);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(LightManuallyActivity.this, LightScheduleActivity.class);
                startActivity(it1);
            }
        });
        btn_DenThuCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_DenThuCong.isChecked())
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
        btn_DenThuCong=findViewById(R.id.btn_DenThuCong);
    }


}