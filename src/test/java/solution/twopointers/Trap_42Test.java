package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Trap_42Test {

    private final Trap_42 test = new Trap_42();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        assertEquals(9, test.trap(new int[]{4, 2, 0, 3, 2, 5}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.trap(new int[]{1, 2}));
        assertEquals(0, test.trap(new int[]{3, 2, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1, 0, 2}));
    }

    @Test
    public void testAdditionalHappyCases() {
        assertEquals(2, test.trap(new int[]{2, 0, 2}));
        assertEquals(7, test.trap(new int[]{3, 0, 2, 0, 4}));
        assertEquals(1, test.trap(new int[]{1, 0, 1}));
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertEquals(0, test.trap(new int[]{}));
        assertEquals(0, test.trap(new int[]{5}));
        assertEquals(0, test.trap(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void testAdditionalGiantCase() {
        assertEquals(43, test.trap(new int[]{5, 0, 0, 0, 5, 0, 1, 0, 4, 0, 2, 0, 5}));
    }

    @Test
    public void testFlat() {
        assertEquals(0, test.trap(new int[]{3, 3, 3, 3}));
    }

    @Test
    public void testValley() {
        assertEquals(8, test.trap(new int[]{4, 0, 0, 4}));
    }

    @Test
    public void testMultipleValleys() {
        assertEquals(6, test.trap(new int[]{3, 0, 3, 0, 3}));
    }

    @Test
    public void testGiantStaircase() {
        int[] height = new int[200];
        for (int i = 0; i < 100; i++) height[i] = i;
        for (int i = 100; i < 200; i++) height[i] = 199 - i;
        // symmetric mountain, no water trapped
        assertEquals(0, test.trap(height));
    }
}
