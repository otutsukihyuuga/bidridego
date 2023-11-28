package com.bidridego;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bidridego.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private EditText firstName;
    private EditText lastName;
    private EditText contact;
    private Button register;

    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        register = findViewById(R.id.register);

        auth = FirebaseAuth.getInstance();
        preferences = getSharedPreferences("BidRigeGo", Context.MODE_PRIVATE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_firstName = firstName.getText().toString().trim();
                String txt_lastName = lastName.getText().toString().trim();
                String txt_email = email.getText().toString().trim();
                String txt_contact = contact.getText().toString().trim();
                String txt_password = password.getText().toString().trim();
                String txt_confirmPassword = confirmPassword.getText().toString().trim();
                if (!txt_confirmPassword.equals(txt_password)) {
                    // Passwords do not match, show Toast message
                    toast(RegisterActivity.this, "Password and Confirm Password aren't same ");
                } else {
                    // Passwords match, proceed with registration
                User user = new User(txt_firstName, txt_lastName, txt_contact, "user");

                if (TextUtils.isEmpty(txt_firstName) || TextUtils.isEmpty(txt_lastName) ||
                        TextUtils.isEmpty(txt_contact) || TextUtils.isEmpty(txt_email) ||
                        TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_confirmPassword)
                ) {
                    toast(RegisterActivity.this, "Please fill all the fields!");
                } else if (txt_password.length() < 6 || !txt_confirmPassword.equals(txt_password)) {
                    toast(RegisterActivity.this, "Password too short!");
                } else {
                    registerUser(user, txt_email, txt_password);
                }
            }
            }
        });
    }

    private void registerUser(User user, String email, String passWord) {

        auth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener(RegisterActivity.this, task -> {
            if (task.isSuccessful()) {

                DatabaseReference usersRef = firebaseDatabase.getReference("users");
                String userId = auth.getCurrentUser().getUid();
                user.setId(userId);
                usersRef.child(userId).setValue(user).addOnCompleteListener(RegisterActivity.this, databaseTask -> {
                    if (databaseTask.isSuccessful()) {
                        toast(RegisterActivity.this, "Registration successful!");
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        logout();
                        startActivity(intent);
//                        finish();
                    } else {
                        auth.getCurrentUser().delete().addOnCompleteListener(authTask -> {
                            if (authTask.isSuccessful()) {
                                toast(RegisterActivity.this, "Failed to save user data! User account deleted.");
                            } else {
                                toast(RegisterActivity.this, "Failed to save user data! Failed to delete user account.");
                            }
                        });
                    }
                });
            } else {
                toast(RegisterActivity.this, "Registration failed!");
            }
        });
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    private void toast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}