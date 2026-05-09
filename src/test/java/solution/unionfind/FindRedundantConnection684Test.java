package solution.unionfind;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class FindRedundantConnection684Test {

    private final FindRedundantConnection_684 test = new FindRedundantConnection_684();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{2, 3}, test.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}}));
        assertArrayEquals(new int[]{1, 4}, test.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}));
    }

    @Test
    public void testEdgeCases() {
        assertArrayEquals(new int[]{1, 2}, test.findRedundantConnection(new int[][]{{1, 2}, {1, 2}}));
    }

    @Test
    public void testLargeCase() {
        // Chain 1-2-3-...-10 with extra edge 1-10
        int[][] edges = new int[10][2];
        for (int i = 0; i < 9; i++) {
            edges[i] = new int[]{i + 1, i + 2};
        }
        edges[9] = new int[]{1, 10};
        assertArrayEquals(new int[]{1, 10}, test.findRedundantConnection(edges));
    }
}
