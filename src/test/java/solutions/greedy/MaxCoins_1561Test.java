package solutions.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaxCoins_1561Test {
    private final MaxCoins_1561 solution = new MaxCoins_1561();

    @Test
    void testBasic() {
        assertEquals(9, solution.maxCoins(new int[]{2, 4, 1, 2, 7, 8}));
    }

    @Test
    void testLargerArray() {
        assertEquals(4, solution.maxCoins(new int[]{2, 4, 5}));
    }

    @Test
    void testAllSame() {
        assertEquals(18, solution.maxCoins(new int[]{9, 8, 7, 6, 5, 1, 2, 3, 4}));
    }

    @Test
    void testSmallArray() {
        assertEquals(1, solution.maxCoins(new int[]{1, 1, 1}));
    }

    @Test
    void testDescending() {
        assertEquals(16, solution.maxCoins(new int[]{10, 9, 8, 7, 6, 5}));
    }

    @Test
    void testAscending() {
        assertEquals(16, solution.maxCoins(new int[]{5, 6, 7, 8, 9, 10}));
    }

    @Test
    void testAllZeros() {
        assertEquals(0, solution.maxCoins(new int[]{0, 0, 0}));
    }

    @Test
    void testLargeValues() {
        assertEquals(3, solution.maxCoins(new int[]{1, 3, 5}));
    }

    @Test
    void testTwelveElements() {
        // sorted: 1,2,3,4,5,6,7,8,9,10,11,12; Bob gets 1,2,3,4; you get 5,7,9,11 = 32
        assertEquals(32, solution.maxCoins(new int[]{12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}));
    }

    @Test
    void testGiantArray() {
        int n = 99999; // must be multiple of 3
        int[] piles = new int[n];
        for (int i = 0; i < n; i++) piles[i] = i + 1;
        // sorted: 1..99999, Bob gets bottom 1/3 (33333), you and Alice alternate on top 2/3
        // you get every other starting from index 33333: indices 33333,33335,...
        long expected = 0;
        for (int i = n / 3; i < n; i += 2) expected += (i + 1);
        assertEquals((int) expected, solution.maxCoins(piles));
    }
}
