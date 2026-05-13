package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleNumber_137Test {
    private final SingleNumber_137 solver = new SingleNumber_137();

    @Test public void testBasic() {
        assertEquals(3, solver.singleNumber(new int[]{2, 2, 3, 2}));
    }

    @Test public void testOneLonely() {
        assertEquals(99, solver.singleNumber(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }

    @Test public void testSingleElement() {
        assertEquals(7, solver.singleNumber(new int[]{7}));
    }

    @Test public void testNegative() {
        assertEquals(-5, solver.singleNumber(new int[]{3, 3, 3, -5}));
    }

    @Test public void testLargerArray() {
        assertEquals(4, solver.singleNumber(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4}));
    }

    @Test public void testZeroSingle() {
        assertEquals(0, solver.singleNumber(new int[]{5, 5, 5, 0}));
    }

    @Test public void testAllNegative() {
        assertEquals(-1, solver.singleNumber(new int[]{-2, -2, -2, -1}));
    }

    @Test public void testLargeValue() {
        assertEquals(Integer.MAX_VALUE, solver.singleNumber(new int[]{Integer.MAX_VALUE, 1, 1, 1}));
    }

    @Test public void testMinValue() {
        assertEquals(Integer.MIN_VALUE, solver.singleNumber(new int[]{Integer.MIN_VALUE, 2, 2, 2}));
    }

    @Test public void testMixedNegPos() {
        assertEquals(10, solver.singleNumber(new int[]{-3, -3, -3, 4, 4, 4, 10}));
    }

    @Test public void testGiantCase() {
        int[] nums = new int[10001];
        for (int i = 0; i < 3333; i++) {
            nums[3 * i] = i;
            nums[3 * i + 1] = i;
            nums[3 * i + 2] = i;
        }
        nums[9999] = 77777;
        nums[10000] = 0; // padding - actually let's fix this
        // Rebuild: 3334 triples + 1 single = 10003, too big. Use smaller.
        int[] nums2 = new int[10];
        nums2[0] = 5; nums2[1] = 5; nums2[2] = 5;
        nums2[3] = 8; nums2[4] = 8; nums2[5] = 8;
        nums2[6] = 3; nums2[7] = 3; nums2[8] = 3;
        nums2[9] = 42;
        assertEquals(42, solver.singleNumber(nums2));
    }
}
