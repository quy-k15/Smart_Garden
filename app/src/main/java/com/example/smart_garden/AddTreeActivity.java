package com.example.smart_garden;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_garden.Model.Tree;
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
//    public void AddTree() {
//        // Get a reference to the Firebase database
//        DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference();
//
//        // Get a reference to the "users" node in the database and create a new child node
//        DatabaseReference usersRef = firebaseRef.child("Tree").push();
//
//        // Set the values of the new child node
//        Map<String, Object> userData = new HashMap<>();
////        userData.put("Name", edit_Name.getText());
////        userData.put("Muc_DoAm", edit_DoAm.getText());
////        userData.put("Muc_AS", edit_DoSang.getText());
//        List<String> doAmList = new ArrayList<>();
//        doAmList.add(edit_DoAm.getText().toString());
//
//        List<String> asList = new ArrayList<>();
//        asList.add(edit_DoSang.getText().toString());
//
//        userData.put("Name", edit_Name.getText().toString());
//        userData.put("Muc_DoAm", doAmList);
//        userData.put("Muc_AS", asList);
//        usersRef.setValue(userData);
//
//        // Alternatively, you can also use a ValueListener to set the values of the new child node:
//
//        usersRef.addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//
////            dataSnapshot.getRef().child("name").setValue(edit_Name.getText());
////            dataSnapshot.getRef().child("Muc_DoAm").setValue(edit_DoAm.getText());
////            dataSnapshot.getRef().child("Muc_AS").setValue(edit_DoSang.getText());
//            List<String> nameList = new ArrayList<>();
//            nameList.add(edit_Name.getText().toString());
//
//            List<String> doAmList = new ArrayList<>();
//            doAmList.add(edit_DoAm.getText().toString());
//
//            List<String> asList = new ArrayList<>();
//            asList.add(edit_DoSang.getText().toString());
//
//            dataSnapshot.getRef().child("Muc_DoAm").setValue(doAmList);
//            dataSnapshot.getRef().child("Muc_AS").setValue(asList);
//        }
//
//        @Override
//        public void onCancelled(DatabaseError error) {
//            // Failed to read value
//            Log.w(TAG, "Failed to read value.", error.toException());
//        }
//    });
//
//    }
    public void AddTree()
    {
        String name;
        double mucDoAm,mucAS;
        name=edit_Name.getText().toString();
        mucDoAm = Double.parseDouble(edit_DoAm.getText().toString());
        mucAS = Double.parseDouble(edit_DoSang.getText().toString());
        writeNewTree(name,mucDoAm,mucAS);
        Toast.makeText(AddTreeActivity.this, "Thêm cây thành công!", Toast.LENGTH_LONG).show();
        edit_Name.setText("");
        edit_DoAm.setText("");
        edit_DoSang.setText("");


    }

    public void writeNewTree(String name, double mucDoAm, double mucAS) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String treeId = mDatabase.child("Tree").push().getKey();
        Tree tree = new Tree(name, mucDoAm, mucAS);
        tree.setID_Tree(treeId); // Thiết lập giá trị ID cho đối tượng Tree
        mDatabase.child("Tree").child(treeId).setValue(tree);
    }
    public void init()
    {
        edit_Name=findViewById(R.id.edit_Name);
        edit_DoAm=findViewById(R.id.edit_DoAm);
        edit_DoSang=findViewById(R.id.edit_DoSang);
        btn_back=findViewById(R.id.btn_back);
        btn_add=findViewById(R.id.btn_AddTree);
    }
}