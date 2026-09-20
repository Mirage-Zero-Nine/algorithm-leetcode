package solutions.monotonicstack;

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
    @Test public void testLeadingZeros() { assertEquals("0", r.removeKdigits("10", 1)); }
    @Test public void testDescending() { assertEquals("1", r.removeKdigits("9876541", 6)); }
    @Test public void testGiant() {
        // 10000 '1's, remove 5000 -> still 5000 '1's
        String num = "1".repeat(10000);
        assertEquals("1".repeat(5000), r.removeKdigits(num, 5000));
    }
    @Test public void testRemoveOneMiddle() { assertEquals("1234", r.removeKdigits("12345",1)); }
    @Test public void testRemoveLeadingCascade() { assertEquals("0", r.removeKdigits("1002",2)); }
    @Test public void testAscendingRemoveTwo() { assertEquals("123", r.removeKdigits("12345",2)); }
    @Test public void testDescendingRemoveTwo() { assertEquals("321", r.removeKdigits("54321",2)); }
    @Test public void testInternalZero() { assertEquals("0", r.removeKdigits("1010",2)); }
    @Test public void testTrailingZeros() { assertEquals("0", r.removeKdigits("1000",2)); }
    @Test public void testKeepSingleMinimum() { assertEquals("1", r.removeKdigits("987654321",8)); }
    @Test public void testKOneAtDrop() { assertEquals("12", r.removeKdigits("132",1)); }
    @Test public void testLeadingZeroNormalization() { assertEquals("0", r.removeKdigits("10001",2)); }
    @Test public void testRepeatedCall() { r.removeKdigits("999",1); assertEquals("12",r.removeKdigits("312",1)); }
}
