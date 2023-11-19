package com.bidridego.models;public class Trip {
    private double cost;
    private String destination;
    private String source;
    private double distance;

    public Trip(double cost, String destination, String source, double distance) {
        this.cost = cost;
        this.destination = destination;
        this.source = source;
        this.distance = distance;
    }

    public Trip(){}

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
}
