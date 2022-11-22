package com.example.skiingapp_topup.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.skiingapp_topup.model.Trip;
import com.example.skiingapp_topup.model.Trip_Expenses;

import java.util.Date;

public class database extends SQLiteOpenHelper {

    private static String DATABASE_NAME="skiingtripmanagement";

    private static String TABLE_TRIPS="trip";
    private static String TRIP_ID = "tripid";
    private static String TRIP_NAME="tripname";
    private static String DESTINATION="destination";
    private static String DESCRIPTION="description";
    private static String DATE_TRIP= "datetrip";
    private static String TYPE_TRIP= "typetrip";
    private static String NUMBER_MEMBER="numbermember";
    private static String NUMBER_DATE="numberdate";
    private static String REQUIRE_RISK_ASSESSMENT="requireriskassessment";
    private static int VERSION = 1;

    private static String TABLE_EXPENSES = "expenses";
    private static String ID_EXPENSES = "idexpenses";
    private static String AMOUNT = "amount";
    private static String DATE_EXPENSES = "dateexpenses";
    private static String TIME_EXPENSES = "timeexpenses";
    private static String COMMENT = "comment";

    private String SQLQuery = "CREATE TABLE "+ TABLE_TRIPS+" ( "+TRIP_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TRIP_NAME+ " TEXT, "
            + DESTINATION+ " TEXT, "
            + DESCRIPTION+ " TEXT, "
            + DATE_TRIP+ " TEXT, "
            + TYPE_TRIP+ " INTEGER, "
            + NUMBER_MEMBER+ " INTEGER, "
            + NUMBER_DATE+ " INTEGER, "
            + REQUIRE_RISK_ASSESSMENT+ " INTEGER) ";

    private String SQLQuery1 = "CREATE TABLE "+ TABLE_EXPENSES+" ( "+ID_EXPENSES+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AMOUNT+ " INTEGER, "
            + DATE_EXPENSES+ " TEXT, "
            + TIME_EXPENSES+ " TEXT, "
            + COMMENT+ " TEXT, "
            + TRIP_ID+" INTEGER , FOREIGN KEY ( "+ TRIP_ID+ " ) REFERENCES "+
            TABLE_TRIPS+ "("+TRIP_ID+"))";



    public database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void AddTrip(Trip trip){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values =new ContentValues();

        values.put(TRIP_NAME,trip.getTrip_name().toString().trim());
        values.put(DESTINATION,trip.getDestination().toString().trim());
        values.put(DESCRIPTION,trip.getDescription().toString().trim());
        values.put(DATE_TRIP,trip.getDate_trip().toString().trim());
        values.put(TYPE_TRIP,trip.getType_trip());
        values.put(NUMBER_MEMBER,trip.getNumber_member());
        values.put(NUMBER_DATE,trip.getNumber_date());
        values.put(REQUIRE_RISK_ASSESSMENT,trip.getRequire_risk_assessment());

        db.insert(TABLE_TRIPS, null,values);
        db.close();
    }

    public boolean UpdateTrip(Trip trip, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TRIP_NAME,trip.getTrip_name());
        values.put(DESTINATION,trip.getDestination());
        values.put(DESCRIPTION,trip.getDescription());
        values.put(DATE_TRIP,trip.getDate_trip());
        values.put(TYPE_TRIP,trip.getType_trip());
        values.put(NUMBER_MEMBER,trip.getNumber_member());
        values.put(NUMBER_DATE,trip.getNumber_date());
        values.put(REQUIRE_RISK_ASSESSMENT,trip.getRequire_risk_assessment());

        db.update(TABLE_TRIPS,values,TRIP_ID+" = "+id, null);
        return true;
    }

    public Cursor getDataTrip(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_TRIPS, null);
//        Cursor cursor = db.rawQuery("DROP TABLE "+TABLE_EXPENSES, null);
        return cursor;
    }

    public int DeleteTrip(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res =db.delete(TABLE_TRIPS, TRIP_ID+" = "+i,null);
        return res;
    }

    public void DeleteAllTrip(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_TRIPS);
    }

    public int DeleteTripExpenses(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res =db.delete(TABLE_EXPENSES, TRIP_ID+" = "+i,null);
        return res;
    }

    public void AddTripExpenses(Trip_Expenses trip_expenses){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values =new ContentValues();

        values.put(AMOUNT,trip_expenses.getAmount_expenses());
        values.put(DATE_EXPENSES,trip_expenses.getDate_expenses());
        values.put(TIME_EXPENSES,trip_expenses.getTime_expenses());
        values.put(COMMENT,trip_expenses.getComment_expenses());
        values.put(TRIP_ID,trip_expenses.getTrip_id());

        db.insert(TABLE_EXPENSES, null,values);
        db.close();
    }

    public Cursor getDataTripExpenses(int id_trip){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_EXPENSES+" WHERE "+TRIP_ID+" = "+id_trip, null);
        return cursor;
    }

    public Cursor getDataTripSearch(String txtString){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_TRIPS+" WHERE "+TRIP_NAME+" LIKE "
                        + "\""+txtString+"%"+ "\"", null);
        return cursor;
    }

    public int DeleteExpenses(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res =db.delete(TABLE_EXPENSES, ID_EXPENSES+" = "+i,null);
        return res;
    }

    public boolean UpdateTripExpenses(Trip_Expenses trip_expenses, int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values =new ContentValues();

        values.put(AMOUNT,trip_expenses.getAmount_expenses());
        values.put(DATE_EXPENSES,trip_expenses.getDate_expenses());
        values.put(TIME_EXPENSES,trip_expenses.getTime_expenses());
        values.put(COMMENT,trip_expenses.getComment_expenses());

        db.update(TABLE_EXPENSES,values,ID_EXPENSES+" = "+id, null);
        return true;
    }
}
