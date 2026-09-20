package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @Test public void testExactBoundary() { assertEquals(3,test.numSubseq(new int[]{2,2},4)); }
    @Test public void testStrictlyBelowBoundary() { assertEquals(3,test.numSubseq(new int[]{1,2},4)); }
    @Test public void testNegativeValues() { assertEquals(3,test.numSubseq(new int[]{-2,-1},-2)); }
    @Test public void testMixedValues() { assertEquals(6,test.numSubseq(new int[]{1,2,3},5)); }
    @Test public void testOneQualifyingAmongThree() { assertEquals(1,test.numSubseq(new int[]{4,8,9},10)); }
    @Test public void testDuplicateBoundaryValues() { assertEquals(7,test.numSubseq(new int[]{1,1,1},3)); }
    @Test public void testAllFail() { assertEquals(0,test.numSubseq(new int[]{10,10,10},10)); }
    @Test public void testFourElementsExact() { assertEquals(15,test.numSubseq(new int[]{1,1,1,1},2)); }
    @Test public void testRepeatedCalls() { assertEquals(6,test.numSubseq(new int[]{2,3,4},7)); assertEquals(0,test.numSubseq(new int[]{5},5)); }

    @ParameterizedTest
    @CsvSource({"'1,1,2,2',3,12", "'1,2,3,4',6,13", "'0,0,0',0,7", "'-3,-2,-1',-4,5", "'2,4,6,8',10,10"})
    void additionalSortedAndBoundaryInputs(String encoded, int target, int expected) {
        String[] values = encoded.split(",");
        int[] nums = new int[values.length];
        for (int i = 0; i < values.length; i++) nums[i] = Integer.parseInt(values[i]);
        assertEquals(expected, test.numSubseq(nums, target));
    }
}
