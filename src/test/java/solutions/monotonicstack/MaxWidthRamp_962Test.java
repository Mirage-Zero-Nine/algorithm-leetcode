package solutions.monotonicstack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaxWidthRamp_962Test {
    private final MaxWidthRamp_962 solver = new MaxWidthRamp_962();

    @Test public void testExample1() {
        assertEquals(4, solver.maxWidthRamp(new int[]{6, 0, 8, 2, 1, 5}));
    }

    @Test public void testExample2() {
        assertEquals(7, solver.maxWidthRamp(new int[]{9, 8, 1, 0, 1, 9, 4, 0, 4, 1}));
    }

    @Test public void testNoRamp() {
        assertEquals(0, solver.maxWidthRamp(new int[]{5, 4, 3, 2, 1}));
    }

    @Test public void testAllEqual() {
        assertEquals(3, solver.maxWidthRamp(new int[]{1, 1, 1, 1}));
    }

    @Test public void testTwoElements() {
        assertEquals(1, solver.maxWidthRamp(new int[]{1, 2}));
    }

    @Test public void testNull() {
        assertEquals(0, solver.maxWidthRamp(null));
    }

    @Test public void testSingleElement() {
        assertEquals(0, solver.maxWidthRamp(new int[]{5}));
    }

    @Test public void testIncreasing() {
        assertEquals(4, solver.maxWidthRamp(new int[]{1, 2, 3, 4, 5}));
    }

    @Test public void testFirstAndLast() {
        assertEquals(4, solver.maxWidthRamp(new int[]{1, 0, 0, 0, 1}));
    }

    @Test public void testGiant() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) arr[i] = i;
        assertEquals(9999, solver.maxWidthRamp(arr));
    }
}
