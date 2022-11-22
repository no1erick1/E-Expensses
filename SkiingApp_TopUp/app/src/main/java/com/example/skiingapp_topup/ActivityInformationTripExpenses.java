package com.example.skiingapp_topup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityInformationTripExpenses extends AppCompatActivity {

    TextView txtTripAmount, txtTripDateExpenses, txtTimeExpenses, txtCommentExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_trip_expenses);

        txtTripAmount = findViewById(R.id.txtTripAmount);
        txtTripDateExpenses = findViewById(R.id.txtTripDateExpenses);
        txtTimeExpenses = findViewById(R.id.txtTripTimeExpenses);
        txtCommentExpenses = findViewById(R.id.txtTripCommentExpenses);

        Intent intent = getIntent();
        String amount = intent.getStringExtra("amount");
        String date_expenses = intent.getStringExtra("date_expenses");
        String time_expenses = intent.getStringExtra("time_expenses");
        String comment_expenses = intent.getStringExtra("comment_expenses");

        txtTripAmount.setText(amount);
        txtTripDateExpenses.setText(date_expenses);
        txtTimeExpenses.setText(time_expenses);
        txtCommentExpenses.setText(comment_expenses);
    }
}