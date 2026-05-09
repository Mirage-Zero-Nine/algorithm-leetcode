package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsOneEditDistance161Test {

    private final IsOneEditDistance_161 test = new IsOneEditDistance_161();

    @Test
    public void testHappyCases() {
        assertTrue(test.isOneEditDistance("ab", "acb"));
        assertTrue(test.isOneEditDistance("cab", "ab"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isOneEditDistance("", ""));
        assertFalse(test.isOneEditDistance("a", "a"));
        assertFalse(test.isOneEditDistance("abc", "xyz"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isOneEditDistance("abcde", "abcdf"));
        assertFalse(test.isOneEditDistance("abcde", "abcde"));
    }
}
