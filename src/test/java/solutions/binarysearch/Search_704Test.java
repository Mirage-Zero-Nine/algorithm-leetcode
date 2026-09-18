package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/05/22 11:54
 * Created with IntelliJ IDEA
 */

public class Search_704Test {
    private final Search_704 test = new Search_704();

    @Test
    public void testTargetExistsInMiddle() {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        assertEquals(4, test.search(nums, target));
    }

    @Test
    public void testTargetDoesNotExist() {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 2;
        assertEquals(-1, test.search(nums, target));
    }

    @Test
    public void testTargetAtBeginning() {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 1;
        assertEquals(0, test.search(nums, target));
    }

    @Test
    public void testTargetAtEnd() {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 5;
        assertEquals(4, test.search(nums, target));
    }

    @Test
    public void testTwoElementArrayChecksBothCandidates() {
        assertEquals(0, test.search(new int[]{2, 7}, 2));
        assertEquals(1, test.search(new int[]{2, 7}, 7));
        assertEquals(-1, test.search(new int[]{2, 7}, 5));
    }

    @Test
    public void testSingleElementFound() {
        int[] nums = {1};
        int target = 1;
        assertEquals(0, test.search(nums, target));
    }

    @Test
    public void testSingleElementNotFound() {
        int[] nums = {1};
        int target = 2;
        assertEquals(-1, test.search(nums, target));
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        int target = 3;
        assertEquals(-1, test.search(nums, target));
    }


    @Test
    public void testLargeArrayNoOverflow() {
        int size = 10_000;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = i - 10_000;
        }

        int target = -1;
        int result = test.search(nums, target);
        assertEquals(size - 1, result);
    }

    @Test
    public void testNullArray() {
        assertEquals(-1, test.search(null, 10));
    }

    @Test
    public void testTargetLessThanMinimum() {
        int[] nums = {2, 4, 6, 8, 10};
        assertEquals(-1, test.search(nums, 1));
    }

    @Test
    public void testTargetGreaterThanMaximum() {
        int[] nums = {-8, -3, 0, 4, 11};
        assertEquals(-1, test.search(nums, 12));
    }

    @Test
    public void testEveryValueAndGapInGiantArray() {
        int[] values = new int[10_000];
        for (int i = 0; i < values.length; i++) {
            values[i] = 2 * i - 10_000;
        }

        for (int i = 0; i < values.length; i++) {
            assertEquals(i, test.search(values, values[i]));
            assertEquals(-1, test.search(values, values[i] + 1));
        }
    }

    @Test
    public void testIntegerExtremesCanBeFoundWithoutArithmeticOverflow() {
        int[] values = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE};
        for (int i = 0; i < values.length; i++) {
            assertEquals(i, test.search(values, values[i]));
        }

        assertEquals(-1, test.search(values, Integer.MAX_VALUE - 1));
    }

    @Test
    public void testRepeatedLookupsLeaveInputUnchanged() {
        int[] values = {-100, -4, 8, 19, 101};
        int[] original = values.clone();
        assertEquals(3, test.search(values, 19));
        assertEquals(-1, test.search(values, 20));
        assertArrayEquals(original, values);
    }
}
