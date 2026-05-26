package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumPrimeArrangements_1175Test {

    private final NumPrimeArrangements_1175 test = new NumPrimeArrangements_1175();

    @Test
    public void testHappyCases() {
        assertEquals(12, test.numPrimeArrangements(5));
        assertEquals(4, test.numPrimeArrangements(4));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.numPrimeArrangements(1));
        assertEquals(1, test.numPrimeArrangements(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(682289015, test.numPrimeArrangements(100));
    }

    @Test
    public void testN3() {
        assertEquals(2, test.numPrimeArrangements(3));
    }

    @Test
    public void testN6() {
        // primes up to 6: 2,3,5 -> 3 primes, 3 composites -> 3! * 3! = 36
        assertEquals(36, test.numPrimeArrangements(6));
    }

    @Test
    public void testN10() {
        // primes up to 10: 2,3,5,7 -> 4 primes, 6 composites -> 4! * 6! = 24 * 720 = 17280
        assertEquals(17280, test.numPrimeArrangements(10));
    }

    @Test
    public void testN20() {
        assertEquals(344376809, test.numPrimeArrangements(20));
    }

    @Test
    public void testN50() {
        assertEquals(451768713, test.numPrimeArrangements(50));
    }

    @Test
    public void testCountPrimes() {
        assertEquals(4, test.countPrimes(10));
        assertEquals(8, test.countPrimes(20));
    }

    @Test
    public void testCountPrimesEdge() {
        assertEquals(0, test.countPrimes(1));
        assertEquals(1, test.countPrimes(2));
        assertEquals(1, test.countPrimes(3));
    }
}
