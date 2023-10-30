package com.bidridego.models;public class Trip {
    public Trip(double cost, String destination, String source, double distance, User postedBy) {
        this.cost = cost;
        this.destination = destination;
        this.source = source;
        this.distance = distance;
        this.postedBy = postedBy;
    }

    public Trip(){}

    private String id;
    private double cost;
    private String destination;
    private String source;
    private double distance;
    private User postedBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }
}
