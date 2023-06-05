package com.example.smart_garden;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smart_garden.Model.Tree;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class TreeAdapter extends RecyclerView.Adapter<TreeAdapter.MyViewHolder>{

    MyGardenActivity myGarden;
    Tree tree;
    private Context mcon;
    private List<Tree> mtree;
    FirebaseFirestore dtb;
    String Name, id_tree;
    MyGardenActivity myGardenActivity;
    public TreeAdapter(Context con,List<Tree> mtree){
//        this.myGarden=mContext;
        mcon = con;
        this.mtree=mtree;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tree, parent, false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        tree = mtree.get(position);
        dtb = FirebaseFirestore.getInstance();
        id_tree=tree.getId_Tree();
        holder.name.setText(tree.getName());
        holder.tv_header.setText("Quản lý vườn:");
        holder.btn_Detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcon, ParameterScheduleActivity.class);
                intent.putExtra("TreeID", tree.getId_Tree());
                mcon.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mtree.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, tv_header;
        Button btn_Detail,btn_Update;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_Name);
            tv_header=itemView.findViewById(R.id.QuanLy);
            btn_Detail=itemView.findViewById(R.id.btn_Detail);
            btn_Update=itemView.findViewById(R.id.btn_Update);
        }
    }
}
