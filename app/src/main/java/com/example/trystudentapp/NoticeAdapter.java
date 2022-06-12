package com.example.trystudentapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;


public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewAdapter> {
    private Context context;
    private ArrayList<com.example.trystudentapp.NoticeData> list;

    public NoticeAdapter(Context context, ArrayList<com.example.trystudentapp.NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_itemlayout, parent, false);
        return new NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, @SuppressLint("RecyclerView") int position) {

        // position = holder.getAdapterPosition();
        com.example.trystudentapp.NoticeData currentItem = list.get(position);
        holder.NoticeTitle.setText(currentItem.getTitle());
        holder.date.setText(currentItem.getDate());
        holder.time.setText(currentItem.getTime());
        try {
            if (currentItem.getImage() != null) {
                Glide.with(context).load(currentItem.getImage()).into(holder.NoticeImg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {

        //private Button deleteNotice;
        private TextView NoticeTitle, date, time;
        private ImageView NoticeImg;

        public NoticeViewAdapter(@NonNull View itemView) {

            super(itemView);
            //deleteNotice = itemView.findViewById(R.id.deleteNoticeBtn);
            NoticeTitle = itemView.findViewById(R.id.NoticeTitle);
            NoticeImg = itemView.findViewById(R.id.NoticeImg);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.timeView);
        }
    }
}

