package com.bidridego.driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bidridego.BidDetails;
import com.bidridego.R;
import com.bidridego.models.BidRideLocation;
import com.bidridego.models.Trip;
import com.bidridego.viewadapter.ArrayTripAdapter;

import java.util.ArrayList;

public class DriverTripsListFragment  extends Fragment {

    private RecyclerView recyclerView;
    private ArrayTripAdapter adapter;
    public ArrayList<Trip> tripArrayList;
    int row_index = 1 ;
    void addDummyData()
    {
        BidRideLocation loc = new BidRideLocation(30, 30, "xyz");
        Trip aTrip = new Trip("d",10, loc, loc, 30, "alice",3,  "date", "String time", false,"suv");
        tripArrayList.add(aTrip);
        row_index++;

        Trip bTrip = new Trip("e",10, loc, loc, 30, "bob",3,  "date", "String time", false,"suv");
        tripArrayList.add(bTrip);
        row_index++;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recyclerview_list, container, false);

        // Initialize RecyclerView
        recyclerView = rootView.findViewById(R.id.recycler_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tripArrayList = new ArrayList <>();
        row_index = 0;
//        while (row_index <= 200) {
//            Trip aTrip = new Trip( 1, "abc", "def", 30);
//            tripArrayList.add(aTrip) ;
//            row_index++;
//        }
        addDummyData(); //to be removed
        // Initialize Adapter
        adapter = new ArrayTripAdapter(R.layout.trip_list_item, tripArrayList, getContext());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ArrayTripAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // You can use the 'position' parameter to get the clicked item position

//                Bundle bundle

                startActivity(new Intent(getActivity(), BidDetails.class));
            }
        });
        // Populate your dataset and update the adapter as needed

        return rootView;
    }
}