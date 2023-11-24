package com.auazer.droidlabtool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the toolbar
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // Set a title (optional)
        Objects.requireNonNull(getSupportActionBar()).setTitle("Your App Title");

        // Connect the CardViews
        CardView cardView1 = findViewById(R.id.cardView1);
        CardView cardView2 = findViewById(R.id.cardView2);
        CardView cardView3 = findViewById(R.id.cardView3);
        CardView cardView4 = findViewById(R.id.cardView4);
        CardView cardView5 = findViewById(R.id.cardView5);

        // Add a click listener to CardView1
        cardView1.setOnClickListener(view -> {
            // Start VersionuninstallActivity
            startActivity(new Intent(MainActivity.this, VersionuninstallActivity.class));
        });

        // Add a click listener to CardView2
        cardView2.setOnClickListener(view -> {
            // Navigate to PhoneunlockActivity
            startActivity(new Intent(MainActivity.this, PhoneunlockActivity.class));
        });

        // Add a click listener to CardView3
        cardView3.setOnClickListener(view -> {
            // Navigate to PrivatesafeunlockActivity
            startActivity(new Intent(MainActivity.this, PrivatesafeunlockActivity.class));
        });

        // Add a click listener to CardView4
        cardView4.setOnClickListener(view -> {
            // Navigate to ContactNumberrecoveryActivity
            startActivity(new Intent(MainActivity.this, ContactNumberrecoveryActivity.class));
        });

        // Add a click listener to CardView5
        cardView5.setOnClickListener(view -> {
            // Navigate to CallLogrecActivity
            startActivity(new Intent(MainActivity.this, CallLogrecActivity.class));
        });
    }
}
