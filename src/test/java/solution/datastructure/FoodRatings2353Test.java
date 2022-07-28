package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/07/28 16:38
 * Created with IntelliJ IDEA
 */

public class FoodRatings2353Test {
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
}