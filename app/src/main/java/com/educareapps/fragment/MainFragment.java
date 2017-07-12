package com.educareapps.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Switch;

import com.educareapps.tourism.AdapterTourPackage;
import com.educareapps.tourism.MainActivity;
import com.educareapps.tourism.R;
import com.educareapps.tourism.StaticAccess;
import com.educareapps.tourism.Tour;
import com.educareapps.tourism.TourDetailActivity;

import java.util.ArrayList;

import static com.educareapps.tourism.StaticAccess.tourPic;

public class MainFragment extends Fragment {

    MainActivity activity;
    GridView gvCommon;
    AdapterTourPackage adapterTourPackage;
ArrayList<Tour>tripArr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, null);
        activity = (MainActivity) getActivity();


        gvCommon = (GridView) v.findViewById(R.id.gvCommon);
        loadCountryWiseTrip(0);

        return v;
    }

    void loadCountryWiseTrip(int countryId){

        switch (countryId){
            case 0:

                tripArr=new ArrayList<>();

                Tour tour=new Tour();
                tour.setId(1);
                tour.setTitle("");
                tour.setDetail("");
                tour.setDuration("");
                tour.setPic(R.drawable.a);

                tripArr.add(tour);
                tripArr.add(tour);
                tripArr.add(tour);

                tripArr.add(tour);
                tripArr.add(tour);
                tripArr.add(tour);

                loadData(tripArr);

                break;
            case 1:
                tripArr=new ArrayList<>();
                Tour tour1=new Tour();
                tour1.setId(1);
                tour1.setTitle("XYZ");
                tour1.setDetail("Something");
                tour1.setDuration("4");
                tour1.setPic(R.drawable.b);

                tripArr.add(tour1);
                tripArr.add(tour1);
                tripArr.add(tour1);

                tripArr.add(tour1);
                tripArr.add(tour1);
                tripArr.add(tour1);

                loadData(tripArr);
                break;
            case 2:
                tripArr=new ArrayList<>();
                Tour tour2=new Tour();
                tour2.setId(1);
                tour2.setTitle("");
                tour2.setDetail("");
                tour2.setDuration("");
                tour2.setPic(R.drawable.c);

                tripArr.add(tour2);
                tripArr.add(tour2);
                tripArr.add(tour2);

                tripArr.add(tour2);
                tripArr.add(tour2);
                tripArr.add(tour2);

                loadData(tripArr);
                break;
            case 3:

                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:

                break;

        }
    }
    void loadData(final ArrayList<Tour>tripArr){
        if(tripArr!=null);
        adapterTourPackage = new AdapterTourPackage(activity, tripArr);
        gvCommon.setAdapter(adapterTourPackage);
        gvCommon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, TourDetailActivity.class);

                String tourTitle = tripArr.get(position).getTitle();
                String duration = tripArr.get(position).getDuration();
                String detail = tripArr.get(position).getDetail();

                intent.putExtra("tourTitle", tourTitle);
                intent.putExtra("duration", duration);
                intent.putExtra("detail", detail);

                startActivity(intent);
                getActivity().finish();
            }
        });
    }

}
