package com.app.pariwisata.service;

import android.os.Debug;
import android.util.Log;

import com.app.pariwisata.model.ObjectFoto;
import com.app.pariwisata.model.ObjectReview;
import com.app.pariwisata.model.ObjectWisata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Taufik on 04/12/17.
 */
public class ObjectWisataService {
    private static ObjectWisataService ourInstance = new ObjectWisataService();
    ArrayList<ObjectWisata> objectWisatas;
    public static ObjectWisataService getInstance() {
        return ourInstance;
    }

    private ObjectWisataService() {
    }

    public ObjectWisata get(int id){
        String request = RequestHandler.getInstance().get( RequestHandler.API_URL_PREFIX+"/object-wisata?id="+id );
        JSONObject job = null;
        Log.d("string response",request);
        try {
            job = new JSONObject(request);

            ObjectWisata ow = new ObjectWisata();
            ow.setId( job.getInt("id") );
            ow.setName( job.getString("name") );
            ow.setDesc( job.getString("description") );
            ow.setBiaya( job.getString("biaya_retribusi") );
            ow.setJamOperasional( job.getString("jam_operasional") );
            ow.setFoto(ObjectFoto.fotoJsonParser( job.getString("fotos") ));
            ow.setReviews(ObjectReview.reviewParser( job.getString("reviews") ));

            return ow;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<ObjectWisata> getAll(){

        if( objectWisatas != null ){
            return objectWisatas;
        }

        String request = RequestHandler.getInstance().get( RequestHandler.API_URL_PREFIX+"/object-wisata" );
        objectWisatas = new ArrayList<>();
        JSONArray arr = null;
        try {
            arr = new JSONArray(request);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject job = arr.getJSONObject(i);

                ObjectWisata ow = new ObjectWisata();
                ow.setId( job.getInt("id") );
                ow.setName( job.getString("name") );
                ow.setDesc( job.getString("description") );
                ow.setBiaya( job.getString("biaya_retribusi") );
                ow.setJamOperasional( job.getString("jam_operasional") );
                ow.setFoto(ObjectFoto.fotoJsonParser( job.getString("fotos") ));
                ow.setReviews(ObjectReview.reviewParser( job.getString("reviews") ));

                objectWisatas.add(ow);
            }

            return objectWisatas;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
