package solution.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CheckPerfectNumber_507Test {

    private final CheckPerfectNumber_507 test = new CheckPerfectNumber_507();

    @Test
    public void testHappyCases() {
        assertTrue(test.checkPerfectNumber(28));
        assertTrue(test.checkPerfectNumber(6));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.checkPerfectNumber(1));
        assertFalse(test.checkPerfectNumber(2));
        assertFalse(test.checkPerfectNumber(12));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.checkPerfectNumber(496));
        assertFalse(test.checkPerfectNumber(1000));
    }

    @Test
    public void testPerfectNumber8128() {
        assertTrue(test.checkPerfectNumber(8128));
    }

    @Test
    public void testPerfectNumber33550336() {
        assertTrue(test.checkPerfectNumber(33550336));
    }

    @Test
    public void testSmallNonPerfect() {
        assertFalse(test.checkPerfectNumber(3));
        assertFalse(test.checkPerfectNumber(4));
        assertFalse(test.checkPerfectNumber(5));
    }

    @Test
    public void testPrimeNumbers() {
        assertFalse(test.checkPerfectNumber(7));
        assertFalse(test.checkPerfectNumber(13));
        assertFalse(test.checkPerfectNumber(97));
    }

    @Test
    public void testNegativeCase() {
        assertFalse(test.checkPerfectNumber(100));
        assertFalse(test.checkPerfectNumber(999));
    }

    @Test
    public void testPowersOfTwo() {
        assertFalse(test.checkPerfectNumber(8));
        assertFalse(test.checkPerfectNumber(16));
        assertFalse(test.checkPerfectNumber(64));
    }

    @Test
    public void testGiantNonPerfect() {
        assertFalse(test.checkPerfectNumber(100000000));
    }
}
