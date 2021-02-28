package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UserMeal {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;


    private static Map<Integer, Integer> caloriesGropedPerDay = new HashMap<>();

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;


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
