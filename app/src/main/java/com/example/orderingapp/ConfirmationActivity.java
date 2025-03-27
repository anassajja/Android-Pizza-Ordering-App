package com.example.orderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Find the Continue Shopping button
        Button continueShoppingButton = findViewById(R.id.continueShoppingButton);

        // Set up the click listener to navigate to HomeActivity
        continueShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmationActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();  // Close the current activity
            }
        });
    }
}
