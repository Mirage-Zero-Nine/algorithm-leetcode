package solution.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RemoveStones_947Test {

    @Test
    public void testHappyCases() {
        assertEquals(5, new RemoveStones_947().removeStones(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}}));
        assertEquals(3, new RemoveStones_947().removeStones(new int[][]{{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, new RemoveStones_947().removeStones(new int[][]{{0, 0}}));
        assertEquals(0, new RemoveStones_947().removeStones(new int[][]{{0, 0}, {1, 1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, new RemoveStones_947().removeStones(new int[][]{{0, 0}, {0, 1}, {1, 0}}));
    }

    @Test
    public void testTwoStonesShareRow() {
        assertEquals(1, new RemoveStones_947().removeStones(new int[][]{{0, 0}, {0, 5}}));
    }

    @Test
    public void testTwoStonesShareColumn() {
        assertEquals(1, new RemoveStones_947().removeStones(new int[][]{{0, 0}, {5, 0}}));
    }

    @Test
    public void testThreeDisconnected() {
        assertEquals(0, new RemoveStones_947().removeStones(new int[][]{{0, 0}, {1, 1}, {2, 2}}));
    }

    @Test
    public void testAllInSameRow() {
        assertEquals(4, new RemoveStones_947().removeStones(new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}}));
    }

    @Test
    public void testAllInSameColumn() {
        assertEquals(3, new RemoveStones_947().removeStones(new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}}));
    }

    @Test
    public void testLShape() {
        // (0,0),(0,1),(0,2),(1,0),(2,0) - all connected via row 0 or col 0
        assertEquals(4, new RemoveStones_947().removeStones(new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 0}, {2, 0}}));
    }

    @Test
    public void testGiantCase() {
        // 100 stones all in row 0 -> one connected component -> 99 removals
        int[][] stones = new int[100][2];
        for (int i = 0; i < 100; i++) stones[i] = new int[]{0, i};
        assertEquals(99, new RemoveStones_947().removeStones(stones));
    }
}
