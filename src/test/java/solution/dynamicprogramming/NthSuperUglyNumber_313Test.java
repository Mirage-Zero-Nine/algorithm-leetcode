package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NthSuperUglyNumber_313Test {

    private final NthSuperUglyNumber_313 test = new NthSuperUglyNumber_313();

    @Test
    public void testHappyCases() {
        assertEquals(32, test.nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
        assertEquals(1, test.nthSuperUglyNumber(1, new int[]{2, 3, 5}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.nthSuperUglyNumber(1, new int[]{2}));
        assertEquals(2, test.nthSuperUglyNumber(2, new int[]{2}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(13, test.nthSuperUglyNumber(6, new int[]{2, 11, 13}));
    }

    @Test
    public void testSinglePrime() {
        assertEquals(8, test.nthSuperUglyNumber(4, new int[]{2}));
    }

    @Test
    public void testClassicUglyNumbers() {
        assertEquals(12, test.nthSuperUglyNumber(10, new int[]{2, 3, 5}));
    }

    @Test
    public void testNEqualsOne() {
        assertEquals(1, test.nthSuperUglyNumber(1, new int[]{7, 11, 13, 17}));
    }

    @Test
    public void testTwoPrimes() {
        // sequence: 1, 2, 3, 4, 6, 8, 9, 12, 16, 18
        assertEquals(18, test.nthSuperUglyNumber(10, new int[]{2, 3}));
    }

    @Test
    public void testLargePrimes() {
        // primes are large, sequence: 1, 97, 101, ...
        assertEquals(97, test.nthSuperUglyNumber(2, new int[]{97, 101}));
    }

    @Test
    public void testGiantCase() {
        // n=100 with primes [2,3,5,7]
        int result = test.nthSuperUglyNumber(100, new int[]{2, 3, 5, 7});
        assertTrue(result > 0);
    }

    @Test
    public void testNthEquals3() {
        // sequence: 1, 2, 4 for prime [2]
        assertEquals(4, test.nthSuperUglyNumber(3, new int[]{2}));
    }
}
