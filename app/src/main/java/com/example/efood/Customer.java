package com.example.efood;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String firstName, lastName, address;
    private List<Meal> cart;
    private double totalCost;

    public Customer(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.cart = new ArrayList<>();
        this.totalCost = 0.0;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCart(List<Meal> cart) {
        this.cart = cart;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public List<Meal> getCart() {
        return cart;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void addToCart(Meal meal) {
        cart.add(meal);
        totalCost += meal.getPrice() * meal.getNumber();
    }
}
