package com.example.orderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderingapp.adapters.CartAdapter;
import com.example.orderingapp.models.Pizza;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private List<Pizza> cartList;
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize views
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        Button checkoutButton = findViewById(R.id.checkoutButton);

        // Setup RecyclerView
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartList = new ArrayList<>();

        // Get pizza data from intent
        String pizzaName = getIntent().getStringExtra("pizzaName");
        double pizzaPrice = getIntent().getDoubleExtra("pizzaPrice", 0.0);
        int pizzaImageResId = getIntent().getIntExtra("pizzaImageResId", R.drawable.pizza_margherita);
        String pizzaDescription = getIntent().getStringExtra("pizzaDescription");

        // Add pizza to cart
        Pizza pizza = new Pizza(pizzaName, pizzaImageResId, pizzaDescription, pizzaPrice);
        cartList.add(pizza);

        // Set adapter
        cartAdapter = new CartAdapter(cartList);
        cartRecyclerView.setAdapter(cartAdapter);

        // Calculate and display total price
        updateTotalPrice();

        // Setup quantity change listener
        cartAdapter.setOnQuantityChangeListener(() -> updateTotalPrice());

        // Setup checkout button
        checkoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });
    }

    private void updateTotalPrice() {
        double totalPrice = 0;
        for (Pizza pizza : cartList) {
            totalPrice += pizza.getTotalPrice();
        }
        totalPriceTextView.setText(String.format(Locale.getDefault(), "Total: %.2f MAD", totalPrice));
    }
}
