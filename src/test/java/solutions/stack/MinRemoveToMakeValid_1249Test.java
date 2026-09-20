package solutions.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinRemoveToMakeValid_1249Test {
    private final MinRemoveToMakeValid_1249 solver = new MinRemoveToMakeValid_1249();

    @Test public void testExample1() {
        assertEquals("lee(t(c)o)de", solver.minRemoveToMakeValid("lee(t(c)o)de)"));
    }

    @Test public void testExample2() {
        assertEquals("ab(c)d", solver.minRemoveToMakeValid("a)b(c)d"));
    }

    @Test public void testAllClose() {
        assertEquals("", solver.minRemoveToMakeValid("))(("));
    }

    @Test public void testNoParens() {
        assertEquals("abc", solver.minRemoveToMakeValid("abc"));
    }

    @Test public void testEmpty() {
        assertEquals("", solver.minRemoveToMakeValid(""));
    }

    @Test public void testBalanced() {
        assertEquals("(a)(b)", solver.minRemoveToMakeValid("(a)(b)"));
    }

    @Test public void testOnlyOpen() {
        assertEquals("abc", solver.minRemoveToMakeValid("(((abc"));
    }

    @Test public void testOnlyClose() {
        assertEquals("abc", solver.minRemoveToMakeValid("abc)))"));
    }

    @Test public void testNestedParens() {
        assertEquals("((a))", solver.minRemoveToMakeValid("((a))"));
    }

    @Test public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5000; i++) sb.append("(a)");
        String input = sb.toString();
        assertEquals(input, solver.minRemoveToMakeValid(input));
    }
    @Test public void testLeadingClose() { assertEquals("((a))",solver.minRemoveToMakeValid(")((a))")); }
    @Test public void testInterleavedInvalid() { assertEquals("a(b)c",solver.minRemoveToMakeValid("a))(b)(c")); }
    @Test public void testEmptyParens() { assertEquals("()",solver.minRemoveToMakeValid("()")); }
    @Test public void testSingleLetter() { assertEquals("a",solver.minRemoveToMakeValid("a")); }
    @Test public void testSingleOpenInput() { assertEquals("",solver.minRemoveToMakeValid("(")); }
    @Test public void testSingleCloseInput() { assertEquals("",solver.minRemoveToMakeValid(")")); }
    @Test public void testMultipleGroups() { assertEquals("(a)(b)(c)",solver.minRemoveToMakeValid("(a)(b)(c)")); }
    @Test public void testNestedWithInvalidTail() { assertEquals("((x))",solver.minRemoveToMakeValid("((x)))")); }
    @Test public void testRepeatedInvocation() { assertEquals("a",solver.minRemoveToMakeValid("a)")); assertEquals("(b)",solver.minRemoveToMakeValid("(b)")); }
    @Test public void testLettersPreserved() { assertEquals("a(b)c",solver.minRemoveToMakeValid("a(b)c)")); }
}
