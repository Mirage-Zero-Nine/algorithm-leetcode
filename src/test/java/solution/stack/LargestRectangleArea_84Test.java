package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LargestRectangleArea_84Test {
    private final LargestRectangleArea_84 l = new LargestRectangleArea_84();

    @Test public void testClassic() { assertEquals(10, l.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3})); }
    @Test public void testSingle() { assertEquals(1, l.largestRectangleArea(new int[]{1})); }
    @Test public void testEmpty() { assertEquals(0, l.largestRectangleArea(new int[]{})); }
    @Test public void testIncreasing() { assertEquals(12, l.largestRectangleArea(new int[]{1, 2, 3, 4, 5, 6})); }
    @Test public void testDecreasing() { assertEquals(12, l.largestRectangleArea(new int[]{6, 5, 4, 3, 2, 1})); }
    @Test public void testAllSame() { assertEquals(15, l.largestRectangleArea(new int[]{3, 3, 3, 3, 3})); }
    @Test public void testTwoBars() { assertEquals(4, l.largestRectangleArea(new int[]{2, 4})); }
    @Test public void testValley() { assertEquals(5, l.largestRectangleArea(new int[]{5, 1, 5})); }
    @Test public void testLargeFirst() { assertEquals(10000, l.largestRectangleArea(new int[]{10000, 2, 3, 4, 5})); }
    @Test public void testGiant() {
        int[] heights = new int[10000];
        java.util.Arrays.fill(heights, 1);
        assertEquals(10000, l.largestRectangleArea(heights));
    }
}
