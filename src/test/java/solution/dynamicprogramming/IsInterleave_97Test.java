package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsInterleave_97Test {

    private final IsInterleave_97 test = new IsInterleave_97();

    @Test
    public void testHappyCases() {
        assertTrue(test.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        assertFalse(test.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.isInterleave("", "", ""));
        assertFalse(test.isInterleave("a", "b", "c"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isInterleave("abc", "def", "adbecf"));
    }

    @Test
    public void testEmptyS1() {
        assertTrue(test.isInterleave("", "abc", "abc"));
    }

    @Test
    public void testEmptyS2() {
        assertTrue(test.isInterleave("abc", "", "abc"));
    }

    @Test
    public void testLengthMismatch() {
        assertFalse(test.isInterleave("a", "b", "abc"));
    }

    @Test
    public void testSingleCharTrue() {
        assertTrue(test.isInterleave("a", "b", "ab"));
    }

    @Test
    public void testSingleCharReversed() {
        assertTrue(test.isInterleave("a", "b", "ba"));
    }

    @Test
    public void testRepeatedChars() {
        assertTrue(test.isInterleave("aa", "aa", "aaaa"));
    }

    @Test
    public void testNotInterleaved() {
        assertFalse(test.isInterleave("ab", "cd", "abdc"));
    }

    @Test
    public void testGiantCase() {
        String s1 = "a".repeat(50);
        String s2 = "b".repeat(50);
        String s3 = "ab".repeat(50);
        assertTrue(test.isInterleave(s1, s2, s3));
    }

    @Test
    public void testGiantCaseFalse() {
        String s1 = "a".repeat(50);
        String s2 = "b".repeat(50);
        String s3 = "a".repeat(50) + "b".repeat(49) + "c";
        assertFalse(test.isInterleave(s1, s2, s3));
    }
}
