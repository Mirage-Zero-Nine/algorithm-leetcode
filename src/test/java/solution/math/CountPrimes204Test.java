package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountPrimes204Test {

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
}
