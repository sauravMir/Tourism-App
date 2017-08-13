package com.educareapps.model;

import android.content.Context;
import android.util.Log;

import com.educareapps.tourism.Countries;
import com.educareapps.tourism.MainActivity;
import com.educareapps.tourism.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by RK-REAZ on 8/6/2017.
 */

public class TourismPlaceModel {
    private String packageId;
    private String country;

    public String getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(String hotelStar) {
        this.hotelStar = hotelStar;
    }

    private String hotelStar;
    private String packageName;
    private String hotel;
    private String place;
    private String pricePerPerson;
    private String descriptions;
    private ArrayList<String> imageArr;

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(String pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public ArrayList<String> getImageArr() {
        return imageArr;
    }

    public void setImageArr(ArrayList<String> imageArr) {
        this.imageArr = imageArr;
    }


}
