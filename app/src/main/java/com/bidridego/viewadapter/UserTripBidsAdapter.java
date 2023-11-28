package com.bidridego.viewadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bidridego.models.BidRideLocation;
import com.bidridego.models.Trip;
import com.bidridego.utils.DateTimeUtils;
import com.bidridego.viewholder.TripViewHolder;
import com.bidridego.viewholder.UserTripBidsViewHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class UserTripBidsAdapter extends RecyclerView.Adapter<UserTripBidsViewHolder> {
    private int trip_row_layout;
    private ArrayList<Trip> tripList;
    private ArrayTripAdapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ArrayTripAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public UserTripBidsAdapter(int trip_row_layout_as_id, ArrayList<Trip> tripList, Context context) {
        this.trip_row_layout = trip_row_layout_as_id;
        this.tripList = tripList;
    }

    @Override
    public int getItemCount() {
        return tripList == null ? 0 : tripList.size();
    }

    @Override
    public UserTripBidsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myTripView = LayoutInflater.from(parent.getContext()).inflate(trip_row_layout, parent, false);

        UserTripBidsViewHolder myViewHolder = new UserTripBidsViewHolder(myTripView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final UserTripBidsViewHolder tripViewHolder, @SuppressLint("RecyclerView") final int listPosition) {
        TextView bidValue = tripViewHolder.bidValue;
        TextView driverName = tripViewHolder.driverName;

        Trip currTrip = this.tripList.get(listPosition);


        if(currTrip != null){
            String[] dateTime = currTrip.getDateAndTime().split(" ");
            if(dateTime.length == 2){
//                date.setText(dateTime[0]);
//                time.setText(dateTime[1]);
                Date dateData = parseDate(dateTime[0], "dd/MM/yyyy");

                String outputDate = DateTimeUtils.formatDate(dateData, "dd MMMM yyyy");
//                date.setText(outputDate);
//                time.setText(dateTime[1]);
            }
            FirebaseDatabase.getInstance().getReference("users").child(currTrip.getPostedBy()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            String driverID = FirebaseAuth.getInstance().getUid();
//            cost.setText("$" + currTrip.getBids().getOrDefault(driverID, currTrip.getCost()));

            if(currTrip.getMinBid() > 0) {
//                tripWhos.setText("Your:");
//                minBid.setText("$"+ currTrip.getMinBid());
            }else {
//                tripWhos.setText("Budget:");
            }

            BidRideLocation to = currTrip.getTo();
            BidRideLocation from = currTrip.getFrom();

//            if(to != null) destination.setText(to.getLocationName());
//            if(from != null) source.setText(from.getLocationName());
        }
        tripViewHolder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(listPosition);
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
}
