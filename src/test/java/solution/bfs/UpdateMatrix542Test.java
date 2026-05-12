package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class UpdateMatrix542Test {

    private final UpdateMatrix_542 test = new UpdateMatrix_542();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}},
            test.updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
        assertArrayEquals(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 2, 1}},
            test.updateMatrix(new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[][]{{0}}, test.updateMatrix(new int[][]{{0}}));
    }

    @Test
    public void testLargeCase() {
        int[][] result = test.updateMatrix(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 0}});
        assertArrayEquals(new int[]{4, 3, 2}, result[0]);
        assertArrayEquals(new int[]{3, 2, 1}, result[1]);
        assertArrayEquals(new int[]{2, 1, 0}, result[2]);
    }
}
