package com.example.trystudentapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewAdapter> {


    private Context context;
    private List<String> list;

    public GalleryAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GalleryViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.galley_image,parent,false);


        return new GalleryViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewAdapter holder, int position) {

        Glide.with(context).load(list.get(position)).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GalleryViewAdapter extends RecyclerView.ViewHolder {
        ImageView imageView;
        public GalleryViewAdapter(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ImageGallery);


        }
    }


}
