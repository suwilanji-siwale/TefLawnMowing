package com.melborp.teflawn.activity.models;

public class Booking {

    String imageUri, name, serviceName, number, location, date, time;

    public Booking() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Booking(String imageUri, String name, String serviceName, String number, String location, String date, String time) {
        this.imageUri = imageUri;
        this.name = name;
        this.serviceName = serviceName;
        this.number = number;
        this.location = location;
        this.date = date;
        this.time = time;

    }

    public String getImageUri() {
        return imageUri;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
