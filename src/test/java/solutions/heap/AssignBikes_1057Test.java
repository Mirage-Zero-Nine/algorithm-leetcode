package solutions.heap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AssignBikes_1057Test {

    private final AssignBikes_1057 test = new AssignBikes_1057();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{1, 0}, test.assignBikes(
            new int[][]{{0, 0}, {2, 1}},
            new int[][]{{1, 2}, {3, 3}}
        ));
        assertArrayEquals(new int[]{0, 2, 1}, test.assignBikes(
            new int[][]{{0, 0}, {1, 1}, {2, 0}},
            new int[][]{{1, 0}, {2, 2}, {2, 1}}
        ));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{0}, test.assignBikes(new int[][]{{0, 0}}, new int[][]{{0, 1}}));
        assertArrayEquals(new int[]{0, 1}, test.assignBikes(
            new int[][]{{0, 0}, {0, 1}},
            new int[][]{{1, 0}, {1, 1}}
        ));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{0, 1, 2, 3}, test.assignBikes(
            new int[][]{{0, 0}, {2, 2}, {4, 4}, {6, 6}},
            new int[][]{{0, 1}, {2, 3}, {4, 5}, {6, 7}, {8, 8}}
        ));
    }

    @Test
    public void testSingleWorkerSingleBike() {
        assertArrayEquals(new int[]{0}, test.assignBikes(new int[][]{{5, 5}}, new int[][]{{5, 5}}));
    }

    @Test
    public void testWorkerAtSamePositionAsBike() {
        assertArrayEquals(new int[]{0, 1}, test.assignBikes(
            new int[][]{{1, 1}, {2, 2}},
            new int[][]{{1, 1}, {2, 2}}
        ));
    }

    @Test
    public void testTieBreakByWorkerIndex() {
        // Two workers equidistant to same bike, lower worker index wins
        assertArrayEquals(new int[]{0, 1}, test.assignBikes(
            new int[][]{{0, 0}, {1, 1}},
            new int[][]{{0, 1}, {1, 0}}
        ));
    }

    @Test
    public void testTieBreakByBikeIndex() {
        // Worker equidistant to two bikes, lower bike index wins
        assertArrayEquals(new int[]{0}, test.assignBikes(
            new int[][]{{0, 0}},
            new int[][]{{1, 0}, {0, 1}}
        ));
    }

    @Test
    public void testThreeWorkersThreeBikes() {
        assertArrayEquals(new int[]{2, 1, 0}, test.assignBikes(
            new int[][]{{0, 0}, {1, 0}, {2, 0}},
            new int[][]{{2, 1}, {1, 1}, {0, 1}}
        ));
    }

    @Test
    public void testMoreBikesThanWorkers() {
        // 1 worker, 3 bikes - picks closest
        assertArrayEquals(new int[]{1}, test.assignBikes(
            new int[][]{{5, 5}},
            new int[][]{{0, 0}, {5, 6}, {9, 9}}
        ));
    }

    @Test
    public void testGiantCase() {
        // 5 workers, 10 bikes
        int[][] workers = {{0, 0}, {100, 100}, {200, 200}, {300, 300}, {400, 400}};
        int[][] bikes = {{0, 1}, {100, 101}, {200, 201}, {300, 301}, {400, 401}, {500, 500}, {600, 600}, {700, 700}, {800, 800}, {900, 900}};
        int[] result = test.assignBikes(workers, bikes);
        // Each worker should get the closest bike
        assertArrayEquals(new int[]{0, 1, 2, 3, 4}, result);
    }

    @Test
    public void testAllWorkersAtOrigin() {
        // Multiple workers at same position, different bikes
        assertArrayEquals(new int[]{0, 1}, test.assignBikes(
            new int[][]{{0, 0}, {0, 0}},
            new int[][]{{1, 0}, {0, 1}}
        ));
    }

    @Test
    public void testDistantWorkerAndBike() {
        assertArrayEquals(new int[]{0}, test.assignBikes(
            new int[][]{{0, 0}},
            new int[][]{{999, 999}}
        ));
    }
}
