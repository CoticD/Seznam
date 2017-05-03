package com.example.coticd.seznam;

import android.app.Application;

import com.example.DataAll;
import com.example.Izdelek;
import com.example.Seznam;
import com.example.User;

import java.io.File;
import java.util.List;
/**
 * Created by CoticD on 14.3.2017.
 */

public class ApplicationMy extends Application {
    DataAll all;
    private static final String DATA_MAP = "seznamdatamap";
    private static final String FILE_NAME = "seznami.json";

    public void onCreate() {
        super.onCreate();
        if (!load())
            all = all.scenarijA();
    }

    public DataAll getAll() {return all;}
    public void setAll(DataAll all) {this.all = all;}

    public void addSeznam(Seznam nov) {
        all.addS(nov);
    }
    public int addIzdelek(String idseznama, Izdelek nov) { return all.addI(idseznama, nov);}

    public Seznam getSeznamById(String id) {
        return all.getSeznamById(id);
    }

    public User getUser() {return all.getUporabnik();}

    public boolean save() {
        File file = new File(this.getExternalFilesDir(DATA_MAP), ""
                + FILE_NAME);

        return ApplicationJson.save(all,file);
    }
    public boolean load(){
        File file = new File(this.getExternalFilesDir(DATA_MAP), ""
                + FILE_NAME);
        DataAll tmp = ApplicationJson.load(file);
        if (tmp!=null) all = tmp;
        else return false;
        return true;
    }

    public void removeLocationByPosition(int adapterPosition) {
        all.getSeznami().remove(adapterPosition);
    }


    public void spremeniIzdelek(String idseznama, int position, String naziv, String opis, String path) {
        all.spremeniIzdelek(idseznama, position, naziv, opis, path);
    }


}