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
}
