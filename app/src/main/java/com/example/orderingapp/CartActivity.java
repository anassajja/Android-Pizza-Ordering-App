package com.example.orderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        TextView pizzaTextView = findViewById(R.id.pizzaTextView);
        Button checkoutButton = findViewById(R.id.checkoutButton);

        String pizza = getIntent().getStringExtra("pizza");
        pizzaTextView.setText(pizza);

        checkoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });
    }
}
