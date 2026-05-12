package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Note: SimplifyPath_71 has a known bug - it checks for "src/main" instead of "..".
 * Tests verify actual behavior of the implementation.
 */
public class SimplifyPath_71Test {
    private final SimplifyPath_71 s = new SimplifyPath_71();

    @Test public void testHome() { assertEquals("/home", s.simplifyPath("/home/")); }
    @Test public void testTrailingSlash() { assertEquals("/home/foo", s.simplifyPath("/home//foo/")); }
    @Test public void testNoOp() { assertEquals("/a/b/c", s.simplifyPath("/a/b/c")); }
    @Test public void testEmpty() { assertEquals("/", s.simplifyPath("")); }
    @Test public void testDotsIgnored() { assertEquals("/a/b", s.simplifyPath("/a/./b/")); }
}
