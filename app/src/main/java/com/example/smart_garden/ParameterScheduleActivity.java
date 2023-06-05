package com.example.smart_garden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_garden.Warter.WaterAutomaticActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ParameterScheduleActivity extends AppCompatActivity {

    private Button btn_back, btn_ThongSo,btn_plants;
    private Intent intent;
    private String treeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter_schedule);

        intent = getIntent();
        treeID = intent.getStringExtra("TreeID");
        Toast.makeText(ParameterScheduleActivity.this,"ID tree"+treeID,Toast.LENGTH_LONG).show();

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello World");
        init();
        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        String[] splitDate = formattedDate.split(",");


        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(ParameterScheduleActivity.this,MyGardenActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });
        btn_plants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(ParameterScheduleActivity.this,MyScheduleActivity.class);
                it1.putExtra("TreeID", treeID);
                startActivity(it1);
            }
        });

        btn_ThongSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it2 = new Intent(ParameterScheduleActivity.this,AboutTreeActivity.class);
                it2.putExtra("TreeID", treeID);
                startActivity(it2);
            }
        });
    }
    public void init()
    {
        btn_ThongSo=findViewById(R.id.btn_ThongSo);
        btn_plants=findViewById(R.id.btn_plants);
        btn_back=findViewById(R.id.btn_back);

    }
}