package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxProductPath1594Test {

    private final MaxProductPath_1594 test = new MaxProductPath_1594();

    @Test
    public void testHappyCases() {
        assertEquals(8, test.maxProductPath(new int[][]{{1, -2, 1}, {1, -2, 1}, {3, -4, 1}}));
        assertEquals(0, test.maxProductPath(new int[][]{{1, 3}, {0, -4}}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(2, test.maxProductPath(new int[][]{{1, 2}}));
        assertEquals(-1, test.maxProductPath(new int[][]{{-1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(120, test.maxProductPath(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }
}
