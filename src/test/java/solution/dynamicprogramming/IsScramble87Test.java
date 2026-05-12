package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsScramble87Test {

    private final IsScramble_87 test = new IsScramble_87();

    @Test
    public void testHappyCases() {
        assertTrue(test.isScramble("great", "rgeat"));
        assertFalse(test.isScramble("abcde", "caebd"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.isScramble("ab", "ba"));
        assertTrue(test.isScramble("a", "a"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isScramble("abcdbdacbdac", "bdacabcdbdac"));
    }
}
