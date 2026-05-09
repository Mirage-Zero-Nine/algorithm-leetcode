package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxArea11Test {

    private final MaxArea_11 test = new MaxArea_11();

    @Test
    public void testHappyCases() {
        assertEquals(49, test.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        assertEquals(1, test.maxArea(new int[]{1, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxArea(null));
        assertEquals(0, test.maxArea(new int[]{}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(24, test.maxArea(new int[]{1, 2, 4, 3, 5, 6, 7, 8, 9}));
    }
}
