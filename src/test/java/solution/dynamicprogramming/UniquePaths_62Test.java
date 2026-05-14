package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UniquePaths_62Test {

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

    @Test
    public void testZeroColumns() {
        assertEquals(0, test.uniquePaths(5, 0));
    }

    @Test
    public void testSingleRow() {
        assertEquals(1, test.uniquePaths(1, 10));
    }

    @Test
    public void testSingleColumn() {
        assertEquals(1, test.uniquePaths(10, 1));
    }

    @Test
    public void testTwoByTwo() {
        assertEquals(2, test.uniquePaths(2, 2));
    }

    @Test
    public void testThreeByThree() {
        assertEquals(6, test.uniquePaths(3, 3));
    }

    @Test
    public void testLargerGrid() {
        assertEquals(924, test.uniquePaths(7, 7));
    }

    @Test
    public void testAsymmetricGrid() {
        assertEquals(10, test.uniquePaths(2, 10));
    }
}
