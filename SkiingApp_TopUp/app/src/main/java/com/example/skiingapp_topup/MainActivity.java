package com.example.skiingapp_topup;

import static android.content.ContentValues.TAG;
import static com.example.skiingapp_topup.R.string.app_name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.skiingapp_topup.database.database;
import com.example.skiingapp_topup.model.Trip;
import com.example.skiingapp_topup.model.Trip_Expenses;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnListTrip, btnResetTrip, btnBackupTrip, btnAboutMe, btnSearch;
    com.example.skiingapp_topup.database.database database;
    ArrayList<Trip> ArrayListTrip;
    int trip_id =0;
    ArrayList<Trip_Expenses> ArrayTripExpenses;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListTrip = findViewById(R.id.buttonListTrip);
        btnResetTrip = findViewById(R.id.buttonResetTrip);
        btnBackupTrip = findViewById(R.id.buttonBackupTrip);
        btnAboutMe = findViewById(R.id.buttonAboutTrip);
        btnSearch = findViewById(R.id.buttonSearchListTrip);

        btnResetTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogReset();
            }
        });

        btnListTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityTrip.class);
                startActivity(intent);
            }
        });
        database = new database(this);

        ArrayListTrip = new ArrayList<>();
        ArrayTripExpenses = new ArrayList<>();
        Cursor cursor = database.getDataTrip();
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
            Cursor cursor1 = database.getDataTripExpenses(id);
            while (cursor1.moveToNext()){
                int id_trip = cursor1.getInt(5);
                int id_e = cursor1.getInt(0);
                int amount = cursor1.getInt(1);
                String date_expenses = cursor1.getString(2);
                String time_expenses = cursor1.getString(3);
                String comment_expenses = cursor1.getString(4);

                ArrayTripExpenses.add(new Trip_Expenses(id_e,amount,date_expenses,time_expenses,comment_expenses,id_trip));
            }
        }

        btnBackupTrip.setOnClickListener(v -> backup());
        
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivitySearch.class);
                startActivity(intent);
            }
        });
        btnAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityAboutMe.class);
                startActivity(intent);
            }
        });
    }

    protected void backup() {
        Map<String, Object> backup = new HashMap<>();
        if(ArrayListTrip != null &&  0 < ArrayListTrip.size()) {
            backup.put("Trip List Back Up", ArrayListTrip);
            if(ArrayTripExpenses != null && 0 < ArrayTripExpenses.size()){
                backup.put("Trip List Expenses Back Up", ArrayTripExpenses);
            }else {
                backup.put("Trip List Expenses Back Up", "is empty");
            }

            FirebaseFirestore.getInstance().collection("Backup")
                    .add(backup)
                    .addOnSuccessListener(document -> {
                        Toast.makeText(this, "Back up list success", Toast.LENGTH_SHORT).show();
                        Log.d(getResources().getString(R.string.project_id), document.getId());
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Back1 up list success", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    });
        }
        else {
            Toast.makeText(this, "Is Empty List", Toast.LENGTH_SHORT).show();
        }
    }

    private void DialogReset(){
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialoginformation);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.DeleteAllTrip();

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Reset success", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

        private void DialogBackUp(){
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogbackup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesBackUp);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Cursor cursor = database.getDataTrip();
//                Trip backup = new Trip("a","a","a","a",1,2,3,1);
//                FirebaseFirestore.getInstance().collection("a")
//                        .add(backup)
//                        .addOnSuccessListener(document -> {
//                            Toast.makeText(MainActivity.this, "Back up list success", Toast.LENGTH_SHORT).show();
//                            Log.d(getResources().getString(app_name), document.getId());
//                        })
//                        .addOnFailureListener(e -> {
//                            Toast.makeText(MainActivity.this, "1Back up list success", Toast.LENGTH_SHORT).show();
//                            e.printStackTrace();
//                        });
//                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                startActivity(intent);
//                Toast.makeText(MainActivity.this, "Back up list success", Toast.LENGTH_SHORT).show();

                Map<String, Object> user = new HashMap<>();
                user.put("first", "Ada");
                user.put("last", "Lovelace");
                user.put("born", 1815);
//

                FirebaseFirestore.getInstance().collection("BackupEntry.TABLE_NAME")
                        .add(user)
                        .addOnSuccessListener(document -> {
                            Toast.makeText(MainActivity.this, "Back up list success", Toast.LENGTH_SHORT).show();
                            Log.d(getResources().getString(R.string.project_id), document.getId());
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(MainActivity.this, "Back1 up list success", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        });
            }
        });
        dialog.show();
    }
}