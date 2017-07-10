package com.app.pariwisata.custom_adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.app.pariwisata.R;
import com.app.pariwisata.model.ObjectReview;

import java.util.ArrayList;

/**
 * Created by Taufik on 05/03/17.
 */

public class CustomListKomentarAdapter extends BaseAdapter{

    ArrayList<ObjectReview> reviews;
    LayoutInflater inflater;

    public CustomListKomentarAdapter(Context ctx,ArrayList<ObjectReview> reviews){
        this.reviews = reviews;
        inflater = (LayoutInflater) ctx.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }

    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public Object getItem(int position) {
        return reviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if( convertView == null ){
            convertView = inflater.inflate(R.layout.fragment_detail_review,null);
        }

        ObjectReview review = this.reviews.get(position);
        TextView name = (TextView) convertView.findViewById(R.id.name_komen);
        TextView email = (TextView) convertView.findViewById(R.id.email_komen);
        TextView desc = (TextView) convertView.findViewById(R.id.desc_komen);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.reviewRatingBar) ;

        name.setText(review.getNameUser());
        email.setText(review.getEmailUser());
        desc.setText(review.getDesc());
        ratingBar.setRating((float)review.getRating());

        Log.d("name",review.getNameUser()+" gan");

        return convertView;
    }
}
