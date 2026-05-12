package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsInterleave97Test {

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
}
