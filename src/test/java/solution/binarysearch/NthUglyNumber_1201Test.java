package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NthUglyNumber_1201Test {

    private final NthUglyNumber_1201 test = new NthUglyNumber_1201();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.nthUglyNumber(3, 2, 3, 5));
        assertEquals(6, test.nthUglyNumber(4, 2, 3, 4));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(2, test.nthUglyNumber(1, 2, 3, 5));
        assertEquals(10, test.nthUglyNumber(5, 2, 11, 13));
    }

    @Test
    public void testLargeCase() {
        assertEquals(10, test.nthUglyNumber(8, 2, 3, 5));
    }

    @Test
    public void testAllDivisorsEqual() {
        assertEquals(21, test.nthUglyNumber(7, 3, 3, 3));
    }

    @Test
    public void testOneDivisorIsMultipleOfOthers() {
        assertEquals(20, test.nthUglyNumber(10, 2, 4, 8));
    }

    @Test
    public void testCoprimeDivisors() {
        assertEquals(66, test.nthUglyNumber(20, 7, 11, 13));
    }

    @Test
    public void testAnotherKnownCase() {
        assertEquals(14, test.nthUglyNumber(10, 2, 3, 7));
    }

    @Test
    public void testLargeNFromMainSample() {
        assertEquals(1999999984, test.nthUglyNumber(1000000000, 2, 217983653, 336916467));
    }

    @Test
    public void testMediumNWithMixedDivisors() {
        assertEquals(108, test.nthUglyNumber(40, 4, 6, 17));
    }

    @Test
    public void testSecondNumberSimple() {
        assertEquals(3, test.nthUglyNumber(2, 2, 3, 5));
    }
}
