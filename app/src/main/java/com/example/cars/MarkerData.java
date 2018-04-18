package com.example.cars;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by nulrybekkarshyga on 18.04.18.
 */

public class MarkerData {
    private Double latitude;
    private Double longitude;
    private String title;

    public MarkerData(Double latitude, Double longitude, String title){
        this.latitude = latitude;
        this.longitude=longitude;
        this.title=title;
    }

    public Double getLatitude(){return latitude;}
    public Double getLongitude(){return longitude;}
    public String getTitle(){return title;}
}
