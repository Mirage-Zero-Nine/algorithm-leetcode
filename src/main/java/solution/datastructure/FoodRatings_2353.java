package solution.datastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Design a food rating system that can do the following:
 * - Modify the rating of a food item listed in the system.
 * - Return the highest-rated food item for a type of cuisine in the system.
 * Implement the FoodRatings class:
 * FoodRatings(String[] foods, String[] cuisines, int[] ratings)
 * - foods[i] is the name of the ith food,
 * - cuisines[i] is the type of cuisine of the ith food, and
 * - ratings[i] is the initial rating of the ith food.
 * - void changeRating(String food, int newRating): Changes the rating of the food item with the name food.
 * - String highestRated(String cuisine): Returns the name of the highest rating food for the given type of cuisine.
 * If there is a tie, return the item with the lexicographically smaller name.
 * Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order.
 *
 * @author BorisMirage
 * Time: 2022/07/28 15:49
 * Created with IntelliJ IDEA
 */

public class FoodRatings_2353 {
    Map<String, Food> foodMap;                    // map food and cuisine
    Map<String, TreeSet<Food>> sortingMap;   // mapping cuisines and its highest rating food

    /**
     * Make both API O(1) by using two hash maps and a customized Food class.
     * First hash map stores the mapping for food name and the Food object.
     * Second map stores the cuisine, and it's highest rating food.
     * Use TreeSet to store the food in second map. Create a Food object for TreeSet to sort by implementing Comparator.
     *
     * @param foods    name of foods
     * @param cuisines cuisine for each food
     * @param ratings  each food's rating
     */
    public FoodRatings_2353(String[] foods, String[] cuisines, int[] ratings) {
        foodMap = new HashMap<>(foods.length); // this map's size won't change
        sortingMap = new HashMap<>();
        for (int i = 0; i < foods.length; i++) {
            Food food = new Food(foods[i], cuisines[i], ratings[i]);
            foodMap.put(foods[i], food);

            TreeSet<Food> cuisineFoodSet = sortingMap.getOrDefault(food.cuisine, new TreeSet<>((o1, o2) -> {
                if (o1.rating == o2.rating) {
                    return o1.name.compareTo(o2.name);
                }
                return o2.rating - o1.rating;
            }));
            cuisineFoodSet.add(food);
            sortingMap.put(food.cuisine, cuisineFoodSet);
        }
    }

    /**
     * Remove the food rating in sorting map by removing the Food object from TreeSet.
     * Then update the food rating in Food object, put the updated Food object to the TreeSet to sort again.
     *
     * @param food      food to update
     * @param newRating new rating
     */
    public void changeRating(String food, int newRating) {

//        if (!foodMap.containsKey(food) || !sortingMap.containsKey(foodMap.get(food).cuisine)) {
//            return;
//        }

        Food oldFood = foodMap.get(food);
        sortingMap.get(oldFood.cuisine).remove(oldFood);
        oldFood.rating = newRating;
        sortingMap.get(oldFood.cuisine).add(oldFood);
    }

    /**
     * Return the cuisine by
     *
     * @param cuisine cuisine searched for highest rating food
     * @return the name of the highest rating food for the given type of cuisine
     */
    public String highestRated(String cuisine) {
        if (!sortingMap.containsKey(cuisine) || sortingMap.get(cuisine).size() == 0) {
            return null;
        }
        return sortingMap.get(cuisine).first().name;
    }

    /**
     * Customized Food object. This is better when used in TreeSet to sort by rating.
     */
    static class Food {
        String name;
        String cuisine;
        int rating;

        public Food(String name, String cuisine, int rating) {
            this.name = name;
            this.cuisine = cuisine;
            this.rating = rating;
        }
    }
}
