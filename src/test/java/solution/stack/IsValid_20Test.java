package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsValid_20Test {
    private final IsValid_20 v = new IsValid_20();

    @Test public void testEmpty() { assertTrue(v.isValid("")); }
    @Test public void testAllOpen() { assertFalse(v.isValid("(((")); }
    @Test public void testAllClose() { assertFalse(v.isValid(")))") ); }
    @Test public void testValidSingle() { assertTrue(v.isValid("()")); }
    @Test public void testValidMixed() { assertTrue(v.isValid("()[]{}")); }
    @Test public void testValidNested() { assertTrue(v.isValid("{[()]}")); }
    @Test public void testInvalidOrder() { assertFalse(v.isValid("(]")); }
    @Test public void testInvalidOpen() { assertFalse(v.isValid("([)]")); }
    @Test public void testValidLong() { assertTrue(v.isValid("{[({})]}") ); }
    @Test public void testInvalidCloseExtra() { assertFalse(v.isValid("()}}")); }
}
