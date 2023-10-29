package com.bidridego;

import android.content.Context;
import android.content.Intent;
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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_firstName = firstName.getText().toString();
                String txt_lastName = lastName.getText().toString();
                String txt_email = email.getText().toString();
                String txt_contact = contact.getText().toString();
                String txt_password = password.getText().toString();
                String txt_confirmPassword = confirmPassword.getText().toString();

                User user = new User(txt_firstName, txt_lastName, txt_contact);

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
        });
    }

    private void registerUser(User user, String email, String passWord) {

        auth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    DatabaseReference usersRef = firebaseDatabase.getReference("users");
                    String userId = auth.getCurrentUser().getUid();
                    usersRef.child(userId).setValue(user).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> databaseTask) {
                            if (databaseTask.isSuccessful()) {
                                toast(RegisterActivity.this, "Registration successful!");
//                              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            } else {
                                auth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> authTask) {
                                        if (authTask.isSuccessful()) {
                                            toast(RegisterActivity.this, "Failed to save user data! User account deleted.");
                                        } else {
                                            toast(RegisterActivity.this, "Failed to save user data! Failed to delete user account.");
                                        }
                                    }
                                });
                            }
                        }
                    });
                } else {
                    toast(RegisterActivity.this, "Registration failed!");
                }
            }
        });

    }

    private void toast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}