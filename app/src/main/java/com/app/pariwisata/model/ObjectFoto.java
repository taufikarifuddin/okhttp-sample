package com.app.pariwisata.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Taufik on 04/12/17.
 */

public class ObjectFoto {

    String foto;
    int id;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ArrayList<ObjectFoto> fotoJsonParser(String data){
        ArrayList<ObjectFoto> fotos = new ArrayList<>();

        try {
            JSONArray jar = new JSONArray(data);
            for (int i = 0; i < jar.length(); i++) {
                JSONObject job = jar.getJSONObject(i);
                ObjectFoto of = new ObjectFoto();
                of.setId( job.getInt("id") );
                of.setFoto( job.getString("foto") );
                fotos.add(of);
            }

            return fotos;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
