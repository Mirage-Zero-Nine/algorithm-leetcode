package solution.slidingWindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import solution.slidingwindow.MaximumUniqueSubarray_1695;

/**
 * @author BorisMirage
 * Time: 2024/11/30 11:42
 * Created with IntelliJ IDEA
 */

public class MaximumUniqueSubarray_1695Test {
    private final MaximumUniqueSubarray_1695 test = new MaximumUniqueSubarray_1695();

    @Test
    public void testNormalCase() {
        int[] nums = {4, 2, 4, 5, 6};
        int result = test.maximumUniqueSubarray(nums);
        assertEquals(17, result);
    }

    @Test
    public void testArrayWithAllUniqueElements() {
        int[] nums = {1, 2, 3, 4, 5};
        int result = test.maximumUniqueSubarray(nums);
        assertEquals(15, result);
    }

    @Test
    public void testArrayWithAllIdenticalElements() {
        int[] nums = {5, 5, 5, 5, 5};
        int result = test.maximumUniqueSubarray(nums);
        assertEquals(5, result);
    }

    @Test
    public void testArrayWithOneElement() {
        int[] nums = {10};
        int result = test.maximumUniqueSubarray(nums);
        assertEquals(10, result);
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        int result = test.maximumUniqueSubarray(nums);
        assertEquals(0, result);
    }

    @Test
    public void testArrayWithMultipleIdenticalSubarrays() {
        int[] nums = {1, 2, 1, 2, 3, 4};
        int result = test.maximumUniqueSubarray(nums);
        assertEquals(10, result);
    }

    @Test
    public void testArrayWithAllNegativeElements() {
        int[] nums = {-1, -2, -3, -4};
        int result = test.maximumUniqueSubarray(nums);
        assertEquals(-1, result);
    }

    @Test
    public void testArrayWithZero() {
        int[] nums = {0, 2, 3, 0, 4};
        int result = test.maximumUniqueSubarray(nums);
        assertEquals(9, result);
    }

    @Test
    public void testArrayWithSingleDuplicateAtEnd() {
        int[] nums = {7, 8, 9, 7};
        int result = test.maximumUniqueSubarray(nums);
        assertEquals(24, result);
    }

    @Test
    public void testArrayWithDuplicatesAtTheBeginning() {
        int[] nums = {5, 5, 6, 7, 8};
        int result = test.maximumUniqueSubarray(nums);
        assertEquals(26, result);
    }

    @Test
    public void testArrayWithLargeNumbers() {
        int[] nums = {1000, 2000, 3000, 4000, 1000, 2000};
        int result = test.maximumUniqueSubarray(nums);
        assertEquals(10000, result);
    }
}