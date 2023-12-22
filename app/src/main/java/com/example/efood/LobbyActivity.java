package com.example.efood;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class LobbyActivity extends AppCompatActivity {

    private EditText editTextFirstName, editTextLastName, editTextAddress;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAddress = findViewById(R.id.editTextAddress);

        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData();
            }
        });

        
        db = FirebaseFirestore.getInstance();
    }

    private void submitData() {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        
        Customer customer = new Customer(firstName, lastName, address);

        
        db.collection("Customer")
                .add(customer)
                .addOnSuccessListener(documentReference -> {
                    Log.d("Firestore", "DocumentSnapshot added with ID: " + documentReference.getId());
                    navigateToRestaurantList();
                })
                .addOnFailureListener(e -> {
                    Log.w("Firestore", "Error adding document", e);
                    Toast.makeText(this, "Error submitting data", Toast.LENGTH_SHORT).show();
                });
    }

    private void navigateToRestaurantList() {
        Log.d("MyTag", "This is a debug message");
        Intent intent = new Intent(this, RestaurantList.class);
        startActivity(intent);
    }
}
