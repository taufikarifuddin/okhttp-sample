package com.app.pariwisata.custom_adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.app.pariwisata.model.Post;

import java.util.ArrayList;

/**
 * Created by Taufik on 04/16/17.
 */

public class CustomListViewTimelineAdapter extends BaseAdapter {

    ArrayList<Post> post;

    public CustomListViewTimelineAdapter(ArrayList<Post> post) {
        this.post = post;
    }

    @Override
    public int getCount() {
        return this.post.size();
    }

    @Override
    public Object getItem(int position) {
        return this.post.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.post.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
