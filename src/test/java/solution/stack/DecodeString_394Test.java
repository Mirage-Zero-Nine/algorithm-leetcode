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
}
