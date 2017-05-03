package com.example.coticd.seznam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.DataAll;
import com.example.Izdelek;
import com.example.Seznam;

import java.util.UUID;

public class ActivityIzdelki extends AppCompatActivity implements View.OnClickListener{
    String id;
    Seznam trenutni;
    ApplicationMy app;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izdelki);

        Bundle extras = getIntent().getExtras();
        id= extras.getString("id");
        app = (ApplicationMy) getApplication();
        trenutni=app.getSeznamById(id);

        mRecyclerView = (RecyclerView) findViewById(R.id.izdelki);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        app = (ApplicationMy) getApplication();
        mAdapter = new AdapterIzdelki(trenutni , this);
        mRecyclerView.setAdapter(mAdapter);

        ImageView dodaj=(ImageView) findViewById(R.id.dodajIzdelek);
        dodaj.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dodajIzdelek:
                Intent i = new Intent(this, ActivityIzdelkiInformacije.class);
                i.putExtra("seznamId",  id);
                i.putExtra("izdelekId",  -1);
                this.startActivity(i);
                break;
        }
    }

    public void setDeleteOnSwipe(final RecyclerView mRecyclerView) {

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                app.removeLocationByPosition(viewHolder.getAdapterPosition());
                app.save();
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}
