package com.example.skiingapp_topup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.example.skiingapp_topup.adapter.adaptertripexpenses;
import com.example.skiingapp_topup.database.database;
import com.example.skiingapp_topup.model.Trip_Expenses;

import java.util.ArrayList;

public class ActivityExpenses extends AppCompatActivity {
    Toolbar toolbar;
    ListView listViewTripExpenses;

    ArrayList<Trip_Expenses> ArrayTripExpenses;
    com.example.skiingapp_topup.database.database database;
    com.example.skiingapp_topup.adapter.adaptertripexpenses adaptertripexpenses;

    int trip_id =0;
    int counter =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        toolbar = findViewById(R.id.toolbarTripExpenses);

        listViewTripExpenses = findViewById(R.id.listviewTripExpenses);

        Intent intent = getIntent();
        trip_id = intent.getIntExtra("id_trip", 0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);
        ArrayTripExpenses = new ArrayList<>();
        ArrayTripExpenses.clear();

        Cursor cursor = database.getDataTripExpenses(trip_id);
        while (cursor.moveToNext()){
            int id_trip = cursor.getInt(5);
            int id = cursor.getInt(0);
            int amount = cursor.getInt(1);
            String date_expenses = cursor.getString(2);
            String time_expenses = cursor.getString(3);
            String comment_expenses = cursor.getString(4);

            ArrayTripExpenses.add(new Trip_Expenses(id,amount,date_expenses,time_expenses,comment_expenses,id_trip));
        }
        adaptertripexpenses = new adaptertripexpenses(ActivityExpenses.this, ArrayTripExpenses);

        listViewTripExpenses.setAdapter(adaptertripexpenses);
        cursor.moveToFirst();
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddtripexpenses, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuaddtripexpenses:

                Intent intent = new Intent(ActivityExpenses.this, ActivityAddTripExpenses.class);
                intent.putExtra("id_trip", trip_id);
                startActivity(intent);
                break;
            default:
                Intent intent1 = new Intent(ActivityExpenses.this, ActivityTrip.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        counter++;
        if(counter>=1){
            Intent intent = new Intent(this, ActivityExpenses.class);
            startActivity(intent);
            finish();
        }
    }

    public void information(final int pos){
        Cursor cursor = database.getDataTripExpenses(trip_id);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if(id == pos){
                Intent intent = new Intent(ActivityExpenses.this, ActivityInformationTripExpenses.class);

                intent.putExtra("id", pos);

                String amount = cursor.getString(1);
                String date_expenses = cursor.getString(2);
                String time_expenses = cursor.getString(3);
                String comment_expenses = cursor.getString(4);
//                int trip_id = cursor.getInt(5);

                intent.putExtra("amount",amount);
                intent.putExtra("date_expenses",date_expenses);
                intent.putExtra("time_expenses",time_expenses);
                intent.putExtra("comment_expenses",comment_expenses);
//                intent.putExtra("id_trip",trip_id);

                startActivity(intent);
            }
        }
    }

    public void update(final int id_tripexpenses){
        Cursor cursor = database.getDataTripExpenses(trip_id);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if(id == id_tripexpenses){
                Intent intent = new Intent(ActivityExpenses.this, ActivityUpdateTripExpenses.class);

                intent.putExtra("id", id_tripexpenses);

                String amount = cursor.getString(1);
                String date_expenses = cursor.getString(2);
                String time_expenses = cursor.getString(3);
                String comment_expenses = cursor.getString(4);
                int trip_id = cursor.getInt(5);

                intent.putExtra("amount",amount);
                intent.putExtra("date_expenses",date_expenses);
                intent.putExtra("time_expenses",time_expenses);
                intent.putExtra("comment_expenses",comment_expenses);
                intent.putExtra("id_trip",trip_id);

                startActivity(intent);
            }
        }
        cursor.close();
    }

    public  void delete(final int id_tripexpenses){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogdeletetripexpenses);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesDeleteTripExpenses);
        Button btnNo = dialog.findViewById(R.id.buttonNoDeleteTripExpenses);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.DeleteExpenses(id_tripexpenses);

                Intent intent = new Intent(ActivityExpenses.this, ActivityExpenses.class);
                intent.putExtra("id_trip",trip_id);
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
}