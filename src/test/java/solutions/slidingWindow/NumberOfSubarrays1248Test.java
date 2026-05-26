package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NumberOfSubarrays1248Test {

    private final NumberOfSubarrays_1248 test = new NumberOfSubarrays_1248();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3));
        assertEquals(0, test.numberOfSubarrays(new int[]{2, 4, 6}, 1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.numberOfSubarrays(new int[]{1}, 1));
        assertEquals(0, test.numberOfSubarrays(new int[]{2}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(16, test.numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2));
    }

    @Test
    public void testAllOdd() {
        assertEquals(2, test.numberOfSubarrays(new int[]{1, 1, 1, 1}, 3));
    }

    @Test
    public void testAllEven() {
        assertEquals(0, test.numberOfSubarrays(new int[]{2, 4, 6, 8}, 1));
    }

    @Test
    public void testKEqualsArrayOddCount() {
        assertEquals(1, test.numberOfSubarrays(new int[]{1, 3, 5}, 3));
    }

    @Test
    public void testKLargerThanOddCount() {
        assertEquals(0, test.numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 3));
    }

    @Test
    public void testSingleOddElement() {
        assertEquals(1, test.numberOfSubarrays(new int[]{3}, 1));
    }

    @Test
    public void testEvensAroundSingleOdd() {
        assertEquals(9, test.numberOfSubarrays(new int[]{2, 2, 1, 2, 2}, 1));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[100000];
        for (int i = 0; i < 100000; i++) arr[i] = (i % 2 == 0) ? 1 : 2;
        // 50000 odd numbers, k=1: each odd at index 2i has 1 even before (at 2i-1) except first, and 1 even after except last
        int result = test.numberOfSubarrays(arr, 1);
        assertTrue(result > 0);
    }
}
