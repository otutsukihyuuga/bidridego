package com.bidridego.driver;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.bidridego.R;

public class BidingDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        SharedPreferences prefs = requireContext().getSharedPreferences("BidRigeGo", Context.MODE_PRIVATE);

        // Inflate the custom layout for the dialog
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_bidding, null);

        // Find views in the custom layout
        EditText priceEditText = view.findViewById(R.id.editTextPrice);
        Button sendButton = view.findViewById(R.id.buttonSend);

        // Set up the AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Place a Bid")
                .setView(view)
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        // Set up the positive button click listener

        return builder.create();
    }
}
