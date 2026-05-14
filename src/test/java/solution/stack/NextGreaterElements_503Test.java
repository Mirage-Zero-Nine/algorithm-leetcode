package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class NextGreaterElements_503Test {
    private final NextGreaterElements_503 solver = new NextGreaterElements_503();

    @Test public void testExample() {
        assertArrayEquals(new int[]{2, -1, 2}, solver.nextGreaterElements(new int[]{1, 2, 1}));
    }

    @Test public void testAllSame() {
        assertArrayEquals(new int[]{-1, -1, -1}, solver.nextGreaterElements(new int[]{1, 1, 1}));
    }

    @Test public void testIncreasing() {
        assertArrayEquals(new int[]{2, 3, 4, -1}, solver.nextGreaterElements(new int[]{1, 2, 3, 4}));
    }

    @Test public void testDecreasing() {
        // circular: 4->none, 3->4, 2->4(wraps), 1->4(wraps)
        assertArrayEquals(new int[]{-1, 4, 4, 4}, solver.nextGreaterElements(new int[]{4, 3, 2, 1}));
    }

    @Test public void testSingle() {
        assertArrayEquals(new int[]{-1}, solver.nextGreaterElements(new int[]{5}));
    }

    @Test public void testCircularWrap() {
        assertArrayEquals(new int[]{5, 6, 6, 8, -1}, solver.nextGreaterElements(new int[]{1, 5, 3, 6, 8}));
    }

    @Test public void testEmpty() {
        assertArrayEquals(new int[]{}, solver.nextGreaterElements(new int[]{}));
    }

    @Test public void testTwoElements() {
        assertArrayEquals(new int[]{2, -1}, solver.nextGreaterElements(new int[]{1, 2}));
    }

    @Test public void testTwoElementsReverse() {
        assertArrayEquals(new int[]{-1, 2}, solver.nextGreaterElements(new int[]{2, 1}));
    }

    @Test public void testNegativeNumbers() {
        assertArrayEquals(new int[]{-1, -1, -1}, solver.nextGreaterElements(new int[]{-3, -3, -3}));
    }

    @Test public void testGiant() {
        int n = 10000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i;
        int[] result = solver.nextGreaterElements(nums);
        // last element (max) has no greater -> -1
        assertEquals(-1, result[n - 1]);
        // first element -> 1
        assertEquals(1, result[0]);
    }
}
