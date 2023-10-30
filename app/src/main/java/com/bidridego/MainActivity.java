package com.bidridego;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bidridego.models.Trip;
import com.bidridego.models.User;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.bidridego.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FirebaseDatabase database;
    private DatabaseReference databaseReferenceToTrips;
    private DatabaseReference databaseReferenceToUsers;
    private FirebaseAuth auth;
    private ArrayList<Trip> tripList = new ArrayList<Trip>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        databaseReferenceToTrips = database.getReference("trips");
        databaseReferenceToUsers = database.getReference("users");
        auth = FirebaseAuth.getInstance();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                createTrip(new Trip(35, "Toronto", "Waterloo",105, null));
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void createTrip(Trip trip) {
        String tripId = databaseReferenceToTrips.push().getKey(); // Generates a unique key
        trip.setId(tripId);
        databaseReferenceToUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                trip.setPostedBy(dataSnapshot.getValue(User.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("loadUser:onCancelled", databaseError.toException());
            }
        });
        databaseReferenceToTrips.child(tripId).setValue(trip);
    }

//    private class TripAdapter extends ArrayAdapter<> {
//        public TripAdapter(Context context) {
//            super(context, 0);
//        }
//        public int getCount(){
//            return tripList.size();
//        }
//        public Trip getItem(int position){
//            return tripList.get(position);
//        }
//        public View getView(int position, View convertView, ViewGroup parent){
//            LayoutInflater inflater = MainActivity.this.getLayoutInflater();
//            View result = null ;
//            result = inflater.inflate(R.layout.chat_row_incoming, null);
//
//            TextView message = (TextView)result.findViewById(R.id.message_text);
//            message.setText(   getItem(position)  ); // get the string at position
//            return result;
//        }
//    }
}