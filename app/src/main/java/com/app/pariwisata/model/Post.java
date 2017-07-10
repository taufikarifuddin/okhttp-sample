package com.app.pariwisata.model;

import java.util.ArrayList;

/**
 * Created by Taufik on 04/16/17.
 */

public class Post {
    private int id;
    private String nameUser,emailUser,desc;
    private ArrayList<PostResource> resources;

    public ArrayList<PostResource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<PostResource> resources) {
        this.resources = resources;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
