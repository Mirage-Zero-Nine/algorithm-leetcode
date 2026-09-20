package solutions.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaxNumberOfApples_1196Test {
    private final MaxNumberOfApples_1196 solution = new MaxNumberOfApples_1196();

    @Test
    void testBasic() {
        assertEquals(4, solution.maxNumberOfApples(new int[]{100, 200, 150, 1000}));
    }

    @Test
    void testAllFit() {
        assertEquals(5, solution.maxNumberOfApples(new int[]{900, 950, 800, 1000, 700, 800}));
    }

    @Test
    void testSingleApple() {
        assertEquals(1, solution.maxNumberOfApples(new int[]{5000}));
    }

    @Test
    void testTooHeavy() {
        assertEquals(0, solution.maxNumberOfApples(new int[]{6000}));
    }

    @Test
    void testSmallApples() {
        assertEquals(10, solution.maxNumberOfApples(new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 100}));
    }

    @Test
    void testEmptyArray() {
        assertEquals(0, solution.maxNumberOfApples(new int[]{}));
    }

    @Test
    void testExactCapacity() {
        assertEquals(5, solution.maxNumberOfApples(new int[]{1000, 1000, 1000, 1000, 1000}));
    }

    @Test
    void testOneOverCapacity() {
        assertEquals(4, solution.maxNumberOfApples(new int[]{1000, 1000, 1000, 1000, 1001}));
    }

    @Test
    void testAllWeightOne() {
        int[] arr = new int[5001];
        java.util.Arrays.fill(arr, 1);
        assertEquals(5000, solution.maxNumberOfApples(arr));
    }

    @Test
    void testMixedWeights() {
        assertEquals(3, solution.maxNumberOfApples(new int[]{1, 2, 3, 5000}));
    }

    @Test
    void testGiantCase() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) arr[i] = 1;
        assertEquals(5000, solution.maxNumberOfApples(arr));
    }
    @Test void testAdditionalZero() { assertEquals(0, solution.maxNumberOfApples(new int[]{5001})); }
    @Test void testAdditionalTwo() { assertEquals(2, solution.maxNumberOfApples(new int[]{1000, 2000})); }
    @Test void testAdditionalExact() { assertEquals(1, solution.maxNumberOfApples(new int[]{5000, 5000})); }
    @Test void testAdditionalMixed() { assertEquals(3, solution.maxNumberOfApples(new int[]{1500, 1500, 1500, 6000})); }
    @Test void testAdditionalSmall() { assertEquals(5, solution.maxNumberOfApples(new int[]{1, 2, 3, 4, 5})); }
    @Test void testAdditionalCapacity() { assertEquals(2, solution.maxNumberOfApples(new int[]{2000, 2000, 2000, 2000})); }
    @Test void testAdditionalLarge() { assertEquals(1, solution.maxNumberOfApples(new int[]{4999, 4999, 4999})); }
    @Test void testAdditionalUnordered() { assertEquals(2, solution.maxNumberOfApples(new int[]{3000, 1000, 2000})); }
    @Test void testAdditionalOne() { assertEquals(1, solution.maxNumberOfApples(new int[]{1})); }
    @Test void testAdditionalZeros() { assertEquals(3, solution.maxNumberOfApples(new int[]{0, 0, 0})); }
}
