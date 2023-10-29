package com.bidridego.models;
public class User {
    public User(String firstName, String lastName, String contact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
//        this.address = address;
    }
    public User() {}
    private String firstName;
    private String lastName;
    private String contact;
//    private String address = "";
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
