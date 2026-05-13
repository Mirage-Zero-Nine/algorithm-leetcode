package solution.greedy;

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
}
