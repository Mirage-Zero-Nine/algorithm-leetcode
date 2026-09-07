package solutions.intervals;

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
@Test
    public void testAllVariantsAgainstSmallCoordinateHeightOracle() {
        java.util.Random random = new java.util.Random(2182026L);
        for (int trial = 0; trial < 100; trial++) {
            int[][] buildings = new int[1 + random.nextInt(12)][3];
            for (int[] building : buildings) {
                building[0] = random.nextInt(20);
                building[1] = building[0] + 1 + random.nextInt(20 - building[0]);
                building[2] = 1 + random.nextInt(12);
            }
            java.util.Arrays.sort(buildings, java.util.Comparator.comparingInt(b -> b[0]));
            List<List<Integer>> expected = new java.util.ArrayList<>();
            int previous = 0;
            for (int x = 0; x <= 20; x++) {
                int height = 0;
                for (int[] b : buildings) if (b[0] <= x && x < b[1]) height = Math.max(height, b[2]);
                if (height != previous) expected.add(List.of(x, height));
                previous = height;
            }
            assertEquals(expected, solver.getSkyline(buildings), "tree trial=" + trial);
            assertEquals(expected, solver.maxHeap(buildings), "max heap trial=" + trial);
            assertEquals(expected, solver.heap(buildings), "heap trial=" + trial);
        }
    }

    @Test
    public void testCoincidentNestedEdgesRevealOnlyVisibleHeight() {
        int[][] buildings = {{0, 4, 3}, {0, 4, 8}, {0, 4, 8}, {4, 7, 5}, {4, 7, 2}};
        assertEquals(List.of(List.of(0, 8), List.of(4, 5), List.of(7, 0)),
                solver.getSkyline(buildings));
    }

    @Test
    public void testGiantDisconnectedBuildingsRetainEveryGroundSegment() {
        int[][] buildings = new int[2000][3];
        List<List<Integer>> expected = new java.util.ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            int height = i % 17 + 1;
            buildings[i] = new int[]{i * 3, i * 3 + 1, height};
            expected.add(List.of(i * 3, height));
            expected.add(List.of(i * 3 + 1, 0));
        }
        assertEquals(expected, solver.getSkyline(buildings));
    }
}
