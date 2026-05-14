package solution.intervals;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetSkyline_218Test {
    private final GetSkyline_218 solver = new GetSkyline_218();

    @Test public void testBasicTreeSet() {
        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        List<List<Integer>> expected = List.of(
                List.of(2, 10), List.of(3, 15), List.of(7, 12),
                List.of(12, 0), List.of(15, 10), List.of(20, 8), List.of(24, 0)
        );
        assertEquals(expected, solver.getSkyline(buildings));
    }

    @Test public void testEmpty() {
        List<List<Integer>> res = solver.getSkyline(new int[][]{});
        assertTrue(res.isEmpty());
    }

    @Test public void testSingleBuilding() {
        int[][] buildings = {{0, 5, 10}};
        List<List<Integer>> expected = List.of(List.of(0, 10), List.of(5, 0));
        assertEquals(expected, solver.getSkyline(buildings));
    }

    @Test public void testMaxHeapApproach() {
        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        List<List<Integer>> expected = List.of(
                List.of(2, 10), List.of(3, 15), List.of(7, 12),
                List.of(12, 0), List.of(15, 10), List.of(20, 8), List.of(24, 0)
        );
        assertEquals(expected, solver.maxHeap(buildings));
    }

    @Test public void testHeapApproach() {
        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        List<List<Integer>> expected = List.of(
                List.of(2, 10), List.of(3, 15), List.of(7, 12),
                List.of(12, 0), List.of(15, 10), List.of(20, 8), List.of(24, 0)
        );
        assertEquals(expected, solver.heap(buildings));
    }

    @Test public void testNull() {
        assertTrue(solver.getSkyline(null).isEmpty());
    }

    @Test public void testTwoSeparateBuildings() {
        int[][] buildings = {{1, 3, 5}, {5, 8, 7}};
        List<List<Integer>> expected = List.of(
                List.of(1, 5), List.of(3, 0), List.of(5, 7), List.of(8, 0)
        );
        assertEquals(expected, solver.getSkyline(buildings));
    }

    @Test public void testTwoOverlappingSameHeight() {
        int[][] buildings = {{1, 5, 10}, {3, 8, 10}};
        List<List<Integer>> expected = List.of(List.of(1, 10), List.of(8, 0));
        assertEquals(expected, solver.getSkyline(buildings));
    }

    @Test public void testNestedBuildings() {
        int[][] buildings = {{1, 10, 5}, {3, 7, 8}};
        List<List<Integer>> expected = List.of(
                List.of(1, 5), List.of(3, 8), List.of(7, 5), List.of(10, 0)
        );
        assertEquals(expected, solver.getSkyline(buildings));
    }

    @Test public void testAdjacentBuildings() {
        int[][] buildings = {{1, 3, 5}, {3, 6, 5}};
        List<List<Integer>> expected = List.of(List.of(1, 5), List.of(6, 0));
        assertEquals(expected, solver.getSkyline(buildings));
    }

    @Test public void testGiantCase() {
        int size = 1000;
        int[][] buildings = new int[size][2 + 1];
        for (int i = 0; i < size; i++) {
            buildings[i] = new int[]{i, i + 2, 10};
        }
        List<List<Integer>> result = solver.getSkyline(buildings);
        // First point should be [0, 10], last should be [size+1, 0]
        assertEquals(List.of(0, 10), result.get(0));
        assertEquals(List.of(size + 1, 0), result.get(result.size() - 1));
    }
}
