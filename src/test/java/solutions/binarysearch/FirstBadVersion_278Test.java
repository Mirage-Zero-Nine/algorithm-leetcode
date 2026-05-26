package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FirstBadVersion_278Test {

    private final FirstBadVersion_278 test = new FirstBadVersion_278();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.firstBadVersion(5));
        assertEquals(10, test.firstBadVersion(10));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.firstBadVersion(1));
        assertEquals(1, test.firstBadVersion(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1000000, test.firstBadVersion(1000000));
    }

    @Test
    public void testSmallRanges() {
        assertEquals(3, test.firstBadVersion(3));
        assertEquals(4, test.firstBadVersion(4));
    }

    @Test
    public void testPowerOfTwoBoundaries() {
        assertEquals(8, test.firstBadVersion(8));
        assertEquals(16, test.firstBadVersion(16));
        assertEquals(32, test.firstBadVersion(32));
    }

    @Test
    public void testPrimeRanges() {
        assertEquals(7, test.firstBadVersion(7));
        assertEquals(97, test.firstBadVersion(97));
    }

    @Test
    public void testNearIntegerLimit() {
        assertEquals(Integer.MAX_VALUE, test.firstBadVersion(Integer.MAX_VALUE));
    }

    @Test
    public void testSequentialRanges() {
        assertEquals(1, test.firstBadVersion(2));
        for (int n = 3; n <= 20; n++) {
            assertEquals(n, test.firstBadVersion(n));
        }

    }

    @Test
    public void testRoundNumbers() {
        assertEquals(100, test.firstBadVersion(100));
        assertEquals(10000, test.firstBadVersion(10000));
    }

    @Test
    public void testLargeRandomLikeRange() {
        assertEquals(123456789, test.firstBadVersion(123456789));
    }
}
