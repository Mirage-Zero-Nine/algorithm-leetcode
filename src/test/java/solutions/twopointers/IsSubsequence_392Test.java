package solutions.twopointers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsSubsequence_392Test {
    private final IsSubsequence_392 solver = new IsSubsequence_392();

    @Test public void testBasic() {
        assertTrue(solver.isSubsequence("abc", "ahbgdc"));
    }

    @Test public void testNotSubseq() {
        assertFalse(solver.isSubsequence("axc", "ahbgdc"));
    }

    @Test public void testEmptyS() {
        assertTrue(solver.isSubsequence("", "abc"));
    }

    @Test public void testSLongerThanT() {
        assertFalse(solver.isSubsequence("abc", "ab"));
    }

    @Test public void testBucketApproach() {
        assertTrue(solver.bucket("abc", "ahbgdc"));
    }

    @Test public void testBucketNotSubseq() {
        assertFalse(solver.bucket("axc", "ahbgdc"));
    }

    @Test public void testEmptyT() {
        assertFalse(solver.isSubsequence("a", ""));
    }

    @Test public void testBothEmpty() {
        assertTrue(solver.isSubsequence("", ""));
    }

    @Test public void testSameString() {
        assertTrue(solver.isSubsequence("abc", "abc"));
    }

    @Test public void testSingleCharMatch() {
        assertTrue(solver.isSubsequence("a", "a"));
    }

    @Test public void testSingleCharNoMatch() {
        assertFalse(solver.isSubsequence("b", "a"));
    }

    @Test public void testBucketEmptyS() {
        assertTrue(solver.bucket("", "abc"));
    }

    @Test public void testGiantCase() {
        String t = "abcdefghij".repeat(5000);
        String s = "aj".repeat(50);
        assertTrue(solver.isSubsequence(s, t));
    }
}
