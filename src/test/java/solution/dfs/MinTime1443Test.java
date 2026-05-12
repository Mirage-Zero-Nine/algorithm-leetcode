package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class MinTime1443Test {

    @Test
    public void testHappyCases() {
        assertEquals(8, new MinTime_1443().minTime(7, new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, List.of(false, false, true, false, true, true, false)));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, new MinTime_1443().minTime(1, new int[][]{}, List.of(false)));
        assertEquals(2, new MinTime_1443().minTime(2, new int[][]{{0, 1}}, List.of(false, true)));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, new MinTime_1443().minTime(4, new int[][]{{0, 1}, {1, 2}, {1, 3}}, List.of(false, true, true, true)));
    }
}
