package com.example.orderingapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderingapp.adapters.PizzaAdapter;
import com.example.orderingapp.models.Pizza;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView pizzaRecyclerView;
    private PizzaAdapter pizzaAdapter;
    private List<Pizza> pizzaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize RecyclerView
        pizzaRecyclerView = findViewById(R.id.pizzaRecyclerView);
        pizzaRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Initialize pizza list with descriptions
        pizzaList = Arrays.asList(
                new Pizza("Margherita", R.drawable.pizza_margherita, "Delicious tomato and cheese pizza.", 8.99),
                new Pizza("Pepperoni", R.drawable.pizza_pepperoni, "Classic pepperoni pizza.", 10.99),
                new Pizza("Hawaiian", R.drawable.pizza_hawaiian, "Pineapple and ham pizza.", 11.99),
                new Pizza("Veggie", R.drawable.pizza_veggie, "Loaded with fresh vegetables.", 9.99)
        );

        // Set adapter
        pizzaAdapter = new PizzaAdapter(pizzaList);
        pizzaRecyclerView.setAdapter(pizzaAdapter);

        // Setup item click listener
        pizzaAdapter.setOnItemClickListener(position -> {
            Pizza selectedPizza = pizzaList.get(position);
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            intent.putExtra("pizzaName", selectedPizza.getName());
            intent.putExtra("pizzaPrice", selectedPizza.getPrice());
            intent.putExtra("pizzaImageResId", selectedPizza.getImageResId());
            intent.putExtra("pizzaDescription", selectedPizza.getDescription()); // Add this line
            startActivity(intent);
        });
    }
}
