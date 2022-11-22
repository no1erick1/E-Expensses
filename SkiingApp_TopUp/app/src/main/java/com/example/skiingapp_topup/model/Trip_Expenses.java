package com.example.skiingapp_topup.model;

public class Trip_Expenses {
    private int trip_expenses_id;
    private int amount_expenses;
    private String date_expenses;
    private String time_expenses;
    private String comment_expenses;
    private int trip_id;

    public Trip_Expenses(int amount_expenses, String date_expenses, String time_expenses, String comment_expenses, int trip_id) {
        this.amount_expenses = amount_expenses;
        this.date_expenses = date_expenses;
        this.time_expenses = time_expenses;
        this.comment_expenses = comment_expenses;
        this.trip_id = trip_id;
    }

    public Trip_Expenses(int trip_expenses_id, int amount_expenses, String date_expenses, String time_expenses, String comment_expenses, int trip_id) {
        this.trip_expenses_id = trip_expenses_id;
        this.amount_expenses = amount_expenses;
        this.date_expenses = date_expenses;
        this.time_expenses = time_expenses;
        this.comment_expenses = comment_expenses;
        this.trip_id = trip_id;
    }

    public Trip_Expenses(int amount_expenses, String date_expenses, String time_expenses, String comment_expenses) {
        this.amount_expenses = amount_expenses;
        this.date_expenses = date_expenses;
        this.time_expenses = time_expenses;
        this.comment_expenses = comment_expenses;
    }

    public int getTrip_expenses_id() {
        return trip_expenses_id;
    }

    public void setTrip_expenses_id(int trip_expenses_id) {
        this.trip_expenses_id = trip_expenses_id;
    }

    public int getAmount_expenses() {
        return amount_expenses;
    }

    public void setAmount_expenses(int amount_expenses) {
        this.amount_expenses = amount_expenses;
    }

    public String getDate_expenses() {
        return date_expenses;
    }

    public void setDate_expenses(String date_expenses) {
        this.date_expenses = date_expenses;
    }

    public String getTime_expenses() {
        return time_expenses;
    }

    public void setTime_expenses(String time_expenses) {
        this.time_expenses = time_expenses;
    }

    public String getComment_expenses() {
        return comment_expenses;
    }

    public void setComment_expenses(String comment_expenses) {
        this.comment_expenses = comment_expenses;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }
}
