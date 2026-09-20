package solutions.heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KthSmallest_378Test {
    private final KthSmallest_378 solver = new KthSmallest_378();

    @Test public void testBasic() {
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        assertEquals(13, solver.kthSmallest(matrix, 8));
    }

    @Test public void testK1() {
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        assertEquals(1, solver.kthSmallest(matrix, 1));
    }

    @Test public void testSingleRow() {
        int[][] matrix = {{-5}};
        assertEquals(-5, solver.kthSmallest(matrix, 1));
    }

    @Test public void testMinHeapBasic() {
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        assertEquals(13, solver.kthSmallestMinHeap(matrix, 8));
    }

    @Test public void testMinHeapK1() {
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };
        assertEquals(1, solver.kthSmallestMinHeap(matrix, 1));
    }

    @Test public void testKLast() {
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        assertEquals(15, solver.kthSmallest(matrix, 9));
    }

    @Test public void testNegativeValues() {
        int[][] matrix = {
                {-5, -4},
                {-3, -2}
        };
        assertEquals(-4, solver.kthSmallest(matrix, 2));
    }

    @Test public void test2x2Middle() {
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };
        assertEquals(3, solver.kthSmallest(matrix, 3));
    }

    @Test public void testMinHeapLast() {
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        assertEquals(15, solver.kthSmallestMinHeap(matrix, 9));
    }

    @Test public void testGiantMatrix() {
        int n = 50;
        int[][] matrix = new int[n][n];
        int val = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = val++;
        assertEquals(0, solver.kthSmallest(matrix, 1));
        assertEquals(2499, solver.kthSmallest(matrix, 2500));
    }

    @Test public void testDuplicateValues() {
        int[][] matrix = {
                {1, 1, 3},
                {1, 1, 3},
                {2, 2, 4}
        };
        assertEquals(1, solver.kthSmallest(matrix, 4));
    }

    @Test public void testMinHeapDuplicates() { int[][] m = {{1, 1}, {1, 2}}; assertEquals(1, solver.kthSmallestMinHeap(m, 3)); }
    @Test public void testNegativeMinHeap() { int[][] m = {{-5, -4}, {-3, -2}}; assertEquals(-3, solver.kthSmallestMinHeap(m, 3)); }
    @Test public void testAllEqual() { int[][] m = {{7, 7}, {7, 7}}; assertEquals(7, solver.kthSmallest(m, 3)); assertEquals(7, solver.kthSmallestMinHeap(m, 3)); }
    @Test public void testDescendingMagnitude() { int[][] m = {{-10, -5}, {-4, 0}}; assertEquals(-5, solver.kthSmallest(m, 2)); }
    @Test public void testTwoByTwoLastBothMethods() { int[][] m = {{1, 2}, {3, 4}}; assertEquals(4, solver.kthSmallest(m, 4)); assertEquals(4, solver.kthSmallestMinHeap(m, 4)); }
    @Test public void testFourByFourMiddle() { int[][] m = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}; assertEquals(8, solver.kthSmallest(m, 8)); assertEquals(8, solver.kthSmallestMinHeap(m, 8)); }
    @Test public void testLargeNegativeValues() { int[][] m = {{-1000000000, -5}, {-3, 1000000000}}; assertEquals(-5, solver.kthSmallest(m, 2)); }
    @Test public void testDuplicateBoundaryValue() { int[][] m = {{1, 2, 2}, {2, 3, 4}, {5, 6, 7}}; assertEquals(2, solver.kthSmallest(m, 4)); }
    @Test public void testBothMethodsAgree() { int[][] m = {{-3, 0, 8}, {-1, 2, 9}, {4, 6, 10}}; assertEquals(solver.kthSmallest(m, 5), solver.kthSmallestMinHeap(m, 5)); }
    @Test public void testRepeatedInvocation() { solver.kthSmallest(new int[][]{{1}}, 1); assertEquals(9, solver.kthSmallestMinHeap(new int[][]{{9}}, 1)); }
}
