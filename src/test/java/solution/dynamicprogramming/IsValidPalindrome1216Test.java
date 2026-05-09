package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsValidPalindrome1216Test {

    private final IsValidPalindrome_1216 test = new IsValidPalindrome_1216();

    @Test
    public void testHappyCases() {
        assertTrue(test.isValidPalindrome("abcdeca", 2));
        assertTrue(test.isValidPalindrome("abbababa", 1));
    }

    @Test
    public void testEdgeCases() {
        assertTrue(test.isValidPalindrome("a", 0));
        assertTrue(test.isValidPalindrome("ab", 1));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isValidPalindrome("abcdefedcba", 0));
    }
}
