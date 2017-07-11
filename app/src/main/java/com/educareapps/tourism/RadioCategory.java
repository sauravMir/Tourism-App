package com.educareapps.tourism;

import java.util.ArrayList;

public class RadioCategory {

    private String name;
    private ArrayList<RadioStation> list = new ArrayList<RadioStation>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<RadioStation> getRadioStationList() {
        return list;
    }

    public void setRadioStationList(ArrayList<RadioStation> radioList) {
        this.list = radioList;
    }

}