package com.example.skiingapp_topup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.skiingapp_topup.database.database;
import com.example.skiingapp_topup.model.Trip;

import java.util.Calendar;
import java.util.Date;

public class ActivityAddTrip extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    RadioButton radioHigh, radioMedium, radioLow;
    Button buttonAddTrip;
    SwitchCompat switchCompat;
    EditText editTripName, editTripDestination, editTripDescription, editTripDate,editTripType, editTripNumberMember,editTripNumberDate, editTripRequireRiskAssesment;
    com.example.skiingapp_topup.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        buttonAddTrip = findViewById(R.id.buttonAddTrip);
        radioHigh = (RadioButton) findViewById(R.id.radioHighScale);
        radioMedium = (RadioButton) findViewById(R.id.radioMediumScale);
        radioLow = (RadioButton) findViewById(R.id.radioLowScale);
        editTripName =findViewById(R.id.EditTextTripName);
        editTripDestination = findViewById(R.id.EditTextTripDestination);
        editTripDescription = findViewById(R.id.EditTextTripDescription);
        editTripDate = findViewById(R.id.EditTextTripDate);
        editTripType = findViewById(R.id.EditTextTripType);
        editTripNumberDate = findViewById(R.id.EditTextTripNumberDate);
        editTripNumberMember = findViewById(R.id.EditTextTripNumberMember);
        editTripRequireRiskAssesment = findViewById(R.id.EditTextTripRequireRiskAssesment);

//        editTripRequireRiskAssesment.setText("0");
//        editTripDate.setText(getTodaysDate().toString());
//        editTripType.setText("0");

        switchCompat=findViewById(R.id.switch_compat);

        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        switchCompat.setChecked(sharedPreferences.getBoolean("value",true));
        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchCompat.isChecked())
                {
                    // When switch checked
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    editTripRequireRiskAssesment.setText("1");
                    switchCompat.setChecked(true);

                }
                else
                {
                    // When switch unchecked
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    editTripRequireRiskAssesment.setText("0");
                    switchCompat.setChecked(false);
                }
            }
        });
////
        radioHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTripType.setText("0");
            }
        });

        radioMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTripType.setText("1");
            }
        });

        radioLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTripType.setText("2");
            }
        });

        database = new database(this);
        buttonAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd();
            }
        });
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
    }
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
                editTripDate.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
    private void DialogAdd() {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogadd);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes =dialog.findViewById(R.id.buttonYes);
        Button btnNo =dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tripname = editTripName.getText().toString().trim();
                String tripdestination = editTripDestination.getText().toString().trim();
                String tripdescription = editTripDescription.getText().toString().trim();
                String tripdate = editTripDate.getText().toString().trim();
                String triptype = editTripType.getText().toString().trim();
                String tripnumbermember = editTripNumberMember.getText().toString().trim();
                String tripnumberdate = editTripNumberDate.getText().toString().trim();
                String triprequireriskassesment = editTripRequireRiskAssesment.getText().toString().trim();

                if(tripname.equals("") || tripdestination.equals("") || tripdescription.equals("")
                ||tripdate.equals("") || triptype.equals("") || tripnumbermember.equals("") ||
                tripnumberdate.equals("") || triprequireriskassesment.equals("")){
                    Toast.makeText(ActivityAddTrip.this, "Dis not enter enough information", Toast.LENGTH_SHORT).show();
                }

                else {
                    Trip trip = CreateTrip();

                    database.AddTrip(trip);
                    Intent intent = new Intent(ActivityAddTrip.this, ActivityTrip.class);
                    startActivity(intent);

                    Toast.makeText(ActivityAddTrip.this, "More success", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private Trip CreateTrip(){
        String tripname = editTripName.getText().toString().trim();
        String tripdestination = editTripDestination.getText().toString().trim();
        String tripdescription = editTripDescription.getText().toString().trim();
        String tripdate = editTripDate.getText().toString().trim();
        int triptype = Integer.parseInt(editTripType.getText().toString().trim());
        int tripnumbermember = Integer.parseInt(editTripNumberMember.getText().toString().trim());
        int tripnumberdate = Integer.parseInt(editTripNumberDate.getText().toString().trim());
        int triprequireriskassesment = Integer.parseInt(editTripRequireRiskAssesment.getText().toString().trim());

        Trip trip = new Trip(tripname, tripdestination, tripdescription, tripdate, triptype, tripnumbermember, tripnumberdate, triprequireriskassesment);
        Log.d("myTag", trip.toString());
        return trip;
    }
}