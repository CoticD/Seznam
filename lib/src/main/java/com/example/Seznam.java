package com.example;

import java.util.ArrayList;

/**
 * Created by CoticD on 28.2.2017.
 */

public class Seznam {
    private String id;
    private String naziv;
    private ArrayList<Izdelek> list;
    private ArrayList<String> idUser;

    public Seznam(String id, String naziv) {
        this.id = id;
        this.naziv=naziv;
        list = new ArrayList<Izdelek>();
        list.add(new Izdelek("Testenine", "1kg 1kg 1kg 1kg 1kg1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg 1kg",""));
        list.add(new Izdelek("Mortadela", "20dag",""));
        list.add(new Izdelek("Sir edamec", "narezan 20dag",""));
        list.add(new Izdelek("Kruh", "črn hleb",""));
        list.add(new Izdelek("Mleko", "alpsko 3,5 * 6",""));
    }

    public String getId() {return id;}
    public String getNaziv() {return naziv;}
    public ArrayList<Izdelek> getList() {return list;}
    public ArrayList<String> getIdUser() {return idUser;}

    public void setId(String id) {this.id = id;}
    public void setNaziv(String naziv) {this.naziv = naziv;}
    public void setList(ArrayList<Izdelek> list) {this.list = list;}
    public void setIdUser(ArrayList<String> idUser) {this.idUser = idUser;}

    public int getIzdelkiSize() { return list.size();}
    public Izdelek getIzdelek(int position) { return list.get(position);}
    public void izbrisi(int position) { list.remove(position);}

    public void addIzdelek(Izdelek nov) {list.add(nov);}

    public void spremeniIzdelek(int position, String naziv, String opis, String path) {
        list.get(position).setNaziv(naziv);
        list.get(position).setOpis(opis);
        list.get(position).setPath(path);
    }

    public int izdelkiSize() {return list.size();}
}
