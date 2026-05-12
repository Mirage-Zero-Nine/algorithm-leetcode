package solution.greedy;

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
}
