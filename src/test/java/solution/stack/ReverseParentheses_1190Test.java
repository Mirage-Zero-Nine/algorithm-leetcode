package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReverseParentheses_1190Test {
    private final ReverseParentheses_1190 solver = new ReverseParentheses_1190();

    @Test public void testExample1() {
        assertEquals("dcba", solver.reverseParentheses("(abcd)"));
    }

    @Test public void testExample2() {
        assertEquals("iloveu", solver.reverseParentheses("(u(love)i)"));
    }

    @Test public void testExample3() {
        assertEquals("leetcode", solver.reverseParentheses("(ed(et(oc))el)"));
    }

    @Test public void testNoParens() {
        assertEquals("abc", solver.reverseParentheses("abc"));
    }

    @Test public void testEmpty() {
        assertEquals("", solver.reverseParentheses(""));
    }

    @Test public void testNested() {
        assertEquals("apmnolkjihgfedcbq", solver.reverseParentheses("a(bcdefghijkl(mno)p)q"));
    }
}
