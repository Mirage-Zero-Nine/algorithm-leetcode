package solution.slidingWindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import solution.slidingwindow.MinimumSumSubarray;

import java.util.List;

/**
 * @author BorisMirage
 * Time: 2024/11/29 11:54
 * Created with IntelliJ IDEA
 */

public class MinimumSumSubarrayTest {
    private final MinimumSumSubarray solution = new MinimumSumSubarray();
    private int output;
    private List<Integer> nums;

    @Test
    public void testBasicCase() {
        nums = Lists.newArrayList(2, 3, -1, 4, -2, 1, 5);
        output = solution.minimumSumSubarray(nums, 3, 5);
        assertEquals(1, output);
    }

    @Test
    public void testEdgeCaseEmptyList() {
        nums = Lists.newArrayList();
        output = solution.minimumSumSubarray(nums, 0, 0);
        assertEquals(-1, output);
    }

    @Test
    public void testNoValidSubarray() {
        nums = Lists.newArrayList(1, 2, 3);
        output = solution.minimumSumSubarray(nums, 4, 5);
        assertEquals(-1, output);
    }

    @Test
    public void testSingleElement() {
        nums = Lists.newArrayList(3);
        output = solution.minimumSumSubarray(nums, 1, 1);
        assertEquals(3, output);
    }

    @Test
    public void testAllPositiveNumbers() {
        nums = Lists.newArrayList(1, 2, 3, 4, 5);
        output = solution.minimumSumSubarray(nums, 1, 3);
        assertEquals(1, output);
    }

    @Test
    public void testAllNegativeNumbers() {
        nums = Lists.newArrayList(-1, -2, -3, -4, -5);
        output = solution.minimumSumSubarray(nums, 2, 4);
        assertEquals(-1, output);
    }

    @Test
    public void testSingleSubarraySize() {
        nums = Lists.newArrayList(4, 7, 2, 5);
        output = solution.minimumSumSubarray(nums, 1, 1);
        assertEquals(2, output);
    }

    @Test
    public void testMultipleValidSubarrays() {
        nums = Lists.newArrayList(-1, -3, 2, 1, -5, 4, -2);
        output = solution.minimumSumSubarray(nums, 3, 5);
        assertEquals(2, output);
    }

    @Test
    public void testMinimumSumSubarrayOutOfRange() {
        nums = Lists.newArrayList(1, 2, 3, 4, 5);
        output = solution.minimumSumSubarray(nums, 5, 6);
        assertEquals(-1, output);
    }

    @Test
    public void testNegativeNumbersWithinPositiveSubarray() {
        nums = Lists.newArrayList(3, -1, -2, 4, 6, -5, 7);
        output = solution.minimumSumSubarray(nums, 2, 4);
        assertEquals(1, output);
    }
}
