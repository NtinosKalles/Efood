package com.example.efood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class MealDetails extends AppCompatActivity {

    private Meal meal;
    private EditText editTextDescription;
    private EditText editTextQuantity;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

        meal = getIntent().getParcelableExtra("meal");

        editTextDescription = findViewById(R.id.editTextDescription);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        Button btnApply = findViewById(R.id.btnApply);
        Button btnCancel = findViewById(R.id.btnCancel);

        editTextDescription.setText(meal.getDescription());
        editTextQuantity.setText(String.valueOf(meal.getNumber()));

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyChanges();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelChanges();
            }
        });
    }


    private void applyChanges() {
            meal.setDescription(editTextDescription.getText().toString());
            meal.setNumber(Integer.parseInt(editTextQuantity.getText().toString()));


        db.collection("Customer")
                .whereEqualTo("firstName", "ntinos")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                        Customer customer = documentSnapshot.toObject(Customer.class);


                        if (customer != null) {
                            customer.addToCart(meal);


                            db.collection("Customer")
                                    .document(documentSnapshot.getId())
                                    .set(customer)
                                    .addOnSuccessListener(aVoid -> {

                                        Intent intent = new Intent(MealDetails.this, OrderSummary.class);
                                        intent.putExtra("selectedMeal", meal);
                                        startActivity(intent);
                                    });
                        }
                    }
                });
    }
    private void cancelChanges() {

        finish();
    }
}
