package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsScramble_87Test {

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

    @Test
    public void testSameString() {
        assertTrue(test.isScramble("abc", "abc"));
    }

    @Test
    public void testDifferentLength() {
        assertFalse(test.isScramble("abc", "ab"));
    }

    @Test
    public void testSingleChar() {
        assertFalse(test.isScramble("a", "b"));
    }

    @Test
    public void testTwoCharScramble() {
        assertTrue(test.isScramble("ab", "ab"));
    }

    @Test
    public void testNotScrambleSameChars() {
        assertFalse(test.isScramble("abcde", "caebd"));
    }

    @Test
    public void testThreeCharScramble() {
        assertTrue(test.isScramble("abc", "bca"));
    }

    @Test
    public void testDpMethod() {
        assertTrue(test.dp("great", "rgeat"));
        assertFalse(test.dp("abcde", "caebd"));
    }

    @Test
    public void testGiantCase() {
        assertFalse(test.isScramble("abcdefghij", "efghijcadb"));
    }

    @Test
    public void testAllSameChars() {
        assertTrue(test.isScramble("aaaa", "aaaa"));
    }
}
