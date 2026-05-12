package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LengthOfLIS300Test {

    private final LengthOfLIS_300 test = new LengthOfLIS_300();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        assertEquals(4, test.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.lengthOfLIS(new int[]{7, 7, 7, 7, 7}));
        assertEquals(1, test.lengthOfLIS(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.lengthOfLIS(new int[]{1, 3, 5, 7, 9, 2, 4, 6, 8, 10}));
    }
}
