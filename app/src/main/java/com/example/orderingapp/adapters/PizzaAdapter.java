package com.example.orderingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderingapp.R;
import com.example.orderingapp.models.Pizza;
import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {
    private final List<Pizza> pizzaList;
    private OnItemClickListener listener;

    public PizzaAdapter(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.pizzaImage.setImageResource(pizza.getImageResId());
        holder.pizzaName.setText(pizza.getName());
        holder.pizzaDescription.setText(pizza.getDescription());
        holder.pizzaPrice.setText(String.format("$%.2f", pizza.getPrice()));

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        ImageView pizzaImage;
        TextView pizzaName;
        TextView pizzaDescription;
        TextView pizzaPrice;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaImage = itemView.findViewById(R.id.pizzaImage);
            pizzaName = itemView.findViewById(R.id.pizzaName);
            pizzaDescription = itemView.findViewById(R.id.pizzaDescription);
            pizzaPrice = itemView.findViewById(R.id.pizzaPrice);
        }
    }
}
