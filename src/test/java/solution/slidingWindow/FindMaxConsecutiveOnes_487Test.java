package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindMaxConsecutiveOnes_487Test {

    private final FindMaxConsecutiveOnes_487 test = new FindMaxConsecutiveOnes_487();

    @Test
    public void testHappyCase1() {
        assertEquals(4, test.findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0}));
    }

    @Test
    public void testHappyCase2() {
        assertEquals(4, test.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1}));
    }

    @Test
    public void testHappyCase3() {
        assertEquals(5, test.findMaxConsecutiveOnes(new int[]{1, 1, 1, 1, 1}));
    }

    @Test
    public void testHappyCase4() {
        // [0, 0, 0], flip one 0 -> [1, 0, 0] or [0, 1, 0] or [0, 0, 1]. Max is 1.
        // Wait, why did I think it was 2? Maybe I was thinking about K=1 flip.
        // If K=1, then for 0,0,0 it is 1.
        // Let's check the code:
        // i=0: nums[0]=0, count=1. while(1>1) no. max=1.
        // i=1: nums[1]=0, count=2. while(2>1) left=0, nums[0]=0, count=1, left=1. max=max(1, 1-1+1)=1.
        // i=2: nums[2]=0, count=2. while(2>1) left=1, nums[1]=0, count=1, left=2. max=max(1, 2-2+1)=1.
        assertEquals(1, test.findMaxConsecutiveOnes(new int[]{0, 0, 0}));
    }

    @Test
    public void testHappyCase5() {
        assertEquals(1, test.findMaxConsecutiveOnes(new int[]{0}));
    }

    @Test
    public void testNegativeCase() {
        // Technically not a negative case but minimal output
        assertEquals(0, test.findMaxConsecutiveOnes(new int[]{}));
    }

    @Test
    public void testEdgeCaseAlternating() {
        assertEquals(3, test.findMaxConsecutiveOnes(new int[]{1, 0, 1, 0, 1, 0}));
    }

    @Test
    public void testEdgeCaseTwoZeros() {
        assertEquals(3, test.findMaxConsecutiveOnes(new int[]{1, 0, 1, 0, 1}));
    }

    @Test
    public void testEdgeCaseZeroAtEnds() {
        assertEquals(3, test.findMaxConsecutiveOnes(new int[]{0, 1, 1, 0}));
    }

    @Test
    public void testGiantCase() {
        int n = 10000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = 1;
        }
        nums[n/2] = 0;
        nums[n/2 + 1] = 0;
        // 111...00...111. Flipping one 0 gives n/2 + 1 consecutive 1s.
        assertEquals(n/2 + 1, test.findMaxConsecutiveOnes(nums));
    }
}
