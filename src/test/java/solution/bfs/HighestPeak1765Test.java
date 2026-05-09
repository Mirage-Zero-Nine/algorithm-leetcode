package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HighestPeak1765Test {

    private final HighestPeak_1765 test = new HighestPeak_1765();

    @Test
    public void testHappyCases() {
        int[][] result = test.highestPeak(new int[][]{{0, 1}, {0, 0}});
        assertEquals(0, result[0][1]);
        assertEquals(1, result[0][0]);
    }

    @Test
    public void testEdgeCases() {
        int[][] result = test.highestPeak(new int[][]{{1}});
        assertEquals(0, result[0][0]);
    }

    @Test
    public void testLargeCase() {
        int[][] result = test.highestPeak(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        assertEquals(0, result[0][0]);
        assertEquals(4, result[2][2]);
    }
}
