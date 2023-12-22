package com.example.efood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {

    private List<Meal> meals;
    private OnMealClickListener onMealClickListener;

    public MealAdapter(List<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = meals.get(position);

        holder.textViewMealName.setText(meal.getName());
        holder.textViewMealPrice.setText(String.valueOf(meal.getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMealClickListener != null) {
                    onMealClickListener.onMealClick(meal);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMealName;
        TextView textViewMealPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMealName = itemView.findViewById(R.id.textViewMealName);
            textViewMealPrice = itemView.findViewById(R.id.textViewMealPrice);


        }
    }
    public void setOnMealClickListener(OnMealClickListener listener) {
        this.onMealClickListener = listener;
    }

}

