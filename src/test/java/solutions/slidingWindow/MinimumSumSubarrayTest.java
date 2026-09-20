package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

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

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource({"1|2,1,1,1", "1|2|3,2,2,3", "-1|-2,1,2,-1", "5|-4|3,1,2,1", "0|0|0,1,3,-1", "4|1|2|3,2,3,3", "-5|10|-2|4,2,4,2", "7,1,1,7", "1|1|1,3,3,3", "2|-1|2|-1,2,4,1"})
    void additionalBoundaryCases(String encoded, int minSize, int maxSize, int expected) {
        nums = java.util.Arrays.stream(encoded.split("\\|" )).map(Integer::valueOf).toList();
        assertEquals(expected, solution.minimumSumSubarray(nums, minSize, maxSize));
    }
}
