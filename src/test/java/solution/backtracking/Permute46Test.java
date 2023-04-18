package solution.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author BorisMirage
 * Time: 2023/04/17 22:03
 * Created with IntelliJ IDEA
 */

public class Permute46Test {

    private final Permute_46 test = new Permute_46();

    @Test
    public void testPermute() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> expectedOutput = new ArrayList<>();
        expectedOutput.add(Arrays.asList(1, 2, 3));
        expectedOutput.add(Arrays.asList(1, 3, 2));
        expectedOutput.add(Arrays.asList(2, 1, 3));
        expectedOutput.add(Arrays.asList(2, 3, 1));
        expectedOutput.add(Arrays.asList(3, 1, 2));
        expectedOutput.add(Arrays.asList(3, 2, 1));
        List<List<Integer>> actualOutput = test.permute(nums);
        assertEquals(expectedOutput.size(), actualOutput.size());
        assertTrue(expectedOutput.containsAll(actualOutput));
        assertTrue(actualOutput.containsAll(expectedOutput));
    }

    @Test
    public void testPermuteWithEmptyArray() {
        int[] nums = {};
        List<List<Integer>> expectedOutput = new ArrayList<>();
        List<List<Integer>> actualOutput = test.permute(nums);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPermuteWithNullArray() {
        int[] nums = null;
        List<List<Integer>> expectedOutput = new ArrayList<>();
        List<List<Integer>> actualOutput = test.permute(nums);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPermuteWithSingleElement() {
        int[] nums = {1};
        List<List<Integer>> expectedOutput = new ArrayList<>();
        expectedOutput.add(Collections.singletonList(1));
        List<List<Integer>> actualOutput = test.permute(nums);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPermuteWithLargeArray() {
        int[] nums = {1, 2, 3, 4, 5};
        List<List<Integer>> actualOutput = test.permute(nums);
        assertEquals(120, actualOutput.size());
    }

    @Test
    public void testPermuteWithNegativeElements() {
        int[] nums = {-1, -2, 3, 4};
        List<List<Integer>> expectedOutput = new ArrayList<>();
        expectedOutput.add(Arrays.asList(-1, -2, 3, 4));
        expectedOutput.add(Arrays.asList(-1, -2, 4, 3));
        expectedOutput.add(Arrays.asList(-1, 3, -2, 4));
        expectedOutput.add(Arrays.asList(-1, 3, 4, -2));
        expectedOutput.add(Arrays.asList(-1, 4, -2, 3));
        expectedOutput.add(Arrays.asList(-1, 4, 3, -2));
        expectedOutput.add(Arrays.asList(-2, -1, 3, 4));
        expectedOutput.add(Arrays.asList(-2, -1, 4, 3));
        expectedOutput.add(Arrays.asList(-2, 3, -1, 4));
        expectedOutput.add(Arrays.asList(-2, 3, 4, -1));
        expectedOutput.add(Arrays.asList(-2, 4, -1, 3));
        expectedOutput.add(Arrays.asList(-2, 4, 3, -1));
        expectedOutput.add(Arrays.asList(3, -1, -2, 4));
        expectedOutput.add(Arrays.asList(3, -1, 4, -2));
        expectedOutput.add(Arrays.asList(3, -2, -1, 4));
        expectedOutput.add(Arrays.asList(3, -2, 4, -1));
        expectedOutput.add(Arrays.asList(3, 4, -1, -2));
        expectedOutput.add(Arrays.asList(3, 4, -2, -1));
        expectedOutput.add(Arrays.asList(4, -1, -2, 3));
        expectedOutput.add(Arrays.asList(4, -1, 3, -2));
        expectedOutput.add(Arrays.asList(4, -2, -1, 3));
        expectedOutput.add(Arrays.asList(4, -2, 3, -1));
        expectedOutput.add(Arrays.asList(4, 3, -1, -2));
        expectedOutput.add(Arrays.asList(4, 3, -2, -1));
        List<List<Integer>> actualOutput = test.permute(nums);
        assertEquals(expectedOutput.size(), actualOutput.size());
        assertTrue(expectedOutput.containsAll(actualOutput));
        assertTrue(actualOutput.containsAll(expectedOutput));
    }
}