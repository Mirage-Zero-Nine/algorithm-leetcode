package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KthFactor_1492Test {

    private final KthFactor_1492 test = new KthFactor_1492();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.kthFactor(12, 3));
        assertEquals(7, test.kthFactor(7, 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.kthFactor(4, 4));
        assertEquals(1, test.kthFactor(1, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(-1, test.kthFactor(1000, 20));
        assertEquals(5, test.kthFactor(1000, 4));
    }

    @Test
    public void testFirstFactor() {
        assertEquals(1, test.kthFactor(12, 1));
    }

    @Test
    public void testLastFactor() {
        assertEquals(12, test.kthFactor(12, 6));
    }

    @Test
    public void testPrimeNumber() {
        assertEquals(1, test.kthFactor(13, 1));
        assertEquals(13, test.kthFactor(13, 2));
        assertEquals(-1, test.kthFactor(13, 3));
    }

    @Test
    public void testPerfectSquare() {
        // factors of 16: 1,2,4,8,16
        assertEquals(4, test.kthFactor(16, 3));
        assertEquals(16, test.kthFactor(16, 5));
    }

    @Test
    public void testNegativeKTooLarge() {
        assertEquals(-1, test.kthFactor(1, 2));
    }

    @Test
    public void testFactorsOf24() {
        // factors: 1,2,3,4,6,8,12,24
        assertEquals(6, test.kthFactor(24, 5));
        assertEquals(24, test.kthFactor(24, 8));
    }

    @Test
    public void testGiantN() {
        // factors of 1000000: many factors
        assertEquals(2, test.kthFactor(1000000, 2));
    }

    @Test
    public void testAdditional1() {
        assertEquals(1, test.kthFactor(1, 1));
    }

    @Test
    public void testAdditional2() {
        assertEquals(2, test.kthFactor(2, 2));
    }

    @Test
    public void testAdditional3() {
        assertEquals(1, test.kthFactor(12, 1));
    }

    @Test
    public void testAdditional4() {
        assertEquals(2, test.kthFactor(12, 2));
    }

    @Test
    public void testAdditional5() {
        assertEquals(3, test.kthFactor(12, 3));
    }

    @Test
    public void testAdditional6() {
        assertEquals(12, test.kthFactor(12, 6));
    }

    @Test
    public void testAdditional7() {
        assertEquals(-1, test.kthFactor(12, 7));
    }

    @Test
    public void testAdditional8() {
        assertEquals(5, test.kthFactor(100, 4));
    }

    @Test
    public void testAdditional9() {
        assertEquals(100, test.kthFactor(100, 9));
    }

    @Test
    public void testAdditional10() {
        assertEquals(1000, test.kthFactor(1000, 16));
    }
}
