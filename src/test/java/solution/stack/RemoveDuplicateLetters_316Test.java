package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemoveDuplicateLetters_316Test {
    private final RemoveDuplicateLetters_316 solver = new RemoveDuplicateLetters_316();

    @Test public void testExample1() {
        assertEquals("abc", solver.removeDuplicateLetters("bcabc"));
    }

    @Test public void testExample2() {
        assertEquals("acdb", solver.removeDuplicateLetters("cbacdcbc"));
    }

    @Test public void testNoDuplicates() {
        assertEquals("abc", solver.removeDuplicateLetters("abc"));
    }

    @Test public void testSingleChar() {
        assertEquals("a", solver.removeDuplicateLetters("a"));
    }

    @Test public void testAllSame() {
        assertEquals("a", solver.removeDuplicateLetters("aaaa"));
    }

    @Test public void testEmpty() {
        assertEquals("", solver.removeDuplicateLetters(""));
    }
}
