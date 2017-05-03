package com.example.coticd.seznam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.Seznam;

import java.util.UUID;

public class ActivitySeznami extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ApplicationMy app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seznami);

        mRecyclerView = (RecyclerView) findViewById(R.id.seznami);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        app = (ApplicationMy) getApplication();
        mAdapter = new AdapterSeznami(app.getAll(), this);
        mRecyclerView.setAdapter(mAdapter);

        ImageView dodaj=(ImageView) findViewById(R.id.imageView3);
        ImageView user=(ImageView) findViewById(R.id.imageView8);
        dodaj.setOnClickListener(this);
        user.setOnClickListener(this);

        setDeleteOnSwipe(mRecyclerView);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
        app.save();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView3:
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.activity_popupseznami, null);
                final EditText imeSeznama = (EditText) alertLayout.findViewById(R.id.imeSeznama);

                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Seznam");
                alert.setView(alertLayout);
                alert.setCancelable(false);
                alert.setNegativeButton("Prekliči", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setPositiveButton("Ustvari", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ime = imeSeznama.getText().toString();
                        String seznamID = UUID.randomUUID().toString();
                        Seznam nov=new Seznam(seznamID, ime);
                        app.addSeznam(nov);
                        mAdapter.notifyDataSetChanged();
                        app.save();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
                break;
            case R.id.imageView8:
                Intent i = new Intent(this, ActivityUser.class);
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
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                app.removeLocationByPosition(viewHolder.getAdapterPosition());
                                app.save();
                                mRecyclerView.getAdapter().notifyDataSetChanged();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                mRecyclerView.getAdapter().notifyDataSetChanged();
                                break;
                        }
                        // mRecyclerView.getAdapter().notifyDataSetChanged();
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(ActivitySeznami.this);
                builder.setTitle("Izbriši seznam");
                builder.setMessage("Ste prepričani?").setPositiveButton("Da", dialogClickListener)
                        .setNegativeButton("Ne", dialogClickListener)
                ;
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                    }
                });
                builder.show();

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}
