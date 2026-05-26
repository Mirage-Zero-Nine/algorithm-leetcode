package solutions.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Unit tests for {@link SumZero_1304}.
 */
public class SumZero_1304Test {

    private final SumZero_1304 solver = new SumZero_1304();

    @Test
    public void testN1() {
        // n = 1, out = [0]
        int[] result = solver.sumZero(1);
        assertEquals(1, result.length);
        assertEquals(0, result[0]);
    }

    @Test
    public void testN2() {
        // n = 2, out = [1, -1], sum = 0
        int[] result = solver.sumZero(2);
        assertEquals(2, result.length);
        assertEquals(0, result[0] + result[1]);
        assertNotEquals(result[0], result[1]); // unique
    }

    @Test
    public void testN3() {
        // n = 3, out = [2, 0, -2], sum = 0
        int[] result = solver.sumZero(3);
        assertEquals(3, result.length);
        assertEquals(0, result[0] + result[1] + result[2]);
    }

    @Test
    public void testN4() {
        // n = 4, out = [3, 1, -1, -3], sum = 0
        int[] result = solver.sumZero(4);
        assertEquals(4, result.length);
        assertEquals(0, result[0] + result[1] + result[2] + result[3]);
    }

    @Test
    public void testN5() {
        // n = 5, out = [4, 2, 0, -2, -4], sum = 0
        int[] result = solver.sumZero(5);
        assertEquals(5, result.length);
        assertEquals(0, result[0] + result[1] + result[2] + result[3] + result[4]);
    }

    @Test
    public void testAllUnique() {
        // n = 10, all elements should be unique
        int[] result = solver.sumZero(10);
        assertEquals(10, result.length);
        long uniqueCount = java.util.Arrays.stream(result).distinct().count();
        assertEquals(10, uniqueCount);
    }

    @Test
    public void testLargeN() {
        // n = 1000, sum should still be 0
        int[] result = solver.sumZero(1000);
        assertEquals(1000, result.length);
        long sum = java.util.Arrays.stream(result).asLongStream().sum();
        assertEquals(0, sum);
    }

    @Test
    public void testN6() {
        // n = 6, out = [5, 3, 1, -1, -3, -5], sum = 0
        int[] result = solver.sumZero(6);
        assertEquals(6, result.length);
        assertEquals(0, result[0] + result[1] + result[2] + result[3] + result[4] + result[5]);
    }

    @Test
    public void testN0() {
        int[] result = solver.sumZero(0);
        assertEquals(0, result.length);
    }

    @Test
    public void testN7Unique() {
        int[] result = solver.sumZero(7);
        assertEquals(7, result.length);
        long uniqueCount = java.util.Arrays.stream(result).distinct().count();
        assertEquals(7, uniqueCount);
        assertEquals(0, java.util.Arrays.stream(result).sum());
    }

    @Test
    public void testGiantN10000() {
        int n = 10000;
        int[] result = solver.sumZero(n);
        assertEquals(n, result.length);
        long sum = java.util.Arrays.stream(result).asLongStream().sum();
        assertEquals(0, sum);
        long uniqueCount = java.util.Arrays.stream(result).distinct().count();
        assertEquals(n, uniqueCount);
    }
}
