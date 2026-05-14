package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/07/28 16:38
 * Created with IntelliJ IDEA
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
    public void test() {
        assertEquals("kimchi", test.highestRated("korean"));
        assertEquals("ramen", test.highestRated("japanese"));
        test.changeRating("sushi", 16);
        assertEquals("sushi", test.highestRated("japanese"));
        test.changeRating("ramen", 16);
        assertEquals("ramen", test.highestRated("japanese"));
    }

    @Test
    public void testHighestRatedForSingleCuisine() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"a", "b", "c"},
                new String[]{"x", "x", "x"},
                new int[]{1, 3, 2}
        );
        assertEquals("b", local.highestRated("x"));
    }

    @Test
    public void testTieBreakByLexicographicallySmallerName() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"apple", "banana"},
                new String[]{"fruit", "fruit"},
                new int[]{5, 5}
        );
        assertEquals("apple", local.highestRated("fruit"));
    }

    @Test
    public void testChangeRatingMaintainsLexicographicTieRule() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"curry", "don"},
                new String[]{"asian", "asian"},
                new int[]{10, 8}
        );
        local.changeRating("don", 10);
        assertEquals("curry", local.highestRated("asian"));
    }

    @Test
    public void testChangeRatingCanPromoteDifferentFood() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"udon", "soba", "gyoza"},
                new String[]{"jp", "jp", "jp"},
                new int[]{3, 4, 1}
        );
        local.changeRating("gyoza", 6);
        assertEquals("gyoza", local.highestRated("jp"));
    }

    @Test
    public void testUnknownCuisineReturnsNull() {
        assertEquals(null, test.highestRated("thai"));
    }

    @Test
    public void testRatingDecreaseChangesTopFood() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"x1", "x2"},
                new String[]{"fusion", "fusion"},
                new int[]{20, 19}
        );
        local.changeRating("x1", 18);
        assertEquals("x2", local.highestRated("fusion"));
    }

    @Test
    public void testNegativeRatingsStillSortedCorrectly() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"n1", "n2", "n3"},
                new String[]{"cold", "cold", "cold"},
                new int[]{-5, -2, -3}
        );
        assertEquals("n2", local.highestRated("cold"));
        local.changeRating("n1", 0);
        assertEquals("n1", local.highestRated("cold"));
    }

    @Test
    public void testMultipleCuisinesIsolation() {
        FoodRatings_2353 local = new FoodRatings_2353(
                new String[]{"pasta", "pizza", "taco", "burrito"},
                new String[]{"italian", "italian", "mexican", "mexican"},
                new int[]{7, 9, 8, 6}
        );
        assertEquals("pizza", local.highestRated("italian"));
        assertEquals("taco", local.highestRated("mexican"));
        local.changeRating("burrito", 10);
        assertEquals("burrito", local.highestRated("mexican"));
        assertEquals("pizza", local.highestRated("italian"));
    }

    @Test
    public void testGiantCaseManyFoodsAndUpdates() {
        int size = 1000;
        String[] foods = new String[size];
        String[] cuisines = new String[size];
        int[] ratings = new int[size];
        for (int i = 0; i < size; i++) {
            foods[i] = "food" + i;
            cuisines[i] = "mega";
            ratings[i] = i;
        }

        FoodRatings_2353 local = new FoodRatings_2353(foods, cuisines, ratings);
        assertEquals("food999", local.highestRated("mega"));

        local.changeRating("food10", 5000);
        assertEquals("food10", local.highestRated("mega"));
    }
}
