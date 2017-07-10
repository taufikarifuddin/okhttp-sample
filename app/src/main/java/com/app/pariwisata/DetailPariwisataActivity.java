package com.app.pariwisata;

import android.*;
import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.media.Rating;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.pariwisata.custom_adapter.CustomListKomentarAdapter;
import com.app.pariwisata.model.ObjectFoto;
import com.app.pariwisata.model.ObjectReview;
import com.app.pariwisata.model.ObjectWisata;
import com.app.pariwisata.service.ObjectWisataService;
import com.app.pariwisata.service.RequestHandler;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.vision.text.Text;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Taufik on 05/03/17.
 */

public class DetailPariwisataActivity extends FragmentActivity {

    ObjectWisata wisata;
    KomentarFragment komentarFragment;
    FormKomentarFragment formKomentarFragment;
    Button btn;
    int id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        komentarFragment = new KomentarFragment();
        formKomentarFragment = new FormKomentarFragment();

        setContentView(R.layout.activity_detail_partiwisata);

        final TextView desc = (TextView) findViewById(R.id.desc);

        final TextView judul = (TextView) findViewById(R.id.judulPariwisata);

        final TextView biayaRetribusi = (TextView) findViewById(R.id.biayaRetribusi);
        final TextView jamOperasional = (TextView) findViewById(R.id.jamOperasional);

        final LinearLayout imageCountainer = (LinearLayout) findViewById(R.id.imageCountainer);

        id = getIntent().getIntExtra(ObjectWisata.ID_INDEX,0);

        new AsyncTask<Integer, Void, ObjectWisata>() {
            @Override
            protected ObjectWisata doInBackground(Integer... params) {
                return ObjectWisataService.getInstance().get(params[0]);
            }

            @Override
            protected void onPostExecute(ObjectWisata objectWisata) {
                wisata = objectWisata;
                desc.setText(objectWisata.getDesc());
                judul.setText(objectWisata.getName());
                biayaRetribusi.setText("Biaya Retribusi : \n"+objectWisata.getBiaya());
                jamOperasional.setText("Jam Operasional : \n"+objectWisata.getJamOperasional());
                for (ObjectFoto foto: objectWisata.getFoto()) {
                    imageCountainer.addView(addImage(foto.getFoto()));
                }
            }
        }.execute(id);

        final Button seeKomentar = (Button) findViewById(R.id.btnListKomentar);
        seeKomentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(DetailPariwisataActivity.this);
                dialog.setContentView(R.layout.fragment_list_review);

                ListView listview = (ListView) dialog.findViewById(R.id.listKomentar);
                listview.setAdapter(new CustomListKomentarAdapter(getApplicationContext(),
                        wisata.getReviews()));

                dialog.show();
            }
        });

        btn = (Button) findViewById(R.id.btnKomentar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(DetailPariwisataActivity.this);
                dialog.setContentView(R.layout.fragment_form_komentar);
                dialog.setTitle("Komentar");

                Button submitBtn = (Button) dialog.findViewById(R.id.submit);
                final EditText komentar = (EditText) dialog.findViewById(R.id.komentar);
                final EditText name = (EditText) dialog.findViewById(R.id.name_komentator);
                final EditText email = (EditText) dialog.findViewById(R.id.email_komentator);
                final RatingBar ratingBar = (RatingBar) dialog.findViewById(R.id.ratingBar);

                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AsyncTask<String, Void, String>() {
                            @Override
                            protected String doInBackground(String... params) {
                                JSONObject job = new JSONObject();
                                try {
                                    job.put("description",params[0]);
                                    job.put("name_user",params[1]);
                                    job.put("email_user",params[2]);
                                    job.put("id_wisata",params[3]);
                                    job.put("rating",params[4]);
                                    job.put("phone_id","123");
//                                    job.put("phone_id",((TelephonyManager)getApplication().getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId());
                                    String response = RequestHandler.getInstance().post(
                                            RequestHandler.API_URL_PREFIX+"/review-wisata",job.toString());
                                    Log.d("response_url",RequestHandler.API_URL_PREFIX+"/review-wisata");
                                    Log.d("response_data",job.toString());
                                    Log.d("response",response+"gan");
                                    return response;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                JSONObject job = null;
                                try {
                                    job = new JSONObject(s);

                                    if(job.getBoolean("status")){
                                        Toast.makeText(getApplicationContext(),job.getString("msg"),Toast.LENGTH_LONG).show();
                                        komentar.setText("");
                                        email.setText("");
                                        name.setText("");
                                        ratingBar.setRating(0);
                                        dialog.dismiss();
                                    }else{
                                        Toast.makeText(getApplicationContext(),"Post Komentar Gagal, Coba lagi",Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.execute(komentar.getText().toString(),name.getText().toString(),email.getText().toString(),
                                String.valueOf(id),String.valueOf(ratingBar.getRating()));
                    }
                });

                Button cancelBtn = (Button) dialog.findViewById(R.id.cancelDialog);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
    }

    public View addImage(String name){

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(20,0,0,0);

        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setLayoutParams(params);
        Picasso.with(this)
                .load(RequestHandler.UPLOAD_URL_PREFIX+"/"+name)
                .resize(200,200)
                .into(imageView);

        return imageView;
    }

}
