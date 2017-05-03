package com.example;

import java.util.ArrayList;

/**
 * Created by CoticD on 20.3.2017.
 */

public class DataAll {
    private User uporabnik;
    private ArrayList<Seznam> seznami;
    private ArrayList<User> prijatelji;


    public DataAll() {
        this.uporabnik=new User();
        this.seznami=new ArrayList<Seznam>();
        this.prijatelji=new ArrayList<User>();
    }
    public DataAll(User uporabnik, ArrayList<Seznam> seznami, ArrayList<User> prijatelji) {
        this.uporabnik = uporabnik;
        this.seznami = seznami;
        this.prijatelji = prijatelji;
    }

    public static DataAll scenarijA() {
        DataAll scenarij=new DataAll();
        scenarij.uporabnik=new User("1", "User1", new ArrayList<String>(), new ArrayList<String>());
        scenarij.seznami.add(new Seznam("1","Seznam 1"));
        scenarij.seznami.add(new Seznam("2","Seznam 2"));
        scenarij.seznami.add(new Seznam("3","Seznam 3"));
        scenarij.seznami.add(new Seznam("4","Seznam 4"));
        scenarij.seznami.add(new Seznam("5","Seznam 5"));
        scenarij.seznami.add(new Seznam("6","Seznam 6"));
        scenarij.seznami.add(new Seznam("7","Seznam 7"));
        scenarij.seznami.add(new Seznam("8","Seznam 8"));
        scenarij.seznami.add(new Seznam("9","Seznam 9"));
        scenarij.seznami.add(new Seznam("10","Seznam 10"));
        scenarij.seznami.add(new Seznam("11","Seznam 11"));
        scenarij.seznami.add(new Seznam("12","Seznam 12"));
        scenarij.seznami.add(new Seznam("13","Seznam 13"));
        scenarij.seznami.add(new Seznam("14","Seznam 14"));
        scenarij.seznami.add(new Seznam("15","Seznam 15"));
        scenarij.seznami.add(new Seznam("16","Seznam 16"));

        return scenarij;
    }

    public User getUporabnik() {return uporabnik;}
    public ArrayList<Seznam> getSeznami() {return seznami;}
    public ArrayList<User> getPrijatelji() {return prijatelji;}

    public void setUporabnik(User uporabnik) {this.uporabnik = uporabnik;}
    public void setSeznami(ArrayList<Seznam> seznami) {this.seznami = seznami;}
    public void setPrijatelji(ArrayList<User> prijatelji) {this.prijatelji = prijatelji;}

    public Seznam getSeznam(int position) {

        return this.seznami.get(position);
    }

    public int getSeznamSize() {
        return this.seznami.size();
    }

    public void addS(Seznam nov) {seznami.add(nov);}
    public int addI(String idseznama, Izdelek nov) {
        for (Seznam s: seznami) {
            if (s.getId().equals(idseznama)){
                s.addIzdelek(nov);
                return s.izdelkiSize()-1;
            }
        }
        return -1;
    }
    public void izbrisi(int position) {seznami.remove(position);}

    public Seznam getSeznamById(String id) {
        for (Seznam s: seznami) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }


    public void spremeniIzdelek(String idseznama, int position, String naziv, String opis, String path) {
        for (Seznam s: seznami) {
            if (s.getId().equals(idseznama)) s.spremeniIzdelek(position, naziv, opis, path);
        }
    }


}