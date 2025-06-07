package com.bidridego.user;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.bidridego.DriverMainActivity;
import com.bidridego.R;
import com.bidridego.models.Trip;
import com.bidridego.services.SaveOrUpdateCallback;
import com.bidridego.services.TripService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AcceptBidDialog extends DialogFragment {
    String driverId;
    String driverName;


    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle("Accept trip with " + driverName)
                .setPositiveButton("Yes", (dialog, which) -> {
                    SharedPreferences preferences = getActivity().getSharedPreferences("BidRigeGo", Context.MODE_PRIVATE);

                    /**
                     * TODO: update the role of the user to driver
                     * **/
                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid()).child("role").setValue("driver");
                    if (preferences != null) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("localRole", "driver");
                        editor.apply();
                    }
                    Intent intent = new Intent(getContext(), DriverMainActivity.class);
                    startActivity(intent);
                    dialog.dismiss();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    // Handle "No" button click or dismiss the dialog
                    dialog.dismiss();
                })
                .create();
    }
}