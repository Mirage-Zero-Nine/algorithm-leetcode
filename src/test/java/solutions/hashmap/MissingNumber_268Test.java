package solutions.hashmap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MissingNumber_268Test {
    private final MissingNumber_268 solution = new MissingNumber_268();

    @Test
    void testBasic() {
        assertEquals(2, solution.missingNumber(new int[]{3, 0, 1}));
    }

    @Test
    void testMissingLast() {
        assertEquals(8, solution.missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    @Test
    void testMissingFirst() {
        assertEquals(0, solution.missingNumber(new int[]{1}));
    }

    @Test
    void testTwoElements() {
        assertEquals(2, solution.missingNumber(new int[]{0, 1}));
    }

    @Test
    void testSingleElement() {
        assertEquals(1, solution.missingNumber(new int[]{0}));
    }

    @Test
    void testMissingMiddle() {
        assertEquals(2, solution.missingNumber(new int[]{0, 1, 3}));
    }

    @Test
    void testBitManipulation() {
        assertEquals(2, solution.missingNumberBitManipulation(new int[]{3, 0, 1}));
        assertEquals(8, solution.missingNumberBitManipulation(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    @Test
    void testNaiveSolution() {
        assertEquals(2, solution.naiveSolution(new int[]{3, 0, 1}));
        assertEquals(0, solution.naiveSolution(new int[]{}));
    }

    @Test
    void testBinarySearch() {
        assertEquals(2, solution.binarySearch(new int[]{3, 0, 1}));
        assertEquals(8, solution.binarySearch(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    @Test
    void testMissingN() {
        assertEquals(3, solution.missingNumberBitManipulation(new int[]{0, 1, 2}));
    }

    @Test
    void testGiantCase() {
        int n = 10000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        // missing 0
        assertEquals(0, solution.missingNumberBitManipulation(nums));
    }

    @Test
    void testSingleElementZeroAllImpls() {
        int[] nums = {0};
        assertEquals(1, solution.missingNumber(nums.clone()));
        assertEquals(1, solution.missingNumberBitManipulation(nums.clone()));
        assertEquals(1, solution.naiveSolution(nums.clone()));
        assertEquals(1, solution.binarySearch(nums.clone()));
    }

    @Test
    void testSingleElementOneAllImpls() {
        int[] nums = {1};
        assertEquals(0, solution.missingNumber(nums.clone()));
        assertEquals(0, solution.missingNumberBitManipulation(nums.clone()));
        assertEquals(0, solution.naiveSolution(nums.clone()));
        assertEquals(0, solution.binarySearch(nums.clone()));
    }

    @Test
    void testSequentialMissingNAllImpls() {
        // [0,1,2,3,4] -> missing 5
        int[] nums = {0, 1, 2, 3, 4};
        assertEquals(5, solution.missingNumber(nums.clone()));
        assertEquals(5, solution.missingNumberBitManipulation(nums.clone()));
        assertEquals(5, solution.naiveSolution(nums.clone()));
        assertEquals(5, solution.binarySearch(nums.clone()));
    }

    @Test
    void testSequentialMissingZeroAllImpls() {
        // [1,2,3,4,5] -> missing 0
        int[] nums = {1, 2, 3, 4, 5};
        assertEquals(0, solution.missingNumber(nums.clone()));
        assertEquals(0, solution.missingNumberBitManipulation(nums.clone()));
        assertEquals(0, solution.naiveSolution(nums.clone()));
        assertEquals(0, solution.binarySearch(nums.clone()));
    }

    @Test
    void testMissingMiddleAllImpls() {
        // [0,1,3,4,5] -> missing 2
        int[] nums = {0, 1, 3, 4, 5};
        assertEquals(2, solution.missingNumber(nums.clone()));
        assertEquals(2, solution.missingNumberBitManipulation(nums.clone()));
        assertEquals(2, solution.naiveSolution(nums.clone()));
        assertEquals(2, solution.binarySearch(nums.clone()));
    }

    @Test
    void testLeetCodeExamplesAllImpls() {
        int[][] inputs = {{3, 0, 1}, {0, 1}, {9, 6, 4, 2, 3, 5, 7, 0, 1}};
        int[] expected = {2, 2, 8};
        for (int i = 0; i < inputs.length; i++) {
            assertEquals(expected[i], solution.missingNumber(inputs[i].clone()));
            assertEquals(expected[i], solution.missingNumberBitManipulation(inputs[i].clone()));
            assertEquals(expected[i], solution.naiveSolution(inputs[i].clone()));
            assertEquals(expected[i], solution.binarySearch(inputs[i].clone()));
        }
    }

    @Test
    void testLargeN10000RandomSeed42() {
        int n = 10000;
        int missing = 4217; // arbitrary missing value
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if (i != missing) {
                list.add(i);
            }
        }
        Collections.shuffle(list, new Random(42L));
        int[] nums = list.stream().mapToInt(Integer::intValue).toArray();

        assertEquals(missing, solution.missingNumberBitManipulation(nums.clone()));
        assertEquals(missing, solution.naiveSolution(nums.clone()));
        assertEquals(missing, solution.binarySearch(nums.clone()));
    }

    @Test
    void testPropertyResultInRange() {
        // For any valid input of size n, result must be in [0, n]
        Random rng = new Random(42L);
        for (int trial = 0; trial < 20; trial++) {
            int n = rng.nextInt(100) + 1;
            int missing = rng.nextInt(n + 1);
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                if (i != missing) {
                    list.add(i);
                }
            }
            Collections.shuffle(list, rng);
            int[] nums = list.stream().mapToInt(Integer::intValue).toArray();

            int result = solution.missingNumberBitManipulation(nums);
            assertTrue(result >= 0 && result <= n, "Result " + result + " out of range [0," + n + "]");
            assertEquals(missing, result);
        }
    }
}
