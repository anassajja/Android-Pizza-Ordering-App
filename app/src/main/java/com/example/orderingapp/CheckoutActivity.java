package com.example.orderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {
    private EditText phoneEditText;
    private EditText addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        phoneEditText = findViewById(R.id.phoneEditText);
        addressEditText = findViewById(R.id.addressEditText);
        Button confirmButton = findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(v -> {
            String phone = phoneEditText.getText().toString();
            String address = addressEditText.getText().toString();

            if (!phone.isEmpty() && !address.isEmpty()) {
                Intent intent = new Intent(CheckoutActivity.this, ConfirmationActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(CheckoutActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
