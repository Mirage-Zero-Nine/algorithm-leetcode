package solutions.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CanJump_55Test {
    private final CanJump_55 solver = new CanJump_55();

    @Test public void testBasic() {
        assertTrue(solver.canJump(new int[]{2, 3, 1, 1, 4}));
    }

    @Test public void testStuck() {
        assertFalse(solver.canJump(new int[]{3, 2, 1, 0, 4}));
    }

    @Test public void testSingle() {
        assertTrue(solver.canJump(new int[]{0}));
    }

    @Test public void testFromEndBasic() {
        assertTrue(solver.canJumpFromEnd(new int[]{2, 3, 1, 1, 4}));
    }

    @Test public void testFromEndStuck() {
        assertFalse(solver.canJumpFromEnd(new int[]{3, 2, 1, 0, 4}));
    }

    @Test public void testNull() {
        assertFalse(solver.canJump(null));
    }

    @Test public void testEmpty() {
        assertFalse(solver.canJump(new int[]{}));
    }

    @Test public void testAllZeros() {
        assertFalse(solver.canJump(new int[]{0, 0, 0}));
    }

    @Test public void testLargeFirstJump() {
        assertTrue(solver.canJump(new int[]{100, 0, 0, 0, 0}));
    }

    @Test public void testFromEndSingle() {
        assertTrue(solver.canJumpFromEnd(new int[]{0}));
    }

    @Test public void testGiant() {
        int[] nums = new int[10000];
        for (int i = 0; i < nums.length; i++) nums[i] = 1;
        assertTrue(solver.canJump(nums));
    }

    @Test public void testGiantFail() {
        int[] nums = new int[10000];
        for (int i = 0; i < nums.length - 1; i++) nums[i] = 1;
        nums[nums.length - 2] = 0;
        assertFalse(solver.canJump(nums));
    }

    @Test public void testFromEndLargeJump() {
        assertTrue(solver.canJumpFromEnd(new int[]{5, 0, 0, 0, 0, 1}));
    }

    @Test public void testTwoElements() {
        assertTrue(solver.canJump(new int[]{1, 0}));
    }

    // --- New tricky/happy/negative/large tests ---

    @Test public void testAllZerosExceptFirstNonzero() {
        // [3,0,0,0] -> first jump covers entire array
        assertTrue(solver.canJump(new int[]{3, 0, 0, 0}));
    }

    @Test public void testStuckAtIndexZero() {
        // [0,1,1,1] with n>1 -> can't move from index 0
        assertFalse(solver.canJump(new int[]{0, 1, 1, 1}));
    }

    @Test public void testStuckMidArray() {
        // [2,0,0,1] -> reach index 2 max, but index 2 is 0, can't reach index 3
        assertFalse(solver.canJump(new int[]{2, 0, 0, 1}));
    }

    @Test public void testJustReachable() {
        // [2,1,0] -> jump 2 from index 0 lands exactly at last index
        assertTrue(solver.canJump(new int[]{2, 1, 0}));
    }

    @Test public void testOffByOneBlocked() {
        // [1,0,1] -> reach index 1 which is 0, can't reach index 2
        assertFalse(solver.canJump(new int[]{1, 0, 1}));
    }

    @Test public void testAllOnesLengthN() {
        // All ones, length 500 -> always reachable one step at a time
        int[] nums = new int[500];
        java.util.Arrays.fill(nums, 1);
        assertTrue(solver.canJump(nums));
    }

    @Test public void testLongArrayZeroInMiddleButJumpableOver() {
        // 1000 elements, all 1s except a 0 at index 500, but index 499 has value 2 to jump over
        int[] nums = new int[1000];
        java.util.Arrays.fill(nums, 1);
        nums[500] = 0;
        nums[499] = 2; // can jump over the zero
        assertTrue(solver.canJump(nums));
    }

    @Test public void testAllSameValueLarge() {
        // 1000 elements all with value 3 -> easily reachable
        int[] nums = new int[1000];
        java.util.Arrays.fill(nums, 3);
        assertTrue(solver.canJump(nums));
    }

    @Test public void testFromEndStuckAtIndexZero() {
        // Verify canJumpFromEnd also handles [0,1,1,1] -> false
        assertFalse(solver.canJumpFromEnd(new int[]{0, 1, 1, 1}));
    }
}
