package com.bidridego.viewholder;

import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bidridego.R;

public class TripViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView cost, destination, source, distance, postedBy, date, time, passengers;
    public Switch isCarPool;
//    public TextView distance;

    public TripViewHolder(View tripView) {
        super(tripView);
        date = tripView.findViewById(R.id.date);
        time = tripView.findViewById(R.id.time);
        cost = tripView.findViewById(R.id.cost);
        destination = tripView.findViewById(R.id.destination);
        source = tripView.findViewById(R.id.source);
//        distance = tripView.findViewById(R.id.distance);
        distance = tripView.findViewById(R.id.cost);
        postedBy = tripView.findViewById(R.id.username);
        passengers = tripView.findViewById(R.id.seats);
        isCarPool = tripView.findViewById(R.id.is_car_pool);
        tripView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d("onclick", "onClick "
                + getLayoutPosition() + " " + destination.getText());
    }
}