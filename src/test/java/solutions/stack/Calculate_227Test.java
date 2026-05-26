package solutions.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Calculate_227Test {
    private final Calculate_227 c = new Calculate_227();

    @Test public void testSingle() { assertEquals(42, c.calculate("42")); }
    @Test public void testAdd() { assertEquals(5, c.calculate("3+2")); }
    @Test public void testPrecedence() { assertEquals(14, c.calculate("1*3+2+3+3*2")); }
    @Test public void testMultipleOps() { assertEquals(7, c.calculate("3+2*2")); }
    @Test public void testDivide() { assertEquals(5, c.calculate("11/2")); }
    @Test public void testSpaces() { assertEquals(1, c.calculate(" 3/2 ")); }
    @Test public void testEmpty() { assertEquals(0, c.calculate("")); }
    @Test public void testNull() { assertEquals(0, c.calculate(null)); }
    @Test public void testSubtract() { assertEquals(1, c.calculate("3-2")); }
    @Test public void testMultiply() { assertEquals(6, c.calculate("3*2")); }
}
