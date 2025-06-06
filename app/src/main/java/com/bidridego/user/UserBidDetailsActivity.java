package com.bidridego.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.bidridego.R;
import com.bidridego.models.BidDetails;
import com.bidridego.models.Trip;

public class UserBidDetailsActivity extends AppCompatActivity {
    TextView source, destination, date, time, driverName, contact, bidValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_bid_details);
        source = findViewById(R.id.bid_details_source);
        destination = findViewById(R.id.bid_details_destination);
        date = findViewById(R.id.bid_details_date);
        time = findViewById(R.id.bid_details_time);
        driverName = findViewById(R.id.bid_details_driver_name);
        contact = findViewById(R.id.bid_details_driver_contact);
//        bidValue = findViewById(R.id.bid_details_cost);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("bidDetails")) {
            BidDetails bidDetails = intent.getParcelableExtra("bidDetails");
            if (bidDetails != null) {
                driverName.setText(bidDetails.getFirstName() + " " + bidDetails.getLastName());
                contact.setText(bidDetails.getContact());
                bidValue.setText(String.valueOf(bidDetails.getBidValue()));
            }
        }
        if (intent != null && intent.hasExtra("trip")) {
            Trip trip = intent.getParcelableExtra("trip");
            source.setText(trip.getFrom().getLocationName());
            destination.setText(trip.getTo().getLocationName());
            date.setText(trip.getDateAndTime());
        }


    }
    public void acceptBid(View view) {
        // Add your logic for accepting the bid here
        // This function will be called when the button is clicked
    }
}