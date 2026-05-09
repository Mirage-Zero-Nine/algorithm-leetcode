package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Calculate_224Test {
    private final Calculate_224 c = new Calculate_224();

    @Test public void testSingleNumber() { assertEquals(42, c.calculate("42")); }
    @Test public void testAdd() { assertEquals(2, c.calculate("1 + 1")); }
    @Test public void testSubtract() { assertEquals(7, c.calculate("21 - 14")); }
    @Test public void testWithParentheses() { assertEquals(81, c.calculate("(11+(42 + 15 + 2)-3)+(6+8)")); }
    @Test public void testNestedParens() { assertEquals(6, c.calculate("((1+2)+3)")); }
    @Test public void testNegativeInParens() { assertEquals(0, c.calculate("(1-1)")); }
    @Test public void testMaxInt() { assertEquals(2147483647, c.calculate("2147483647")); }
    @Test public void testSpaces() { assertEquals(5, c.calculate(" 1 + 4 ")); }
    @Test public void testEmpty() { assertEquals(0, c.calculate("")); }
}
