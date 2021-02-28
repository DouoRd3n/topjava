package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UserMeal {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public static Map<Integer, Integer> getCaloriesGropedPerDay() {
        return caloriesGropedPerDay;
    }

    private static Map<Integer, Integer> caloriesGropedPerDay = new HashMap<>();

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        if (caloriesGropedPerDay.get(dateTime.getDayOfMonth())==null){

            caloriesGropedPerDay.put(dateTime.getDayOfMonth(), calories);
        } else {
            caloriesGropedPerDay.put(dateTime.getDayOfMonth(), caloriesGropedPerDay.get(dateTime.getDayOfMonth())+calories);
        }

    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }
}
