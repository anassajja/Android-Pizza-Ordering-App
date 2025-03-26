package com.example.orderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {
    private EditText phoneEditText;
    private EditText addressEditText;
    private RadioGroup paymentMethodGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout); // Ensure it's using the correct XML layout

        // Link the EditText fields
        phoneEditText = findViewById(R.id.phoneEditText);
        addressEditText = findViewById(R.id.addressEditText);

        // Link the RadioGroup for payment method
        paymentMethodGroup = findViewById(R.id.paymentMethodGroup);

        // Link the confirmOrderButton
        Button confirmButton = findViewById(R.id.confirmOrderButton);

        // Set OnClickListener for the confirmButton
        confirmButton.setOnClickListener(v -> {
            // Get the text entered in EditText fields
            String phone = phoneEditText.getText().toString().trim();
            String address = addressEditText.getText().toString().trim();

            // Get the selected payment method
            int selectedPaymentId = paymentMethodGroup.getCheckedRadioButtonId();

            // Check if both fields are filled and a payment method is selected
            if (!phone.isEmpty() && !address.isEmpty() && selectedPaymentId != -1) {
                // Proceed with the order based on the selected payment method
                String paymentMethod = selectedPaymentId == R.id.cashOnDeliveryRadio
                        ? "Cash on Delivery"
                        : "Credit/Debit Card";

                // Optionally pass the data to the next activity
                Intent intent = new Intent(CheckoutActivity.this, ConfirmationActivity.class);
                intent.putExtra("phone", phone);
                intent.putExtra("address", address);
                intent.putExtra("paymentMethod", paymentMethod);
                startActivity(intent);
            } else {
                // Show a message if fields or payment method are missing
                Toast.makeText(CheckoutActivity.this, "Please fill all fields and select a payment method", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
