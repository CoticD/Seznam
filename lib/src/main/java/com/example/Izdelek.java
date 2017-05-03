package com.example;

/**
 * Created by CoticD on 28.2.2017.
 */

public class Izdelek {
    private String naziv;
    private String opis;
    private String path;
    private boolean isChecked;
    public static final String NODATA="_NA";

    public Izdelek( String naziv, String opis, String path) {
        this.naziv = naziv;
        this.opis = opis;
        this.path = path;
        this.isChecked = false;
    }

    public Izdelek() {
        this.naziv = "";
        this.opis = "";
        this.path = null;
        this.isChecked = false;
    }

    //get metode
    public String getNaziv() {
        return naziv;
    }
    public String getOpis() {return opis;}
    public String getPath() {return path;}
    public boolean getIsChecked() {return isChecked;}

    //set metode
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }
    public void setPath(String path) {this.path = path;}
    public void setIsChecked(boolean isChecked) {this.isChecked = isChecked;}

    public boolean hasImage() {
        if (path==null) return false;
        else if (path.equals(NODATA)) return false;
        return true;
    }
}
