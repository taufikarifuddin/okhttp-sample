package com.app.pariwisata;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.pariwisata.R;
import com.app.pariwisata.custom_adapter.CustomListKomentarAdapter;
import com.app.pariwisata.model.ObjectReview;

import java.util.ArrayList;

/**
 * Created by Taufik on 05/03/17.
 */

public class KomentarFragment extends Fragment {

    ArrayList<ObjectReview> reviews;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
//        reviews = (ArrayList<ObjectReview>) bundle.getSerializable(ObjectReview.REVIEW_KEY);

        View view = inflater.inflate(R.layout.fragment_layout_komentar,container,false);

        listView = (ListView) view.findViewById(R.id.listKomentar);
        listView.setAdapter(new CustomListKomentarAdapter(getContext(),reviews));

        return view;
    }

}
