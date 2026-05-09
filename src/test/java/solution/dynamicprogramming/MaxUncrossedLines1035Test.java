package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxUncrossedLines1035Test {

    private final MaxUncrossedLines_1035 test = new MaxUncrossedLines_1035();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.maxUncrossedLines(new int[]{1, 4, 2}, new int[]{1, 2, 4}));
        assertEquals(3, test.maxUncrossedLines(new int[]{2, 5, 1, 2, 5}, new int[]{10, 5, 2, 1, 5, 2}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxUncrossedLines(new int[]{1, 2, 3}, new int[]{4, 5, 6}));
        assertEquals(1, test.maxUncrossedLines(new int[]{1}, new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.maxUncrossedLines(new int[]{1, 3, 7, 1, 7, 5}, new int[]{1, 9, 2, 5, 1}));
    }
}
