package com.example.skiingapp_topup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.skiingapp_topup.database.database;
import com.example.skiingapp_topup.model.Trip_Expenses;

import java.util.Calendar;

public class ActivityUpdateTripExpenses extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    Button buttonUpdateTripExpenses;
    EditText editTextAmountUpdate, editTextDateExpensesUpdate, editTextTimeExpensesUpdate, editTextCommentUpdate;
    com.example.skiingapp_topup.database.database database;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_trip_expenses);

        buttonUpdateTripExpenses = findViewById(R.id.buttonUpdateTripExpenses);
        editTextAmountUpdate = findViewById(R.id.EditTextTripAmountUpdate);
        editTextDateExpensesUpdate = findViewById(R.id.EditTextTripDateExpensesUpdate);
        editTextTimeExpensesUpdate = findViewById(R.id.EditTextTripTimeExpensesUpdate);
        editTextCommentUpdate = findViewById(R.id.EditTextTripCommentUpdate);

        Intent intent = getIntent();

        int id = intent.getIntExtra("id",0);

        String amount = intent.getStringExtra("amount");
        String date_expenses = intent.getStringExtra("date_expenses");
        String time_expenses = intent.getStringExtra("time_expenses");
        String comment_expenses = intent.getStringExtra("comment_expenses");
        int id_trip = intent.getIntExtra("id_trip",0);

        editTextAmountUpdate.setText(amount);
        editTextDateExpensesUpdate.setText(date_expenses);
        editTextTimeExpensesUpdate.setText(time_expenses);
        editTextCommentUpdate.setText(comment_expenses);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButtonUpdateExpenses);
        dateButton.setText(date_expenses);
        database = new database(this);
        buttonUpdateTripExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdate(id,id_trip);
            }
        });
        editTextTimeExpensesUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(ActivityUpdateTripExpenses.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = " PM";
                        } else {
                            amPm = " AM";
                        }
                        editTextTimeExpensesUpdate.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });
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
                editTextDateExpensesUpdate.setText(date);
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
    private void DialogUpdate(int id, int id_trip) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogupdatetripexpenses);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesUpdateDialog);
        Button btnNo = dialog.findViewById(R.id.buttonNoUpdateDialog);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = editTextAmountUpdate.getText().toString().trim();
                String date_expenses = editTextDateExpensesUpdate.getText().toString().trim();
                String time_expenses = editTextTimeExpensesUpdate.getText().toString().trim();
                String comment_expenses = editTextCommentUpdate.getText().toString().trim();

                if(amount.equals("") || date_expenses.equals("")
                        || time_expenses.equals("") || comment_expenses.equals("")){
                    Toast.makeText(ActivityUpdateTripExpenses.this, "Dis not enter enough information", Toast.LENGTH_SHORT).show();
                } else{
                    Trip_Expenses trip_expenses = updateTripExpenses();

                    database.UpdateTripExpenses(trip_expenses, id);
                    Intent intent = new Intent(ActivityUpdateTripExpenses.this, ActivityExpenses.class);
                    intent.putExtra("id_trip", id_trip);
                    startActivity(intent);

                    Toast.makeText(ActivityUpdateTripExpenses.this, "More success", Toast.LENGTH_SHORT).show();
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
    private Trip_Expenses updateTripExpenses() {
        int amount = Integer.parseInt(editTextAmountUpdate.getText().toString().trim());
        String date_expenses = editTextDateExpensesUpdate.getText().toString().trim();
        String time_expenses = editTextTimeExpensesUpdate.getText().toString().trim();
        String comment_expenses = editTextCommentUpdate.getText().toString().trim();

        Trip_Expenses trip_expenses = new Trip_Expenses(amount, date_expenses,time_expenses,comment_expenses);

        return trip_expenses;
    }
}