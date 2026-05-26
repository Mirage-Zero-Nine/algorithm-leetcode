package solutions.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinimumCost_1135Test {

    private final MinimumCost_1135 test = new MinimumCost_1135();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.minimumCost(3, new int[][]{{1, 2, 5}, {1, 3, 6}, {2, 3, 1}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.minimumCost(4, new int[][]{{1, 2, 3}, {3, 4, 4}}));
        assertEquals(0, test.minimumCost(1, new int[][]{}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.minimumCost(4, new int[][]{{1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {1, 4, 10}}));
    }

    @Test
    public void testTwoCities() {
        assertEquals(5, test.minimumCost(2, new int[][]{{1, 2, 5}}));
    }

    @Test
    public void testTwoCitiesMultipleEdges() {
        assertEquals(3, test.minimumCost(2, new int[][]{{1, 2, 5}, {1, 2, 3}}));
    }

    @Test
    public void testAllCitiesAlreadyConnected() {
        assertEquals(3, test.minimumCost(3, new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 10}}));
    }

    @Test
    public void testImpossibleDisconnected() {
        assertEquals(-1, test.minimumCost(3, new int[][]{{1, 2, 5}}));
    }

    @Test
    public void testSingleCityNoEdges() {
        assertEquals(0, test.minimumCost(1, new int[][]{}));
    }

    @Test
    public void testFiveCitiesLinear() {
        assertEquals(10, test.minimumCost(5, new int[][]{{1, 2, 1}, {2, 3, 2}, {3, 4, 3}, {4, 5, 4}}));
    }

    @Test
    public void testGiantCase() {
        int n = 500;
        int[][] connections = new int[n - 1][3];
        for (int i = 0; i < n - 1; i++) {
            connections[i] = new int[]{i + 1, i + 2, 1};
        }
        assertEquals(n - 1, test.minimumCost(n, connections));
    }
}
