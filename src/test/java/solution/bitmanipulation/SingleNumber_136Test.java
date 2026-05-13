package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleNumber_136Test {
    private final SingleNumber_136 solver = new SingleNumber_136();

    @Test public void testBasic() {
        assertEquals(1, solver.singleNumber(new int[]{2, 2, 1}));
    }

    @Test public void testFourPairs() {
        assertEquals(4, solver.singleNumber(new int[]{4, 1, 2, 1, 2}));
    }

    @Test public void testSingleElement() {
        assertEquals(1, solver.singleNumber(new int[]{1}));
    }

    @Test public void testNegative() {
        assertEquals(-5, solver.singleNumber(new int[]{-5, 1, 1, 2, 2}));
    }

    @Test public void testLargerArray() {
        assertEquals(7, solver.singleNumber(new int[]{3, 5, 3, 5, 7, 9, 9}));
    }

    @Test public void testZeroSingle() {
        assertEquals(0, solver.singleNumber(new int[]{1, 1, 0}));
    }

    @Test public void testLargeValue() {
        assertEquals(Integer.MAX_VALUE, solver.singleNumber(new int[]{Integer.MAX_VALUE, 1, 1}));
    }

    @Test public void testMinValue() {
        assertEquals(Integer.MIN_VALUE, solver.singleNumber(new int[]{Integer.MIN_VALUE, 99, 99}));
    }

    @Test public void testMixedNegPos() {
        assertEquals(3, solver.singleNumber(new int[]{-1, -1, 2, 2, 3}));
    }

    @Test public void testGiantCase() {
        int[] nums = new int[10001];
        for (int i = 0; i < 5000; i++) {
            nums[2 * i] = i;
            nums[2 * i + 1] = i;
        }
        nums[10000] = 99999;
        assertEquals(99999, solver.singleNumber(nums));
    }
}
