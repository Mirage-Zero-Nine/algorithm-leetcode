package solutions.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemoveDuplicates_1047Test {
    private final RemoveDuplicates_1047 solver = new RemoveDuplicates_1047();

    @Test public void testExample() {
        assertEquals("ca", solver.removeDuplicates("abbaca"));
    }

    @Test public void testAllRemoved() {
        assertEquals("", solver.removeDuplicates("aabb"));
    }

    @Test public void testNoDuplicates() {
        assertEquals("abc", solver.removeDuplicates("abc"));
    }

    @Test public void testChainReaction() {
        // "azxxzy" -> remove xx -> "azzy" -> remove zz -> "ay"
        assertEquals("ay", solver.removeDuplicates("azxxzy"));
    }

    @Test public void testSingleChar() {
        assertEquals("a", solver.removeDuplicates("a"));
    }

    @Test public void testAllSameEvenLength() {
        assertEquals("", solver.removeDuplicates("aaaa"));
    }

    @Test public void testAllSameOddLength() {
        assertEquals("a", solver.removeDuplicates("aaa"));
    }

    @Test public void testMultipleChainReactions() {
        // "abbacddc" -> remove bb -> "aacddc" -> remove aa -> "cddc" -> remove dd -> "cc" -> remove cc -> ""
        assertEquals("", solver.removeDuplicates("abbacddc"));
    }

    @Test public void testDuplicatesAtEnd() {
        assertEquals("ab", solver.removeDuplicates("abcc"));
    }

    @Test public void testDuplicatesAtStart() {
        assertEquals("bc", solver.removeDuplicates("aabc"));
    }

    @Test public void testGiantInput() {
        // Build a string of 10000 'a' chars - all should cancel out (even count)
        String s = "a".repeat(10000);
        assertEquals("", solver.removeDuplicates(s));
    }
    @Test public void testTwoCharsDifferent() { assertEquals("ab",solver.removeDuplicates("ab")); }
    @Test public void testTwoEqual() { assertEquals("",solver.removeDuplicates("aa")); }
    @Test public void testAlternating() { assertEquals("abab",solver.removeDuplicates("abab")); }
    @Test public void testThreePairsChain() { assertEquals("",solver.removeDuplicates("aabbcc")); }
    @Test public void testBoundaryChain() { assertEquals("",solver.removeDuplicates("abccba")); }
    @Test public void testRepeatedLettersRemain() { assertEquals("",solver.removeDuplicates("aabbaa")); }
    @Test public void testLongOddRun() { assertEquals("a",solver.removeDuplicates("a".repeat(9999))); }
    @Test public void testNoMutationByRepeatCall() { assertEquals("ca",solver.removeDuplicates("abbaca")); assertEquals("ab",solver.removeDuplicates("aaab")); }
    @Test public void testDifferentAlphabet() { assertEquals("z",solver.removeDuplicates("zz"+"z")); }
    @Test public void testChainAcrossBoundary() { assertEquals("a",solver.removeDuplicates("abbccaa")); }
}
