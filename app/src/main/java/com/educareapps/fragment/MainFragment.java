package com.educareapps.fragment;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.educareapps.adapter.AdapterTourPackage;
import com.educareapps.model.TourismPlaceModel;
import com.educareapps.parser.TourismPlaceJson;
import com.educareapps.tourism.CountryCategory;
import com.educareapps.tourism.MainActivity;
import com.educareapps.tourism.R;
import com.educareapps.tourism.Tour;
import com.educareapps.tourism.TourDetailActivity;
import com.educareapps.utilities.AppController;
import com.educareapps.utilities.RootUrl;
import com.educareapps.utilities.StaticInstance;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    MainActivity activity;
    GridView gvCommon;
    AdapterTourPackage adapterTourPackage;
    ArrayList<Tour> tripArr;
    ArrayList<TourismPlaceModel> tripArrTemp;
    ArrayList<CountryCategory> rdCategoryLst;
    ProgressDialog progressDialog;
    SwipeRefreshLayout swipeLayout;
    StaticInstance staticInstance;
String countryId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, null);
        activity = (MainActivity) getActivity();


        gvCommon = (GridView) v.findViewById(R.id.gvCommon);
        rdCategoryLst = new ArrayList<>();

        staticInstance = StaticInstance.getInstance();
        progressDialog = new ProgressDialog(activity);
//        loadCountryWiseTrip("");
        swipeLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipe_container);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //my update process
                loadCountryWiseTrip(countryId);
            }
        });
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        return v;
    }

    public void loadCountryWiseTrip(String countryId) {
        this.countryId=countryId;
        // Make Request
        //http://192.52.243.6/TourismApp/Packages/getPackageByID?id=1
        showProgress();
        makeRequest(RootUrl.RootUrl + "getPackageByID?id=" + countryId);


    }

    /// making json request
    private void makeRequest(String url) {
        JsonArrayRequest jreq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("Json", response.toString());
                        parserJson(response);
                        hideProgress();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Json", error.toString());
                hideProgress();

            }
        });
        AppController.getInstance().addToRequestQueue(jreq);

    }

    private void parserJson(JSONArray response) {
        TourismPlaceJson parserMode = new TourismPlaceJson(activity, response);
        parserMode.parser();
        tripArrTemp = new ArrayList<>();
        tripArrTemp = parserMode.tourismArr;
        if (tripArrTemp.size() > 0)
            loadData(tripArrTemp);

    }

    public void setCategoryLst(ArrayList<CountryCategory> rdCategoryLst) {
        // Make Request
        this.rdCategoryLst = rdCategoryLst;


    }

    public void loadData(final ArrayList<TourismPlaceModel> tripArr) {
//        swipeLayout.removeAllViews();
        swipeLayout.setRefreshing(false);
        if (tripArr != null) ;
        adapterTourPackage = new AdapterTourPackage(activity, tripArr);
        gvCommon.setAdapter(adapterTourPackage);
        gvCommon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                staticInstance.clearAll();
                staticInstance.setTourismPlaceModel(tripArr.get(position));
                Intent intent = new Intent(activity, TourDetailActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void showProgress() {
        if (progressDialog != null) {
            progressDialog.setMessage("please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

    }

    private void hideProgress() {

        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }
}
