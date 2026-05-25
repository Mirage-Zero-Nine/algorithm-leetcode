package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class ThreeSumClosest_16Test {

    private final ThreeSumClosest_16 test = new ThreeSumClosest_16();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        assertEquals(0, test.threeSumClosest(new int[]{0, 0, 0}, 1));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.threeSumClosest(new int[]{1, 1, -2}, 0));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.threeSumClosest(new int[]{-1, 0, 1, 2, -1, -4}, 3));
    }

    @Test
    public void testExactMatch() {
        assertEquals(6, test.threeSumClosest(new int[]{1, 2, 3, 4, 5}, 6));
    }

    @Test
    public void testAllNegative() {
        assertEquals(-6, test.threeSumClosest(new int[]{-3, -2, -1}, -6));
    }

    @Test
    public void testAllPositive() {
        assertEquals(9, test.threeSumClosest(new int[]{1, 2, 3, 4}, 10));
    }

    @Test
    public void testNegativeTarget() {
        assertEquals(-3, test.threeSumClosest(new int[]{-1, -2, -3, 1, 2}, -3));
    }

    @Test
    public void testLessThanThreeElements() {
        assertEquals(0, test.threeSumClosest(new int[]{1, 2}, 3));
    }

    @Test
    public void testDuplicateElements() {
        assertEquals(3, test.threeSumClosest(new int[]{1, 1, 1, 1}, 3));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) {
            nums[i] = i - 500;
        }
        assertEquals(1000, test.threeSumClosest(nums, 1000));
    }

    @Test
    public void testLargeTarget() {
        assertEquals(12, test.threeSumClosest(new int[]{1, 2, 3, 4, 5}, 100));
    }

    // --- NEW TESTS ---

    @Test
    public void testExactlyThreeElements() {
        // Exactly 3 elements -> their sum is the only possible triplet
        assertEquals(6, test.threeSumClosest(new int[]{1, 2, 3}, 100));
        assertEquals(-6, test.threeSumClosest(new int[]{-1, -2, -3}, -100));
    }

    @Test
    public void testTargetEqualsActualTripletSum() {
        // Target equals an actual triplet sum -> returns that exact sum
        assertEquals(9, test.threeSumClosest(new int[]{1, 2, 3, 4, 5, 6}, 9));
    }

    @Test
    public void testTargetFarBelowAllSums() {
        // Target far below -> minimum triplet sum
        assertEquals(6, test.threeSumClosest(new int[]{1, 2, 3, 4, 5}, -1000));
    }

    @Test
    public void testTargetFarAboveAllSums() {
        // Target far above -> maximum triplet sum
        assertEquals(12, test.threeSumClosest(new int[]{1, 2, 3, 4, 5}, 1000));
    }

    @Test
    public void testAllZerosTargetZero() {
        assertEquals(0, test.threeSumClosest(new int[]{0, 0, 0, 0, 0}, 0));
    }

    @Test
    public void testAllZerosTargetFive() {
        assertEquals(0, test.threeSumClosest(new int[]{0, 0, 0, 0, 0}, 5));
    }

    @Test
    public void testNegativeTargetWithNegativeNumbers() {
        assertEquals(-10, test.threeSumClosest(new int[]{-5, -4, -3, -1, 0}, -10));
    }

    @Test
    public void testTieCaseTwoTripletsEquallyClose() {
        // [-1,1,2,3] target=4: triplets are (−1,1,2)=2, (−1,1,3)=3, (−1,2,3)=4, (1,2,3)=6
        // target=4 -> exact match 4
        // For a real tie: [1,2,3,4] target=8: (1,3,4)=8 exact. Use [1,2,4,5] target=9: (1,4,5)=10, (2,4,5)=11, (1,2,5)=8, (1,2,4)=7, (2,3,5) n/a
        // [1,2,3,5] target=8: (1,2,5)=8 exact. Let's use [-1,0,1,3] target=3: (-1,0,3)=2, (-1,1,3)=3 exact
        // True tie: [-1,1,2,4] target=5: (-1,2,4)=5 exact. Use [-3,0,1,4] target=3: (-3,1,4)=2, (0,1,4)=5 both dist 1
        int result = test.threeSumClosest(new int[]{-3, 0, 1, 4}, 3);
        assertTrue(result == 2 || result == 5, "Expected 2 or 5 (both equally close to 3), got " + result);
    }

    @Test
    public void testLargeRandomCrossCheckBruteForce() {
        Random rand = new Random(42L);
        int[] nums = new int[200];
        for (int i = 0; i < 200; i++) {
            nums[i] = rand.nextInt(201) - 100; // range [-100, 100]
        }
        int target = rand.nextInt(201) - 100;

        int actual = test.threeSumClosest(nums, target);

        // Brute-force O(n^3) to find expected
        int expected = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(sum - target) < Math.abs(expected - target)) {
                        expected = sum;
                    }
                }
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testSingleRepeatedValue() {
        // All 7s, target=20 -> 3*7=21 is the only possible sum
        assertEquals(21, test.threeSumClosest(new int[]{7, 7, 7, 7, 7}, 20));
    }
}
