package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemoveKdigits_402Test {
    private final RemoveKdigits_402 r = new RemoveKdigits_402();

    @Test public void testClassic() { assertEquals("1219", r.removeKdigits("1432219", 3)); }
    @Test public void testRemoveLeading() { assertEquals("200", r.removeKdigits("10200", 1)); }
    @Test public void testRemoveLast() { assertEquals("11", r.removeKdigits("112", 1)); }
    @Test public void testAllRemoved() { assertEquals("0", r.removeKdigits("1234567890", 9)); }
    @Test public void testNoRemove() { assertEquals("123", r.removeKdigits("123", 0)); }
    @Test public void testSameLength() { assertEquals("0", r.removeKdigits("10", 2)); }
    @Test public void testSameDigits() { assertEquals("11", r.removeKdigits("11111", 3)); }
}
