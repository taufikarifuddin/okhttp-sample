package com.app.pariwisata.model;

import java.util.ArrayList;

/**
 * Created by Taufik on 04/12/17.
 */

public class ObjectWisata {

    public static String ID_INDEX = "id";
    private int id;
    private String name,desc,jamOperasional,biaya;
    private ArrayList<ObjectFoto> foto;
    private ArrayList<ObjectReview> reviews;

    public ObjectWisata(String name) {
        this.name = name;
    }

    public ObjectWisata() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getJamOperasional() {
        return jamOperasional;
    }

    public void setJamOperasional(String jamOperasional) {
        this.jamOperasional = jamOperasional;
    }

    public String getBiaya() {
        return biaya;
    }

    public void setBiaya(String biaya) {
        this.biaya = biaya;
    }

    public void setFoto(ArrayList<ObjectFoto> foto) {
        this.foto = foto;
    }

    public ArrayList<ObjectFoto> getFoto() {
        return foto;
    }

    public ArrayList<ObjectReview> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<ObjectReview> reviews) {
        this.reviews = reviews;
    }
}
