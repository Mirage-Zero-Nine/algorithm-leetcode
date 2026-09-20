package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import org.junit.jupiter.api.Test;

/** Contract and regression tests for both minimum-knight-move implementations. */
public class MinKnightMoves_1197Test {

    private final MinKnightMoves_1197 test = new MinKnightMoves_1197();

    @Test
    public void testOfficialExamplesAndMinimums() {
        assertBoth(2, 1, 1);
        assertBoth(5, 5, 4);
        assertBoth(0, 0, 0);
        assertBoth(1, 0, 3);
        assertBoth(0, 1, 3);
        assertBoth(1, 1, 2);
        assertBoth(2, 0, 2);
        assertBoth(2, 2, 4);
    }

    @Test
    public void testAxisTargets() {
        assertBoth(3, 0, 3);
        assertBoth(4, 0, 2);
        assertBoth(5, 0, 3);
        assertBoth(6, 0, 4);
        assertBoth(10, 0, 6);
        assertBoth(0, 10, 6);
    }

    @Test
    public void testDiagonalTargets() {
        assertBoth(3, 3, 2);
        assertBoth(4, 4, 4);
        assertBoth(5, 5, 4);
        assertBoth(8, 8, 6);
        assertBoth(12, 12, 8);
    }

    @Test
    public void testNearOriginTraps() {
        int[][] points = {{1, 1}, {1, 2}, {2, 1}, {2, 2}, {3, 1}, {3, 2}, {3, 3},
                {4, 1}, {4, 2}, {4, 3}, {4, 4}};
        for (int[] point : points) {
            assertEquals(referenceBfs(point[0], point[1]), test.minKnightMoves(point[0], point[1]));
            assertEquals(referenceBfs(point[0], point[1]), test.formula(point[0], point[1]));
        }
    }

    @Test
    public void testNegativeCoordinatesAreSymmetric() {
        assertBoth(-2, -1, 1);
        assertBoth(-5, 5, 4);
        assertBoth(5, -5, 4);
        assertBoth(-8, 3, 5);
        assertBoth(10, -7, 7);
        assertBoth(-12, -12, 8);
    }

    @Test
    public void testAllQuadrantsHaveTheSameDistance() {
        int[][] points = {{1, 2}, {4, 7}, {8, 3}, {11, 9}, {20, 14}};
        for (int[] point : points) {
            int expected = referenceBfs(point[0], point[1]);
            assertEquals(expected, test.minKnightMoves(point[0], point[1]));
            assertEquals(expected, test.minKnightMoves(-point[0], point[1]));
            assertEquals(expected, test.minKnightMoves(point[0], -point[1]));
            assertEquals(expected, test.minKnightMoves(-point[0], -point[1]));
            assertEquals(expected, test.formula(-point[0], -point[1]));
        }
    }

    @Test
    public void testCoordinateSwapSymmetry() {
        int[][] points = {{0, 6}, {2, 9}, {4, 7}, {8, 3}, {13, 20}, {21, 13}};
        for (int[] point : points) {
            assertEquals(test.minKnightMoves(point[0], point[1]), test.minKnightMoves(point[1], point[0]));
            assertEquals(test.formula(point[0], point[1]), test.formula(point[1], point[0]));
        }
    }

    @Test
    public void testSmallGridAgainstIndependentOracle() {
        for (int x = -8; x <= 8; x++) {
            for (int y = -8; y <= 8; y++) {
                int expected = referenceBfs(x, y);
                assertEquals(expected, test.minKnightMoves(x, y), "BFS at (" + x + "," + y + ")");
                assertEquals(expected, test.formula(x, y), "formula at (" + x + "," + y + ")");
            }
        }
    }

    @Test
    public void testLargerMixedSignsAgainstIndependentOracle() {
        int[][] points = {{9, 4}, {-9, 4}, {4, -9}, {-9, -4}, {15, 2}, {-15, -2},
                {17, 11}, {-17, 11}, {19, 19}, {-20, 13}, {24, 7}, {-25, -18}};
        for (int[] point : points) {
            int expected = referenceBfs(point[0], point[1]);
            assertEquals(expected, test.minKnightMoves(point[0], point[1]));
            assertEquals(expected, test.formula(point[0], point[1]));
        }
    }

    @Test
    public void testFormulaMatchesIndependentOracleOnSeededCoordinates() {
        int state = 0x13579BDF;
        for (int i = 0; i < 100; i++) {
            state = state * 1103515245 + 12345;
            int x = (state >>> 1) % 31 - 15;
            state = state * 1103515245 + 12345;
            int y = (state >>> 1) % 31 - 15;
            int expected = referenceBfs(x, y);
            assertEquals(expected, test.minKnightMoves(x, y));
            assertEquals(expected, test.formula(x, y));
        }
    }

    @Test
    public void testRepeatedCallsDoNotLeakBfsState() {
        assertEquals(1, test.minKnightMoves(2, 1));
        assertEquals(8, test.minKnightMoves(12, 12));
        assertEquals(0, test.minKnightMoves(0, 0));
        assertEquals(3, test.minKnightMoves(-1, 0));
        assertEquals(7, test.minKnightMoves(10, -7));
    }

    @Test
    public void testRepeatedFormulaCallsDoNotLeakState() {
        assertEquals(1, test.formula(2, 1));
        assertEquals(8, test.formula(12, 12));
        assertEquals(0, test.formula(0, 0));
        assertEquals(3, test.formula(-1, 0));
        assertEquals(7, test.formula(10, -7));
    }

    @Test
    public void testBoundaryCoordinatesOnEachAxis() {
        assertEquals(150, test.minKnightMoves(300, 0));
        assertEquals(150, test.minKnightMoves(0, -300));
        assertEquals(150, test.formula(-300, 0));
        assertEquals(150, test.formula(0, 300));
    }

    @Test
    public void testBoundaryDiagonalCoordinates() {
        assertEquals(100, test.minKnightMoves(150, 150));
        assertEquals(100, test.minKnightMoves(-150, -150));
        assertEquals(100, test.formula(150, -150));
    }

    @Test
    public void testBoundaryAsymmetricCoordinates() {
        assertEquals(150, test.minKnightMoves(299, 1));
        assertEquals(150, test.minKnightMoves(-299, 1));
        assertEquals(150, test.formula(-299, -1));
    }

    @Test
    public void testLargeCoordinatesUseTheFormulaAndBfsConsistently() {
        int[][] points = {{60, 60}, {60, 0}, {60, 59}, {101, 37}, {149, 150}, {250, 17}};
        for (int[] point : points) {
            int expected = referenceBfs(point[0], point[1]);
            assertEquals(expected, test.minKnightMoves(point[0], point[1]));
            assertEquals(expected, test.formula(point[0], point[1]));
        }
    }

    @Test
    public void testZeroAndOneCoordinatesAcrossSigns() {
        assertEquals(0, test.minKnightMoves(0, 0));
        assertEquals(3, test.minKnightMoves(-1, 0));
        assertEquals(3, test.minKnightMoves(0, -1));
        assertEquals(2, test.minKnightMoves(-1, -1));
        assertEquals(2, test.formula(0, -2));
        assertEquals(2, test.formula(-2, 0));
    }

    @Test
    public void testBfsResultIsIndependentOfPriorFormulaCalls() {
        test.formula(150, 150);
        test.formula(-17, 11);
        assertEquals(referenceBfs(25, 18), test.minKnightMoves(25, 18));
        assertEquals(referenceBfs(-25, -18), test.minKnightMoves(-25, -18));
    }

    @Test
    public void testFormulaResultIsIndependentOfPriorBfsCalls() {
        test.minKnightMoves(25, 18);
        test.minKnightMoves(-300, 0);
        assertEquals(referenceBfs(17, 11), test.formula(17, 11));
        assertEquals(referenceBfs(-17, -11), test.formula(-17, -11));
    }

    @Test
    public void testIndependentOracleCoversTheOldIncorrectOneOneExpectation() {
        assertEquals(2, referenceBfs(1, 1));
        assertEquals(2, test.minKnightMoves(1, 1));
        assertEquals(2, test.formula(1, 1));
    }

    private void assertBoth(int x, int y, int expected) {
        assertEquals(expected, test.minKnightMoves(x, y), "BFS at (" + x + "," + y + ")");
        assertEquals(expected, test.formula(x, y), "formula at (" + x + "," + y + ")");
    }

    /** Independent shortest-path oracle on a finite box safely larger than the target. */
    private int referenceBfs(int targetX, int targetY) {
        int x = Math.abs(targetX);
        int y = Math.abs(targetY);
        int bound = Math.max(x, y) + 8;
        int size = bound * 2 + 1;
        int[][] distance = new int[size][size];
        for (int[] row : distance) {
            Arrays.fill(row, -1);
        }
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{bound, bound});
        distance[bound][bound] = 0;
        int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};
        while (!queue.isEmpty()) {
            int[] current = queue.remove();
            int currentX = current[0] - bound;
            int currentY = current[1] - bound;
            if (currentX == x && currentY == y) {
                return distance[current[0]][current[1]];
            }
            for (int i = 0; i < dx.length; i++) {
                int nextX = current[0] + dx[i];
                int nextY = current[1] + dy[i];
                if (nextX >= 0 && nextX < size && nextY >= 0 && nextY < size
                        && distance[nextX][nextY] == -1) {
                    distance[nextX][nextY] = distance[current[0]][current[1]] + 1;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }
        throw new AssertionError("finite oracle box was too small");
    }
}
