package com.bidridego.viewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bidridego.R;
import com.bidridego.models.Trip;
import com.bidridego.viewholder.TripViewHolder;

import java.util.ArrayList;

public class ArrayTripAdapter extends RecyclerView.Adapter<TripViewHolder> {
    private int trip_row_layout;
    private ArrayList<Trip> tripList;

    public ArrayTripAdapter(int trip_row_layout_as_id, ArrayList<Trip> tripList, Context context) {
        trip_row_layout = trip_row_layout_as_id;
        this.tripList = tripList;
    }

    @Override
    public int getItemCount() {
        return tripList == null ? 0 : tripList.size();
    }

    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myTripView = LayoutInflater.from(parent.getContext()).inflate(trip_row_layout, parent, false);

        TripViewHolder myViewHolder = new TripViewHolder(myTripView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final TripViewHolder tripViewHolder, final int listPosition) {
        TextView cost = tripViewHolder.cost;
        TextView destination = tripViewHolder.destination;
        TextView source = tripViewHolder.source;
//        TextView distance = tripViewHolder.distance;
        TextView date = tripViewHolder.date;
        TextView time = tripViewHolder.time;
        TextView postedBy = tripViewHolder.postedBy;
//        TextView passengers = tripViewHolder.passengers;
//        TextView isCarPool = tripViewHolder.isCarPool;

        Trip currTrip = tripList.get(listPosition);

//        date.setText( currTrip.getDate());
//        time.setText(currTrip.getTime());
//        postedBy.setText(currTrip.getPostedBy());
//        passengers.setText(currTrip.getPassengers());
//        isCarPool.setText(""+currTrip.isCarPool());
//        cost.setText(""+ currTrip.getCost());
//        destination.setText(currTrip.getTo().getLocationName());
//        source.setText(currTrip.getFrom().getLocationName());

//        distance.setText("" + currTrip.getDistance());
//        distance.setText("10");
    }
}
