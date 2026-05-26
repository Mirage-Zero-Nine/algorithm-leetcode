package solutions.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NearestValidPoint_1779Test {
    private final NearestValidPoint_1779 solution = new NearestValidPoint_1779();

    @Test
    void testBasic() {
        assertEquals(2, solution.nearestValidPoint(3, 4, new int[][]{{1, 2}, {3, 1}, {2, 4}, {2, 3}, {4, 4}}));
    }

    @Test
    void testNoValid() {
        assertEquals(-1, solution.nearestValidPoint(3, 4, new int[][]{{2, 3}}));
    }

    @Test
    void testMultipleValid() {
        assertEquals(0, solution.nearestValidPoint(3, 4, new int[][]{{3, 4}}));
    }

    @Test
    void testSameDistance() {
        assertEquals(0, solution.nearestValidPoint(3, 4, new int[][]{{2, 4}, {3, 3}}));
    }

    @Test
    void testFarPoints() {
        assertEquals(1, solution.nearestValidPoint(1, 1, new int[][]{{1, 10}, {1, 2}}));
    }

    @Test
    void testExactMatch() {
        assertEquals(0, solution.nearestValidPoint(5, 5, new int[][]{{5, 5}, {5, 6}, {6, 5}}));
    }

    @Test
    void testOnlyYMatch() {
        assertEquals(1, solution.nearestValidPoint(3, 4, new int[][]{{1, 4}, {2, 4}, {5, 4}}));
    }

    @Test
    void testOnlyXMatch() {
        assertEquals(1, solution.nearestValidPoint(3, 4, new int[][]{{3, 100}, {3, 5}}));
    }

    @Test
    void testAllInvalid() {
        assertEquals(-1, solution.nearestValidPoint(3, 4, new int[][]{{1, 2}, {5, 6}, {7, 8}}));
    }

    @Test
    void testSinglePoint() {
        assertEquals(0, solution.nearestValidPoint(1, 1, new int[][]{{1, 1}}));
    }

    @Test
    void testGiantCase() {
        int[][] points = new int[10000][2];
        for (int i = 0; i < 10000; i++) {
            points[i] = new int[]{i, i};
        }
        points[5000] = new int[]{5, 999};
        int result = solution.nearestValidPoint(5, 5, points);
        assertEquals(5, result);
    }
}
