package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinOperations_1551Test {

    private final MinOperations_1551 test = new MinOperations_1551();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.minOperations(3));
        assertEquals(4, test.minOperations(4));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.minOperations(1));
        assertEquals(1, test.minOperations(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(25, test.minOperations(10));
        assertEquals(30, test.minOperations(11));
    }

    @Test
    public void testN5() {
        assertEquals(6, test.minOperations(5));
    }

    @Test
    public void testN6() {
        assertEquals(9, test.minOperations(6));
    }

    @Test
    public void testN7() {
        assertEquals(12, test.minOperations(7));
    }

    @Test
    public void testN8() {
        assertEquals(16, test.minOperations(8));
    }

    @Test
    public void testN9() {
        assertEquals(20, test.minOperations(9));
    }

    @Test
    public void testGiantN() {
        // n=10000 (even), result = (5000)^2 = 25000000
        assertEquals(25000000, test.minOperations(10000));
    }

    @Test
    public void testGiantOddN() {
        // n=9999 (odd), n/2=4999, result = 4999*5000 = 24995000
        assertEquals(24995000, test.minOperations(9999));
    }
}
