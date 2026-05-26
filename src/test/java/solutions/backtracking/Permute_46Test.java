package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Permute_46Test {
    private final Permute_46 solution = new Permute_46();

    @Test
    void testThreeElements() {
        List<List<Integer>> result = solution.permute(new int[]{1, 2, 3});
        assertEquals(6, result.size());
    }

    @Test
    void testTwoElements() {
        List<List<Integer>> result = solution.permute(new int[]{0, 1});
        assertEquals(2, result.size());
    }

    @Test
    void testSingleElement() {
        List<List<Integer>> result = solution.permute(new int[]{1});
        assertEquals(1, result.size());
    }

    @Test
    void testFourElements() {
        List<List<Integer>> result = solution.permute(new int[]{1, 2, 3, 4});
        assertEquals(24, result.size());
    }

    @Test
    void testNegativeNumbers() {
        List<List<Integer>> result = solution.permute(new int[]{-1, 0, 1});
        assertEquals(6, result.size());
    }

    @Test
    void testNullInput() {
        List<List<Integer>> result = solution.permute(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testEmptyArray() {
        List<List<Integer>> result = solution.permute(new int[]{});
        assertTrue(result.isEmpty());
    }

    @Test
    void testFiveElements() {
        List<List<Integer>> result = solution.permute(new int[]{1, 2, 3, 4, 5});
        assertEquals(120, result.size());
    }

    @Test
    void testSixElements() {
        List<List<Integer>> result = solution.permute(new int[]{1, 2, 3, 4, 5, 6});
        assertEquals(720, result.size());
    }

    @Test
    void testAllNegative() {
        List<List<Integer>> result = solution.permute(new int[]{-3, -2, -1});
        assertEquals(6, result.size());
    }

    @Test
    void testGiantCase() {
        // 7! = 5040
        List<List<Integer>> result = solution.permute(new int[]{1, 2, 3, 4, 5, 6, 7});
        assertEquals(5040, result.size());
    }

    @Test
    void testTwoElementsExactPermutations() {
        List<List<Integer>> result = solution.permute(new int[]{5, 10});
        Set<List<Integer>> actual = new HashSet<>(result);
        Set<List<Integer>> expected = Set.of(Arrays.asList(5, 10), Arrays.asList(10, 5));
        assertEquals(expected, actual);
    }

    @Test
    void testThreeElementsExactPermutations() {
        List<List<Integer>> result = solution.permute(new int[]{1, 2, 3});
        Set<List<Integer>> actual = new HashSet<>(result);
        Set<List<Integer>> expected = Set.of(
                Arrays.asList(1, 2, 3), Arrays.asList(1, 3, 2),
                Arrays.asList(2, 1, 3), Arrays.asList(2, 3, 1),
                Arrays.asList(3, 1, 2), Arrays.asList(3, 2, 1));
        assertEquals(expected, actual);
    }

    @Test
    void testAllResultsAreUnique() {
        List<List<Integer>> result = solution.permute(new int[]{1, 2, 3, 4, 5});
        Set<List<Integer>> unique = new HashSet<>(result);
        assertEquals(result.size(), unique.size(), "All permutations should be unique");
    }

    @Test
    void testEveryResultContainsSameElements() {
        int[] input = {-5, 0, 7, 13};
        List<Integer> sortedInput = Arrays.stream(input).sorted().boxed().toList();
        List<List<Integer>> result = solution.permute(input);
        for (List<Integer> perm : result) {
            List<Integer> sorted = perm.stream().sorted().toList();
            assertEquals(sortedInput, sorted, "Each permutation must contain the same elements");
        }
    }

    @Test
    void testEveryInputElementAppearsInEveryResult() {
        int[] input = {2, 4, 6};
        List<List<Integer>> result = solution.permute(input);
        for (int num : input) {
            for (List<Integer> perm : result) {
                assertTrue(perm.contains(num), num + " should appear in every permutation");
            }
        }
    }

    @Test
    void testNegativeNumbersExactPermutations() {
        List<List<Integer>> result = solution.permute(new int[]{-1, -2});
        Set<List<Integer>> actual = new HashSet<>(result);
        Set<List<Integer>> expected = Set.of(Arrays.asList(-1, -2), Arrays.asList(-2, -1));
        assertEquals(expected, actual);
    }

    @Test
    void testLargeValuesPermutation() {
        List<List<Integer>> result = solution.permute(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE});
        assertEquals(6, result.size());
        Set<List<Integer>> unique = new HashSet<>(result);
        assertEquals(6, unique.size());
        for (List<Integer> perm : result) {
            assertEquals(3, perm.size());
            assertTrue(perm.contains(Integer.MIN_VALUE));
            assertTrue(perm.contains(0));
            assertTrue(perm.contains(Integer.MAX_VALUE));
        }
    }

    @Test
    void testCountEqualsFactorial() {
        for (int n = 1; n <= 6; n++) {
            int[] input = new int[n];
            for (int i = 0; i < n; i++) input[i] = i + 1;
            List<List<Integer>> result = solution.permute(input);
            int factorial = 1;
            for (int i = 2; i <= n; i++) factorial *= i;
            assertEquals(factorial, result.size(), "n=" + n + " should produce n! permutations");
        }
    }

    @Test
    void testSingleElementExactResult() {
        List<List<Integer>> result = solution.permute(new int[]{42});
        assertEquals(1, result.size());
        assertEquals(List.of(42), result.get(0));
    }
}
