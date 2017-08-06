package com.educareapps.tourism;

import java.util.ArrayList;

public class CountryCategory {


    String id;
    private String name;
    private ArrayList<Countries> list = new ArrayList<Countries>();
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Countries> getRadioStationList() {
        return list;
    }

    public void setRadioStationList(ArrayList<Countries> radioList) {
        this.list = radioList;
    }

}