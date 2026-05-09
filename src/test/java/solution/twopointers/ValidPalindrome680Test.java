package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ValidPalindrome680Test {

    private final ValidPalindrome_680 test = new ValidPalindrome_680();

    @Test
    public void testHappyCases() {
        assertTrue(test.validPalindrome("aba"));
        assertTrue(test.validPalindrome("abca"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.validPalindrome("abc"));
        assertTrue(test.validPalindrome("a"));
        assertTrue(test.validPalindrome(""));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }
}
