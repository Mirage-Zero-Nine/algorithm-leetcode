package solution.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsArmstrong_1134Test {

    private final IsArmstrong_1134 test = new IsArmstrong_1134();

    @Test
    public void testHappyCases() {
        assertTrue(test.isArmstrong(153));
        assertTrue(test.isArmstrong(9));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isArmstrong(10));
        assertFalse(test.isArmstrong(100));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isArmstrong(1634));
        assertFalse(test.isArmstrong(1000));
    }

    @Test
    public void testSingleDigits() {
        assertTrue(test.isArmstrong(1));
        assertTrue(test.isArmstrong(5));
        assertTrue(test.isArmstrong(7));
    }

    @Test
    public void testTwoDigitNonArmstrong() {
        assertFalse(test.isArmstrong(11));
        assertFalse(test.isArmstrong(99));
    }

    @Test
    public void testThreeDigitArmstrong() {
        assertTrue(test.isArmstrong(370));
        assertTrue(test.isArmstrong(371));
        assertTrue(test.isArmstrong(407));
    }

    @Test
    public void testThreeDigitNonArmstrong() {
        assertFalse(test.isArmstrong(200));
        assertFalse(test.isArmstrong(999));
    }

    @Test
    public void testFourDigitArmstrong() {
        assertTrue(test.isArmstrong(8208));
        assertTrue(test.isArmstrong(9474));
    }

    @Test
    public void testFourDigitNonArmstrong() {
        assertFalse(test.isArmstrong(1234));
        assertFalse(test.isArmstrong(5000));
    }

    @Test
    public void testGiantCase() {
        assertTrue(test.isArmstrong(54748));
        assertFalse(test.isArmstrong(99999));
    }
}
