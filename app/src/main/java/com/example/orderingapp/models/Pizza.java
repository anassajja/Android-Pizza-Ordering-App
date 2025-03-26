package com.example.orderingapp.models;

public class Pizza {
    private final String name;
    private final int imageResId;
    private final String description;
    private final double price;

    public Pizza(String name, int imageResId, String description, double price) {
        this.name = name;
        this.imageResId = imageResId;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
