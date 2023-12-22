package com.example.efood;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class RestaurantList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Αρχικοποίηση του Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Λήψη των εστιατορίων από το Firestore και εμφάνισή τους στο RecyclerView
        getRestaurantsFromFirestore();

    }

    private void getRestaurantsFromFirestore() {
        CollectionReference restaurantsRef = db.collection("Restaurant");

        restaurantsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Restaurant> restaurants = new ArrayList<>();
                    for (DocumentSnapshot document : task.getResult()) {
                        String name = document.getString("Name");
                        String telephone = document.getString("Telephone");

                        // Λήψη του collection "Menu" για κάθε εστιατόριο
                        List<Meal> menu = getMenuFromFirestore(document.getId());

                        Restaurant restaurant = new Restaurant(name, telephone, menu);
                        restaurants.add(restaurant);
                    }

                    // Εμφάνιση των εστιατορίων στο RecyclerView
                    adapter = new RestaurantAdapter(restaurants);
                    recyclerView.setAdapter(adapter);

                    // Ορισμός του OnRestaurantClickListener
                    adapter.setOnRestaurantClickListener(new OnRestaurantClickListener() {
                        @Override
                        public void onRestaurantClick(Restaurant restaurant) {
                            // Αντικατάσταση του Toast με κώδικα που θέλετε
                            Toast.makeText(RestaurantList.this, "Clicked on " + restaurant.getName(), Toast.LENGTH_SHORT).show();

                            // Στο κομμάτι του κώδικα όπου ξεκινάτε την MealListActivity
                            Intent intent = new Intent(RestaurantList.this, MealList.class);
                            intent.putExtra("restaurant", restaurant);
                            startActivity(intent);
                        }
                    });
                } else {
                    Log.e("Firestore", "Error getting documents from Restaurant collection", task.getException());
                    Toast.makeText(RestaurantList.this, "Error getting restaurants", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private List<Meal> getMenuFromFirestore(String restaurantId) {
        CollectionReference menuRef = db.collection("Restaurant").document(restaurantId).collection("Menu");

        List<Meal> menu = new ArrayList<>();

        menuRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        String mealName = document.getString("Name");
                        double mealPrice = document.getDouble("Price");

                        Meal meal = new Meal(mealName, "", mealPrice, 0);
                        menu.add(meal);
                    }

                    // Ενημέρωση του Adapter όταν ολοκληρωθεί η λήψη του μενού
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("Firestore", "Error getting documents from Menu collection", task.getException());
                }
            }
        });

        return menu;
    }
}
