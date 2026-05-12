package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Rob198Test {

    private final Rob_198 test = new Rob_198();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.rob(new int[]{1, 2, 3, 1}));
        assertEquals(12, test.rob(new int[]{2, 7, 9, 3, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.rob(new int[]{}));
        assertEquals(1, test.rob(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.rob(new int[]{1, 2, 3, 4, 5}));
    }
}
