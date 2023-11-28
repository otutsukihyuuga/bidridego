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
        preferences = getSharedPreferences("BidRideGo", Context.MODE_PRIVATE);

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(!validateEmpty(email))
                        validateAndHandleEmail();
                }
            }
        });
        firstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    validateEmpty(firstName);
                }
            }
        });
        lastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    validateEmpty(lastName);
                }
            }
        });
        contact.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(!validateEmpty(contact))
                        validateContact();
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(!validateEmpty(password))
                        validatePasswordLength();
                }
            }
        });
        confirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateConfirmPassword();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_firstName = firstName.getText().toString();
                String txt_lastName = lastName.getText().toString();
                String txt_email = email.getText().toString();
                String txt_contact = contact.getText().toString();
                String txt_password = password.getText().toString();

                User user = new User(txt_firstName, txt_lastName, txt_contact, "user");

                if (hasError()) {
                    Toast.makeText(getApplicationContext(),"Remove above errors",Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(user, txt_email, txt_password);
                }
            }
        });
    }
    private boolean hasError(){
        return firstName.getError() != null || lastName.getError() != null || contact.getError() != null
                || email.getError()!= null || password.getError()!= null || confirmPassword.getError()!= null;
    }
    private void validatePasswordLength(){
        String pass = password.getText().toString().trim();
        if(pass.length()<6)
        {
            password.setError("Password length must be 6 characters");
        }else{
            password.setError(null);
        }
    }
    private void validateConfirmPassword(){
        String pass = password.getText().toString().trim();
        String confirmpass = confirmPassword.getText().toString().trim();
        if(!pass.equals(confirmpass))
            confirmPassword.setError("Should be same as password");
        else
            confirmPassword.setError(null);
    }
    private void validateContact(){
        String number = contact.getText().toString().trim();
        if(number.length()!=10)
        {
            contact.setError("Number can be 10 digits only");
        }
        else
            contact.setError(null);
    }
    private boolean validateEmpty(EditText et){
        String enteredText = et.getText().toString().trim();
        if(enteredText == null|| enteredText.isEmpty())
        {
            et.setError("Field cannot be empty");
            return true;
        }
        else{
            et.setError(null);
        }
        return false;
    }
    private void validateAndHandleEmail() {
        String enteredText = email.getText().toString().trim();

        if (!isValidEmail(enteredText)) {
            email.setError("Invalid email address");
        }
        else {
            email.setError(null);
        }
    }

    private boolean isValidEmail(String email) {
        // Perform your email validation here
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void registerUser(User user, String email, String passWord) {

        auth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    DatabaseReference usersRef = firebaseDatabase.getReference("users");
                    String userId = auth.getCurrentUser().getUid();
                    user.setId(userId);
                    usersRef.child(userId).setValue(user).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> databaseTask) {
                            if (databaseTask.isSuccessful()) {
                                toast(RegisterActivity.this, "Registration successful!");
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
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
        logout();

    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void toast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}