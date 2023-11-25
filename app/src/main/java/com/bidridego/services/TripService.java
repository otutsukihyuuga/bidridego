package com.bidridego.services;

import androidx.annotation.NonNull;

import com.bidridego.models.Trip;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TripService {
    private static TripService tripsService = null;
    private final FirebaseAuth auth;
    private final FirebaseDatabase firebaseDatabase;
    private final DatabaseReference databaseReferenceToTrips;
//    private final DatabaseReference databaseReferenceToUsers;
//    private UserService userService;

    private TripService() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        databaseReferenceToTrips = firebaseDatabase.getReference("trips");

//        databaseReferenceToUsers = firebaseDatabase.getReference("users");
//        userService = UserService.getInstance();
    }

    public static TripService getInstance() {
        if (tripsService == null) {
            tripsService = new TripService();
        }
        return tripsService;
    }

    public void saveOrUpdate(Trip trip) {
        if (trip.getId() == null) {
            trip.setId(this.databaseReferenceToTrips.push().getKey());
        }
        trip.setPostedBy(auth.getCurrentUser().getUid());

        databaseReferenceToTrips.child(trip.getId()).setValue(trip);
    }
}
