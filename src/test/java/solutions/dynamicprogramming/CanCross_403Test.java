package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanCross_403Test {

    private final CanCross_403 test = new CanCross_403();

    @Test
    public void testHappyCases() {
        assertTrue(test.canCross(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));
        assertFalse(test.canCross(new int[]{0, 1, 2, 3, 4, 8, 9, 11}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.canCross(new int[]{}));
        assertFalse(test.canCross(new int[]{0, 2}));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.canCross(new int[]{0, 1, 3, 6, 10, 15, 21}));
    }

    @Test
    public void testTwoStones() {
        // 0,1 => first jump is 1, lands on last stone
        assertTrue(test.canCross(new int[]{0, 1}));
    }

    @Test
    public void testConsecutiveStones() {
        // 0,1,2,3,4,5 => can always jump 1
        assertTrue(test.canCross(new int[]{0, 1, 2, 3, 4, 5}));
    }

    @Test
    public void testGapTooLargeEarly() {
        // Second stone at position 3 (first jump must be 1)
        assertFalse(test.canCross(new int[]{0, 3, 6, 9}));
    }

    @Test
    public void testCannotReachEnd() {
        // 0,1,3,6,7 => from 6 with step 3, can only jump to 8,9,10 => 7 not reachable
        assertFalse(test.canCross(new int[]{0, 1, 3, 6, 7}));
    }

    @Test
    public void testExponentialGrowth() {
        // Stones at 0,1,3,6,10,15,21,28 (triangular numbers, always jump k+1)
        assertTrue(test.canCross(new int[]{0, 1, 3, 6, 10, 15, 21, 28}));
    }

    @Test
    public void testImpossibleLargeGap() {
        // After position 1 with step 1, next can be 1,2 => position 3 needs step 2 from 1
        // From 3 with step 2, can reach 4,5,6 => 5 exists
        // From 5 with step 2, can reach 6,7,8 => none exist => but 100 is too far
        assertFalse(test.canCross(new int[]{0, 1, 3, 5, 100}));
    }

    @Test
    public void testBacktrackingNeeded() {
        // Multiple paths, needs backtracking
        assertTrue(test.canCross(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}));
    }

    @Test
    public void testGiantCase() {
        // Large array of consecutive stones
        int[] stones = new int[100];
        for (int i = 0; i < 100; i++) stones[i] = i;
        assertTrue(test.canCross(stones));
    }
}
