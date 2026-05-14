package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsOneEditDistance_161Test {

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

    @Test
    public void testInsertAtEnd() {
        assertTrue(test.isOneEditDistance("abc", "abcd"));
    }

    @Test
    public void testDeleteAtEnd() {
        assertTrue(test.isOneEditDistance("abcd", "abc"));
    }

    @Test
    public void testReplaceMiddle() {
        assertTrue(test.isOneEditDistance("abc", "axc"));
    }

    @Test
    public void testTwoEditsApart() {
        assertFalse(test.isOneEditDistance("abc", "axy"));
    }

    @Test
    public void testLengthDiffMoreThanOne() {
        assertFalse(test.isOneEditDistance("a", "abc"));
        assertFalse(test.isOneEditDistance("abc", "a"));
    }

    @Test
    public void testEmptyAndOneChar() {
        assertTrue(test.isOneEditDistance("", "a"));
        assertTrue(test.isOneEditDistance("a", ""));
    }

    @Test
    public void testGiantCase() {
        String base = "a".repeat(10000);
        String oneEdit = base + "b";
        assertTrue(test.isOneEditDistance(base, oneEdit));
        assertFalse(test.isOneEditDistance(base, base));
    }

    @Test
    public void testCompletelyDifferentSameLength() {
        assertFalse(test.isOneEditDistance("ab", "cd"));
    }

    @Test
    public void testInsertAtStart() {
        assertTrue(test.isOneEditDistance("bc", "abc"));
    }
}
