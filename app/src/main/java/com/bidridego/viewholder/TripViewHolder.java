package com.bidridego.viewholder;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bidridego.R;

public class TripViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView cost;
    public TextView destination;
    public TextView source;
    public TextView distance;
    public TextView postedBy;

    public TripViewHolder(View tripView) {
        super(tripView);
        cost = tripView.findViewById(R.id.cost);
        destination = tripView.findViewById(R.id.source);
        source = tripView.findViewById(R.id.source);
//        distance = tripView.findViewById(R.id.distance);
        postedBy = tripView.findViewById(R.id.username);
        tripView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d("onclick", "onClick "
                + getLayoutPosition() + " " + destination.getText());
    }
}