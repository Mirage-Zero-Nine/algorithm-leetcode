package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MajorityChecker_1157Test {

    @Test
    public void testHappyCases() {
        MajorityChecker_1157 mc = new MajorityChecker_1157(new int[]{1, 1, 2, 2, 1, 1});
        assertEquals(1, mc.query(0, 5, 4));
        assertEquals(-1, mc.query(0, 3, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        MajorityChecker_1157 mc = new MajorityChecker_1157(new int[]{1});
        assertEquals(1, mc.query(0, 0, 1));
        assertEquals(-1, mc.query(0, 0, 2));
    }

    @Test
    public void testLargeCase() {
        MajorityChecker_1157 mc = new MajorityChecker_1157(new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 2});
        assertEquals(1, mc.query(0, 4, 3));
        assertEquals(2, mc.query(5, 9, 3));
        assertEquals(-1, mc.query(0, 9, 6));
    }

    @Test
    public void testAllSameElements() {
        MajorityChecker_1157 mc = new MajorityChecker_1157(new int[]{3, 3, 3, 3});
        assertEquals(3, mc.query(0, 3, 4));
        assertEquals(3, mc.query(1, 2, 2));
    }

    @Test
    public void testNoMajority() {
        MajorityChecker_1157 mc = new MajorityChecker_1157(new int[]{1, 2, 3, 4, 5});
        assertEquals(-1, mc.query(0, 4, 2));
    }

    @Test
    public void testExactThreshold() {
        MajorityChecker_1157 mc = new MajorityChecker_1157(new int[]{2, 2, 1, 1, 2});
        // subarray [0,4]: 2 appears 3 times
        assertEquals(2, mc.query(0, 4, 3));
        assertEquals(-1, mc.query(0, 4, 4));
    }

    @Test
    public void testSubarrayQuery() {
        MajorityChecker_1157 mc = new MajorityChecker_1157(new int[]{1, 2, 2, 2, 1});
        assertEquals(2, mc.query(1, 3, 2));
        assertEquals(-1, mc.query(0, 4, 4));
    }

    @Test
    public void testSingleElementSubarray() {
        MajorityChecker_1157 mc = new MajorityChecker_1157(new int[]{5, 6, 7});
        assertEquals(5, mc.query(0, 0, 1));
        assertEquals(6, mc.query(1, 1, 1));
        assertEquals(7, mc.query(2, 2, 1));
    }

    @Test
    public void testTwoElementSubarray() {
        MajorityChecker_1157 mc = new MajorityChecker_1157(new int[]{1, 1, 2, 2});
        assertEquals(1, mc.query(0, 1, 2));
        assertEquals(2, mc.query(2, 3, 2));
        assertEquals(-1, mc.query(1, 2, 2));
    }

    @Test
    public void testThresholdOne() {
        MajorityChecker_1157 mc = new MajorityChecker_1157(new int[]{4, 5, 6});
        // threshold=1, any element qualifies; Boyer-Moore returns the last standing candidate
        int result = mc.query(0, 2, 1);
        // result must be one of the elements in range
        assertEquals(true, result == 4 || result == 5 || result == 6);
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[10000];
        for (int i = 0; i < 7000; i++) arr[i] = 1;
        for (int i = 7000; i < 10000; i++) arr[i] = 2;
        MajorityChecker_1157 mc = new MajorityChecker_1157(arr);
        assertEquals(1, mc.query(0, 9999, 5001));
        assertEquals(-1, mc.query(0, 9999, 7001));
        assertEquals(2, mc.query(7000, 9999, 3000));
    }
}
