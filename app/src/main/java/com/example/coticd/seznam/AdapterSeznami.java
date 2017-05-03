package com.example.coticd.seznam;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.DataAll;
import com.example.Seznam;

/**
 * Created by CoticD on 14.3.2017.
 */

public class AdapterSeznami extends RecyclerView.Adapter<AdapterSeznami.ViewHolder>{
    DataAll all;
    Activity ac;

    public AdapterSeznami(DataAll all, Activity ac) {
        this.all = all;
        this.ac = ac;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView imeSeznama;

        public ViewHolder(View v) {
            super(v);
            imeSeznama = (TextView) v.findViewById(R.id.nazivSeznama);
        }
    }

    private static void startDView(String lokacijaID, Activity ac) {
        Intent i = new Intent(ac.getBaseContext(), ActivityIzdelki.class);
        i.putExtra("id",  lokacijaID);
        ac.startActivity(i);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seznam, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Seznam trenutni=all.getSeznam(position);
        final String naziv=trenutni.getNaziv();
        holder.imeSeznama.setText(naziv);

        holder.imeSeznama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdapterSeznami.startDView(trenutni.getId(),ac);
            }
        });
    }

    @Override
    public int getItemCount() {
        return all.getSeznamSize();
    }
}
