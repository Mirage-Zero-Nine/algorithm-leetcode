package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BackspaceCompare_844Test {
    private final BackspaceCompare_844 b = new BackspaceCompare_844();

    @Test public void testSimple() { assertTrue(b.backspaceCompare("ab#c", "ad#c")); }
    @Test public void testNotEqual() { assertFalse(b.backspaceCompare("ab#c", "ad" + "b#")); }
    @Test public void testEmpty() { assertTrue(b.backspaceCompare("", "")); }
    @Test public void testBackspaceAll() { assertTrue(b.backspaceCompare("a##c", "#a#c")); }
    @Test public void testNoBackspace() { assertTrue(b.backspaceCompare("abc", "abc")); }
    @Test public void testNull() { assertFalse(b.backspaceCompare(null, "abc")); }
    @Test public void testBackspaceStart() { assertTrue(b.backspaceCompare("#a", "#a")); }
}
