package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/04 11:01
 * Created with IntelliJ IDEA
 */

public class RemoveDuplicates26Test {

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
}