package com.example.orderingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderingapp.R;
import com.example.orderingapp.models.Pizza;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private final List<Pizza> cartList;
    private OnQuantityChangeListener quantityChangeListener;

    public CartAdapter(List<Pizza> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Pizza pizza = cartList.get(position);
        holder.pizzaImage.setImageResource(pizza.getImageResId());
        holder.pizzaName.setText(pizza.getName());
        holder.pizzaPrice.setText(String.format(Locale.getDefault(), "%.2f MAD", pizza.getPrice()));
        holder.quantityTextView.setText(String.valueOf(pizza.getQuantity()));

        holder.increaseQuantity.setOnClickListener(v -> {
            pizza.setQuantity(pizza.getQuantity() + 1);
            notifyItemChanged(position);
            if (quantityChangeListener != null) {
                quantityChangeListener.onQuantityChanged();
            }
        });

        holder.decreaseQuantity.setOnClickListener(v -> {
            if (pizza.getQuantity() > 1) {
                pizza.setQuantity(pizza.getQuantity() - 1);
                notifyItemChanged(position);
                if (quantityChangeListener != null) {
                    quantityChangeListener.onQuantityChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void setOnQuantityChangeListener(OnQuantityChangeListener listener) {
        this.quantityChangeListener = listener;
    }

    public interface OnQuantityChangeListener {
        void onQuantityChanged();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView pizzaImage;
        TextView pizzaName;
        TextView pizzaPrice;
        TextView quantityTextView;
        ImageButton increaseQuantity;
        ImageButton decreaseQuantity;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaImage = itemView.findViewById(R.id.pizzaImage);
            pizzaName = itemView.findViewById(R.id.pizzaName);
            pizzaPrice = itemView.findViewById(R.id.pizzaPrice);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            increaseQuantity = itemView.findViewById(R.id.increaseQuantity);
            decreaseQuantity = itemView.findViewById(R.id.decreaseQuantity);
        }
    }
}
