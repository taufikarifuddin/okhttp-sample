package com.app.pariwisata.service;

import android.util.Log;

import com.app.pariwisata.model.PublicFacility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Taufik on 04/12/17.
 */
public class PublicFacilitiesService {

    private static PublicFacilitiesService ourInstance = new PublicFacilitiesService();

    public static PublicFacilitiesService getInstance() {
        return ourInstance;
    }

    private PublicFacilitiesService() {
    }

    public ArrayList<PublicFacility> getAll(){
        try {
            String request = RequestHandler.getInstance().get(RequestHandler.API_URL_PREFIX+"/public-facilies");
            Log.d("response",request+" gan");
            ArrayList<PublicFacility> publicFacilities = new ArrayList<>();

            JSONArray jsonArray = new JSONArray(request);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject job = jsonArray.getJSONObject(i);

                PublicFacility publicFacility = new PublicFacility();
                publicFacility.setId( job.getInt("id") );
                publicFacility.setLat( job.getDouble("lat") );
                publicFacility.setLng( job.getDouble("lng") );
                publicFacility.setName( job.getString("name") );
                publicFacility.setCategory( job.getInt("public_facility_category") );

                publicFacilities.add(publicFacility);
            }

            return publicFacilities;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
