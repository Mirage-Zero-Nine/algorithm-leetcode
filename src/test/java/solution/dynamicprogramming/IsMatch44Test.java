package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsMatch44Test {

    private final IsMatch_44 test = new IsMatch_44();

    @Test
    public void testHappyCases() {
        assertTrue(test.isMatch("aa", "*"));
        assertTrue(test.isMatch("cb", "?b"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isMatch("aa", "a"));
        assertFalse(test.isMatch("cb", "?a"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isMatch("adceb", "*a*b"));
        assertFalse(test.isMatch("acdcb", "a*c?b"));
    }
}
