package com.bidridego.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Trip implements Serializable {
    public Trip(String id, double cost, BidRideLocation to, BidRideLocation from, double distance, String postedBy, int passengers, String dateAndTime, boolean isCarPool, String rideType) {
        this.id = id;
        this.cost = cost;
        this.to = to;
        this.from = from;
        this.distance = distance;
        this.postedBy = postedBy;
        this.passengers = passengers;
        this.dateAndTime = dateAndTime;
        this.isCarPool = isCarPool;
        this.rideType = rideType;
    }

    public Trip(){}

    private String id;
    private double cost;
    private BidRideLocation from;
    private BidRideLocation to;
    private double distance;
    private String postedBy;
    private int passengers;
    private String dateAndTime;
    private boolean isCarPool = false;
    private String rideType;
    private double minBid;
//    private ArrayList<Bid> bids = new ArrayList<>();
    private HashMap<String, Double> bids = new HashMap<>();

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public BidRideLocation getFrom() {
        return this.from;
    }

    public void setFrom(BidRideLocation from) {
        this.from = from;
    }

    public BidRideLocation getTo() {
        return this.to;
    }

    public void setTo(BidRideLocation to) {
        this.to = to;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getPostedBy() {
        return this.postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public int getPassengers() {
        return this.passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public boolean isCarPool() {
        return this.isCarPool;
    }

    public void setCarPool(boolean carPool) {
        this.isCarPool = carPool;
    }

    public String getRideType() {
        return this.rideType;
    }

    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    public double getMinBid() {
        return minBid;
    }

    public void setMinBid(double minBid) {
        this.minBid = minBid;
    }

    public HashMap<String, Double> getBids() {
        return bids;
    }

    public void setBids(HashMap<String, Double> bids) {
        this.bids = bids;
    }
}
