package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
