package solution.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanPermutePalindrome266Test {

    private final CanPermutePalindrome_266 test = new CanPermutePalindrome_266();

    @Test
    public void testHappyCases() {
        assertTrue(test.canPermutePalindrome("aab"));
        assertTrue(test.canPermutePalindrome("carerac"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.canPermutePalindrome("code"));
        assertTrue(test.canPermutePalindrome("a"));
        assertTrue(test.canPermutePalindrome("aa"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.canPermutePalindrome("aabbccddeeff"));
        assertTrue(test.canPermutePalindrome("aabbccddeeffg"));
        assertFalse(test.canPermutePalindrome("aabbccddeeffgh"));
    }
}
