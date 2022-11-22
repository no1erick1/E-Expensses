package com.example.skiingapp_topup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityTripInformation extends AppCompatActivity {

    TextView textTripName, textTripDestination, textTripDescription, textTripDate,textTripType, textTripNumberMember,textTripNumberDate, textTripRequireRiskAssesment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_information);

        textTripName = findViewById(R.id.txtTripName);
        textTripDestination = findViewById(R.id.txtTripDestination);
        textTripDescription = findViewById(R.id.txtTripDescription);
        textTripDate = findViewById(R.id.txtTripDate);
        textTripType = findViewById(R.id.txtTripType);
        textTripNumberMember = findViewById(R.id.txtTripNumberMember);
        textTripNumberDate = findViewById(R.id.txtTripNumberDate);
        textTripRequireRiskAssesment = findViewById(R.id.txtTripRequireRiskAssesment);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String destination = intent.getStringExtra("destination");
        String description = intent.getStringExtra("description");
        String date = intent.getStringExtra("date");
        String type = intent.getStringExtra("type");
        String numberMember = intent.getStringExtra("numberMember");
        String numberDate = intent.getStringExtra("numberDate");
        String requireRiskAssesment = intent.getStringExtra("requireRiskAssesment");

        switch(type) {
            case "0":
                textTripType.setText("high");
                textTripType.setTextColor(this.getResources().getColor(R.color.EMERG));
                break;
            case "1":
                textTripType.setText("medium");
                break;
            default:
                textTripType.setText("low");
                textTripType.setTextColor(this.getResources().getColor(R.color.GREEN));
                break;
        }

        switch(requireRiskAssesment) {
            case "1":
                textTripRequireRiskAssesment.setText("YES");
                textTripRequireRiskAssesment.setTextColor(this.getResources().getColor(R.color.GREEN));
                break;
            default:
                textTripRequireRiskAssesment.setText("NO");
                textTripRequireRiskAssesment.setTextColor(this.getResources().getColor(R.color.EMERG));
                break;
        }

        textTripName.setText(name);
        textTripDestination.setText(destination);
        textTripDescription.setText(description);
        textTripDate.setText(date);
        textTripNumberMember.setText(numberMember);
        textTripNumberDate.setText(numberDate);
    }
}