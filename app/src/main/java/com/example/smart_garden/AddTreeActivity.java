package com.example.smart_garden;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_garden.Model.Tree;
import com.example.smart_garden.Model.quan_ly;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddTreeActivity extends AppCompatActivity {

    private Button btn_back, btn_add;
    private EditText edit_Name, edit_DoAm, edit_DoSang;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tree);
        init();
        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(AddTreeActivity.this,MyGardenActivity.class);
                startActivity(it1);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTree();
            }
        });
    }
    public void AddTree()
    {
        String name;
        double mucDoAm,mucAS;
        name=edit_Name.getText().toString();
        mucDoAm = Double.parseDouble(edit_DoAm.getText().toString());
        mucAS = Double.parseDouble(edit_DoSang.getText().toString());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String treeId = mDatabase.child("Tree").push().getKey();
        String quanlyID = mDatabase.child("quan_ly").push().getKey();
        Tree tree = new Tree(name,quanlyID);

        quan_ly quanly = new quan_ly(mucDoAm, mucAS);
        tree.setId_Tree(treeId); // Thiết lập giá trị ID cho đối tượng Tree
        quanly.setId_quanLy(quanlyID);

        FirebaseDatabase.getInstance().getReference("Tree").child(treeId)
            .setValue(tree).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(AddTreeActivity.this,"Thêm cây thành công",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddTreeActivity.this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
                }
            });
        FirebaseDatabase.getInstance().getReference("quan_ly").child(quanlyID)
                .setValue(quanly).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddTreeActivity.this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
//        writeNewTree(name,mucDoAm,mucAS);
        edit_Name.setText("");
        edit_DoAm.setText("");
        edit_DoSang.setText("");


    }

//    public void writeNewTree(String name, double mucDoAm, double mucAS) {
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        String treeId = mDatabase.child("Tree").push().getKey();
//        Tree tree = new Tree(name, mucDoAm, mucAS);
//        tree.setID_Tree(treeId); // Thiết lập giá trị ID cho đối tượng Tree
//        mDatabase.child("Tree").child(name).setValue(tree);
//
//    }
    public void init()
    {
        edit_Name=findViewById(R.id.edit_Name);
        edit_DoAm=findViewById(R.id.edit_DoAm);
        edit_DoSang=findViewById(R.id.edit_DoSang);
        btn_back=findViewById(R.id.btn_back);
        btn_add=findViewById(R.id.btn_AddTree);
    }
}