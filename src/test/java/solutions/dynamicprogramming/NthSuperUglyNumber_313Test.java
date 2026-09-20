package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest(name = "super ugly index {0}")
    @CsvSource({"2,'2;3',2", "3,'2;3',3", "4,'2;3',4", "5,'2;3',6", "2,'3;5',3", "3,'3;5',5", "4,'3;5',9", "5,'2;5',8", "6,'2;5',10", "7,'2;3;7',8"})
    public void testAdditionalPrimeSets(int index, String encodedPrimes, int expected) {
        String[] values = encodedPrimes.split(";"); int[] primes = new int[values.length];
        for (int i = 0; i < values.length; i++) primes[i] = Integer.parseInt(values[i]);
        assertEquals(expected, test.nthSuperUglyNumber(index, primes));
    }
}
