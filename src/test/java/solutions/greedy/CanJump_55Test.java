package solutions.greedy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CanJump_55Test {
    private final CanJump_55 solver = new CanJump_55();

    @Test
    void nullInputUsesDocumentedImplementationFallback() {
        assertTrue(solver.canJump(null));
    }

    @Test
    void emptyInputUsesDocumentedImplementationFallback() {
        assertTrue(solver.canJump(new int[0]));
    }

    @Test
    void singletonZeroIsReachable() {
        assertTrue(solver.canJump(new int[]{0}));
    }

    @Test
    void singletonPositiveIsReachable() {
        assertTrue(solver.canJump(new int[]{7}));
    }

    @Test
    void officialReachableExample() {
        assertTrue(solver.canJump(new int[]{2, 3, 1, 1, 4}));
    }

    @Test
    void officialBlockedExample() {
        assertFalse(solver.canJump(new int[]{3, 2, 1, 0, 4}));
    }

    @Test
    void twoElementReachable() {
        assertTrue(solver.canJump(new int[]{1, 0}));
    }

    @Test
    void twoElementBlocked() {
        assertFalse(solver.canJump(new int[]{0, 1}));
    }

    @Test
    void firstJumpReachesEndExactly() {
        assertTrue(solver.canJump(new int[]{2, 0, 0}));
    }

    @Test
    void firstJumpOvershootsEnd() {
        assertTrue(solver.canJump(new int[]{10, 0, 0, 0}));
    }

    @Test
    void firstJumpCoversAllTrailingZeros() {
        assertTrue(solver.canJump(new int[]{3, 0, 0, 0}));
    }

    @Test
    void largeFirstJumpCoversShortArray() {
        assertTrue(solver.canJump(new int[]{100, 0, 0, 0, 0}));
    }

    @Test
    void zeroAtStartBlocksLongerArray() {
        assertFalse(solver.canJump(new int[]{0, 1, 2, 3}));
    }

    @Test
    void zeroAfterReachablePrefixCanBeCrossed() {
        assertTrue(solver.canJump(new int[]{2, 0, 1, 0}));
    }

    @Test
    void zeroAtCurrentFrontierBlocks() {
        assertFalse(solver.canJump(new int[]{1, 0, 1}));
    }

    @Test
    void zeroAtLastReachableIndexBlocks() {
        assertFalse(solver.canJump(new int[]{2, 0, 0, 1}));
    }

    @Test
    void exactJumpToLastIndexIsReachable() {
        assertTrue(solver.canJump(new int[]{2, 1, 0}));
    }

    @Test
    void laterLongJumpRecoversFromSmallSteps() {
        assertTrue(solver.canJump(new int[]{1, 1, 1, 5, 0, 0}));
    }

    @Test
    void longJumpMustComeBeforeGap() {
        assertTrue(solver.canJump(new int[]{2, 1, 3, 0, 0, 0}));
    }

    @Test
    void gapBeyondCurrentReachIsFailure() {
        assertFalse(solver.canJump(new int[]{1, 0, 0, 5}));
    }

    @Test
    void allZerosOfLengthThreeAreBlocked() {
        assertFalse(solver.canJump(new int[]{0, 0, 0}));
    }

    @Test
    void allOnesReachTheEnd() {
        assertTrue(solver.canJump(new int[]{1, 1, 1, 1, 1, 1}));
    }

    @Test
    void repeatedValuesAndDuplicateZeros() {
        assertFalse(solver.canJump(new int[]{2, 2, 0, 0, 1, 0}));
    }

    @Test
    void integerMaximumJumpDoesNotOverflow() {
        assertTrue(solver.canJump(new int[]{Integer.MAX_VALUE, 0}));
    }

    @Test
    void inputIsNotMutated() {
        int[] nums = {2, 3, 1, 1, 4};
        int[] original = nums.clone();
        assertTrue(solver.canJump(nums));
        assertArrayEquals(original, nums);
    }

    @Test
    void repeatedCallsDoNotShareState() {
        assertFalse(solver.canJump(new int[]{1, 0, 1}));
        assertTrue(solver.canJump(new int[]{2, 0, 0}));
        assertFalse(solver.canJump(new int[]{0, 1}));
    }

    @Test
    void maximumLengthAllOnes() {
        int[] nums = new int[10_000];
        Arrays.fill(nums, 1);
        assertTrue(solver.canJump(nums));
    }

    @Test
    void maximumLengthBlockedAtLastGap() {
        int[] nums = new int[10_000];
        Arrays.fill(nums, 1);
        nums[nums.length - 2] = 0;
        assertFalse(solver.canJump(nums));
    }

    @Test
    void maximumLengthSingleInitialJump() {
        int[] nums = new int[10_000];
        nums[0] = 100_000;
        assertTrue(solver.canJump(nums));
    }

    @Test
    void reachableZeroInMiddleCanBeSkipped() {
        int[] nums = new int[1_000];
        Arrays.fill(nums, 1);
        nums[499] = 2;
        nums[500] = 0;
        assertTrue(solver.canJump(nums));
    }

    @Test
    void largeUniformJumpValuesAreReachable() {
        int[] nums = new int[1_000];
        Arrays.fill(nums, 3);
        assertTrue(solver.canJump(nums));
    }

    @Test
    void exhaustiveSmallArraysMatchIndependentReachabilityOracle() {
        for (int length = 1; length <= 6; length++) {
            int combinations = (int) Math.pow(4, length);
            for (int encoded = 0; encoded < combinations; encoded++) {
                int value = encoded;
                int[] nums = new int[length];
                for (int i = 0; i < length; i++) {
                    nums[i] = value % 4;
                    value /= 4;
                }
                assertTrue(solver.canJump(nums) == oracle(nums),
                        () -> "Mismatch for " + Arrays.toString(nums));
            }
        }
    }

    private boolean oracle(int[] nums) {
        boolean[] reachable = new boolean[nums.length];
        reachable[0] = true;
        for (int i = 0; i < nums.length; i++) {
            if (!reachable[i]) {
                continue;
            }
            long end = Math.min((long) nums.length - 1, i + (long) nums[i]);
            for (int next = i + 1; next <= end; next++) {
                reachable[next] = true;
            }
        }
        return reachable[nums.length - 1];
    }
}
