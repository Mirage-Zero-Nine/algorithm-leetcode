package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UniquePaths62Test {

    private final UniquePaths_62 test = new UniquePaths_62();

    @Test
    public void testHappyCases() {
        assertEquals(28, test.uniquePaths(3, 7));
        assertEquals(3, test.uniquePaths(3, 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.uniquePaths(0, 5));
        assertEquals(1, test.uniquePaths(1, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(48620, test.uniquePaths(10, 10));
    }
}
