package com.educareapps.utilities;


import com.educareapps.model.TourismPlaceModel;

/**
 * Created by RAFI on 10/3/2016.
 */

public class StaticInstance {
    private static StaticInstance instance = null;

    // Getter-Setters
    public static StaticInstance getInstance() {
        if (instance == null) {
            instance = new StaticInstance();
            return instance;
        } else
            return instance;
    }

    public static void setInstance(StaticInstance instance) {
        StaticInstance.instance = instance;
    }


    public TourismPlaceModel getTourismPlaceModel() {
        return tourismPlaceModel;
    }

    public void setTourismPlaceModel(TourismPlaceModel tourismPlaceModel) {
        this.tourismPlaceModel = tourismPlaceModel;
    }

    private TourismPlaceModel tourismPlaceModel;




    // this collection for only task basis and update if getting any error;


    private StaticInstance() {
    }






    public void clearTourism() {
        tourismPlaceModel = null;
    }


    public void clearAll() {
     tourismPlaceModel=null;
    }


}
