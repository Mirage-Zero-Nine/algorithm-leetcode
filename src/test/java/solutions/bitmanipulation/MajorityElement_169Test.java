package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MajorityElement_169Test {
    private final MajorityElement_169 solver = new MajorityElement_169();

    @Test public void testBasic() {
        assertEquals(3, solver.majorityElement(new int[]{3, 2, 3}));
    }

    @Test public void testMoreElements() {
        assertEquals(2, solver.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

    @Test public void testSingle() {
        assertEquals(7, solver.majorityElement(new int[]{7}));
    }

    @Test public void testAllSame() {
        assertEquals(5, solver.majorityElement(new int[]{5, 5, 5, 5, 5}));
    }

    @Test public void testNegative() {
        assertEquals(-1, solver.majorityElement(new int[]{-1, -1, -1, 2, 3}));
    }

    @Test public void testTwoElements() {
        assertEquals(1, solver.majorityElement(new int[]{1, 1}));
    }

    @Test public void testMajorityAtEnd() {
        assertEquals(4, solver.majorityElement(new int[]{1, 4, 4, 4, 4}));
    }

    @Test public void testMajorityAtStart() {
        assertEquals(9, solver.majorityElement(new int[]{9, 9, 9, 1, 2}));
    }

    @Test public void testZeroMajority() {
        assertEquals(0, solver.majorityElement(new int[]{0, 0, 0, 1, 2}));
    }

    @Test public void testLargeNegative() {
        assertEquals(-100, solver.majorityElement(new int[]{-100, -100, -100, 50, 50}));
    }

    @Test public void testGiantCase() {
        int[] arr = new int[1001];
        for (int i = 0; i < 501; i++) arr[i] = 42;
        for (int i = 501; i < 1001; i++) arr[i] = 7;
        assertEquals(42, solver.majorityElement(arr));
    }

    @Test public void testExactMajoritiesAcrossSignedBoundaries() {
        for (int majority : new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1, 0, 1, 0x55555555}) {
            int[] nums = {majority, ~majority, majority, ~majority, majority};
            assertEquals(majority, solver.majorityElement(nums));
        }
    }

    @Test public void testMajorityAtEveryPositionAmongMinorities() {
        for (int minorityPosition = 0; minorityPosition < 9; minorityPosition++) {
            int[] nums = new int[9];
            java.util.Arrays.fill(nums, -73);
            nums[minorityPosition] = 73;
            assertEquals(-73, solver.majorityElement(nums));
        }
    }

    @Test public void testLargeExactMajorityWithVaryingMinorityBits() {
        int[] nums = new int[50001];
        java.util.Arrays.fill(nums, 0, 25001, Integer.MIN_VALUE);
        for (int i = 25001; i < nums.length; i++) nums[i] = i - 25001;
        assertEquals(Integer.MIN_VALUE, solver.majorityElement(nums));
    }
}
