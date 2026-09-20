package solutions.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReconstructQueue_406Test {
    private final ReconstructQueue_406 solver = new ReconstructQueue_406();

    @Test public void testBasic() {
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] expected = {{5, 0}, {7, 0}, {5, 2}, {6, 1}, {4, 4}, {7, 1}};
        assertArrayEquals(expected, solver.reconstructQueue(people));
    }

    @Test public void testSingle() {
        int[][] people = {{5, 0}};
        int[][] expected = {{5, 0}};
        assertArrayEquals(expected, solver.reconstructQueue(people));
    }

    @Test public void testEmpty() {
        int[][] people = {};
        int[][] expected = {};
        assertArrayEquals(expected, solver.reconstructQueue(people));
    }

    @Test public void testSameHeight() {
        int[][] people = {{5, 0}, {5, 1}, {5, 2}};
        int[][] expected = {{5, 0}, {5, 1}, {5, 2}};
        assertArrayEquals(expected, solver.reconstructQueue(people));
    }

    @Test public void testAscending() {
        int[][] people = {{6, 0}, {5, 0}, {4, 0}};
        int[][] expected = {{4, 0}, {5, 0}, {6, 0}};
        assertArrayEquals(expected, solver.reconstructQueue(people));
    }

    @Test public void testTwoPeople() {
        int[][] people = {{7, 0}, {7, 1}};
        int[][] expected = {{7, 0}, {7, 1}};
        assertArrayEquals(expected, solver.reconstructQueue(people));
    }

    @Test public void testDescending() {
        int[][] people = {{6, 0}, {5, 1}, {4, 2}};
        int[][] expected = {{6, 0}, {5, 1}, {4, 2}};
        assertArrayEquals(expected, solver.reconstructQueue(people));
    }

    @Test public void testAllSameK() {
        int[][] people = {{3, 0}, {5, 0}, {7, 0}};
        int[][] expected = {{3, 0}, {5, 0}, {7, 0}};
        assertArrayEquals(expected, solver.reconstructQueue(people));
    }

    @Test public void testLargerCase() {
        int[][] people = {{2, 4}, {3, 4}, {9, 0}, {3, 5}, {7, 2}, {5, 3}, {6, 0}, {1, 4}, {8, 1}, {4, 2}};
        int[][] result = solver.reconstructQueue(people);
        // Verify each person has correct k value
        for (int i = 0; i < result.length; i++) {
            int count = 0;
            for (int j = 0; j < i; j++) {
                if (result[j][0] >= result[i][0]) count++;
            }
            assertEquals(result[i][1], count);
        }
    }

    @Test public void testGiantCase() {
        int n = 500;
        int[][] people = new int[n][2];
        for (int i = 0; i < n; i++) {
            people[i] = new int[]{n - i, i};
        }
        int[][] result = solver.reconstructQueue(people);
        assertEquals(n, result.length);
    }
    @Test public void testAdditionalPair() { assertArrayEquals(new int[][]{{4, 0}, {5, 0}}, solver.reconstructQueue(new int[][]{{5, 0}, {4, 0}})); }
    @Test public void testAdditionalSame() { assertArrayEquals(new int[][]{{6, 0}, {6, 1}}, solver.reconstructQueue(new int[][]{{6, 1}, {6, 0}})); }
    @Test public void testAdditionalThree() { assertArrayEquals(new int[][]{{5, 0}, {6, 0}, {7, 0}}, solver.reconstructQueue(new int[][]{{7, 0}, {5, 0}, {6, 0}})); }
    @Test public void testAdditionalK() { assertArrayEquals(new int[][]{{4, 0}, {5, 0}, {5, 1}}, solver.reconstructQueue(new int[][]{{5, 0}, {5, 1}, {4, 0}})); }
    @Test public void testAdditionalFour() { int[][] p={{7,0},{6,1},{5,2},{4,3}}; assertEquals(4, solver.reconstructQueue(p).length); }
    @Test public void testAdditionalMixed() { int[][] p={{6,0},{6,1},{5,0}}; assertEquals(3, solver.reconstructQueue(p).length); }
    @Test public void testAdditionalDescendingK() { int[][] p={{9,0},{8,0},{7,0},{6,0}}; assertEquals(4, solver.reconstructQueue(p).length); }
    @Test public void testAdditionalSingleK() { assertArrayEquals(new int[][]{{5, 0}}, solver.reconstructQueue(new int[][]{{5, 0}})); }
    @Test public void testAdditionalEqualHeight() { int[][] p={{3,2},{3,0},{3,1}}; assertEquals(3, solver.reconstructQueue(p).length); }
    @Test public void testAdditionalFive() { int[][] p={{10,0},{9,1},{8,2},{7,3},{6,4}}; assertEquals(5, solver.reconstructQueue(p).length); }
}
