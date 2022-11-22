package com.example.skiingapp_topup.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.skiingapp_topup.ActivityExpenses;
import com.example.skiingapp_topup.ActivityTrip;
import com.example.skiingapp_topup.R;
import com.example.skiingapp_topup.model.Trip;
import com.example.skiingapp_topup.model.Trip_Expenses;

import java.util.ArrayList;

public class adaptertripexpenses extends BaseAdapter {
    private ActivityExpenses context;
    private ArrayList<Trip_Expenses> ArrayListTripExpenses;

    public adaptertripexpenses(ActivityExpenses context, ArrayList<Trip_Expenses> arrayListTripExpenses) {
        this.context = context;
        ArrayListTripExpenses = arrayListTripExpenses;
    }

    @Override
    public  int getCount(){
        return ArrayListTripExpenses.size();
    }

    @Override
    public Object getItem(int position) {
        return ArrayListTripExpenses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.listtripextenses, null);

        TextView txtAmount = convertView.findViewById(R.id.TextViewTripAmount);

        ImageButton imageInformation = convertView.findViewById(R.id.tripexpensesinformation);
        ImageButton imageUpdate = convertView.findViewById(R.id.tripexpensesupdate);
        ImageButton imageDelete= convertView.findViewById(R.id.tripexpensesdelete);

        Trip_Expenses trip_expenses = ArrayListTripExpenses.get(position);

        txtAmount.setText(trip_expenses.getComment_expenses());

        int id =trip_expenses.getTrip_expenses_id();

        imageInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.information(id);
            }
        });

        imageUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.update(id);
            }
        });

        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.delete(id);
            }
        });

        return convertView;
    }
}
