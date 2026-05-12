package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Rob213Test {

    private final Rob_213 test = new Rob_213();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.rob(new int[]{2, 3, 2}));
        assertEquals(4, test.rob(new int[]{1, 2, 3, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.rob(new int[]{}));
        assertEquals(1, test.rob(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(12, test.rob(new int[]{1, 2, 3, 4, 5, 6}));
    }
}
