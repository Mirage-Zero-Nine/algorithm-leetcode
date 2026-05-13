package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MinTime_1443Test {

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

    @Test
    public void testNoApples() {
        assertEquals(0, new MinTime_1443().minTime(7, new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, List.of(false, false, false, false, false, false, false)));
    }

    @Test
    public void testAllApples() {
        assertEquals(12, new MinTime_1443().minTime(7, new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, List.of(true, true, true, true, true, true, true)));
    }

    @Test
    public void testOnlyRootHasApple() {
        assertEquals(0, new MinTime_1443().minTime(3, new int[][]{{0, 1}, {0, 2}}, List.of(true, false, false)));
    }

    @Test
    public void testLinearTree() {
        // 0 - 1 - 2 - 3 - 4, apple at 4
        assertEquals(8, new MinTime_1443().minTime(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}, List.of(false, false, false, false, true)));
    }

    @Test
    public void testAppleAtMiddle() {
        // 0 - 1 - 2 - 3, apple at 2
        assertEquals(4, new MinTime_1443().minTime(4, new int[][]{{0, 1}, {1, 2}, {2, 3}}, List.of(false, false, true, false)));
    }

    @Test
    public void testSingleNodeWithApple() {
        assertEquals(0, new MinTime_1443().minTime(1, new int[][]{}, List.of(true)));
    }

    @Test
    public void testGiantTree() {
        // Star graph: 0 connected to 1..99, apple at every even node
        int n = 100;
        int[][] edges = new int[n - 1][2];
        List<Boolean> hasApple = new ArrayList<>();
        hasApple.add(false); // root
        for (int i = 1; i < n; i++) {
            edges[i - 1] = new int[]{0, i};
            hasApple.add(i % 2 == 0);
        }
        // 49 apples, each needs 2 steps
        assertEquals(98, new MinTime_1443().minTime(n, edges, hasApple));
    }
}
