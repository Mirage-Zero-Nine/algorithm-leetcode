package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class ThreeSum_15Test {

    private final ThreeSum_15 test = new ThreeSum_15();

    @Test
    public void testHappyCases() {
        List<List<Integer>> result = test.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        assertEquals(2, result.size());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.threeSum(new int[]{}).size());
        assertEquals(0, test.threeSum(new int[]{0}).size());
        assertEquals(1, test.threeSum(new int[]{0, 0, 0}).size());
    }

    @Test
    public void testLargeCase() {
        List<List<Integer>> result = test.threeSum(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6});
        assertTrue(result.size() > 0);
    }

    @Test
    public void testAdditionalHappyCases() {
        assertTripletsEqual(
            List.of(List.of(-2, 0, 2), List.of(-2, 1, 1)),
            test.threeSum(new int[]{-2, 0, 1, 1, 2})
        );
        assertTripletsEqual(
            List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)),
            test.threeSum(new int[]{-1, -1, 0, 1, 2})
        );
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertEquals(0, test.threeSum(new int[]{1, 2, 3, 4}).size());
        assertEquals(0, test.threeSum(new int[]{-4, -3, -2}).size());
        assertTripletsEqual(List.of(List.of(0, 0, 0)), test.threeSum(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testAdditionalGiantCase() {
        List<List<Integer>> result = test.threeSum(new int[]{-6, -4, -2, 0, 2, 4, 6, -1, 1, -3, 3, -5, 5});
        assertTrue(result.size() >= 10);
    }

    @Test
    public void testAllPositive() {
        assertEquals(0, test.threeSum(new int[]{1, 2, 3, 4, 5, 6}).size());
    }

    @Test
    public void testDuplicateElements() {
        List<List<Integer>> result = test.threeSum(new int[]{0, 0, 0, 0, 0});
        assertEquals(1, result.size());
        assertEquals(List.of(0, 0, 0), result.get(0));
    }

    @Test
    public void testTwoElements() {
        assertEquals(0, test.threeSum(new int[]{-1, 1}).size());
    }

    @Test
    public void testGiantWithManyDuplicates() {
        int[] nums = new int[300];
        for (int i = 0; i < 100; i++) nums[i] = -1;
        for (int i = 100; i < 200; i++) nums[i] = 0;
        for (int i = 200; i < 300; i++) nums[i] = 1;
        List<List<Integer>> result = test.threeSum(nums);
        // should contain [-1, 0, 1] and [0, 0, 0]
        assertEquals(2, result.size());
    }

    @Test
    public void testEmptyArray() {
        assertEquals(List.of(), test.threeSum(new int[]{}));
    }

    @Test
    public void testLessThanThreeElements() {
        assertEquals(List.of(), test.threeSum(new int[]{0}));
        assertEquals(List.of(), test.threeSum(new int[]{-1, 1}));
    }

    @Test
    public void testAllZeros() {
        List<List<Integer>> result = test.threeSum(new int[]{0, 0, 0, 0, 0});
        assertEquals(Set.of(List.of(0, 0, 0)), new HashSet<>(result));
    }

    @Test
    public void testAllNegatives() {
        assertEquals(List.of(), test.threeSum(new int[]{-5, -4, -3, -2, -1}));
    }

    @Test
    public void testThreeElementsTriplet() {
        // [3, -3, 0] sorted -> [-3, 0, 3]
        List<List<Integer>> result = test.threeSum(new int[]{3, -3, 0});
        assertEquals(Set.of(List.of(-3, 0, 3)), new HashSet<>(result));
    }

    @Test
    public void testLeetCodeExample() {
        List<List<Integer>> result = test.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        Set<List<Integer>> expected = Set.of(List.of(-1, -1, 2), List.of(-1, 0, 1));
        assertEquals(expected, new HashSet<>(result));
    }

    @Test
    public void testManyDuplicatesDeduplication() {
        // [-2,-2,-2,0,0,0,2,2,2] should only produce [-2,0,2] and [0,0,0]
        List<List<Integer>> result = test.threeSum(new int[]{-2, -2, -2, 0, 0, 0, 2, 2, 2});
        Set<List<Integer>> expected = Set.of(List.of(-2, 0, 2), List.of(0, 0, 0));
        assertEquals(expected, new HashSet<>(result));
    }

    @Test
    public void testLargeRandomCrossCheckBruteForce() {
        Random rng = new Random(42L);
        int[] nums = new int[200];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rng.nextInt(201) - 100; // [-100, 100]
        }

        List<List<Integer>> result = test.threeSum(nums);

        // Brute-force to find all unique triplets
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        Set<List<Integer>> bruteForce = new HashSet<>();
        for (int i = 0; i < sorted.length - 2; i++) {
            for (int j = i + 1; j < sorted.length - 1; j++) {
                for (int k = j + 1; k < sorted.length; k++) {
                    if (sorted[i] + sorted[j] + sorted[k] == 0) {
                        bruteForce.add(List.of(sorted[i], sorted[j], sorted[k]));
                    }
                }
            }
        }

        assertEquals(bruteForce, new HashSet<>(result));
    }

    @Test
    public void testPropertyEveryTripletSumsToZero() {
        int[] nums = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
        List<List<Integer>> result = test.threeSum(nums);
        for (List<Integer> triplet : result) {
            assertEquals(0, triplet.get(0) + triplet.get(1) + triplet.get(2),
                "Triplet does not sum to 0: " + triplet);
        }
    }

    @Test
    public void testPropertyNoDuplicateTripletsAndSortedInternally() {
        int[] nums = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
        List<List<Integer>> result = test.threeSum(nums);

        // No duplicate triplets
        Set<List<Integer>> asSet = new HashSet<>(result);
        assertEquals(result.size(), asSet.size(), "Duplicate triplets found");

        // Each triplet is sorted internally
        for (List<Integer> triplet : result) {
            assertEquals(3, triplet.size());
            assertTrue(triplet.get(0) <= triplet.get(1) && triplet.get(1) <= triplet.get(2),
                "Triplet not sorted: " + triplet);
        }
    }

    private void assertTripletsEqual(List<List<Integer>> expected, List<List<Integer>> actual) {
        Comparator<List<Integer>> cmp = Comparator
            .comparing((List<Integer> triplet) -> triplet.get(0))
            .thenComparing(triplet -> triplet.get(1))
            .thenComparing(triplet -> triplet.get(2));
        assertEquals(expected.stream().sorted(cmp).toList(), actual.stream().sorted(cmp).toList());
    }
}
