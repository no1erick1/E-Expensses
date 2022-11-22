package com.example.skiingapp_topup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.skiingapp_topup.ActivitySearch;
import com.example.skiingapp_topup.ActivityTrip;
import com.example.skiingapp_topup.R;
import com.example.skiingapp_topup.model.Trip;

import java.util.ArrayList;

public class adaptertripsearch extends BaseAdapter {
    private ActivitySearch context;
    private ArrayList<Trip> ArrayListTrip;

    public adaptertripsearch(ActivitySearch context, ArrayList<Trip> arrayListTrip) {
        this.context = context;
        ArrayListTrip = arrayListTrip;
    }

    @Override
    public int getCount() {
        return ArrayListTrip.size();
    }

    @Override
    public Object getItem(int position) {
        return ArrayListTrip.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.listtripsearch, null);

        TextView TextViewTripNameSearch = convertView.findViewById(R.id.TextViewTripNameSearch);

        ImageButton imageNew = convertView.findViewById(R.id.tripinformationsearch);
        ImageButton imageUpdate = convertView.findViewById(R.id.tripupdatesearch);
        ImageButton imageDelete= convertView.findViewById(R.id.tripdeletesearch);

        Trip trip = ArrayListTrip.get(position);

        TextViewTripNameSearch.setText(trip.getTrip_name());

        int id = trip.getTrip_id();

        imageNew.setOnClickListener(new View.OnClickListener() {
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
