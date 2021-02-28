package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMealsUtil {
//    private static   Map<Integer,Integer> caloriesGropedByDay;


    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
//caloriesGropedByDay = gropedForDay(meals);


//        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//
//
//        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));

    }

//    private static Map<Integer, Integer> gropedForDay(List<UserMeal> meals) {
//        return meals.stream()
//                .collect(Collectors.groupingBy(um -> um.getDateTime().getDayOfMonth(), Collectors.summingInt(userMeal -> userMeal.getCalories())));
//    }

    private static boolean getExces(UserMeal um, int caloriesPerDay){
      boolean excess =  UserMeal.getCaloriesGropedPerDay().get(um.getDateTime().getDayOfMonth())>caloriesPerDay;
        return excess;
    }


    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {   //O(3N)
        // TODO return filtered list with excess. Implement by cycles

        List<UserMealWithExcess> mealExcess = new ArrayList<>();

        for (UserMeal um: meals) {
            if (TimeUtil.isBetweenHalfOpen(um.getDateTime().toLocalTime(), startTime,endTime)){
                mealExcess.add(new UserMealWithExcess(um.getDateTime(),um.getDescription(),um.getCalories(),getExces(um, caloriesPerDay)));
            }
            return mealExcess;
        }






        return mealExcess;
    }
//        Map<Integer, List<UserMeal>> collect = meals.stream()
//                .collect(Collectors.groupingBy(um -> um.getDateTime().getDayOfMonth()));


//    final Map<Integer, Integer> collect = meals.stream()
//            .collect(Collectors.groupingBy(um -> um.getDateTime().getDayOfMonth(), Collectors.summingInt(UserMeal::getCalories)));


    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime
            startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams


        List<UserMealWithExcess> collect = meals.stream()
                .filter(m -> TimeUtil.isBetweenHalfOpen(m.getDateTime().toLocalTime(), startTime, endTime))
                .map(m -> new UserMealWithExcess(m.getDateTime(), m.getDescription(), m.getCalories(),
                        getExces(m, caloriesPerDay)))
                .collect(Collectors.toList());

        return collect;
    }


}


