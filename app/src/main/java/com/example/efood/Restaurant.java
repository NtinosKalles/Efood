package com.example.efood;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class Restaurant implements Parcelable {
    private String name, telephone;
    private List<Meal> menu;

    public Restaurant() {
       
    }

    public Restaurant(String name, String telephone, List<Meal> menu) {
        this.name = name;
        this.telephone = telephone;
        this.menu = menu;
    }

    protected Restaurant(Parcel in) {
        name = in.readString();
        telephone = in.readString();
        menu = in.createTypedArrayList(Meal.CREATOR);
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }

    public List<Meal> getMenu() {
        return menu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(telephone);
        dest.writeTypedList(menu);
    }
}

