package com.bidridego.models;

public class BidDetails extends Bid {
    User driver;

    public BidDetails(String driverID, double bidValue, User driver) {
        super(driverID, bidValue);
        this.driver = driver;
    }

    public BidDetails(User driver) {
        this.driver = driver;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }
}
