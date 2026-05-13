package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountPrimes_204Test {

    private final CountPrimes_204 test = new CountPrimes_204();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.countPrimes(10));
        assertEquals(25, test.countPrimes(100));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.countPrimes(0));
        assertEquals(0, test.countPrimes(1));
        assertEquals(0, test.countPrimes(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(168, test.countPrimes(1000));
        assertEquals(1229, test.countPrimes(10000));
    }

    @Test
    public void testThree() {
        assertEquals(1, test.countPrimes(3));
    }

    @Test
    public void testFour() {
        assertEquals(2, test.countPrimes(4));
    }

    @Test
    public void testFive() {
        assertEquals(2, test.countPrimes(5));
    }

    @Test
    public void testSmallPrimes() {
        assertEquals(3, test.countPrimes(6));
        assertEquals(4, test.countPrimes(8));
    }

    @Test
    public void testMediumValues() {
        assertEquals(8, test.countPrimes(20));
        assertEquals(15, test.countPrimes(50));
    }

    @Test
    public void testFiveThousand() {
        assertEquals(669, test.countPrimes(5000));
    }

    @Test
    public void testGiantCase() {
        assertEquals(9592, test.countPrimes(100000));
    }
}
