package solutions.monotonicstack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Find132pattern_456Test {
    private final Find132pattern_456 f = new Find132pattern_456();

    @Test public void testClassic() { assertFalse(f.find132pattern(new int[]{1, 2, 3, 4})); }
    @Test public void testNoPattern() { assertFalse(f.find132pattern(new int[]{3, 1, 4, 1})); }
    @Test public void testEmpty() { assertFalse(f.find132pattern(new int[]{})); }
    @Test public void testNull() { assertFalse(f.find132pattern(null)); }
    @Test public void testSmall() { assertFalse(f.find132pattern(new int[]{1, 2})); }
    @Test public void testClassic2() { assertTrue(f.find132pattern(new int[]{3, 5, 0, 3, 4})); }
    @Test public void testDecreasing() { assertFalse(f.find132pattern(new int[]{4, 3, 2, 1})); }

    // Additional happy cases
    @Test public void testSimplePattern() { assertTrue(f.find132pattern(new int[]{1, 3, 2})); }
    @Test public void testPatternAtEnd() { assertTrue(f.find132pattern(new int[]{5, 4, 3, 1, 5, 2})); }

    // Negative case: all same values
    @Test public void testAllSame() { assertFalse(f.find132pattern(new int[]{3, 3, 3, 3})); }

    // Edge case: exactly 3 elements, no pattern
    @Test public void testThreeNoPattern() { assertFalse(f.find132pattern(new int[]{1, 2, 3})); }

    // Giant test case
    @Test public void testGiant() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) arr[i] = i;
        assertFalse(f.find132pattern(arr)); // strictly increasing, no 132 pattern
    }
}
