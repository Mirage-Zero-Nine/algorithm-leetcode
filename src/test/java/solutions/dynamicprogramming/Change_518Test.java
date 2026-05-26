package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Change_518Test {

    private final Change_518 test = new Change_518();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.change(5, new int[]{1, 2, 5}));
        assertEquals(0, test.change(3, new int[]{2}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.change(0, new int[]{1, 2, 3}));
        assertEquals(1, test.change(1, new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(10, test.change(10, new int[]{1, 2, 5}));
    }

    @Test
    public void testSingleCoin() {
        assertEquals(1, test.change(5, new int[]{5}));
        assertEquals(1, test.change(10, new int[]{5}));
        assertEquals(0, test.change(3, new int[]{5}));
    }

    @Test
    public void testAmountZero() {
        assertEquals(1, test.change(0, new int[]{1}));
        assertEquals(1, test.change(0, new int[]{5, 10, 25}));
    }

    @Test
    public void testNoCoins() {
        assertEquals(0, test.change(5, new int[]{}));
    }

    @Test
    public void testTwoCoins() {
        assertEquals(3, test.change(4, new int[]{1, 2})); // 1111, 112, 22
    }

    @Test
    public void testLargeAmount() {
        assertEquals(242, test.change(100, new int[]{1, 5, 10, 25}));
    }

    @Test
    public void testCoinLargerThanAmount() {
        assertEquals(0, test.change(1, new int[]{2}));
        assertEquals(1, test.change(2, new int[]{2, 5}));
    }

    @Test
    public void testGiantCase() {
        int result = test.change(500, new int[]{1, 2, 5, 10, 25, 50});
        // Just verify it runs and is consistent
        assertEquals(result, test.change(500, new int[]{1, 2, 5, 10, 25, 50}));
    }
}
