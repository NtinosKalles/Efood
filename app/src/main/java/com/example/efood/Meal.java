package com.example.efood;

import android.os.Parcel;
import android.os.Parcelable;

public class Meal implements Parcelable {
    private String name;
    private String description;
    private double price;
    private int number;

    // Υπολογιστής για καταχώρηση Meal από Parcel
    protected Meal(Parcel in) {
        name = in.readString();
        description = in.readString();
        price = in.readDouble();
        number = in.readInt();
    }

    // Δημιουργία Meal από Parcel
    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    // Υπολογιστής για Meal
    public Meal(String name, String description, double price, int number) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.number = number;
    }

    // Επιστροφή του ονόματος του γεύματος
    public String getName() {
        return name;
    }

    // Επιστροφή της περιγραφής του γεύματος
    public String getDescription() {
        return description;
    }

    // Επιστροφή της τιμής του γεύματος
    public double getPrice() {
        return price;
    }

    // Επιστροφή του αριθμού του γεύματος
    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    // Μέθοδος για εγγραφή του Meal σε ένα Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeInt(number);
    }

    // Μέθοδος για τον διαχειριστή αντικειμένου
    @Override
    public int describeContents() {
        return 0;
    }
}

