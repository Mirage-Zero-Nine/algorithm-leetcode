package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Sqrt_69Test {

    private final Sqrt_69 test = new Sqrt_69();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.sqrt(4));
        assertEquals(2, test.sqrt(8));
        assertEquals(3, test.sqrt(9));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.sqrt(0));
        assertEquals(1, test.sqrt(1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(100, test.sqrt(10000));
        assertEquals(46340, test.sqrt(2147395600));
    }

    @Test
    public void testPerfectSquares() {
        assertEquals(5, test.sqrt(25));
        assertEquals(10, test.sqrt(100));
        assertEquals(12, test.sqrt(144));
    }

    @Test
    public void testNonPerfectSquares() {
        assertEquals(3, test.sqrt(10));
        assertEquals(4, test.sqrt(20));
        assertEquals(7, test.sqrt(50));
    }

    @Test
    public void testSmallValues() {
        assertEquals(1, test.sqrt(2));
        assertEquals(1, test.sqrt(3));
    }

    @Test
    public void testIntMaxValue() {
        assertEquals(46340, test.sqrt(Integer.MAX_VALUE));
    }

    @Test
    public void testBinarySearchHappyCases() {
        assertEquals(2, test.binarySearch(4));
        assertEquals(3, test.binarySearch(9));
        assertEquals(2, test.binarySearch(8));
    }

    @Test
    public void testBinarySearchEdgeCases() {
        assertEquals(0, test.binarySearch(0));
        assertEquals(1, test.binarySearch(1));
    }

    @Test
    public void testBinarySearchLarge() {
        assertEquals(46340, test.binarySearch(Integer.MAX_VALUE));
        assertEquals(100, test.binarySearch(10000));
    }

    @Test
    public void testGiantConsistencyCheck() {
        int[] values = {999, 1024, 2048, 4096, 8192, 65536, 100000, 1000000};
        for (int v : values) {
            assertEquals(test.binarySearch(v), test.sqrt(v));
        }
    }
}
