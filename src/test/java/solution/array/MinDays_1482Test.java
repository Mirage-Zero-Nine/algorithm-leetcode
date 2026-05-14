package solution.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinDays_1482Test {
    private final MinDays_1482 solution = new MinDays_1482();

    @Test
    void testBasic() {
        assertEquals(3, solution.minDays(new int[]{1, 10, 3, 10, 2}, 3, 1));
    }

    @Test
    void testImpossible() {
        assertEquals(-1, solution.minDays(new int[]{1, 10, 3, 10, 2}, 3, 2));
    }

    @Test
    void testLargeK() {
        assertEquals(12, solution.minDays(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3));
    }

    @Test
    void testSingleBouquet() {
        assertEquals(9, solution.minDays(new int[]{1, 10, 2, 9, 3, 8, 4, 7, 5, 6}, 4, 2));
    }

    @Test
    void testMinDays() {
        assertEquals(1000000000, solution.minDays(new int[]{1000000000, 1000000000}, 1, 1));
    }

    @Test
    void testAllSameDay() {
        assertEquals(5, solution.minDays(new int[]{5, 5, 5, 5, 5}, 2, 2));
    }

    @Test
    void testSingleFlower() {
        assertEquals(1, solution.minDays(new int[]{1}, 1, 1));
    }

    @Test
    void testImpossibleTooFewFlowers() {
        assertEquals(-1, solution.minDays(new int[]{1, 2, 3}, 2, 2));
    }

    @Test
    void testExactFit() {
        assertEquals(3, solution.minDays(new int[]{1, 2, 3, 1, 2, 3}, 2, 3));
    }

    @Test
    void testGiantCase() {
        int[] bloomDay = new int[100000];
        for (int i = 0; i < 100000; i++) {
            bloomDay[i] = i + 1;
        }
        assertEquals(100000, solution.minDays(bloomDay, 1, 100000));
    }
}
