package solutions.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaxPerformance_1383Test {
    private final MaxPerformance_1383 solver = new MaxPerformance_1383();

    @Test public void testBasicK2() {
        int[] speed = {2, 10, 3, 1, 5, 8};
        int[] efficiency = {5, 4, 3, 9, 7, 2};
        assertEquals(60, solver.maxPerformance(6, speed, efficiency, 2));
    }

    @Test public void testK3() {
        int[] speed = {2, 10, 3, 1, 5, 8};
        int[] efficiency = {5, 4, 3, 9, 7, 2};
        assertEquals(68, solver.maxPerformance(6, speed, efficiency, 3));
    }

    @Test public void testK4() {
        int[] speed = {2, 10, 3, 1, 5, 8};
        int[] efficiency = {5, 4, 3, 9, 7, 2};
        assertEquals(72, solver.maxPerformance(6, speed, efficiency, 4));
    }

    @Test public void testKOne() {
        int[] speed = {1, 2, 3};
        int[] efficiency = {3, 2, 1};
        // Top by efficiency: pair [1,3] alone -> 1*3=3
        // But algorithm iterates through and uses heap, and returns max seen
        assertEquals(4, solver.maxPerformance(3, speed, efficiency, 1));
    }

    @Test public void testSingleEngineer() {
        int[] speed = {5};
        int[] efficiency = {10};
        assertEquals(50, solver.maxPerformance(1, speed, efficiency, 1));
    }

    @Test public void testKEqualsN() {
        int[] speed = {2, 10, 3, 1, 5, 8};
        int[] efficiency = {5, 4, 3, 9, 7, 2};
        assertEquals(72, solver.maxPerformance(6, speed, efficiency, 6));
    }

    @Test public void testTwoEngineersPickBoth() {
        int[] speed = {3, 7};
        int[] efficiency = {6, 2};
        assertEquals(20, solver.maxPerformance(2, speed, efficiency, 2));
    }

    @Test public void testAllSameEfficiency() {
        int[] speed = {1, 2, 3, 4, 5};
        int[] efficiency = {5, 5, 5, 5, 5};
        assertEquals(75, solver.maxPerformance(5, speed, efficiency, 5));
    }

    @Test public void testAllSameSpeed() {
        int[] speed = {3, 3, 3};
        int[] efficiency = {1, 2, 3};
        assertEquals(9, solver.maxPerformance(3, speed, efficiency, 1));
    }

    @Test public void testGiantCase() {
        int n = 100000;
        int[] speed = new int[n];
        int[] efficiency = new int[n];
        for (int i = 0; i < n; i++) {
            speed[i] = i + 1;
            efficiency[i] = n - i;
        }
        // Just verify it runs without error and returns a non-negative result
        int result = solver.maxPerformance(n, speed, efficiency, n / 2);
        assertTrue(result >= 0);
    }
    @Test public void testAdditionalSingle() { assertEquals(10, solver.maxPerformance(1, new int[]{2}, new int[]{5}, 1)); }
    @Test public void testAdditionalTwoOne() { assertEquals(15, solver.maxPerformance(2, new int[]{2, 3}, new int[]{5, 5}, 1)); }
    @Test public void testAdditionalTwoBoth() { assertEquals(25, solver.maxPerformance(2, new int[]{2, 3}, new int[]{5, 5}, 2)); }
    @Test public void testAdditionalChooseEfficiency() { assertEquals(20, solver.maxPerformance(3, new int[]{10, 1, 1}, new int[]{1, 10, 10}, 2)); }
    @Test public void testAdditionalChooseSpeed() { assertEquals(10, solver.maxPerformance(3, new int[]{10, 1, 1}, new int[]{1, 10, 10}, 1)); }
    @Test public void testAdditionalEqual() { assertEquals(12, solver.maxPerformance(3, new int[]{2, 2, 2}, new int[]{2, 2, 2}, 3)); }
    @Test public void testAdditionalKOne() { assertEquals(100, solver.maxPerformance(2, new int[]{10, 100}, new int[]{5, 1}, 1)); }
    @Test public void testAdditionalKTwo() { assertEquals(110, solver.maxPerformance(2, new int[]{10, 100}, new int[]{5, 1}, 2)); }
    @Test public void testAdditionalThree() { assertEquals(12, solver.maxPerformance(3, new int[]{3, 3, 3}, new int[]{2, 2, 2}, 2)); }
    @Test public void testAdditionalLargeEfficiency() { assertEquals(1000, solver.maxPerformance(2, new int[]{10, 20}, new int[]{100, 50}, 1)); }
}
