package com.educareapps.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.educareapps.tourism.AdapterTourPackage;
import com.educareapps.tourism.MainActivity;
import com.educareapps.tourism.R;
import com.educareapps.tourism.StaticAccess;
import com.educareapps.tourism.TourDetailActivity;

import static com.educareapps.tourism.StaticAccess.tourPic;

public class MainFragment extends Fragment {

    MainActivity activity;
    GridView gvCommon;
    AdapterTourPackage adapterTourPackage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, null);
        activity = (MainActivity) getActivity();


        gvCommon = (GridView) v.findViewById(R.id.gvCommon);
        adapterTourPackage = new AdapterTourPackage(activity, tourPic, StaticAccess.tourTitle, StaticAccess.duration, StaticAccess.detail);
        gvCommon.setAdapter(adapterTourPackage);
        gvCommon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, TourDetailActivity.class);

                String tourTitle = StaticAccess.tourTitle[position];
                String duration = StaticAccess.duration[position];
                String detail = StaticAccess.detail[position];

                intent.putExtra("tourTitle", tourTitle);
                intent.putExtra("duration", duration);
                intent.putExtra("detail", detail);

                startActivity(intent);
                getActivity().finish();
            }
        });

        return v;
    }

}
