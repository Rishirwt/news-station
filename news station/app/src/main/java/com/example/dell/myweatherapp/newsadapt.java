package com.example.dell.myweatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class newsadapt extends RecyclerView.Adapter<newsadapt.viewholder> {
    ArrayList<newsblock> news;
    public newsadapt(ArrayList<newsblock> news) {
        this.news = news;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=(LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview=layoutInflater.inflate(R.layout.card,viewGroup,false);
        return new viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {
        newsblock singlenews=news.get(i);
        viewholder.title.setText(String.valueOf(singlenews.getTitle()));

        viewholder.desc.setText(String.valueOf(singlenews.getDesc()));
        viewholder.author.setText(String.valueOf(singlenews.getAuthor()));
        viewholder.published.setText(String.valueOf(singlenews.getPublished()));


        Picasso.get().load(singlenews.getUrlimage()).into(viewholder.image);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    class viewholder extends RecyclerView.ViewHolder
    {

        TextView title,desc,published,author;
        ImageView image;
        public viewholder(@NonNull View itemView) {
            super(itemView);
title=itemView.findViewById(R.id.title);
desc=itemView.findViewById(R.id.desc);
author=itemView.findViewById(R.id.author);
published=itemView.findViewById(R.id.publishedat);
image=itemView.findViewById(R.id.image);

        }
    }
}
