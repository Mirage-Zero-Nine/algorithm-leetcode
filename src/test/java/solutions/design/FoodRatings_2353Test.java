package solutions.design;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Stateful tests for the LeetCode 2353 food-rating API.
 *
 * <p>The expected result for generated operation streams is computed with independent maps and a
 * fresh sort, rather than by duplicating the production {@code TreeSet} implementation.</p>
 *
 * @author BorisMirage
 */
public class FoodRatings_2353Test {
    private FoodRatings_2353 test;

    @BeforeEach
    public void setUp() {
        test = new FoodRatings_2353(
                new String[]{"kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"},
                new String[]{"korean", "japanese", "japanese", "greek", "japanese", "korean"},
                new int[]{9, 12, 8, 15, 14, 7}
        );
    }

    @Test
    public void officialExampleSequence() {
        assertEquals("kimchi", test.highestRated("korean"));
        assertEquals("ramen", test.highestRated("japanese"));
        test.changeRating("sushi", 16);
        assertEquals("sushi", test.highestRated("japanese"));
        test.changeRating("ramen", 16);
        assertEquals("ramen", test.highestRated("japanese"));
    }

    @Test
    public void selectsHighestInitialRatingInEachCuisine() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"a", "b", "c", "d"},
                new String[]{"x", "x", "y", "y"},
                new int[]{1, 3, 8, 5}
        );
        assertEquals("b", local.highestRated("x"));
        assertEquals("c", local.highestRated("y"));
    }

    @Test
    public void tiesUseLexicographicallySmallerFoodName() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"banana", "apple", "apricot"},
                new String[]{"fruit", "fruit", "fruit"},
                new int[]{5, 5, 5}
        );
        assertEquals("apple", local.highestRated("fruit"));
    }

    @Test
    public void ratingChangeMaintainsLexicographicTieRule() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"curry", "don", "udon"},
                new String[]{"asian", "asian", "asian"},
                new int[]{10, 8, 7}
        );
        local.changeRating("don", 10);
        assertEquals("curry", local.highestRated("asian"));
        local.changeRating("curry", 9);
        assertEquals("don", local.highestRated("asian"));
        local.changeRating("don", 9);
        assertEquals("curry", local.highestRated("asian"));
    }

    @Test
    public void promotionAndDemotionChangeTheTopFood() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"udon", "soba", "gyoza"},
                new String[]{"jp", "jp", "jp"},
                new int[]{3, 4, 1}
        );
        local.changeRating("gyoza", 6);
        assertEquals("gyoza", local.highestRated("jp"));
        local.changeRating("gyoza", 2);
        assertEquals("soba", local.highestRated("jp"));
        local.changeRating("soba", 1);
        local.changeRating("udon", 1);
        assertEquals("gyoza", local.highestRated("jp"));
    }

    @Test
    public void decreasingTopFoodRevealsNextCandidate() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"x1", "x2", "x3"},
                new String[]{"fusion", "fusion", "fusion"},
                new int[]{20, 19, 18}
        );
        local.changeRating("x1", 18);
        assertEquals("x2", local.highestRated("fusion"));
        local.changeRating("x2", 17);
        assertEquals("x1", local.highestRated("fusion"));
    }

    @Test
    public void repeatedUpdatesToOneFoodAreNotContaminatedByOldTreeSetEntries() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"alpha", "beta"},
                new String[]{"c", "c"},
                new int[]{4, 4}
        );
        local.changeRating("alpha", 9);
        assertEquals("alpha", local.highestRated("c"));
        local.changeRating("alpha", 1);
        assertEquals("beta", local.highestRated("c"));
        local.changeRating("alpha", 4);
        assertEquals("alpha", local.highestRated("c"));
        local.changeRating("alpha", 4);
        assertEquals("alpha", local.highestRated("c"));
    }

    @Test
    public void cuisinesRemainIndependentDuringUpdates() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"pasta", "pizza", "taco", "burrito"},
                new String[]{"italian", "italian", "mexican", "mexican"},
                new int[]{7, 9, 8, 6}
        );
        local.changeRating("burrito", 10);
        assertEquals("pizza", local.highestRated("italian"));
        assertEquals("burrito", local.highestRated("mexican"));
        local.changeRating("pizza", 1);
        assertEquals("pasta", local.highestRated("italian"));
        assertEquals("burrito", local.highestRated("mexican"));
    }

    @Test
    public void unknownCuisineReturnsNull() {
        assertEquals(null, test.highestRated("thai"));
        assertEquals(null, test.highestRated(""));
    }

    @Test
    public void repeatedQueriesDoNotChangeState() {
        assertEquals("ramen", test.highestRated("japanese"));
        assertEquals("ramen", test.highestRated("japanese"));
        test.changeRating("sushi", 14);
        assertEquals("ramen", test.highestRated("japanese"));
        assertEquals("ramen", test.highestRated("japanese"));
    }

    @Test
    public void constructorDoesNotRetainMutableInputArrayState() {
        String[] foods = {"kimchi", "miso"};
        String[] cuisines = {"korean", "japanese"};
        int[] ratings = {9, 12};
        FoodRatings_2353 local = new FoodRatings_2353(foods, cuisines, ratings);
        foods[0] = "changed";
        cuisines[0] = "japanese";
        ratings[0] = 100;

        assertEquals("kimchi", local.highestRated("korean"));
        assertEquals("miso", local.highestRated("japanese"));
        assertArrayEquals(new String[]{"changed", "miso"}, foods);
        assertArrayEquals(new String[]{"japanese", "japanese"}, cuisines);
        assertArrayEquals(new int[]{100, 12}, ratings);
    }

    @Test
    public void separateInstancesDoNotShareRatings() {
        FoodRatings_2353 first = new FoodRatings_2353(
                new String[]{"same", "other"}, new String[]{"c", "c"}, new int[]{1, 2});
        FoodRatings_2353 second = new FoodRatings_2353(
                new String[]{"same", "other"}, new String[]{"c", "c"}, new int[]{10, 3});
        first.changeRating("same", 100);
        assertEquals("same", first.highestRated("c"));
        assertEquals("same", second.highestRated("c"));
        second.changeRating("same", 0);
        assertEquals("other", second.highestRated("c"));
        assertEquals("same", first.highestRated("c"));
    }

    @Test
    public void lexicalPrefixOrderingIsHandledCorrectly() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"aa", "a", "ab", "b"},
                new String[]{"c", "c", "c", "c"},
                new int[]{5, 5, 5, 5}
        );
        assertEquals("a", local.highestRated("c"));
        local.changeRating("a", 4);
        assertEquals("aa", local.highestRated("c"));
        local.changeRating("aa", 4);
        assertEquals("ab", local.highestRated("c"));
    }

    @Test
    public void officialRatingBoundariesAreOrdered() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"low", "high", "middle"},
                new String[]{"c", "c", "c"},
                new int[]{1, 100_000_000, 50_000_000}
        );
        assertEquals("high", local.highestRated("c"));
        local.changeRating("high", 1);
        assertEquals("middle", local.highestRated("c"));
        local.changeRating("low", 100_000_000);
        assertEquals("low", local.highestRated("c"));
    }

    @Test
    public void singletonCuisinesAndMultipleSingletonsAreSupported() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"one", "two", "three"},
                new String[]{"a", "b", "c"},
                new int[]{1, 2, 3}
        );
        assertEquals("one", local.highestRated("a"));
        assertEquals("two", local.highestRated("b"));
        assertEquals("three", local.highestRated("c"));
        local.changeRating("two", 1);
        assertEquals("two", local.highestRated("b"));
    }

    @Test
    public void allFoodsTiedInOneCuisineRemainLexicallySortedAfterUpdates() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"delta", "charlie", "bravo", "alpha"},
                new String[]{"same", "same", "same", "same"},
                new int[]{7, 7, 7, 7}
        );
        assertEquals("alpha", local.highestRated("same"));
        local.changeRating("alpha", 6);
        assertEquals("bravo", local.highestRated("same"));
        local.changeRating("alpha", 7);
        assertEquals("alpha", local.highestRated("same"));
        local.changeRating("alpha", 8);
        assertEquals("alpha", local.highestRated("same"));
    }

    @Test
    public void implementationSupportsNegativeRatingsWithoutChangingTieRules() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"n1", "n2", "n3"},
                new String[]{"cold", "cold", "cold"},
                new int[]{-5, -2, -3}
        );
        assertEquals("n2", local.highestRated("cold"));
        local.changeRating("n1", 0);
        assertEquals("n1", local.highestRated("cold"));
        local.changeRating("n1", -3);
        local.changeRating("n2", -3);
        assertEquals("n1", local.highestRated("cold"));
    }

    @Test
    public void smallInterleavedSequenceMatchesIndependentMapAndSortOracle() {
        String[] foods = {"s0", "s1", "s2", "s3", "s4"};
        String[] cuisines = {"a", "a", "b", "b", "a"};
        int[] initial = {4, 9, 2, 8, 9};
        FoodRatings_2353 local = new FoodRatings_2353(foods, cuisines, initial);
        Map<String, String> cuisineByFood = new HashMap<>();
        Map<String, Integer> ratings = new HashMap<>();
        for (int i = 0; i < foods.length; i++) {
            cuisineByFood.put(foods[i], cuisines[i]);
            ratings.put(foods[i], initial[i]);
        }

        assertMatchesOracle(local, cuisineByFood, ratings, "a", "b");
        local.changeRating("s0", 9);
        ratings.put("s0", 9);
        assertMatchesOracle(local, cuisineByFood, ratings, "a", "b");
        local.changeRating("s1", 1);
        ratings.put("s1", 1);
        assertMatchesOracle(local, cuisineByFood, ratings, "a", "b");
        local.changeRating("s3", 9);
        ratings.put("s3", 9);
        assertMatchesOracle(local, cuisineByFood, ratings, "a", "b");
        local.changeRating("s4", 1);
        ratings.put("s4", 1);
        assertMatchesOracle(local, cuisineByFood, ratings, "a", "b");
    }

    @Test
    public void seededRandomizedOperationsMatchIndependentOracle() {
        Random random = new Random(2_353_2026L);
        int foodCount = 80;
        String[] foods = new String[foodCount];
        String[] cuisines = new String[foodCount];
        int[] initialRatings = new int[foodCount];
        Map<String, String> cuisineByFood = new HashMap<>();
        Map<String, Integer> ratings = new HashMap<>();
        for (int i = 0; i < foodCount; i++) {
            foods[i] = String.format("food%02d", i);
            cuisines[i] = "cuisine" + (i % 5);
            initialRatings[i] = 1 + random.nextInt(100_000_000);
            cuisineByFood.put(foods[i], cuisines[i]);
            ratings.put(foods[i], initialRatings[i]);
        }
        FoodRatings_2353 local = new FoodRatings_2353(foods, cuisines, initialRatings);

        for (int operation = 0; operation < 4_000; operation++) {
            int foodIndex = random.nextInt(foodCount);
            if (random.nextInt(5) < 3) {
                int newRating = 1 + random.nextInt(100_000_000);
                local.changeRating(foods[foodIndex], newRating);
                ratings.put(foods[foodIndex], newRating);
            } else {
                String cuisine = "cuisine" + random.nextInt(5);
                assertEquals(expectedTop(cuisineByFood, ratings, cuisine), local.highestRated(cuisine),
                        "operation " + operation + " for " + cuisine);
            }
        }
        assertMatchesOracle(local, cuisineByFood, ratings,
                "cuisine0", "cuisine1", "cuisine2", "cuisine3", "cuisine4");
    }

    @Test
    public void maximumFoodCountAndOperationCountStayCorrect() {
        int size = 10_000;
        String[] foods = new String[size];
        String[] cuisines = new String[size];
        int[] ratings = new int[size];
        for (int i = 0; i < size; i++) {
            foods[i] = String.format("food%05d", i);
            cuisines[i] = "cuisine" + (i % 10);
            ratings[i] = 1 + (i % 100);
        }
        FoodRatings_2353 local = new FoodRatings_2353(foods, cuisines, ratings);

        // 10,000 updates plus 9,990 queries is within the 20,000-call problem limit.
        for (String food : foods) {
            local.changeRating(food, 100_000_000);
        }
        for (int operation = 0; operation < 9_990; operation++) {
            int cuisineNumber = operation % 10;
            assertEquals(String.format("food%05d", cuisineNumber),
                    local.highestRated("cuisine" + cuisineNumber));
        }
    }

    @Test
    public void maximumFoodAndCuisineNameLengthsAreAccepted() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"abcdefghij", "abcdefghi", "abcdefghik"},
                new String[]{"abcdefghij", "abcdefghij", "abcdefghik"},
                new int[]{100_000_000, 100_000_000, 1}
        );
        assertEquals("abcdefghi", local.highestRated("abcdefghij"));
        assertEquals("abcdefghik", local.highestRated("abcdefghik"));
        local.changeRating("abcdefghik", 100_000_000);
        assertEquals("abcdefghik", local.highestRated("abcdefghik"));
    }

    @Test
    public void independentFreshInputsCanBeUpdatedInDifferentOrders() {
        FoodRatings_2353 first = new FoodRatings_2353(
                new String[]{"a", "b", "c"}, new String[]{"x", "x", "x"}, new int[]{3, 2, 1});
        FoodRatings_2353 second = new FoodRatings_2353(
                new String[]{"a", "b", "c"}, new String[]{"x", "x", "x"}, new int[]{3, 2, 1});
        first.changeRating("c", 10);
        first.changeRating("a", 0);
        second.changeRating("b", 20);
        second.changeRating("c", 19);
        assertEquals("c", first.highestRated("x"));
        assertEquals("b", second.highestRated("x"));
    }

    private static void assertMatchesOracle(FoodRatings_2353 actual,
                                            Map<String, String> cuisineByFood,
                                            Map<String, Integer> ratings,
                                            String... cuisines) {
        for (String cuisine : cuisines) {
            assertEquals(expectedTop(cuisineByFood, ratings, cuisine), actual.highestRated(cuisine), cuisine);
        }
    }

    /** Independently sorts all foods in the requested cuisine by rating descending, then name ascending. */
    private static String expectedTop(Map<String, String> cuisineByFood,
                                      Map<String, Integer> ratings,
                                      String cuisine) {
        List<String> candidates = new ArrayList<>();
        for (Map.Entry<String, String> entry : cuisineByFood.entrySet()) {
            if (entry.getValue().equals(cuisine)) {
                candidates.add(entry.getKey());
            }
        }
        candidates.sort(Comparator.<String>comparingInt(food -> ratings.get(food))
                .reversed()
                .thenComparing(Comparator.naturalOrder()));
        return candidates.isEmpty() ? null : candidates.get(0);
    }
}
