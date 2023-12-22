package com.example.efood;

import android.os.Parcel;
import android.os.Parcelable;

public class Meal implements Parcelable {
    private String name;
    private String description;
    private double price;
    private int number;

    
    protected Meal(Parcel in) {
        name = in.readString();
        description = in.readString();
        price = in.readDouble();
        number = in.readInt();
    }

    
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

    
    public Meal(String name, String description, double price, int number) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.number = number;
    }

    
    public String getName() {
        return name;
    }

    
    public String getDescription() {
        return description;
    }

    
    public double getPrice() {
        return price;
    }

    
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

    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeInt(number);
    }

    
    @Override
    public int describeContents() {
        return 0;
    }
}

