package com.app.pariwisata.custom_adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.pariwisata.R;
import com.app.pariwisata.model.ObjectWisata;
import com.app.pariwisata.service.RequestHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Taufik on 04/16/17.
 */

public class CustomListViewDetailAdapter extends BaseAdapter{

    ArrayList<ObjectWisata> listOfData;
    Context ctx;
    LayoutInflater inflater;

    public CustomListViewDetailAdapter(Context ctx,ArrayList<ObjectWisata> listOfData){
        this.listOfData = listOfData;
        this.ctx = ctx;
        inflater = (LayoutInflater) ctx.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }

    @Override
    public int getCount() {
        return listOfData.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if( convertView == null ){
            convertView = inflater.inflate(R.layout.fragment_list_detail_adapter,null);
        }

        ObjectWisata objectWisata = this.listOfData.get(position);

        TextView textview = (TextView) convertView.findViewById(R.id.titlePariwisata);
        textview.setText(objectWisata.getName());

        ImageView view = (ImageView) convertView.findViewById(R.id.photo);

        if( objectWisata.getFoto().size() > 0 ) {
            Picasso.with(this.ctx)
                    .load(RequestHandler.UPLOAD_URL_PREFIX + "/" +objectWisata.getFoto().get(0).getFoto())
                    .resize(100,100)
                    .into(view);
        }

        return convertView;
    }
}
