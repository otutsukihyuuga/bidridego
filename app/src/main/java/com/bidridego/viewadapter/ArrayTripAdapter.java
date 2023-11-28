package com.bidridego.viewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bidridego.models.BidRideLocation;
import com.bidridego.models.Trip;
import com.bidridego.models.User;
import com.bidridego.viewholder.TripViewHolder;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ArrayTripAdapter extends RecyclerView.Adapter<TripViewHolder> {
    private int trip_row_layout;
    private ArrayList<Trip> tripList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ArrayTripAdapter(int trip_row_layout_as_id, ArrayList<Trip> tripList, Context context) {
        this.trip_row_layout = trip_row_layout_as_id;
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
        TextView date = tripViewHolder.date;
        TextView time = tripViewHolder.time;
        TextView minBid = tripViewHolder.minBid;

        Trip currTrip = this.tripList.get(listPosition);


        if(currTrip != null){
            Date dateData = parseDate(currTrip.getDate(), "dd/MM/yyyy");

            String outputDate = formatDate(dateData, "dd MMMM yyyy");
            date.setText(outputDate);
            time.setText(currTrip.getTime());
            FirebaseDatabase.getInstance().getReference("users").child(currTrip.getPostedBy()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            cost.setText("" + currTrip.getCost());

            if(currTrip.getMinBid() > 0) {
                minBid.setText(String.valueOf(currTrip.getMinBid()));
            }

            BidRideLocation to = currTrip.getTo();
            BidRideLocation from = currTrip.getFrom();

            if(to != null) destination.setText(to.getLocationName());
            if(from != null) source.setText(from.getLocationName());
        }
        tripViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(listPosition);
                }
            }
        });
    }
    private static Date parseDate(String dateStr, String inputFormat) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(inputFormat, Locale.getDefault());
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String formatDate(Date date, String outputFormat) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(outputFormat, Locale.getDefault());
            return sdf.format(date);
        } else {
            return null;
        }
    }
}
