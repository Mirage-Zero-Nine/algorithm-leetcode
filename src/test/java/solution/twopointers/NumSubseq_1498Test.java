package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumSubseq_1498Test {

    private final NumSubseq_1498 test = new NumSubseq_1498();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.numSubseq(new int[]{3, 5, 6, 7}, 9));
        assertEquals(6, test.numSubseq(new int[]{3, 3, 6, 8}, 10));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numSubseq(new int[]{2, 3, 3, 4, 6, 7}, 2));
        assertEquals(1, test.numSubseq(new int[]{1}, 2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(61, test.numSubseq(new int[]{2, 3, 3, 4, 6, 7}, 12));
    }

    @Test
    public void testAllElementsSameAndMeetTarget() {
        assertEquals(7, test.numSubseq(new int[]{2, 2, 2}, 5));
    }

    @Test
    public void testAllElementsSameExceedTarget() {
        assertEquals(0, test.numSubseq(new int[]{5, 5, 5}, 3));
    }

    @Test
    public void testSingleElementMeetsTarget() {
        assertEquals(1, test.numSubseq(new int[]{3}, 6));
    }

    @Test
    public void testSingleElementExceedsTarget() {
        assertEquals(0, test.numSubseq(new int[]{5}, 3));
    }

    @Test
    public void testTwoElementsMeetTarget() {
        assertEquals(2, test.numSubseq(new int[]{1, 2}, 3));
    }

    @Test
    public void testTwoElementsOnlyFirstMeets() {
        assertEquals(1, test.numSubseq(new int[]{1, 5}, 3));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) nums[i] = 1;
        // all elements are 1, target >= 2, all 2^1000 - 1 non-empty subsequences qualify
        // 2^1000 - 1 mod 10^9+7
        int mod = (int) 1e9 + 7;
        long expected = 1;
        for (int i = 0; i < 1000; i++) expected = expected * 2 % mod;
        expected = (expected - 1 + mod) % mod;
        assertEquals((int) expected, test.numSubseq(nums, 2));
    }
}
