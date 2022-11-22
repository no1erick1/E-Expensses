package com.example.skiingapp_topup.model;

import java.util.Date;

public class Trip {
    private int trip_id;
    private String trip_name;
    private String destination;
    private String description;
    private String date_trip;
    private int type_trip;
    private int number_member;
    private int number_date;
    private int require_risk_assessment;

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_trip() {
        return date_trip;
    }

    public void setDate_trip(String date_trip) {
        this.date_trip = date_trip;
    }

    public int getType_trip() {
        return type_trip;
    }

    public void setType_trip(int type_trip) {
        this.type_trip = type_trip;
    }

    public int getNumber_member() {
        return number_member;
    }

    public void setNumber_member(int number_member) {
        this.number_member = number_member;
    }

    public int getNumber_date() {
        return number_date;
    }

    public void setNumber_date(int number_date) {
        this.number_date = number_date;
    }

    public int getRequire_risk_assessment() {
        return require_risk_assessment;
    }

    public void setRequire_risk_assessment(int require_risk_assessment) {
        this.require_risk_assessment = require_risk_assessment;
    }

    public Trip(int trip_id, String trip_name, String destination, String description, String date_trip, int type_trip, int number_member, int number_date, int require_risk_assessment) {
        this.trip_id = trip_id;
        this.trip_name = trip_name;
        this.destination = destination;
        this.description = description;
        this.date_trip = date_trip;
        this.type_trip = type_trip;
        this.number_member = number_member;
        this.number_date = number_date;
        this.require_risk_assessment = require_risk_assessment;
    }

    public Trip(String trip_name, String destination, String description, String date_trip, int type_trip, int number_member, int number_date, int require_risk_assessment) {
        this.trip_name = trip_name;
        this.destination = destination;
        this.description = description;
        this.date_trip = date_trip;
        this.type_trip = type_trip;
        this.number_member = number_member;
        this.number_date = number_date;
        this.require_risk_assessment = require_risk_assessment;
    }


}
