package com.bidridego.models;

public class BidRideLocation {
    public BidRideLocation(double lat, double lng, String locationName) {
        this.lat = lat;
        this.lng = lng;
        this.locationName = locationName;
    }

    public BidRideLocation(){}

    private double lat;
    private double lng;
    private  String locationName;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
