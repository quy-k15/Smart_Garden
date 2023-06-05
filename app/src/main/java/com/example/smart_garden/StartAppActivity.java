package com.example.smart_garden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartAppActivity extends AppCompatActivity {
    private Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);
        btn_start = findViewById(R.id.btn_start);

        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it2 = new Intent(StartAppActivity.this,MyGardenActivity.class);
                startActivity(it2);
            }
        });
    }
}
