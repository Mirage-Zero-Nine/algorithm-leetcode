package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UniquePathsWithObstacles63Test {

    private final UniquePathsWithObstacles_63 test = new UniquePathsWithObstacles_63();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.uniquePathsWithObstacles(new int[][]{{1}}));
        assertEquals(1, test.uniquePathsWithObstacles(new int[][]{{0}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.uniquePathsWithObstacles(new int[][]{{0, 0}, {1, 0}}));
    }
}
