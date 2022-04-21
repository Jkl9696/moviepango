package com.example.movieapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeViewHolder> {
    ArrayList<DataSetList> arrayList;
    Context context;


    public YoutubeAdapter(ArrayList<DataSetList> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.video_per_row, parent, false);
        return new YoutubeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeViewHolder holder, int position) {

        DataSetList current = arrayList.get(position);
        holder.webView.loadUrl(current.getLink());
        holder.button.setOnClickListener(v -> {
            Intent i = new Intent(context, VideoFullScreen.class);
            i.putExtra("link", current.getLink());
            context.startActivity(i);



        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
