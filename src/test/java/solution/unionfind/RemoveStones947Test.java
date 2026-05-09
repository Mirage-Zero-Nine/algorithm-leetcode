package solution.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RemoveStones947Test {

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
}
