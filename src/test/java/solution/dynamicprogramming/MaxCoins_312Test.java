package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxCoins_312Test {

    private final MaxCoins_312 test = new MaxCoins_312();

    @Test
    public void testHappyCases() {
        assertEquals(167, test.maxCoins(new int[]{3, 1, 5, 8}));
        assertEquals(10, test.maxCoins(new int[]{1, 5}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.maxCoins(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3630, test.maxCoins(new int[]{8, 2, 6, 8, 9, 8, 1, 4, 1, 5, 3, 0, 7, 7, 0, 4, 2, 2, 5}));
    }

    @Test
    public void testSingleLargeValue() {
        assertEquals(100, test.maxCoins(new int[]{100}));
    }

    @Test
    public void testTwoEqual() {
        assertEquals(12, test.maxCoins(new int[]{3, 3}));
    }

    @Test
    public void testThreeBalloons() {
        assertEquals(88, test.maxCoins(new int[]{2, 4, 8}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(4, test.maxCoins(new int[]{1, 1, 1, 1}));
    }

    @Test
    public void testZeroValues() {
        assertEquals(0, test.maxCoins(new int[]{0, 0, 0}));
    }

    @Test
    public void testIncreasing() {
        assertEquals(110, test.maxCoins(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[50];
        for (int i = 0; i < 50; i++) {
            arr[i] = (i % 5) + 1;
        }
        int result = test.maxCoins(arr);
        // Just verify it runs and returns a positive value
        assertEquals(true, result > 0);
    }
}
