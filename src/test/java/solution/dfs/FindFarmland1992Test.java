package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindFarmland1992Test {

    private final FindFarmland_1992 test = new FindFarmland_1992();

    @Test
    public void testHappyCases() {
        int[][] result = test.findFarmland(new int[][]{{1, 0, 0}, {0, 1, 1}, {0, 1, 1}});
        assertEquals(2, result.length);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.findFarmland(new int[][]{{0}}).length);
        assertArrayEquals(new int[][]{{0, 0, 0, 0}}, test.findFarmland(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        int[][] result = test.findFarmland(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
        assertEquals(2, result.length);
    }
}
