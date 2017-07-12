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

public class MainFragment extends Fragment {

    MainActivity activity;
    GridView gvCommon;
    AdapterTourPackage adapterTourPackage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, null);
        activity = (MainActivity) getActivity();

        gvCommon = (GridView) v.findViewById(R.id.gvCommon);
        adapterTourPackage = new AdapterTourPackage(activity, StaticAccess.tourPic, StaticAccess.tourTitle, StaticAccess.duration, StaticAccess.detail);
        gvCommon.setAdapter(adapterTourPackage);
        gvCommon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, TourDetailActivity.class);

                /*int tourPic = StaticAccess.tourPic[position];
                String tourTitle = StaticAccess.tourTitle[position];
                String duration = StaticAccess.duration[position];
                String detail = StaticAccess.detail[position];

                intent.putExtra("tourPic", tourPic);
                intent.putExtra("tourPic", tourTitle);
                intent.putExtra("tourPic", duration);
                intent.putExtra("tourPic", detail);*/

                startActivity(intent);
            }
        });

        // information_tv = (TextView) v.findViewById(R.id.informationText);

        return v;

    }

}
