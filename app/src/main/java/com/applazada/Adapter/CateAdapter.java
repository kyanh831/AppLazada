package com.applazada.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.applazada.Models.Category;
import com.applazada.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class CateAdapter extends RecyclerView.Adapter<CateAdapter.ViewHolder> {
    List<Category> categoryList;

    public CateAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView = inflater.inflate(R.layout.item_cate,parent,false);
        ViewHolder viewHolder = new ViewHolder(categoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CateAdapter.ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.cate_name.setText(category.getCate_name());
        Glide.with(holder.context).load(category.getUrl_img()).placeholder(R.drawable.ic_launcher_background).into(holder.cate_img);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cate_img;
        TextView cate_name;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cate_img = itemView.findViewById(R.id.cate_img);
            cate_name = itemView.findViewById(R.id.cate_name);
            context = itemView.getContext();
        }
    }
}
