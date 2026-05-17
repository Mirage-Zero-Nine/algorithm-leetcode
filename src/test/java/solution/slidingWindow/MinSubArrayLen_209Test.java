package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;
import solution.slidingwindow.MinSubArrayLen_209;

/**
 * @author BorisMirage
 * Time: 2024/11/29 13:07
 * Created with IntelliJ IDEA
 */

public class MinSubArrayLen_209Test {

    private final MinSubArrayLen_209 test = new MinSubArrayLen_209();

    @Test
    public void test() {
        assertEquals(2, test.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    @Test
    public void test1() {
        assertEquals(1, test.minSubArrayLen(4, new int[]{1, 4, 4}));
    }

    @Test
    public void test3() {
        assertEquals(2, test.minSubArrayLen(5, new int[]{1, 3, 2, 2, 2, 2, 2}));
    }

    @Test
    public void test4() {
        assertEquals(2, test.minSubArrayLen(8, new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void test5() {
        assertEquals(1, test.minSubArrayLen(7, new int[]{10}));
    }

    @Test
    public void test6() {
        assertEquals(5, test.minSubArrayLen(10, new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2}));
    }

    @Test
    public void test7() {
        assertEquals(5, test.minSubArrayLen(15, new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void test8() {
        assertEquals(1, test.minSubArrayLen(7, new int[]{1, 2, 3, 8}));
    }

    @Test
    public void test9() {
        assertEquals(2, test.minSubArrayLen(6, new int[]{2, 3, 1, 2, 4, 3}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.minSubArrayLen(5, new int[]{}));
        assertEquals(0, test.minSubArrayLen(0, new int[]{}));
    }

    @Test
    public void testEqualElementsSumExact() {
        assertEquals(5, test.minSubArrayLen(10, new int[]{2, 2, 2, 2, 2}));
        assertEquals(5, test.minSubArrayLen(5, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testArrayWithOnesNoMatch() {
        assertEquals(0, test.minSubArrayLen(15, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testInvalid() {
        assertEquals(0, test.minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testSingleElementLessThanTarget() {
        assertEquals(0, test.minSubArrayLen(10, new int[]{5}));
    }

    @Test
    public void testSumOfEntireArrayLessThanTarget() {
        assertEquals(0, test.minSubArrayLen(100, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    @Test
    public void testTargetEqualsArraySum() {
        // target equals exact sum of array -> need entire array
        assertEquals(5, test.minSubArrayLen(15, new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testAllSameValue() {
        // all 3s, target 9 -> need 3 elements
        assertEquals(3, test.minSubArrayLen(9, new int[]{3, 3, 3, 3, 3}));
    }

    @Test
    public void testStrictlyIncreasing() {
        // [1,2,3,4,5,6,7,8,9,10], target 15 -> [7,8] or [4,5,6] or [6,9+]... shortest is [7,8]=15 -> len 2? No, 7+8=15. Actually [6,9]=15? No. Let's check: [10,5]=15 -> 10+5=15, len 2? indices 3,4: 4+5=9. indices 8,9: 9+10=19 len 2. Actually 5+6+7=18>=15 len 3, but 6+9=not adjacent. 9+10=19>=15 len 2.
        assertEquals(2, test.minSubArrayLen(15, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    public void testStrictlyDecreasing() {
        // [10,9,8,7,6,5,4,3,2,1], target 15 -> 10+9=19>=15, len 2
        assertEquals(2, test.minSubArrayLen(15, new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}));
    }

    @Test
    public void testSingleHugeElementInMiddle() {
        // huge element in middle satisfies target alone
        assertEquals(1, test.minSubArrayLen(50, new int[]{1, 2, 3, 100, 1, 2, 3}));
    }

    @Test
    public void testLargeArraySeed42() {
        Random rand = new Random(42L);
        int[] nums = new int[10000];
        for (int i = 0; i < 10000; i++) {
            nums[i] = rand.nextInt(100) + 1; // 1-100
        }
        // sum of all ~505000, use target requiring most of array
        int target = 400000;
        int result = test.minSubArrayLen(target, nums);
        assertTrue(result > 0, "Should find a subarray");
        assertTrue(result <= 10000, "Result should be <= array length");
    }

    @Test
    public void testMultipleSubarraysReturnShortest() {
        // [1,1,1,1,5,1,1,5,1], target 5 -> single 5 gives len 1
        assertEquals(1, test.minSubArrayLen(5, new int[]{1, 1, 1, 1, 5, 1, 1, 5, 1}));
        // [4,3,1,1,3,4], target 7 -> [4,3] len 2 at start and [3,4] len 2 at end
        assertEquals(2, test.minSubArrayLen(7, new int[]{4, 3, 1, 1, 3, 4}));
    }

    @Test
    public void testPropertyResultBounds() {
        int[][] cases = {{7, 2, 3, 1, 2, 4, 3}, {4, 1, 4, 4}, {100, 1, 2, 3}};
        for (int[] c : cases) {
            int target = c[0];
            int[] nums = new int[c.length - 1];
            System.arraycopy(c, 1, nums, 0, nums.length);
            int result = test.minSubArrayLen(target, nums);
            if (result > 0) {
                assertTrue(result <= nums.length, "Result must be <= array length");
            }
        }
    }
}
