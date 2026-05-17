package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/04 11:01
 * Created with IntelliJ IDEA
 */

public class RemoveDuplicates_26Test {

    private final RemoveDuplicates_26 test = new RemoveDuplicates_26();

    @Test
    public void test() {
        int[] nums = new int[]{1, 1, 2};
        int length = nums.length;
        assertEquals(2, test.removeDuplicates(nums));
        assertEquals(length, nums.length);
        int[] expected = new int[]{1, 2};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], nums[i]);
        }
    }

    @Test
    public void test1() {
        int[] nums = new int[]{1, 2, 3, 4};
        int length = nums.length;
        assertEquals(4, test.removeDuplicates(nums));
        assertEquals(length, nums.length);
        int[] expected = new int[]{1, 2, 3, 4};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], nums[i]);
        }
    }

    @Test
    public void test2() {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int length = nums.length;
        assertEquals(5, test.removeDuplicates(nums));
        assertEquals(length, nums.length);
        int[] expected = new int[]{0, 1, 2, 3, 4};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], nums[i]);
        }
    }

    @Test
    public void test3() {
        int[] nums = new int[]{1, 1};
        int length = nums.length;
        assertEquals(1, test.removeDuplicates(nums));
        assertEquals(length, nums.length);
        int[] expected = new int[]{1};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], nums[i]);
        }
    }

    @Test
    public void testSingleElement() {
        assertEquals(1, test.removeDuplicates(new int[]{5}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.removeDuplicates(new int[]{}));
    }

    @Test
    public void testAllSameElements() {
        int[] nums = new int[]{7, 7, 7, 7, 7};
        assertEquals(1, test.removeDuplicates(nums));
        assertEquals(7, nums[0]);
    }

    @Test
    public void testNegativeNumbers() {
        int[] nums = new int[]{-3, -3, -1, 0, 0, 2};
        assertEquals(4, test.removeDuplicates(nums));
        assertEquals(-3, nums[0]);
        assertEquals(-1, nums[1]);
        assertEquals(0, nums[2]);
        assertEquals(2, nums[3]);
    }

    @Test
    public void testAlreadyUnique() {
        int[] nums = new int[]{-2, -1, 0, 1, 2};
        assertEquals(5, test.removeDuplicates(nums));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[10000];
        for (int i = 0; i < 10000; i++) {
            nums[i] = i / 5;
        }
        assertEquals(2000, test.removeDuplicates(nums));
    }

    @Test
    public void testTwoDistinctElements() {
        int[] nums = new int[]{1, 1, 1, 2, 2, 2};
        assertEquals(2, test.removeDuplicates(nums));
        assertEquals(1, nums[0]);
        assertEquals(2, nums[1]);
    }

    @Test
    public void testTwoSameElements() {
        int[] nums = new int[]{4, 4};
        assertEquals(1, test.removeDuplicates(nums));
        assertEquals(4, nums[0]);
    }

    @Test
    public void testTwoDifferentElements() {
        int[] nums = new int[]{3, 7};
        assertEquals(2, test.removeDuplicates(nums));
        assertEquals(3, nums[0]);
        assertEquals(7, nums[1]);
    }

    @Test
    public void testAllNegativeValues() {
        int[] nums = new int[]{-10, -10, -5, -5, -5, -1, -1};
        int k = test.removeDuplicates(nums);
        assertEquals(3, k);
        assertEquals(-10, nums[0]);
        assertEquals(-5, nums[1]);
        assertEquals(-1, nums[2]);
    }

    @Test
    public void testNegativeToPositiveRange() {
        int[] nums = new int[]{Integer.MIN_VALUE, -1, -1, 0, 0, 1, Integer.MAX_VALUE, Integer.MAX_VALUE};
        int k = test.removeDuplicates(nums);
        assertEquals(5, k);
        assertEquals(Integer.MIN_VALUE, nums[0]);
        assertEquals(-1, nums[1]);
        assertEquals(0, nums[2]);
        assertEquals(1, nums[3]);
        assertEquals(Integer.MAX_VALUE, nums[4]);
    }

    @Test
    public void testLargeSortedWithDuplicates_seed42() {
        Random rand = new Random(42L);
        int[] nums = new int[2000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(500);
        }
        Arrays.sort(nums);
        TreeSet<Integer> unique = new TreeSet<>();
        for (int n : nums) {
            unique.add(n);
        }

        int k = test.removeDuplicates(nums);
        assertEquals(unique.size(), k);

        int idx = 0;
        for (int val : unique) {
            assertEquals(val, nums[idx++]);
        }
    }

    @Test
    public void testProperty_returnedKElementsAreSortedAndDistinct() {
        int[] nums = new int[]{-5, -5, -3, -3, 0, 0, 0, 2, 4, 4, 7, 7, 7, 9};
        int k = test.removeDuplicates(nums);

        // Property: first k elements are strictly ascending (sorted and distinct)
        for (int i = 1; i < k; i++) {
            assertTrue(nums[i] > nums[i - 1], "nums[" + i + "] should be > nums[" + (i - 1) + "]");
        }
    }

    @Test
    public void testProperty_returnedKEqualsUniqueCount() {
        int[] nums = new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
        int[] copy = nums.clone();
        int k = test.removeDuplicates(nums);

        long uniqueCount = Arrays.stream(copy).distinct().count();
        assertEquals(uniqueCount, k);
    }

    @Test
    public void testLeetCodeExample1() {
        int[] nums = new int[]{1, 1, 2};
        int k = test.removeDuplicates(nums);
        assertEquals(2, k);
        assertEquals(1, nums[0]);
        assertEquals(2, nums[1]);
    }

    @Test
    public void testLeetCodeExample2() {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k = test.removeDuplicates(nums);
        assertEquals(5, k);
        int[] expected = {0, 1, 2, 3, 4};
        for (int i = 0; i < k; i++) {
            assertEquals(expected[i], nums[i]);
        }
    }
}
