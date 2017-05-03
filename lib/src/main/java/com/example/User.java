package com.example;

import java.util.ArrayList;

/**
 * Created by CoticD on 28.2.2017.
 */

public class User {
    private String idUser;
    private String vzdevek;
    private ArrayList<String> idPrijatelji;
    private ArrayList<String> idSeznami;

    //konstruktor
    public User(String idUser, String vzdevek, ArrayList<String> idPrijatelji, ArrayList<String> idSeznam) {
        this.idUser = idUser;
        this.vzdevek = vzdevek;
        this.idPrijatelji = idPrijatelji;
        this.idSeznami = idSeznam;
    }
    public User() {
        idUser="";
        vzdevek="";
        idPrijatelji=new ArrayList<>();
        idSeznami=new ArrayList<>();
    }

    //get metode
    public String getIdUser() {return idUser;}
    public String getVzdevek() {return vzdevek;}
    public ArrayList<String> getIdPrijatelji() {return idPrijatelji;}
    public ArrayList<String> getIdSeznam() {return idSeznami;}

    //set metode
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    public void setVzdevek(String vzdevek) {
        this.vzdevek = vzdevek;
    }
    public void setIdPrijatelji(ArrayList<String> list) {
        this.idPrijatelji = list;
    }
    public void setidSeznami(ArrayList<String> list) {
        this.idSeznami = list;
    }


}
