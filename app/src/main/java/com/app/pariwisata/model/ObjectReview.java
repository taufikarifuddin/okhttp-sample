package com.app.pariwisata.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Taufik on 04/12/17.
 */

public class ObjectReview{

    public static String REVIEW_KEY = "REVIEW_LIST";

    int id;
    String emailUser,nameUser,desc,phoneId;
    double rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getRating() {
        return rating;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public static ArrayList<ObjectReview> reviewParser(String jsonData){
        ArrayList<ObjectReview> reviews = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(jsonData);
            for (int i = 0; i < arr.length(); i++) {
                ObjectReview review = new ObjectReview();
                JSONObject job = arr.getJSONObject(i);

                review.setDesc( job.getString("description") );
                review.setEmailUser( job.getString("email_user") );
                review.setNameUser( job.getString("name_user") );
                review.setRating( job.getDouble("rating") );
                review.setPhoneId( job.getString("phone_id") );

                reviews.add(review);
            }

            return reviews;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
