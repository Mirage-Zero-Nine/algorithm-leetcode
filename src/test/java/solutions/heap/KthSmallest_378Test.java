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
}
