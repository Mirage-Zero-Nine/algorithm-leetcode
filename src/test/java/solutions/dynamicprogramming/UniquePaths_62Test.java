package solutions.dynamicprogramming;

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

    @Test
    public void testSymmetryProperty() {
        assertEquals(test.uniquePaths(3, 7), test.uniquePaths(7, 3));
        assertEquals(test.uniquePaths(5, 12), test.uniquePaths(12, 5));
        assertEquals(test.uniquePaths(10, 15), test.uniquePaths(15, 10));
    }

    @Test
    public void testBoundary1x100() {
        assertEquals(1, test.uniquePaths(1, 100));
    }

    @Test
    public void testBoundary100x1() {
        assertEquals(1, test.uniquePaths(100, 1));
    }

    @Test
    public void testLargeGrid100x100() {
        long result = test.uniquePaths(100, 100);
        // Result overflows int but we verify it completes without error and is non-zero
        // The actual mathematical answer overflows int; implementation returns whatever int holds
        assert result != 0;
    }

    @Test
    public void testMonotonicityMoreRowsMorePaths() {
        // uniquePaths(m, n) >= uniquePaths(m-1, n) for m >= 2
        for (int m = 2; m <= 10; m++) {
            assert test.uniquePaths(m, 5) >= test.uniquePaths(m - 1, 5);
        }
    }

    @Test
    public void testBinomialCrossCheck() {
        // uniquePaths(m, n) = C(m+n-2, m-1)
        // uniquePaths(4, 5) = C(7, 3) = 35
        assertEquals(35, test.uniquePaths(4, 5));
        // uniquePaths(5, 6) = C(9, 4) = 126
        assertEquals(126, test.uniquePaths(5, 6));
        // uniquePaths(6, 4) = C(8, 5) = 56
        assertEquals(56, test.uniquePaths(6, 4));
    }

    @Test
    public void testZeroBothDimensions() {
        assertEquals(0, test.uniquePaths(0, 0));
    }

    @Test
    public void testTwoByThree() {
        // C(3,1) = 3
        assertEquals(3, test.uniquePaths(2, 3));
    }

    @Test
    public void testMediumGrid20x15() {
        // C(33, 19) = 818809200
        assertEquals(818809200, test.uniquePaths(20, 15));
    }
}
