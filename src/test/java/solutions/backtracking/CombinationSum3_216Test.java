package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CombinationSum3_216Test {
    private final CombinationSum3_216 solution = new CombinationSum3_216();

    @Test
    void testBasic() {
        List<List<Integer>> result = solution.combinationSum3(3, 7);
        assertEquals(Set.of("1,2,4"), canonical(result));
    }

    @Test
    void testMultipleSolutions() {
        List<List<Integer>> result = solution.combinationSum3(3, 9);
        assertEquals(Set.of("1,2,6", "1,3,5", "2,3,4"), canonical(result));
    }

    @Test
    void testNoSolution() {
        List<List<Integer>> result = solution.combinationSum3(4, 1);
        assertTrue(result.isEmpty());
    }

    @Test
    void testLargeK() {
        List<List<Integer>> result = solution.combinationSum3(9, 45);
        assertEquals(Set.of("1,2,3,4,5,6,7,8,9"), canonical(result));
    }

    @Test
    void testSmallN() {
        List<List<Integer>> result = solution.combinationSum3(2, 3);
        assertEquals(Set.of("1,2"), canonical(result));
    }

    @Test
    void testKZero() {
        List<List<Integer>> result = solution.combinationSum3(0, 5);
        assertTrue(result.isEmpty());
    }

    @Test
    void testNZero() {
        List<List<Integer>> result = solution.combinationSum3(2, 0);
        assertTrue(result.isEmpty());
    }

    @Test
    void testNegativeK() {
        List<List<Integer>> result = solution.combinationSum3(-1, 5);
        assertTrue(result.isEmpty());
    }

    @Test
    void testNTooLarge() {
        // max sum with k=2 is 8+9=17, so n=18 should give 0
        List<List<Integer>> result = solution.combinationSum3(2, 18);
        assertTrue(result.isEmpty());
    }

    @Test
    void testK2N17() {
        // only [8,9] sums to 17
        List<List<Integer>> result = solution.combinationSum3(2, 17);
        assertEquals(Set.of("8,9"), canonical(result));
    }

    @Test
    void testK3N15() {
        List<List<Integer>> result = solution.combinationSum3(3, 15);
        // combinations: [1,5,9],[1,6,8],[2,4,9],[2,5,8],[2,6,7],[3,4,8],[3,5,7],[4,5,6]
        assertEquals(Set.of("1,5,9", "1,6,8", "2,4,9", "2,5,8",
                "2,6,7", "3,4,8", "3,5,7", "4,5,6"), canonical(result));
    }

    @Test
    void testGiantK9N45() {
        // only one combination: [1,2,3,4,5,6,7,8,9]
        List<List<Integer>> result = solution.combinationSum3(9, 45);
        assertEquals(Set.of("1,2,3,4,5,6,7,8,9"), canonical(result));
    }

    /**
     * Enumerates every official (k, n) pair and compares with an independent
     * bitmask oracle. This checks completeness as well as the no-duplicates
     * requirement without relying on the implementation's traversal.
     */
    @Test
    void testEveryOfficialInputAgainstBitmaskOracle() {
        for (int k = 2; k <= 9; k++) {
            for (int n = 1; n <= 60; n++) {
                List<List<Integer>> actual = solution.combinationSum3(k, n);
                Set<String> actualCanonical = canonical(actual);
                assertEquals(actual.size(), actualCanonical.size(),
                        "duplicate combinations for k=" + k + ", n=" + n);
                assertEquals(expected(k, n), actualCanonical,
                        "wrong combinations for k=" + k + ", n=" + n);
            }
        }
    }

    @Test
    void testMinimumAndMaximumTargets() {
        assertEquals(Set.of("1,2,3,4"), canonical(solution.combinationSum3(4, 10)));
        assertEquals(Set.of("6,7,8,9"), canonical(solution.combinationSum3(4, 30)));
        assertEquals(Set.of("1,2,3,4,5,6,7,8"), canonical(solution.combinationSum3(8, 36)));
        assertTrue(solution.combinationSum3(8, 35).isEmpty());
        assertTrue(solution.combinationSum3(8, 45).isEmpty());
    }

    @Test
    void testNoDuplicateCombinationsAndCombinationProperties() {
        List<List<Integer>> result = solution.combinationSum3(5, 25);
        Set<String> unique = canonical(result);
        assertEquals(unique.size(), result.size());
        for (List<Integer> combination : result) {
            assertEquals(5, combination.size());
            int sum = 0;
            for (int i = 0; i < combination.size(); i++) {
                int value = combination.get(i);
                assertTrue(value >= 1 && value <= 9);
                sum += value;
            }
            assertEquals(5, new HashSet<>(combination).size());
            assertEquals(25, sum);
        }
    }

    @Test
    void testDocumentedImplementationExtensionsForKOne() {
        assertEquals(Set.of("1"), canonical(solution.combinationSum3(1, 1)));
        assertEquals(Set.of("9"), canonical(solution.combinationSum3(1, 9)));
        assertTrue(solution.combinationSum3(1, 0).isEmpty());
        assertTrue(solution.combinationSum3(1, 10).isEmpty());
    }

    @Test
    void testInvalidInputsReturnEmpty() {
        assertTrue(solution.combinationSum3(-1, -1).isEmpty());
        assertTrue(solution.combinationSum3(0, 0).isEmpty());
        assertTrue(solution.combinationSum3(10, 45).isEmpty());
        assertTrue(solution.combinationSum3(100, 1).isEmpty());
        assertTrue(solution.combinationSum3(2, -5).isEmpty());
    }

    @Test
    void testTwoElementExtremes() {
        assertEquals(Set.of("1,9", "2,8", "3,7", "4,6"),
                canonical(solution.combinationSum3(2, 10)));
        assertEquals(Set.of("2,9", "3,8", "4,7", "5,6"),
                canonical(solution.combinationSum3(2, 11)));
        assertTrue(solution.combinationSum3(2, 1).isEmpty());
        assertTrue(solution.combinationSum3(2, 18).isEmpty());
    }

    @Test
    void testSixElementTargets() {
        assertEquals(Set.of("1,2,3,4,5,6"), canonical(solution.combinationSum3(6, 21)));
        assertEquals(Set.of("4,5,6,7,8,9"), canonical(solution.combinationSum3(6, 39)));
        assertTrue(solution.combinationSum3(6, 20).isEmpty());
        assertTrue(solution.combinationSum3(6, 40).isEmpty());
    }

    @Test
    void testFeasibleBoundsForEveryOfficialK() {
        // The minimum and maximum sums for k distinct values in [1, 9] are
        // independently known, so the adjacent targets must have no result.
        for (int k = 2; k <= 9; k++) {
            int minimum = k * (k + 1) / 2;
            int maximum = k * (19 - k) / 2;
            assertEquals(expected(k, minimum), canonical(solution.combinationSum3(k, minimum)),
                    "minimum target for k=" + k);
            assertEquals(expected(k, maximum), canonical(solution.combinationSum3(k, maximum)),
                    "maximum target for k=" + k);
            assertTrue(solution.combinationSum3(k, minimum - 1).isEmpty(),
                    "below minimum target for k=" + k);
            assertTrue(solution.combinationSum3(k, maximum + 1).isEmpty(),
                    "above maximum target for k=" + k);
        }
    }

    @Test
    void testManySolutionsAtMiddleTarget() {
        Set<String> expected = expected(4, 22);
        assertEquals(Set.of("1,4,8,9", "1,5,7,9", "1,6,7,8", "2,3,8,9",
                "2,4,7,9", "2,5,6,9", "2,5,7,8", "3,4,6,9", "3,4,7,8",
                "3,5,6,8", "4,5,6,7"), expected);
        assertEquals(expected, canonical(solution.combinationSum3(4, 22)));
    }

    @Test
    void testStrictlyIncreasingUniqueCombinations() {
        List<List<Integer>> result = solution.combinationSum3(5, 25);
        for (List<Integer> combination : result) {
            for (int i = 1; i < combination.size(); i++) {
                assertTrue(combination.get(i - 1) < combination.get(i),
                        "combination must be a strictly increasing set");
            }
        }
    }

    @Test
    void testReturnedCombinationsHaveIndependentMutableLists() {
        List<List<Integer>> result = solution.combinationSum3(3, 9);
        Set<String> before = canonical(result);
        assertEquals(Set.of("1,2,6", "1,3,5", "2,3,4"), before);
        String mutatedCombination = canonical(List.of(result.get(0))).iterator().next();
        result.get(0).set(0, 99);
        Set<String> after = canonical(result);
        assertEquals(99, result.get(0).get(0));
        Set<String> untouched = new HashSet<>(before);
        untouched.remove(mutatedCombination);
        assertTrue(after.containsAll(untouched));
        assertEquals(Set.of("1,2,6", "1,3,5", "2,3,4"),
                canonical(solution.combinationSum3(3, 9)));
    }

    @Test
    void testLargestOfficialNoSolutionTargetsDoNotLeakState() {
        assertTrue(solution.combinationSum3(2, 60).isEmpty());
        assertTrue(solution.combinationSum3(8, 60).isEmpty());
        assertTrue(solution.combinationSum3(9, 44).isEmpty());
        assertEquals(Set.of("1,2,3,4,5,6,7,8,9"),
                canonical(solution.combinationSum3(9, 45)));
    }

    @Test
    void testRepeatedCallsDoNotLeakMutableState() {
        List<List<Integer>> first = solution.combinationSum3(3, 7);
        List<List<Integer>> second = solution.combinationSum3(2, 17);
        assertEquals(Set.of("1,2,4"), canonical(first));
        assertEquals(Set.of("8,9"), canonical(second));
        first.get(0).set(0, 99);
        assertEquals(Set.of("8,9"), canonical(solution.combinationSum3(2, 17)));
    }

    private static Set<String> expected(int k, int n) {
        Set<String> expected = new HashSet<>();
        for (int mask = 0; mask < (1 << 9); mask++) {
            if (Integer.bitCount(mask) != k) {
                continue;
            }
            int sum = 0;
            List<Integer> values = new ArrayList<>();
            for (int bit = 0; bit < 9; bit++) {
                if ((mask & (1 << bit)) != 0) {
                    values.add(bit + 1);
                    sum += bit + 1;
                }
            }
            if (sum == n) {
                expected.add(join(values));
            }
        }
        return expected;
    }

    private static Set<String> canonical(List<List<Integer>> combinations) {
        Set<String> canonical = new HashSet<>();
        for (List<Integer> combination : combinations) {
            List<Integer> normalized = new ArrayList<>(combination);
            normalized.sort(Integer::compareTo);
            canonical.add(join(normalized));
        }
        return canonical;
    }

    private static String join(List<Integer> values) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            if (i > 0) {
                output.append(',');
            }
            output.append(values.get(i));
        }
        return output.toString();
    }
}
