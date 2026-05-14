package solution.intervals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class IntervalIntersection_986Test {
    private final IntervalIntersection_986 solver = new IntervalIntersection_986();

    @Test public void testBasic() {
        int[][] first = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] second = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        int[][] expected = {{1, 2}, {5, 5}, {8, 10}, {15, 23}, {24, 24}, {25, 25}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testNoIntersection() {
        int[][] first = {{1, 3}};
        int[][] second = {{5, 10}};
        int[][] expected = {};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testFullOverlap() {
        int[][] first = {{1, 10}};
        int[][] second = {{2, 5}};
        int[][] expected = {{2, 5}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testEmptyFirst() {
        int[][] first = {};
        int[][] second = {{1, 2}};
        int[][] expected = {};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testEndpointOverlap() {
        int[][] first = {{1, 5}};
        int[][] second = {{5, 10}};
        int[][] expected = {{5, 5}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testEmptySecond() {
        int[][] first = {{1, 2}};
        int[][] second = {};
        int[][] expected = {};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testBothEmpty() {
        int[][] first = {};
        int[][] second = {};
        int[][] expected = {};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testIdenticalIntervals() {
        int[][] first = {{1, 5}, {10, 15}};
        int[][] second = {{1, 5}, {10, 15}};
        int[][] expected = {{1, 5}, {10, 15}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testSinglePointIntervals() {
        int[][] first = {{3, 3}, {7, 7}};
        int[][] second = {{3, 5}, {6, 8}};
        int[][] expected = {{3, 3}, {7, 7}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testMultipleOverlapsWithOneInterval() {
        int[][] first = {{0, 100}};
        int[][] second = {{1, 2}, {5, 10}, {20, 30}};
        int[][] expected = {{1, 2}, {5, 10}, {20, 30}};
        assertArrayEquals(expected, solver.intervalIntersection(first, second));
    }

    @Test public void testGiantCase() {
        int size = 1000;
        int[][] first = new int[size][2];
        int[][] second = new int[size][2];
        for (int i = 0; i < size; i++) {
            first[i] = new int[]{i * 4, i * 4 + 2};
            second[i] = new int[]{i * 4 + 1, i * 4 + 3};
        }
        int[][] result = solver.intervalIntersection(first, second);
        // Each pair overlaps at [i*4+1, i*4+2]
        for (int i = 0; i < size; i++) {
            assertArrayEquals(new int[]{i * 4 + 1, i * 4 + 2}, result[i]);
        }
    }
}
