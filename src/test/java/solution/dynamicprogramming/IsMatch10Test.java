package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsMatch10Test {

    private final IsMatch_10 test = new IsMatch_10();

    @Test
    public void testHappyCases() {
        assertTrue(test.isMatch("aa", "a*"));
        assertTrue(test.isMatch("ab", ".*"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isMatch("aa", "a"));
        assertFalse(test.isMatch("mississippi", "mis*is*p*."));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isMatch("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s"));
    }
}
