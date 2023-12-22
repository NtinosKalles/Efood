package com.example.efood;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class OrderSummary extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);


        List<Meal> selectedMeals = getIntent().getParcelableArrayListExtra("selectedMeal");
        double totalAmount = getIntent().getDoubleExtra("totalAmount", 0.0);


        TextView textViewTotalAmount = findViewById(R.id.textViewTotalAmount);
        textViewTotalAmount.setText("Total Amount: $" + String.format("%.2f", totalAmount));


        TextView textViewSelectedMeals = findViewById(R.id.textViewSelectedMeals);
        StringBuilder selectedMealsText = new StringBuilder("Selected Meals:\n");
        for (Meal meal : selectedMeals) {
            selectedMealsText.append("- ").append(meal.getName()).append("\n");
        }
        textViewSelectedMeals.setText(selectedMealsText.toString());
    }
}
