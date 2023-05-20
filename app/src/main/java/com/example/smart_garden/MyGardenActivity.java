package com.example.smart_garden;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smart_garden.Model.Tree;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyGardenActivity extends AppCompatActivity {
    private Button btn_back,btn_add;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private RecyclerView rcvtree;
    private TreeAdapter treeAdapter;
    private ArrayList<Tree> mtree;
    TextView month,day,year, tv_DoAm,tv_AnhSang,tv_NhietDo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_garden);
        init();
        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        String[] splitDate = formattedDate.split(",");

        month.setText(splitDate[1]+"/");
        day.setText(splitDate[0]+"/");
        year.setText(splitDate[2]);
        getListTreeData();// Lấy dữ liệu các loại cây từ firebase

        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(MyGardenActivity.this,MainActivity.class);
                startActivity(it1);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(MyGardenActivity.this,AddTreeActivity.class);
                startActivity(it1);
            }
        });

    }
    public void init()
    {
        btn_back=findViewById(R.id.btn_back);
        btn_add=findViewById(R.id.btn_AddTree);
        day=findViewById(R.id.tv_day);
        month=findViewById(R.id.tv_month);
        year=findViewById(R.id.tv_year);

        rcvtree=findViewById(R.id.tree_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvtree.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvtree.addItemDecoration(dividerItemDecoration);

        mtree = new ArrayList<>();
        treeAdapter = new TreeAdapter(this,mtree);
        rcvtree.setAdapter(treeAdapter);
    }
    private void getListTreeData()
    {
        mFirebaseInstance=FirebaseDatabase.getInstance();
        mDatabase=FirebaseDatabase.getInstance().getReference("Tree");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Tree tree = dataSnapshot.getValue(Tree.class);
                    mtree.add(tree);
                }
                treeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyGardenActivity.this,"Lấy dữ liệu cây lỗi!",Toast.LENGTH_LONG);
            }
        });
    }




}
