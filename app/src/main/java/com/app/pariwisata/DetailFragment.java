package com.app.pariwisata;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.pariwisata.custom_adapter.CustomListViewDetailAdapter;
import com.app.pariwisata.model.ObjectWisata;
import com.app.pariwisata.service.AsyncTaskListener;
import com.app.pariwisata.service.ObjectWisataService;

import java.util.ArrayList;

/**
 * Created by Taufik on 04/14/17.
 */

public class DetailFragment extends Fragment{

    ArrayList<ObjectWisata> listData = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        final ListView listView = (ListView) rootView.findViewById(R.id.listOfData);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),DetailPariwisataActivity.class);
                intent.putExtra(ObjectWisata.ID_INDEX,listData.get(position).getId());

                startActivity(intent);
            }
        });

        new AsyncTask<Void, Void, ArrayList<ObjectWisata>>() {
            @Override
            protected ArrayList<ObjectWisata> doInBackground(Void... params) {
                return ObjectWisataService.getInstance().getAll();
            }

            @Override
            protected void onPostExecute(ArrayList<ObjectWisata> objectWisatas) {
                listData = objectWisatas;
                listView.setAdapter(new CustomListViewDetailAdapter(getContext(),objectWisatas));
                Log.d("SizeOfWisata",objectWisatas.size()+" sizenya");
            }
        }.execute();

        return rootView;
    }


}
