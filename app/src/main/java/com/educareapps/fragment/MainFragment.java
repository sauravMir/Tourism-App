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
import com.educareapps.tourism.Tour;
import com.educareapps.tourism.TourDetailActivity;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    MainActivity activity;
    GridView gvCommon;
    AdapterTourPackage adapterTourPackage;
    ArrayList<Tour> tripArr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, null);
        activity = (MainActivity) getActivity();


        gvCommon = (GridView) v.findViewById(R.id.gvCommon);
        loadCountryWiseTrip(0);

        return v;
    }

    void loadCountryWiseTrip(int countryId) {

        switch (countryId) {
            case 0:
                tripArr = new ArrayList<>();

                Tour tour_00 = new Tour();
                tour_00.setId(1);
                tour_00.setTitle("Room 1");
                tour_00.setDetail("Compatable room for guest");
                tour_00.setDuration(" 1 night");
                tour_00.setPic(R.drawable.k);
                tripArr.add(tour_00);

                Tour tour_01 = new Tour();
                tour_01.setId(2);
                tour_01.setTitle("Room 2");
                tour_01.setDetail("Compatable room for guest");
                tour_01.setDuration(" 2 night");
                tour_01.setPic(R.drawable.n);
                tripArr.add(tour_01);

                Tour tour_02 = new Tour();
                tour_02.setId(3);
                tour_02.setTitle("Room 3");
                tour_02.setDetail("Compatable room for guest");
                tour_02.setDuration(" 3 night");
                tour_02.setPic(R.drawable.w);
                tripArr.add(tour_02);

                Tour tour_03 = new Tour();
                tour_03.setId(4);
                tour_03.setTitle("Room 4");
                tour_03.setDetail("Compatable room for guest");
                tour_03.setDuration(" 4 night");
                tour_03.setPic(R.drawable.y);
                tripArr.add(tour_03);


                Tour tour_05 = new Tour();
                tour_05.setId(5);
                tour_05.setTitle("Room 5");
                tour_05.setDetail("Compatable room for guest");
                tour_05.setDuration(" 5 night");
                tour_05.setPic(R.drawable.x);
                tripArr.add(tour_05);

                Tour tour_06 = new Tour();
                tour_06.setId(6);
                tour_06.setTitle("Room 6");
                tour_06.setDetail("Compatable room for guest");
                tour_06.setDuration(" 6 night");
                tour_06.setPic(R.drawable.z);
                tripArr.add(tour_06);


                loadData(tripArr);

                break;
            case 1:
                tripArr = new ArrayList<>();

                Tour tour_10 = new Tour();
                tour_10.setId(1);
                tour_10.setTitle("Room 1");
                tour_10.setDetail("Compatable room for guest");
                tour_10.setDuration(" 1 night");
                tour_10.setPic(R.drawable.e);
                tripArr.add(tour_10);

                Tour tour_11 = new Tour();
                tour_11.setId(2);
                tour_11.setTitle("Room 2");
                tour_11.setDetail("Compatable room for guest");
                tour_11.setDuration(" 2 night");
                tour_11.setPic(R.drawable.f);
                tripArr.add(tour_11);

                Tour tour_12 = new Tour();
                tour_12.setId(3);
                tour_12.setTitle("Room 3");
                tour_12.setDetail("Compatable room for guest");
                tour_12.setDuration(" 3 night");
                tour_12.setPic(R.drawable.g);
                tripArr.add(tour_12);

                Tour tour_13 = new Tour();
                tour_13.setId(4);
                tour_13.setTitle("Room 4");
                tour_13.setDetail("Compatable room for guest");
                tour_13.setDuration(" 4 night");
                tour_13.setPic(R.drawable.k);
                tripArr.add(tour_13);


                Tour tour_15 = new Tour();
                tour_15.setId(5);
                tour_15.setTitle("Room 5");
                tour_15.setDetail("Compatable room for guest");
                tour_15.setDuration(" 5 night");
                tour_15.setPic(R.drawable.l);
                tripArr.add(tour_15);

                Tour tour_16 = new Tour();
                tour_16.setId(6);
                tour_16.setTitle("Room 6");
                tour_16.setDetail("Compatable room for guest");
                tour_16.setDuration(" 6 night");
                tour_16.setPic(R.drawable.m);
                tripArr.add(tour_16);

                loadData(tripArr);
                break;
            case 2:
                tripArr = new ArrayList<>();
                Tour tour2 = new Tour();
                tour2.setId(1);
                tour2.setTitle("Room 2");
                tour2.setDetail("Compatable room for guest");
                tour2.setDuration(" 1 night");
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

                tripArr = new ArrayList<>();
                Tour tour3 = new Tour();
                tour3.setId(1);
                tour3.setTitle("Room 2");
                tour3.setDetail("Compatable room for guest");
                tour3.setDuration(" 1 night");
                tour3.setPic(R.drawable.d);

                tripArr.add(tour3);
                tripArr.add(tour3);
                tripArr.add(tour3);

                tripArr.add(tour3);
                tripArr.add(tour3);
                tripArr.add(tour3);

                loadData(tripArr);

                break;
            case 4:

                tripArr = new ArrayList<>();
                Tour tour4 = new Tour();
                tour4.setId(1);
                tour4.setTitle("Room 2");
                tour4.setDetail("Compatable room for guest");
                tour4.setDuration(" 1 night");
                tour4.setPic(R.drawable.e);

                tripArr.add(tour4);
                tripArr.add(tour4);
                tripArr.add(tour4);

                tripArr.add(tour4);
                tripArr.add(tour4);
                tripArr.add(tour4);

                loadData(tripArr);

                break;
            case 5:
                tripArr = new ArrayList<>();
                Tour tour5 = new Tour();
                tour5.setId(1);
                tour5.setTitle("Room 2");
                tour5.setDetail("Compatable room for guest");
                tour5.setDuration(" 1 night");
                tour5.setPic(R.drawable.f);

                tripArr.add(tour5);
                tripArr.add(tour5);
                tripArr.add(tour5);

                tripArr.add(tour5);
                tripArr.add(tour5);
                tripArr.add(tour5);

                loadData(tripArr);
                break;
            case 6:

                tripArr = new ArrayList<>();
                Tour tour6 = new Tour();
                tour6.setId(1);
                tour6.setTitle("Room 2");
                tour6.setDetail("Compatable room for guest");
                tour6.setDuration(" 1 night");
                tour6.setPic(R.drawable.g);

                tripArr.add(tour6);
                tripArr.add(tour6);
                tripArr.add(tour6);

                tripArr.add(tour6);
                tripArr.add(tour6);
                tripArr.add(tour6);

                loadData(tripArr);
                break;
            case 7:
                tripArr = new ArrayList<>();
                Tour tour7 = new Tour();
                tour7.setId(1);
                tour7.setTitle("Room 2");
                tour7.setDetail("Compatable room for guest");
                tour7.setDuration(" 1 night");
                tour7.setPic(R.drawable.j);

                tripArr.add(tour7);
                tripArr.add(tour7);
                tripArr.add(tour7);

                tripArr.add(tour7);
                tripArr.add(tour7);
                tripArr.add(tour7);

                loadData(tripArr);
                break;
            case 8:
                tripArr = new ArrayList<>();
                Tour tour8 = new Tour();
                tour8.setId(1);
                tour8.setTitle("Room 2");
                tour8.setDetail("Compatable room for guest");
                tour8.setDuration(" 1 night");
                tour8.setPic(R.drawable.k);

                tripArr.add(tour8);
                tripArr.add(tour8);
                tripArr.add(tour8);

                tripArr.add(tour8);
                tripArr.add(tour8);
                tripArr.add(tour8);

                loadData(tripArr);
                break;
            case 9:
                tripArr = new ArrayList<>();
                Tour tour9 = new Tour();
                tour9.setId(1);
                tour9.setTitle("Room 2");
                tour9.setDetail("Compatable room for guest");
                tour9.setDuration(" 1 night");
                tour9.setPic(R.drawable.l);

                tripArr.add(tour9);
                tripArr.add(tour9);
                tripArr.add(tour9);

                tripArr.add(tour9);
                tripArr.add(tour9);
                tripArr.add(tour9);

                loadData(tripArr);
                break;
            case 10:
                tripArr = new ArrayList<>();
                Tour tour10 = new Tour();
                tour10.setId(1);
                tour10.setTitle("Room 2");
                tour10.setDetail("Compatable room for guest");
                tour10.setDuration(" 1 night");
                tour10.setPic(R.drawable.m);

                tripArr.add(tour10);
                tripArr.add(tour10);
                tripArr.add(tour10);

                tripArr.add(tour10);
                tripArr.add(tour10);
                tripArr.add(tour10);

                loadData(tripArr);
                break;
            case 11:
                tripArr = new ArrayList<>();
                Tour tour11 = new Tour();
                tour11.setId(1);
                tour11.setTitle("Room 2");
                tour11.setDetail("Compatable room for guest");
                tour11.setDuration(" 1 night");
                tour11.setPic(R.drawable.n);

                tripArr.add(tour11);
                tripArr.add(tour11);
                tripArr.add(tour11);

                tripArr.add(tour11);
                tripArr.add(tour11);
                tripArr.add(tour11);

                loadData(tripArr);
                break;

        }
    }

    void loadData(final ArrayList<Tour> tripArr) {
        if (tripArr != null) ;
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
