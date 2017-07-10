package com.app.pariwisata.service;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Taufik on 04/12/17.
 */

public class RequestHandler {

    private static RequestHandler handler = new RequestHandler();
//    public static String API_URL_PREFIX = "http://192.168.43.36:88/work/project/pariwisata/web/api";
    public static String BASE_URL_PREFIX = "http://192.168.43.36/pariwisata";
    public static String API_URL_PREFIX = BASE_URL_PREFIX+"/web/api";
    public static String UPLOAD_URL_PREFIX = BASE_URL_PREFIX+"/uploads";


    private OkHttpClient client;
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private RequestHandler(){
        client = new OkHttpClient();
    };

    public static RequestHandler getInstance(){
        return handler;
    }

    public String get(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        String response = getResponse(request);
        Log.d("response","result : "+response);
        try {
            JSONObject job = new JSONObject(response);
            if( job.getBoolean("status") ){
                return  job.getString("data");
            }
        }catch ( JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public String post(String url,String jsonofData){
        RequestBody requestBody = RequestBody.create(JSON,jsonofData);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        String response = getResponse(request);
        return response;
    }


    private String getResponse(Request request){
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

}
