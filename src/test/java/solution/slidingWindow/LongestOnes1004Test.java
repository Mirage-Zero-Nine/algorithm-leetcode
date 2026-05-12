package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestOnes1004Test {

    private final LongestOnes_1004 test = new LongestOnes_1004();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
        assertEquals(10, test.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestOnes(new int[]{0}, 0));
        assertEquals(1, test.longestOnes(new int[]{1}, 0));
    }

    @Test
    public void testLargeCase() {
        assertEquals(11, test.longestOnes(new int[]{1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1}, 1));
    }
}
