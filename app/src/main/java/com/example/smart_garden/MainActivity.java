package com.example.smart_garden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button btn_garden,btn_plants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello World");
        init();
        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        String[] splitDate = formattedDate.split(",");


        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        btn_plants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(MainActivity.this,MyScheduleActivity.class);
                startActivity(it1);
            }
        });

        btn_garden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it2 = new Intent(MainActivity.this,MyGardenActivity.class);
                startActivity(it2);
            }
        });
    }
    public void init()
    {
        btn_garden=findViewById(R.id.btn_garden);
        btn_plants=findViewById(R.id.btn_plants);

    }
}