package com.bidridego.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.bidridego.R;
import com.bidridego.models.Trip;
import com.bidridego.utils.DateTimeUtils;
import com.bidridego.viewadapter.UserTripBidsAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class UserTripBidsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserTripBidsAdapter adapter;
    public ArrayList<Trip> tripArrayList;
    private FirebaseDatabase firebaseDatabase;
    private HashMap<String, Double> bids;
//    private DatabaseReference databaseReferenceToTrips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_trip_bids);

        Trip trip = (Trip) getIntent().getSerializableExtra("trip");
        bids = trip.getBids();
        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReferenceToTrips = firebaseDatabase.getReference("trips");
        // Initialize RecyclerView
        recyclerView = findViewById(R.id.user_trip_bids);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        tripArrayList = new ArrayList <>();
        // Initialize Adapter
        adapter = new UserTripBidsAdapter(R.layout.user_trip_bids_list_item, tripArrayList, this);
        recyclerView.setAdapter(adapter);

//        String filterDate;
//        try {
//            filterDate = DateTimeUtils.getTimeStampFromDate(new Date());
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }

//        databaseReferenceToTrips.orderByChild("dateAndTime").startAt(filterDate).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                tripArrayList.clear();
//                snapshot.getChildren().forEach(e->{
//                    tripArrayList.add(e.getValue(Trip.class));
//                });
//                adapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}