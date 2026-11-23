package solutions.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.IntStream;

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
    private final Map<String, TreeSet<String>> foodsByCuisine;
    private final Map<String, Integer> ratingByFood;
    private final Map<String, String> cuisineByFood;
    private final Map<String, String> highestRating;

    /**
     * Creates one {@link TreeSet} per cuisine, ordered by descending rating and then ascending
     * food name, so its first entry is always that cuisine's winner. The rating and cuisine maps
     * identify the keys needed for updates; the separate winner map caches each set's first entry
     * for constant-expected-time queries. A food must be removed before its rating changes because
     * the set comparator reads the rating map; after reinsertion, refreshing the cache restores the
     * invariant that every cached winner equals its set's first entry. For {@code n} foods,
     * construction takes expected O(n log(n + 1)) time (hash operations are expected O(1)) and the
     * object retains O(n) space. If hash operations degrade to O(n), construction can take
     * O(n² log(n + 1)) time because map accesses also occur during tree comparisons.
     * <p>
     * LeetCode inputs have 1..20,000 foods, lowercase names of at most 10 characters, ratings
     * from 1 through 100,000,000, and aligned arrays; empty arrays are also supported. The maps
     * and ordered sets retain O(n) entries. The comparator reads current values from
     * {@code ratingByFood}, so an entry must leave its tree before its rating changes.
     * Time complexity: O(n log(n + 1)) expected, where {@code n} is the number of foods; tree
     * insertions do logarithmically many comparisons and hash operations are expected O(1).
     * Worst-case time is O(n² log(n + 1)) if hash operations degrade to O(n).
     * Auxiliary space complexity: O(n) peak transient space for hash-table bucket arrays during
     * resizing; the maps and cuisine sets themselves are retained by this object.
     *
     * @param foods    distinct food names
     * @param cuisines cuisine for each food
     * @param ratings  initial rating for each food
     */
    public FoodRatings_2353(String[] foods, String[] cuisines, int[] ratings) {
        foodsByCuisine = new HashMap<>();
        ratingByFood = new HashMap<>();
        cuisineByFood = new HashMap<>();
        highestRating = new HashMap<>();
        IntStream.range(0, foods.length).forEach(i -> {
            ratingByFood.put(foods[i], ratings[i]);
            cuisineByFood.put(foods[i], cuisines[i]);
            foodsByCuisine.computeIfAbsent(cuisines[i], _ -> new TreeSet<>((first, second) -> {
                // The tree reads current ratings from the map; names make equal ratings distinct and ordered.
                int byRating = Integer.compare(ratingByFood.get(second), ratingByFood.get(first));
                return byRating != 0 ? byRating : first.compareTo(second);
            })).add(foods[i]);
            highestRating.put(cuisines[i], foodsByCuisine.get(cuisines[i]).first());
        });
    }

    /**
     * Removes the food from its ordered set before changing the rating used by the comparator,
     * then reinserts it at its new position and refreshes the cached winner. An unknown food is
     * ignored. Removing before changing the comparator's rating preserves the tree ordering;
     * reinsertion places the food correctly, and reading {@code first()} refreshes the cached
     * winner. With expected O(1) hash operations and {@code k} foods in that cuisine, the expected
     * time is O(log(k + 1)); tree operations use O(log(k + 1)) comparisons even in the worst case. If hash
     * operations degrade to O(n), comparator lookups make the worst-case time O(n log(k + 1)), where
     * {@code n} is the total number of foods. The operation changes only retained maps and sets.
     * <p>
     * The cuisine set, rating map, and cached-winner map remain consistent after a successful
     * update; a missing food leaves all state unchanged. With {@code k} foods in its cuisine, the
     * expected time is O(log(k + 1)); hash operations are expected O(1), and the tree performs
     * logarithmically many comparisons. Worst-case time is O(n log(k + 1)) if hash operations
     * degrade to O(n), where {@code n} is the total food count.
     * Time complexity: O(log(k + 1)) expected, where {@code k} is the size of the food's cuisine set.
     * Auxiliary space complexity: O(1), excluding the retained map and set entries.
     *
     * @param food      food whose rating changes
     * @param newRating replacement rating
     */
    public void changeRating(String food, int newRating) {
        if (!cuisineByFood.containsKey(food)) {
            return;
        }
        String cuisine = cuisineByFood.get(food);
        TreeSet<String> cuisineFoods = foodsByCuisine.get(cuisine);
        cuisineFoods.remove(food);
        ratingByFood.put(food, newRating);
        cuisineFoods.add(food);
        highestRating.put(cuisine, cuisineFoods.first());
    }

    /**
     * Returns the cached winner for a cuisine, or {@code null} when no such cuisine is present.
     * The constructor and every successful update keep this cache equal to the first entry in
     * that cuisine's ordered set. Lookup is expected O(1); under pathological hash collisions it
     * can scan the {@code c} cuisine entries. The returned name is already retained by the object.
     * <p>
     * Time complexity: O(1) expected and O(c) worst case, for lookup in the cuisine cache, where
     * {@code c} is the number of cuisines.
     * Auxiliary space complexity: O(1), excluding the returned reference and retained state.
     *
     * @param cuisine cuisine to query
     * @return highest-rated food name, with lexicographically smaller names breaking ties, or {@code null}
     */
    public String highestRated(String cuisine) {
        return highestRating.get(cuisine);
    }
}
