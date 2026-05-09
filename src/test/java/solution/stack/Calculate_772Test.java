package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Calculate_772Test {
    private final Calculate_772 c = new Calculate_772();

    @Test public void testNegativePrefix() { assertEquals(0, c.calculate("-1+4*3/3/3")); }
    @Test public void testNegInParens() { assertEquals(8, c.calculate("1 - (-7)")); }
    @Test public void testComplex() { assertEquals(-12, c.calculate("(2+6* 3+5- (3*14/7+2)*5)+3")); }
    @Test public void testDoubleNeg() { assertEquals(3, c.calculate("2-(5-6)")); }
    @Test public void testSingleNumber() { assertEquals(42, c.calculate("42")); }
    @Test public void testAdd() { assertEquals(5, c.calculate("2+3")); }
    @Test public void testPrecedence() { assertEquals(14, c.calculate("2+3*4")); }
    @Test public void testSpaces() { assertEquals(5, c.calculate(" 2 + 3 ")); }
    @Test public void testEmpty() { assertEquals(0, c.calculate("")); }
    @Test public void testNull() { assertEquals(0, c.calculate(null)); }
}
