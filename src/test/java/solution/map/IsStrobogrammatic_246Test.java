package solution.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsStrobogrammatic_246Test {

    private final IsStrobogrammatic_246 test = new IsStrobogrammatic_246();

    @Test
    public void testHappyCases() {
        assertTrue(test.isStrobogrammatic("69"));
        assertTrue(test.isStrobogrammatic("88"));
        assertTrue(test.isStrobogrammatic("818"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isStrobogrammatic("962"));
        assertFalse(test.isStrobogrammatic("2"));
        assertTrue(test.isStrobogrammatic("0"));
        assertTrue(test.isStrobogrammatic("1"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isStrobogrammatic("1001001"));
        assertFalse(test.isStrobogrammatic("1234567"));
    }

    @Test
    public void testSingleDigitStrobogrammatic() {
        assertTrue(test.isStrobogrammatic("8"));
    }

    @Test
    public void testSingleDigitNonStrobogrammatic() {
        assertFalse(test.isStrobogrammatic("6"));
        assertFalse(test.isStrobogrammatic("9"));
    }

    @Test
    public void testTwoDigitPairs() {
        assertTrue(test.isStrobogrammatic("96"));
        assertTrue(test.isStrobogrammatic("11"));
        assertFalse(test.isStrobogrammatic("12"));
    }

    @Test
    public void testThreeDigitStrobogrammatic() {
        assertTrue(test.isStrobogrammatic("101"));
        assertTrue(test.isStrobogrammatic("609"));
        assertTrue(test.isStrobogrammatic("808"));
    }

    @Test
    public void testInvalidCharacters() {
        assertFalse(test.isStrobogrammatic("234"));
        assertFalse(test.isStrobogrammatic("555"));
        assertFalse(test.isStrobogrammatic("7"));
    }

    @Test
    public void testLongerStrobogrammatic() {
        assertTrue(test.isStrobogrammatic("60809"));
        assertTrue(test.isStrobogrammatic("10001"));
        assertTrue(test.isStrobogrammatic("88888"));
    }

    @Test
    public void testGiantCase() {
        // Build a large strobogrammatic number: 1 + many 0s + 1
        StringBuilder sb = new StringBuilder();
        sb.append('1');
        for (int i = 0; i < 1000; i++) {
            sb.append('0');
        }
        sb.append('1');
        assertTrue(test.isStrobogrammatic(sb.toString()));

        // Non-strobogrammatic large number
        sb.setCharAt(500, '3');
        assertFalse(test.isStrobogrammatic(sb.toString()));
    }
}
