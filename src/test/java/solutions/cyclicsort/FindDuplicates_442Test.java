package solutions.cyclicsort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link FindDuplicates_442}.
 *
 * <p>The expected values are derived by an independent frequency map. Since the solution
 * intentionally marks its input array in place and the answer order is unspecified, every
 * assertion supplies a fresh array and compares duplicate sets plus their exact cardinality.</p>
 */
public class FindDuplicates_442Test {

    private final FindDuplicates_442 solver = new FindDuplicates_442();

    @Test
    public void testOfficialExampleOne() {
        assertDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}, 2, 3);
    }

    @Test
    public void testOfficialExampleTwo() {
        assertDuplicates(new int[]{1, 1, 2}, 1);
    }

    @Test
    public void testOfficialExampleThree() {
        assertDuplicates(new int[]{1});
    }

    @Test
    public void testNoDuplicatesAscendingPermutation() {
        assertDuplicates(new int[]{1, 2, 3, 4, 5, 6});
    }

    @Test
    public void testNoDuplicatesReversePermutation() {
        assertDuplicates(new int[]{8, 7, 6, 5, 4, 3, 2, 1});
    }

    @Test
    public void testSingletonIsNotADuplicate() {
        assertDuplicates(new int[]{1});
    }

    @Test
    public void testEmptyInputUsesDocumentedGuard() {
        assertTrue(solver.findDuplicates(new int[0]).isEmpty());
    }

    @Test
    public void testNullInputUsesDocumentedGuard() {
        assertTrue(solver.findDuplicates(null).isEmpty());
    }

    @Test
    public void testDuplicateAtLowestValue() {
        assertDuplicates(new int[]{1, 1, 2, 3, 4}, 1);
    }

    @Test
    public void testDuplicateAtHighestValue() {
        assertDuplicates(new int[]{1, 2, 3, 4, 5, 5}, 5);
    }

    @Test
    public void testDuplicateInTheMiddle() {
        assertDuplicates(new int[]{1, 2, 3, 2, 4, 5}, 2);
    }

    @Test
    public void testAdjacentDuplicatePairs() {
        assertDuplicates(new int[]{1, 1, 2, 2, 3, 3, 4, 4}, 1, 2, 3, 4);
    }

    @Test
    public void testSeparatedDuplicatePairs() {
        assertDuplicates(new int[]{1, 2, 3, 4, 1, 2, 3, 4}, 1, 2, 3, 4);
    }

    @Test
    public void testInterleavedDuplicatesAndSingletons() {
        assertDuplicates(new int[]{6, 1, 4, 2, 5, 3, 2, 6}, 2, 6);
    }

    @Test
    public void testHighValuesDuplicatedInReverseOrder() {
        assertDuplicates(new int[]{8, 7, 6, 5, 8, 7, 6, 5}, 5, 6, 7, 8);
    }

    @Test
    public void testDuplicateResultContainsEachValueExactlyOnce() {
        List<Integer> result = solver.findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        assertEquals(Set.of(2, 3), new HashSet<>(result));
        assertEquals(2, result.size(), "a value appearing twice must be reported once");
    }

    @Test
    public void testRepeatedCallsUseFreshMutableInputs() {
        assertDuplicates(new int[]{2, 5, 1, 4, 3, 2}, 2);
        assertDuplicates(new int[]{3, 1, 4, 1, 5, 2, 3}, 1, 3);
        assertDuplicates(new int[]{1, 2, 3, 4, 5});
    }

    @Test
    public void testReturnedListsAreIndependent() {
        List<Integer> first = solver.findDuplicates(new int[]{1, 2, 1, 3});
        List<Integer> second = solver.findDuplicates(new int[]{2, 3, 2, 1});

        assertNotSame(first, second);
        first.clear();
        assertEquals(List.of(2), second);
    }

    @Test
    public void testAllValuesAppearTwice() {
        assertDuplicates(new int[]{1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6}, 1, 2, 3, 4, 5, 6);
    }

    @Test
    public void testMaximumValueCanBeTheOnlyDuplicate() {
        int n = 100_000;
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = i + 1;
        }
        values[n - 2] = n;
        assertDuplicates(values, n);
    }

    @Test
    public void testMaximumLengthNoDuplicates() {
        int n = 100_000;
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = n - i;
        }
        assertDuplicates(values);
    }

    @Test
    public void testMaximumLengthEveryValueDuplicated() {
        int distinct = 50_000;
        int[] values = new int[distinct * 2];
        for (int i = 0; i < distinct; i++) {
            values[i] = i + 1;
            values[distinct + i] = i + 1;
        }

        List<Integer> actual = solver.findDuplicates(values);
        assertEquals(distinct, actual.size());
        assertEquals(distinct, new HashSet<>(actual).size());
        assertTrue(actual.contains(1));
        assertTrue(actual.contains(distinct));
    }

    @Test
    public void testExhaustiveValidSmallArraysAgainstFrequencyOracle() {
        for (int n = 1; n <= 6; n++) {
            int combinations = (int) Math.pow(n, n);
            for (int encoded = 0; encoded < combinations; encoded++) {
                int[] values = new int[n];
                int[] frequency = new int[n + 1];
                int remaining = encoded;
                boolean valid = true;
                for (int i = 0; i < n; i++) {
                    values[i] = remaining % n + 1;
                    remaining /= n;
                    if (++frequency[values[i]] > 2) {
                        valid = false;
                    }
                }
                if (!valid) {
                    continue;
                }

                List<Integer> expected = new ArrayList<>();
                for (int value = 1; value <= n; value++) {
                    if (frequency[value] == 2) {
                        expected.add(value);
                    }
                }
                List<Integer> actual = solver.findDuplicates(values);
                actual.sort(Integer::compareTo);
                assertEquals(expected, actual, "n=" + n + ", encoded=" + encoded);
            }
        }
    }

    @Test
    public void testSeededValidRandomArraysAgainstFrequencyOracle() {
        Random random = new Random(442_2026L);
        for (int caseNumber = 0; caseNumber < 250; caseNumber++) {
            int n = 2 + random.nextInt(499);
            int duplicateCount = random.nextInt(n / 2 + 1);
            int[] values = new int[n];
            for (int i = 0; i < n; i++) {
                values[i] = i + 1;
            }
            for (int i = 0; i < duplicateCount; i++) {
                values[n - 1 - i] = i + 1;
            }
            shuffle(values, random);

            assertDuplicatesUsingOracle(values, "case=" + caseNumber);
        }
    }

    @Test
    public void testSparseDuplicateDistribution() {
        int n = 2000;
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = i + 1;
        }
        values[1500] = 1;
        values[1600] = 1000;
        values[1700] = 2000;

        assertDuplicates(values, 1, 1000, 2000);
    }

    @Test
    public void testOutputOrderIsNotPartOfTheContract() {
        List<Integer> result = solver.findDuplicates(new int[]{3, 1, 4, 2, 4, 3});
        assertEquals(Set.of(3, 4), new HashSet<>(result));
        assertEquals(2, result.size());
    }

    private void assertDuplicates(int[] values, Integer... expectedValues) {
        List<Integer> expected = Arrays.asList(expectedValues);
        List<Integer> actual = solver.findDuplicates(values);
        assertEquals(new HashSet<>(expected), new HashSet<>(actual));
        assertEquals(expected.size(), actual.size(), "each duplicate should be reported once");
        assertFalse(actual.stream().anyMatch(value -> value == null));
    }

    private void assertDuplicatesUsingOracle(int[] values, String message) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int value : values) {
            frequency.merge(value, 1, Integer::sum);
        }
        Set<Integer> expected = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() == 2) {
                expected.add(entry.getKey());
            }
        }

        List<Integer> actual = solver.findDuplicates(values);
        assertEquals(expected, new HashSet<>(actual), message);
        assertEquals(expected.size(), actual.size(), message + ": exact result cardinality");
    }

    private static void shuffle(int[] values, Random random) {
        for (int i = values.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temporary = values[i];
            values[i] = values[j];
            values[j] = temporary;
        }
    }
}
