package com.educareapps.fragment;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.educareapps.adapter.AdapterTourPackage;
import com.educareapps.parser.ParserMode;
import com.educareapps.tourism.CountryCategory;
import com.educareapps.tourism.MainActivity;
import com.educareapps.tourism.R;
import com.educareapps.tourism.Tour;
import com.educareapps.tourism.TourDetailActivity;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    MainActivity activity;
    GridView gvCommon;
    AdapterTourPackage adapterTourPackage;
    ArrayList<Tour> tripArr;
    ArrayList<CountryCategory> rdCategoryLst;
    ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, null);
        activity = (MainActivity) getActivity();


        gvCommon = (GridView) v.findViewById(R.id.gvCommon);
        rdCategoryLst=new ArrayList<>();


        progressDialog = new ProgressDialog(activity);
        loadCountryWiseTrip("");

        return v;
    }

    public void loadCountryWiseTrip(String countryId) {
           // Make Request



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
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(jreq);

    }

    private void parserJson(JSONArray response) {
        ParserMode parserMode = new ParserMode(activity, response);
        parserMode.parser();
    }

    public void setCategoryLst(ArrayList<CountryCategory> rdCategoryLst) {
           // Make Request
        this.rdCategoryLst=rdCategoryLst;


    }

    public  void loadData(final ArrayList<Tour> tripArr) {
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
    private void showProgress() {
        if (progressDialog != null){
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
