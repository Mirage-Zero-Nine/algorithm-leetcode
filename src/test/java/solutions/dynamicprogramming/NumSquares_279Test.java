package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class NumSquares_279Test {

    private final NumSquares_279 test = new NumSquares_279();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.numSquares(12));
        assertEquals(2, test.numSquares(13));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.numSquares(1));
        assertEquals(2, test.numSquares(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.numSquares(100));
        assertEquals(3, test.numSquares(99));
    }

    @Test
    public void testPerfectSquares() {
        assertEquals(1, test.numSquares(4));
        assertEquals(1, test.numSquares(9));
        assertEquals(1, test.numSquares(16));
        assertEquals(1, test.numSquares(25));
    }

    @Test
    public void testSumOfTwoSquares() {
        assertEquals(2, test.numSquares(5));   // 4+1
        assertEquals(2, test.numSquares(8));   // 4+4
        assertEquals(2, test.numSquares(10));  // 9+1
    }

    @Test
    public void testThreeSquares() {
        assertEquals(3, test.numSquares(3));   // 1+1+1
        assertEquals(3, test.numSquares(6));   // 4+1+1
    }

    @Test
    public void testFourSquares() {
        assertEquals(4, test.numSquares(7));   // 4+1+1+1
        assertEquals(4, test.numSquares(15));  // 4+4+4+1+1+1 -> actually 9+4+1+1
    }

    @Test
    public void testOne() {
        assertEquals(1, test.numSquares(1));
    }

    @Test
    public void testMediumValues() {
        assertEquals(2, test.numSquares(18));  // 9+9
        assertEquals(2, test.numSquares(20));  // 16+4
        assertEquals(1, test.numSquares(36));
    }

    @Test
    public void testExhaustiveSmallValues() {
        int[] expected = minimumSquaresByBreadthFirstSearch(500);

        for (int n = 1; n <= 500; n++) {
            assertEquals(expected[n], test.numSquares(n), "n=" + n);
        }
    }

    @Test
    public void testUpperConstraintBoundary() {
        // 10000 = 100^2, perfect square
        assertEquals(1, test.numSquares(10000));
    }

    /**
     * Independent reference implementation: each edge adds one perfect square,
     * so BFS finds the minimum number of summands.
     */
    private int[] minimumSquaresByBreadthFirstSearch(int max) {
        int[] distance = new int[max + 1];
        Arrays.fill(distance, -1);
        distance[0] = 0;

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int sum = queue.remove();
            for (int root = 1; sum + root * root <= max; root++) {
                int next = sum + root * root;
                if (distance[next] == -1) {
                    distance[next] = distance[sum] + 1;
                    queue.add(next);
                }
            }
        }
        return distance;
    }
}
