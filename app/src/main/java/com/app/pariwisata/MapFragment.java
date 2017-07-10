package com.app.pariwisata;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.pariwisata.model.PublicFacility;
import com.app.pariwisata.service.PublicFacilitiesService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Taufik on 04/14/17.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback{

    GoogleMap googleMap;
    private static double LAT_SDA = -7.4471541;
    private static double LNG_SDA = 112.6717159;
    ArrayList<Marker> markers;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps,container,false);
        markers = new ArrayList<>();
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps);
        supportMapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return true;
            }
        });
        this.googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(LAT_SDA,LNG_SDA),10.0f));

        new AsyncTask<Void, Void, ArrayList<PublicFacility>>() {
            @Override
            protected ArrayList<PublicFacility> doInBackground(Void... params) {
                return PublicFacilitiesService.getInstance().getAll();
            }

            @Override
            protected void onPostExecute(ArrayList<PublicFacility> publicFacilities) {
                markers.add(googleMap.addMarker(new MarkerOptions()
                        .title("Pusat Sidoarjo")
                        .position(new LatLng(LAT_SDA,LNG_SDA))));

                for( PublicFacility facility : publicFacilities ){

                    markers.add(googleMap.addMarker(new MarkerOptions()
                    .title( facility.getName() )
                    .icon(BitmapDescriptorFactory.fromBitmap(getIcon(facility.getCategory())))
                    .position(new LatLng(facility.getLat(),facility.getLng()))));
                }
            }
        }.execute();

    }

    public Bitmap getIcon(int idCategory){
        int icon;
        switch(idCategory){
            case 3 : icon = R.drawable.mas;
                break;
            case 4 : icon = R.drawable.pom;
                break;
            case 5 : icon = R.drawable.train;
                break;
            case 6 : icon = R.drawable.hos;
                break;
            default: icon = R.drawable.mas;
        }

        int height = 35;
        int width = 35;
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(icon);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        return smallMarker;
    }
}
