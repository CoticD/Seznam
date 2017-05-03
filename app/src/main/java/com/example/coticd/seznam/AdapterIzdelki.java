package com.example.coticd.seznam;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.DataAll;
import com.example.Izdelek;
import com.example.Seznam;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by CoticD on 21.3.2017.
 */

public class AdapterIzdelki extends RecyclerView.Adapter<AdapterIzdelki.ViewHolder>{
    Seznam seznam;
    Activity ac;

    public AdapterIzdelki(Seznam all, Activity ac) {
        this.seznam = all;
        this.ac = ac;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView nazivSeznama;
        public TextView opisSeznama;
        public ImageView image;
        public CheckBox checkBox;

        public ViewHolder(View v) {
            super(v);
            nazivSeznama = (TextView) v.findViewById(R.id.nazivSeznama);
            opisSeznama = (TextView) v.findViewById(R.id.opisSeznama);
            image = (ImageView)v.findViewById(R.id.imageView6);
            checkBox = (CheckBox) v.findViewById(R.id.checkBox);
        }
    }


    @Override
    public AdapterIzdelki.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_izdelek, parent, false);
        // set the view's size, margins, paddings and layout parameters
        AdapterIzdelki.ViewHolder vh = new AdapterIzdelki.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final AdapterIzdelki.ViewHolder holder, final int position) {
        final Izdelek trenutni=seznam.getIzdelek(position);
        final String naziv=trenutni.getNaziv();
        final String opis=trenutni.getOpis();
        holder.nazivSeznama.setText(naziv);
        holder.opisSeznama.setText(opis);
        if (trenutni.hasImage()) {
            System.out.println("Picasso: "+trenutni.getPath());


            File f = new File(trenutni.getPath());
            Picasso.with(ac.getApplicationContext())
                    .load(f) //URL
                    .placeholder(R.drawable.ic_cloud_download_black_32dp)
                    .error(R.drawable.ic_error_black_32dp)
                    .noFade()
                    .into(holder.image);
        }
        if(trenutni.getIsChecked()==true){
            holder.checkBox.setChecked(true);
            holder.checkBox.setBackgroundColor(Color.GREEN);
        }else{
            holder.checkBox.setChecked(false);
            holder.checkBox.setBackgroundColor(Color.RED);
        }
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkBox.isChecked()){
                    trenutni.setIsChecked(true);
                }else{
                    trenutni.setIsChecked(false);
                }

                if(trenutni.getIsChecked()==true){
                    holder.checkBox.setChecked(true);
                    holder.checkBox.setBackgroundColor(Color.GREEN);
                }else{
                    holder.checkBox.setChecked(false);
                    holder.checkBox.setBackgroundColor(Color.RED);
                }
            }
        });
        holder.nazivSeznama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ac.getBaseContext(), ActivityIzdelkiInformacije.class);
                i.putExtra("seznamId",  seznam.getId());
                i.putExtra("izdelekId",  position);
                ac.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return seznam.getIzdelkiSize();
    }
}
