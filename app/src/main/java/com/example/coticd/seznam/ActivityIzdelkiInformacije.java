package com.example.coticd.seznam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Izdelek;
import com.example.Seznam;
import com.frosquivel.magicalcamera.Functionallities.PermissionGranted;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ActivityIzdelkiInformacije extends AppCompatActivity implements View.OnClickListener{
    String idseznama;
    int position;
    Seznam trenutni;
    ImageView ivSlika;
    ApplicationMy app;
    TextView naziv;
    TextView opis;
    Button shrani;
    String path;
    Button slikaj;
    PermissionGranted permissionGranted;
    MagicalCamera magicalCamera;

    private int RESIZE_PHOTO_PIXELS_PERCENTAGE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izdelki_informacije);


        naziv=(TextView)findViewById(R.id.izdelekNaziv);
        opis=(TextView)findViewById(R.id.izdelekOpis);
        shrani=(Button)findViewById(R.id.shrani);
        slikaj=(Button)findViewById(R.id.dodajSliko);
        ivSlika=(ImageView) findViewById(R.id.slika);

        shrani.setOnClickListener(this);
        slikaj.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        idseznama= extras.getString("seznamId");
        position=extras.getInt("izdelekId");

        app = (ApplicationMy) getApplication();

        trenutni=app.getSeznamById(idseznama);
        if(position!=-1){
            Izdelek edit=trenutni.getIzdelek(position);
            naziv.setText(edit.getNaziv());
            opis.setText(edit.getOpis());

            File f = new File(trenutni.getIzdelek(position).getPath());
            Picasso.with(getApplicationContext())
                    .load(f)
                    .placeholder(R.drawable.ic_cloud_download_black_32dp)
                    .error(R.drawable.ic_error_black_32dp)
                    .noFade()
                    .into(ivSlika);

        }

        permissionGranted = new PermissionGranted(this);

        if (android.os.Build.VERSION.SDK_INT >= 24) {
            permissionGranted.checkAllMagicalCameraPermission();
        }else{
            permissionGranted.checkCameraPermission();
            permissionGranted.checkReadExternalPermission();
            permissionGranted.checkWriteExternalPermission();
            permissionGranted.checkLocationPermission();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (magicalCamera ==null)    magicalCamera =  new MagicalCamera(this,RESIZE_PHOTO_PIXELS_PERCENTAGE,permissionGranted);
        magicalCamera.permissionGrant(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        magicalCamera.resultPhoto(requestCode, resultCode, data);

        Bitmap slika=magicalCamera.getPhoto();

        int srcWidth = slika.getWidth();
        int srcHeight = slika.getHeight();
        int dstWidth = (int)(srcWidth*0.1f);
        int dstHeight = (int)(srcHeight*0.1f);
        Bitmap dstBitmap = Bitmap.createScaledBitmap(slika, dstWidth, dstHeight, true);
        ivSlika.setImageBitmap(dstBitmap);

        path = magicalCamera.savePhotoInMemoryDevice(dstBitmap,"myPhotoName", MagicalCamera.JPEG, true);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dodajSliko:
                if (magicalCamera ==null) magicalCamera =  new MagicalCamera(this,permissionGranted);
                magicalCamera.takePhoto();
                break;
            case R.id.shrani:
                String naz=naziv.getText().toString();
                String opi=opis.getText().toString();
                if(position==-1) {
                    if (naz.matches("") || opi.matches("")) {
                        Toast.makeText(this, "Mankajo podatki", Toast.LENGTH_SHORT).show();
                    }else{
                        Izdelek nov = new Izdelek(naz, opi, path);
                        position = app.addIzdelek(idseznama, nov);
                        app.save();
                        Toast.makeText(this, "Izdelek ustvarjen.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    if (naz.matches("") || opi.matches("")) {
                        Toast.makeText(this, "Mankajo podatki", Toast.LENGTH_SHORT).show();
                    } else {
                        app.spremeniIzdelek(idseznama, position, naz, opi, path);
                        app.save();
                        Toast.makeText(this, "Podatki shranjeni", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

}
