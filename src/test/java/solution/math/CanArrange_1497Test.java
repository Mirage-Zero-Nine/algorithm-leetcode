package solution.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanArrange_1497Test {

    private final CanArrange_1497 test = new CanArrange_1497();

    @Test
    public void testHappyCases() {
        assertTrue(test.canArrange(new int[]{1, 2, 3, 4, 5, 10, 6, 7, 8, 9}, 5));
        assertTrue(test.canArrange(new int[]{1, 2, 3, 4, 5, 6}, 7));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.canArrange(new int[]{1, 2, 3, 4, 5, 6}, 10));
        assertTrue(test.canArrange(new int[]{-1, 1}, 2));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.canArrange(new int[]{2, 4, 6, 8, 10, 12}, 2));
        assertTrue(test.canArrange(new int[]{1, 3, 5, 7, 9, 11}, 4));
    }

    @Test
    public void testAllZeros() {
        assertTrue(test.canArrange(new int[]{0, 0, 0, 0}, 3));
    }

    @Test
    public void testKEqualsOne() {
        // any pair sum is divisible by 1
        assertTrue(test.canArrange(new int[]{7, 13, 99, 1}, 1));
    }

    @Test
    public void testNegativeNumbers() {
        assertFalse(test.canArrange(new int[]{-1, -3, 4, 6}, 5));
        assertTrue(test.canArrange(new int[]{-1, -9, 4, 6}, 5));
    }

    @Test
    public void testCannotArrange() {
        assertFalse(test.canArrange(new int[]{1, 1, 1, 1}, 3));
    }

    @Test
    public void testTwoElements() {
        assertTrue(test.canArrange(new int[]{3, 7}, 5));
        assertFalse(test.canArrange(new int[]{3, 7}, 3));
    }

    @Test
    public void testAllSameValue() {
        assertTrue(test.canArrange(new int[]{5, 5, 5, 5}, 5));
    }

    @Test
    public void testGiantCase() {
        // 1000 elements, pairs (i, k-i) for i=1..500
        int k = 1000;
        int[] arr = new int[1000];
        for (int i = 0; i < 500; i++) {
            arr[2 * i] = i + 1;
            arr[2 * i + 1] = k - (i + 1);
        }
        assertTrue(test.canArrange(arr, k));
    }
}
