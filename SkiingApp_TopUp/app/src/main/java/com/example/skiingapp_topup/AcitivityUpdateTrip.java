package com.example.skiingapp_topup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.skiingapp_topup.database.database;
import com.example.skiingapp_topup.model.Trip;

import java.util.Calendar;

public class AcitivityUpdateTrip extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    RadioButton radioHigh, radioMedium, radioLow;
    Button buttonUpdateTrip;
    SwitchCompat switchCompat;
    EditText editUpdateTripName, editUpdateTripDestination, editUpdateTripDescription, editUpdateTripDate,editUpdateTripType, editUpdateTripNumberMember,editUpdateTripNumberDate, editUpdateTripRequireRiskAssesment;
    com.example.skiingapp_topup.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acitivity_update_trip);

        buttonUpdateTrip = findViewById(R.id.buttonUpdateTrip);
        radioHigh = (RadioButton) findViewById(R.id.radioUpdateHighScale);
        radioMedium = (RadioButton) findViewById(R.id.radioUpdateMediumScale);
        radioLow = (RadioButton) findViewById(R.id.radioUpdateLowScale);
        editUpdateTripName =findViewById(R.id.EditTextUpdateTripName);
        editUpdateTripDestination = findViewById(R.id.EditTextUpdateTripDestination);
        editUpdateTripDescription = findViewById(R.id.EditTextUpdateTripDescription);
        editUpdateTripDate = findViewById(R.id.EditTextUpdateTripDate);
        editUpdateTripType = findViewById(R.id.EditTextUpdateTripType);
        editUpdateTripNumberDate = findViewById(R.id.EditTextUpdateTripNumberDate);
        editUpdateTripNumberMember = findViewById(R.id.EditTextUpdateTripNumberMember);
        editUpdateTripRequireRiskAssesment = findViewById(R.id.EditTextUpdateTripRequireRiskAssesment);

        Intent intent = getIntent();

        int id = intent.getIntExtra("id", 0);

        String name = intent.getStringExtra("name");
        String destination = intent.getStringExtra("destination");
        String description = intent.getStringExtra("description");
        String date = intent.getStringExtra("date");
        String type = intent.getStringExtra("type");
        String numberMember = intent.getStringExtra("numberMember");
        String numberDate = intent.getStringExtra("numberDate");
        String requireRiskAssesment = intent.getStringExtra("requireRiskAssesment");

        editUpdateTripName.setText(name);
        editUpdateTripDestination.setText(destination);
        editUpdateTripDescription.setText(description);
        editUpdateTripDate.setText(date);
        editUpdateTripType.setText(type);
        editUpdateTripNumberMember.setText(numberMember);
        editUpdateTripNumberDate.setText(numberDate);
        editUpdateTripRequireRiskAssesment.setText(requireRiskAssesment);

        if(type.equals("0")){
            radioHigh.setChecked(true);
        }
        if(type.equals("1")){
            radioMedium.setChecked(true);
        }
        if(type.equals("2")){
            radioLow.setChecked(true);
        }

        switchCompat=findViewById(R.id.switch_compat);
        radioHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUpdateTripType.setText("0");
                radioHigh.setChecked(true);
            }
        });

        radioMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUpdateTripType.setText("1");
                radioMedium.setChecked(true);
            }
        });

        radioLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUpdateTripType.setText("2");
                radioLow.setChecked(true);
            }
        });
        initDatePicker();
        dateButton = findViewById(R.id.dateUpdatePickerButton);
        dateButton.setText(date);
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
                    editUpdateTripRequireRiskAssesment.setText("1");
                    switchCompat.setChecked(true);

                }
                else
                {
                    // When switch unchecked
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    editUpdateTripRequireRiskAssesment.setText("0");
                    switchCompat.setChecked(false);
                }
            }
        });

        database = new database(this);
        buttonUpdateTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdate(id);
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
                editUpdateTripDate.setText(date);
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

    private void DialogUpdate(int id){
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogupdate);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesUpdate);
        Button btnNo = dialog.findViewById(R.id.buttonNoUpdate);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tripname = editUpdateTripName.getText().toString().trim();
                String tripdestination = editUpdateTripDestination.getText().toString().trim();
                String tripdescription = editUpdateTripDescription.getText().toString().trim();
                String tripdate = editUpdateTripDate.getText().toString().trim();
                String triptype = (editUpdateTripType.getText().toString().trim());
                String tripnumbermember = (editUpdateTripNumberMember.getText().toString().trim());
                String tripnumberdate = (editUpdateTripNumberDate.getText().toString().trim());
                String triprequireriskassesment = (editUpdateTripRequireRiskAssesment.getText().toString().trim());

                if(tripname.equals("") || tripdestination.equals("") || tripdescription.equals("")
                        ||tripdate.equals("") || triptype.equals("") || tripnumbermember.equals("") ||
                        tripnumberdate.equals("") || triprequireriskassesment.equals("")){
                    Toast.makeText(AcitivityUpdateTrip.this, "Dis not enter enough information", Toast.LENGTH_SHORT).show();
                }
                else {
                    Trip trip = updateTrip();

                    database.UpdateTrip(trip, id);
                    Intent intent = new Intent(AcitivityUpdateTrip.this, ActivityTrip.class);
                    startActivity(intent);

                    Toast.makeText(AcitivityUpdateTrip.this, "More success", Toast.LENGTH_SHORT).show();

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

    private Trip updateTrip(){
        String tripname = editUpdateTripName.getText().toString().trim();
        String tripdestination = editUpdateTripDestination.getText().toString().trim();
        String tripdescription = editUpdateTripDescription.getText().toString().trim();
        String tripdate = editUpdateTripDate.getText().toString().trim();
        int triptype = Integer.parseInt(editUpdateTripType.getText().toString().trim());
        int tripnumbermember = Integer.parseInt(editUpdateTripNumberMember.getText().toString().trim());
        int tripnumberdate = Integer.parseInt(editUpdateTripNumberDate.getText().toString().trim());
        int triprequireriskassesment = Integer.parseInt(editUpdateTripRequireRiskAssesment.getText().toString().trim());

        Trip trip = new Trip(tripname, tripdestination, tripdescription, tripdate, triptype, tripnumbermember, tripnumberdate, triprequireriskassesment);

        return trip;
    }
}

