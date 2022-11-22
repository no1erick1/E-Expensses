package com.example.skiingapp_topup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.skiingapp_topup.adapter.adaptertrip;
import com.example.skiingapp_topup.adapter.adaptertripsearch;
import com.example.skiingapp_topup.database.database;
import com.example.skiingapp_topup.model.Trip;

import java.util.ArrayList;

public class ActivitySearch extends AppCompatActivity {
    Toolbar toolbar;
    ListView listViewtrip;
    ArrayList<Trip> ArrayListTrip;
    Button searchbutton;
    EditText textSearchTrip;
    com.example.skiingapp_topup.database.database database;
    com.example.skiingapp_topup.adapter.adaptertripsearch adaptertrip;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = findViewById(R.id.toolbarTripSearch);
        listViewtrip = findViewById(R.id.listviewTripSearch);
        searchbutton = findViewById(R.id.buttonSearchTrip);
        textSearchTrip = findViewById(R.id.TextTripSearch);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        ArrayListTrip = new ArrayList<>();

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayListTrip.clear();
                Cursor cursor = database.getDataTripSearch(textSearchTrip.getText().toString().trim());
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String destination = cursor.getString(2);
                    String description = cursor.getString(3);
                    String date = cursor.getString(4);
                    int typetrip = cursor.getInt(5);
                    int numbermember = cursor.getInt(6);
                    int numberdate = cursor.getInt(7);
                    int requirerisk = cursor.getInt(8);

                    ArrayListTrip.add(new Trip(id, name, destination, description, date, typetrip, numbermember, numberdate, requirerisk));
                }

                adaptertrip = new adaptertripsearch(ActivitySearch.this, ArrayListTrip);
                listViewtrip.setAdapter(adaptertrip);
                cursor.moveToFirst();
                cursor.close();
            }
        });

        listViewtrip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(ActivitySearch.this, ActivityExpenses.class);
                int id_trip = ArrayListTrip.get(position).getTrip_id();
                intent1.putExtra("id_trip", id_trip);
                startActivity(intent1);
            }
        });
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuadd:
                Intent intent1 = new Intent(ActivitySearch.this, ActivityAddTrip.class);
                startActivity(intent1);
                break;
            default:
                Intent intent = new Intent(ActivitySearch.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        count++;
        if (count >= 1) {
            Intent intent = new Intent(ActivitySearch.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void information(final int pos){
        Cursor cursor = database.getDataTrip();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if (id == pos){
                Intent intent = new Intent(ActivitySearch.this, ActivityTripInformation.class);

                intent.putExtra("id", id);

                String name = cursor.getString(1);
                String destination = cursor.getString(2);
                String description = cursor.getString(3);
                String date = cursor.getString(4);
                String type = cursor.getString(5);
                String numberMember = cursor.getString(6);
                String numberDate = cursor.getString(7);
                String requireRiskAssesment = cursor.getString(8);

                intent.putExtra("name", name);
                intent.putExtra("destination", destination);
                intent.putExtra("description", description);
                intent.putExtra("date", date);
                intent.putExtra("type", type);
                intent.putExtra("numberMember", numberMember);
                intent.putExtra("numberDate", numberDate);
                intent.putExtra("requireRiskAssesment", requireRiskAssesment);

                startActivity(intent);
            }
        }
    }

    public void delete(final int position){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogdeletetrip);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnYes = dialog.findViewById(R.id.buttonYesDelete);
        Button btnNo = dialog.findViewById(R.id.buttonNoDelete);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //database = new database(ActivityTrip.this);

                database.DeleteTrip(position);
                database.DeleteTripExpenses(position);
                Intent intent = new Intent(ActivitySearch.this, ActivityTrip.class);

                startActivity(intent);
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

    public void update(final int pos){
        Cursor cursor = database.getDataTrip();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if(id == pos){
                Intent intent = new Intent(ActivitySearch.this, AcitivityUpdateTrip.class);

                intent.putExtra("id", id);

                String name = cursor.getString(1);
                String destination = cursor.getString(2);
                String description = cursor.getString(3);
                String date = cursor.getString(4);
                String type = cursor.getString(5);
                String numberMember = cursor.getString(6);
                String numberDate = cursor.getString(7);
                String requireRiskAssesment = cursor.getString(8);

                intent.putExtra("name", name);
                intent.putExtra("destination", destination);
                intent.putExtra("description", description);
                intent.putExtra("date", date);
                intent.putExtra("type", type);
                intent.putExtra("numberMember", numberMember);
                intent.putExtra("numberDate", numberDate);
                intent.putExtra("requireRiskAssesment", requireRiskAssesment);

                startActivity(intent);
            }
        }
    }
}