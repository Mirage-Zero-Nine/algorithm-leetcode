package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsHappy_202Test {

    private final IsHappy_202 test = new IsHappy_202();

    @Test
    public void testHappyCases() {
        assertTrue(test.isHappy(19));
        assertTrue(test.isHappy(1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isHappy(2));
        assertFalse(test.isHappy(4));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isHappy(100));
        assertFalse(test.isHappy(99));
    }

    @Test
    public void testAdditionalHappyCases() {
        assertTrue(test.isHappy(7));
        assertTrue(test.isHappy(10));
        assertTrue(test.isHappy(68));
    }

    @Test
    public void testAdditionalNegativeAndEdgeCases() {
        assertFalse(test.isHappy(0));
        assertFalse(test.isHappy(3));
        assertFalse(test.isHappy(20));
    }

    @Test
    public void testAdditionalGiantCase() {
        assertTrue(test.isHappy(100000));
        assertFalse(test.isHappy(999999));
    }

    @Test
    public void testKnownHappyNumbers() {
        assertTrue(test.isHappy(23));
        assertTrue(test.isHappy(28));
        assertTrue(test.isHappy(44));
    }

    @Test
    public void testKnownUnhappyNumbers() {
        assertFalse(test.isHappy(11));
        assertFalse(test.isHappy(15));
        assertFalse(test.isHappy(16));
    }

    @Test
    public void testLargeHappyNumber() {
        assertTrue(test.isHappy(1000000));
    }

    @Test
    public void testPowersOfTen() {
        // 10, 100, 1000 are all happy (sum of squares -> 1)
        assertTrue(test.isHappy(10));
        assertTrue(test.isHappy(100));
        assertTrue(test.isHappy(1000));
    }
}
