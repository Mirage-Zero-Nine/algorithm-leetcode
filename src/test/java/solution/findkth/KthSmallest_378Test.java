package solution.findkth;

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
}
