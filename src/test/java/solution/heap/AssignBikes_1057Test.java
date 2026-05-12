package solution.heap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AssignBikes_1057Test {

    private final AssignBikes_1057 test = new AssignBikes_1057();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{1, 0}, test.assignBikes(
            new int[][]{{0, 0}, {2, 1}},
            new int[][]{{1, 2}, {3, 3}}
        ));
        assertArrayEquals(new int[]{0, 2, 1}, test.assignBikes(
            new int[][]{{0, 0}, {1, 1}, {2, 0}},
            new int[][]{{1, 0}, {2, 2}, {2, 1}}
        ));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{0}, test.assignBikes(new int[][]{{0, 0}}, new int[][]{{0, 1}}));
        assertArrayEquals(new int[]{0, 1}, test.assignBikes(
            new int[][]{{0, 0}, {0, 1}},
            new int[][]{{1, 0}, {1, 1}}
        ));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{0, 1, 2, 3}, test.assignBikes(
            new int[][]{{0, 0}, {2, 2}, {4, 4}, {6, 6}},
            new int[][]{{0, 1}, {2, 3}, {4, 5}, {6, 7}, {8, 8}}
        ));
    }
}
