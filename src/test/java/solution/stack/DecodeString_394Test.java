package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DecodeString_394Test {
    private final DecodeString_394 d = new DecodeString_394();

    @Test public void testSimple() { assertEquals("accaccacc", d.decodeString("3[a2[c]]")); }
    @Test public void testNoRepeat() { assertEquals("abc", d.decodeString("abc")); }
    @Test public void testSingleRepeat() { assertEquals("aaa", d.decodeString("3[a]")); }
    @Test public void testNull() { assertNull(d.decodeString(null)); }
    @Test public void testEmpty() { assertEquals("", d.decodeString("")); }
    @Test public void testMultipleRepeat() { assertEquals("ababab", d.decodeString("3[ab]")); }
    @Test public void testNestedDeep() { assertEquals("aaaaaa", d.decodeString("2[3[a]]")); }
    @Test public void testAdjacentBrackets() { assertEquals("aaabbb", d.decodeString("3[a]3[b]")); }
    @Test public void testMixedTextAndBrackets() { assertEquals("xaaay", d.decodeString("x3[a]y")); }
    @Test public void testDoubleDigitRepeat() { assertEquals("a".repeat(12), d.decodeString("12[a]")); }
    @Test public void testGiant() {
        // 100[ab] = "ab" repeated 100 times
        assertEquals("ab".repeat(100), d.decodeString("100[ab]"));
    }
}
