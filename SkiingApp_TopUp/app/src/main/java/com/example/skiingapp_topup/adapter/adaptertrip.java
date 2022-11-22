package com.example.skiingapp_topup.adapter;

import com.example.skiingapp_topup.ActivitySearch;
import com.example.skiingapp_topup.ActivityTrip;
import com.example.skiingapp_topup.R;
import com.example.skiingapp_topup.model.Trip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class adaptertrip extends BaseAdapter {
    private  ActivityTrip context;
    private ActivitySearch contextSearch;
    private ArrayList<Trip> ArrayListTrip;

    public adaptertrip(ActivityTrip context, ArrayList<Trip> arrayListTrip) {
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

        convertView = inflater.inflate(R.layout.listtrip, null);

        TextView TextViewTripName = convertView.findViewById(R.id.TextViewTripName);

        ImageButton imageNew = convertView.findViewById(R.id.tripinformation);
        ImageButton imageUpdate = convertView.findViewById(R.id.tripupdate);
        ImageButton imageDelete= convertView.findViewById(R.id.tripdelete);

        Trip trip = ArrayListTrip.get(position);

        TextViewTripName.setText(trip.getTrip_name());

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
