package com.bidridego.user;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class BecomeDriverDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle("Become a Driver")
                .setMessage("Do you want to become a driver?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Handle "Yes" button click
                    // You can perform any action here if needed
                    dialog.dismiss();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    // Handle "No" button click or dismiss the dialog
                    dialog.dismiss();
                })
                .create();
    }
}
