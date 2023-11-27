package com.bidridego;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.GridLayout;
import android.os.Bundle;

public class bidlist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidlist);

        GridLayout gridLayout = findViewById(R.id.bid);
        gridLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bidlist.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}