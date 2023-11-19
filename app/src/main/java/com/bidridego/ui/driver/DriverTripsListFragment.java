package com.bidridego.ui.driver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bidridego.R;
import com.bidridego.models.Trip;
import com.bidridego.viewadapter.ArrayTripAdapter;

import java.util.ArrayList;

public class DriverTripsListFragment  extends Fragment {

    private RecyclerView recyclerView;
    private ArrayTripAdapter adapter;
    public ArrayList<Trip> tripArrayList;
    int row_index = 1 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recyclerview_list, container, false);

        // Initialize RecyclerView
        recyclerView = rootView.findViewById(R.id.recycler_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tripArrayList = new ArrayList <>();

        while (row_index <= 200) {
            Trip aTrip = new Trip( 1, "abc", "def", 30);
            tripArrayList.add(aTrip) ;
            row_index++;
        }

        // Initialize Adapter
        adapter = new ArrayTripAdapter(R.layout.trip_list_item, tripArrayList, getContext());
        recyclerView.setAdapter(adapter);

        // Populate your dataset and update the adapter as needed

        return rootView;
    }
}