package solution.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AssignBikes_1066Test {

    private final AssignBikes_1066 test = new AssignBikes_1066();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.assignBikes(new int[][]{{0, 0}, {2, 1}}, new int[][]{{1, 2}, {3, 3}}));
        assertEquals(3, test.assignBikes(new int[][]{{0, 0}, {1, 1}}, new int[][]{{1, 0}, {2, 2}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.assignBikes(new int[][]{{0, 0}}, new int[][]{{0, 1}}));
        assertEquals(0, test.assignBikes(new int[][]{{0, 0}}, new int[][]{{0, 0}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.assignBikes(
            new int[][]{{0, 0}, {2, 2}, {4, 4}},
            new int[][]{{0, 1}, {2, 3}, {4, 5}, {8, 8}}
        ));
    }

    @Test
    public void testSingleWorkerSingleBikeSamePosition() {
        assertEquals(0, test.assignBikes(new int[][]{{5, 5}}, new int[][]{{5, 5}}));
    }

    @Test
    public void testSingleWorkerMultipleBikes() {
        // Worker at (0,0), bikes at (1,0), (0,3), (5,5). Closest is (1,0) with distance 1
        assertEquals(1, test.assignBikes(new int[][]{{0, 0}}, new int[][]{{1, 0}, {0, 3}, {5, 5}}));
    }

    @Test
    public void testTwoWorkersSwapOptimal() {
        // Workers at (0,0) and (1,1), bikes at (1,0) and (0,1)
        // Assign w0->b1(dist 1), w1->b0(dist 1) = 2 OR w0->b0(dist 1), w1->b1(dist 1) = 2
        assertEquals(2, test.assignBikes(new int[][]{{0, 0}, {1, 1}}, new int[][]{{1, 0}, {0, 1}}));
    }

    @Test
    public void testWorkersAndBikesAtSameSpot() {
        assertEquals(0, test.assignBikes(new int[][]{{3, 3}, {7, 7}}, new int[][]{{3, 3}, {7, 7}}));
    }

    @Test
    public void testThreeWorkersThreeBikes() {
        assertEquals(3, test.assignBikes(
            new int[][]{{0, 0}, {1, 0}, {2, 0}},
            new int[][]{{0, 1}, {1, 1}, {2, 1}}
        ));
    }

    @Test
    public void testWorkersFarFromBikes() {
        assertEquals(20, test.assignBikes(new int[][]{{0, 0}}, new int[][]{{10, 10}}));
    }

    @Test
    public void testMoreBikesThanWorkers() {
        // 2 workers, 4 bikes - should pick optimal assignment
        assertEquals(2, test.assignBikes(
            new int[][]{{0, 0}, {5, 5}},
            new int[][]{{0, 1}, {5, 6}, {10, 10}, {20, 20}}
        ));
    }

    @Test
    public void testGiantCase() {
        // 3 workers, 8 bikes spread out
        int[][] workers = {{0, 0}, {100, 100}, {200, 200}};
        int[][] bikes = {{1, 1}, {99, 99}, {201, 201}, {50, 50}, {150, 150}, {250, 250}, {300, 300}, {400, 400}};
        int result = test.assignBikes(workers, bikes);
        // w0->b0(2), w1->b1(2), w2->b2(2) = 6
        assertEquals(6, result);
    }

    @Test
    public void testAllWorkersAllBikesSameDistance() {
        // All workers equidistant from all bikes
        assertEquals(4, test.assignBikes(
            new int[][]{{0, 0}, {2, 2}},
            new int[][]{{1, 1}, {1, 1}}
        ));
    }
}
