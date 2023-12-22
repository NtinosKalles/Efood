package com.example.efood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;


public class MealList extends AppCompatActivity implements OnMealClickListener {

    private RecyclerView recyclerView;
    private MealAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);

        recyclerView = findViewById(R.id.recyclerViewMeals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Restaurant restaurant = getIntent().getParcelableExtra("restaurant");

        if (restaurant != null) {
            List<Meal> meals = restaurant.getMenu();

            adapter = new MealAdapter(meals);
            recyclerView.setAdapter(adapter);
            adapter.setOnMealClickListener(this);
        }


    }

    @Override
    public void onMealClick(Meal meal) {
        Intent intent = new Intent(MealList.this, MealDetails.class);
        intent.putExtra("meal", meal);
        startActivity(intent);
    }

}

