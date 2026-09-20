package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

/** Tests for the DFS enumeration of LeetCode 1215 stepping numbers. */
public class CountSteppingNumbers_1215Test {

    @Test
    public void officialExampleZeroThroughTwentyOne() {
        assertIterableEquals(
                List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 21),
                new CountSteppingNumbers_1215().countSteppingNumbers(0, 21));
    }

    @Test
    public void officialExampleTenThroughFifteen() {
        assertIterableEquals(
                List.of(10, 12),
                new CountSteppingNumbers_1215().countSteppingNumbers(10, 15));
    }

    @Test
    public void singletonZeroIsIncluded() {
        assertIterableEquals(List.of(0), new CountSteppingNumbers_1215().countSteppingNumbers(0, 0));
    }

    @Test
    public void singletonOneIsIncluded() {
        assertIterableEquals(List.of(1), new CountSteppingNumbers_1215().countSteppingNumbers(1, 1));
    }

    @Test
    public void singletonNineIsIncluded() {
        assertIterableEquals(List.of(9), new CountSteppingNumbers_1215().countSteppingNumbers(9, 9));
    }

    @Test
    public void singletonTwelveIsIncluded() {
        assertIterableEquals(List.of(12), new CountSteppingNumbers_1215().countSteppingNumbers(12, 12));
    }

    @Test
    public void singletonElevenHasNoResult() {
        assertTrue(new CountSteppingNumbers_1215().countSteppingNumbers(11, 11).isEmpty());
    }

    @Test
    public void allSingleDigitValuesAreSteppingNumbers() {
        assertIterableEquals(
                List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
                new CountSteppingNumbers_1215().countSteppingNumbers(0, 9));
    }

    @Test
    public void lowerBoundExcludesZero() {
        assertIterableEquals(
                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                new CountSteppingNumbers_1215().countSteppingNumbers(1, 9));
    }

    @Test
    public void noResultsBetweenThirteenAndTwenty() {
        assertTrue(new CountSteppingNumbers_1215().countSteppingNumbers(13, 20).isEmpty());
    }

    @Test
    public void twentyOneIsTheOnlyResultAtTheEndOfTheSecondDecade() {
        assertIterableEquals(List.of(21), new CountSteppingNumbers_1215().countSteppingNumbers(20, 21));
    }

    @Test
    public void twoDigitBranchingRangeIsExact() {
        assertIterableEquals(
                List.of(32, 34),
                new CountSteppingNumbers_1215().countSteppingNumbers(30, 40));
    }

    @Test
    public void crossingTheNinetyNineBoundaryIsExact() {
        assertIterableEquals(
                List.of(89, 98),
                new CountSteppingNumbers_1215().countSteppingNumbers(89, 100));
    }

    @Test
    public void exactThreeDigitBoundaryIsIncluded() {
        assertIterableEquals(List.of(101), new CountSteppingNumbers_1215().countSteppingNumbers(101, 101));
    }

    @Test
    public void hundredThroughTwoHundredHasAllThreeExpectedValues() {
        assertIterableEquals(
                List.of(101, 121, 123),
                new CountSteppingNumbers_1215().countSteppingNumbers(100, 200));
    }

    @Test
    public void narrowFourDigitNoMatchRangeIsEmpty() {
        assertTrue(new CountSteppingNumbers_1215().countSteppingNumbers(1000, 1005).isEmpty());
    }

    @Test
    public void firstFourDigitSteppingNumberIsIncludedAtItsUpperBoundary() {
        assertIterableEquals(
                List.of(1010),
                new CountSteppingNumbers_1215().countSteppingNumbers(1000, 1010));
    }

    @Test
    public void fullThreeDigitRangeMatchesIndependentDigitOracle() {
        List<Integer> actual = new CountSteppingNumbers_1215().countSteppingNumbers(100, 999);
        assertIterableEquals(generateByDigits(100, 999), actual);
        assertSortedUniqueAndStepping(actual);
    }

    @Test
    public void fullFourDigitRangeMatchesIndependentDigitOracle() {
        assertIterableEquals(
                generateByDigits(0, 9_999),
                new CountSteppingNumbers_1215().countSteppingNumbers(0, 9_999));
    }

    @Test
    public void exhaustiveSmallIntervalsMatchBruteForceOracle() {
        CountSteppingNumbers_1215 solution = new CountSteppingNumbers_1215();
        for (int low = 0; low <= 120; low++) {
            for (int high = low; high <= Math.min(160, low + 20); high++) {
                assertIterableEquals(
                        bruteForce(low, high),
                        solution.countSteppingNumbers(low, high),
                        "range [" + low + ", " + high + "]");
            }
        }
    }

    @Test
    public void isolatedNoMatchIntervalsRemainEmpty() {
        CountSteppingNumbers_1215 solution = new CountSteppingNumbers_1215();
        for (int[] range : new int[][] {{11, 11}, {13, 20}, {22, 22}, {40, 41}, {1000, 1005}}) {
            assertTrue(solution.countSteppingNumbers(range[0], range[1]).isEmpty(), "range " + Arrays.toString(range));
        }
    }

    @Test
    public void twoBillionUpperBoundMatchesIndependentDigitOracle() {
        List<Integer> actual = new CountSteppingNumbers_1215().countSteppingNumbers(0, 2_000_000_000);
        List<Integer> expected = generateByDigits(0, 2_000_000_000);
        assertIterableEquals(expected, actual);
        assertEquals(3_502, actual.size());
        assertSortedUniqueAndStepping(actual);
    }

    @Test
    public void maximumUpperBoundWindowHasNoOverflowArtifacts() {
        List<Integer> actual = new CountSteppingNumbers_1215().countSteppingNumbers(1_999_999_999, 2_000_000_000);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void billionRangeMatchesIndependentDigitOracle() {
        List<Integer> actual = new CountSteppingNumbers_1215().countSteppingNumbers(1_000_000_000, 2_000_000_000);
        assertIterableEquals(generateByDigits(1_000_000_000, 2_000_000_000), actual);
        assertSortedUniqueAndStepping(actual);
    }

    @Test
    public void resultContainsNoDuplicatesAndIsStrictlySorted() {
        List<Integer> result = new CountSteppingNumbers_1215().countSteppingNumbers(0, 1_000_000);
        assertSortedUniqueAndStepping(result);
        assertEquals(new TreeSet<>(result).size(), result.size());
    }

    @Test
    public void resultExcludesAdjacentDigitsWithDifferenceOtherThanOne() {
        List<Integer> result = new CountSteppingNumbers_1215().countSteppingNumbers(0, 10_000);
        assertFalse(result.contains(11));
        assertFalse(result.contains(13));
        assertFalse(result.contains(1011));
        assertFalse(result.contains(1000));
    }

    @Test
    public void repeatedCallsDoNotLeakResultsBetweenRanges() {
        CountSteppingNumbers_1215 solution = new CountSteppingNumbers_1215();
        assertIterableEquals(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), solution.countSteppingNumbers(0, 9));
        assertIterableEquals(List.of(101), solution.countSteppingNumbers(101, 101));
        assertTrue(solution.countSteppingNumbers(13, 20).isEmpty());
    }

    @Test
    public void independentInstancesProduceIndependentResults() {
        CountSteppingNumbers_1215 first = new CountSteppingNumbers_1215();
        CountSteppingNumbers_1215 second = new CountSteppingNumbers_1215();
        List<Integer> firstResult = first.countSteppingNumbers(0, 21);
        List<Integer> secondResult = second.countSteppingNumbers(100, 200);
        assertIterableEquals(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 21), firstResult);
        assertIterableEquals(List.of(101, 121, 123), secondResult);
    }

    private static List<Integer> bruteForce(int low, int high) {
        List<Integer> expected = new ArrayList<>();
        for (int candidate = low; candidate <= high; candidate++) {
            if (isSteppingNumber(candidate)) {
                expected.add(candidate);
            }
        }
        return expected;
    }

    /** Independent string-based generator: it builds valid digit paths, not numeric DFS paths. */
    private static List<Integer> generateByDigits(int low, int high) {
        List<Integer> expected = new ArrayList<>();
        if (low == 0) {
            expected.add(0);
        }

        Deque<String> pending = new ArrayDeque<>();
        for (char digit = '1'; digit <= '9'; digit++) {
            pending.addLast(String.valueOf(digit));
        }
        while (!pending.isEmpty()) {
            String digits = pending.removeFirst();
            long value = Long.parseLong(digits);
            if (value > high) {
                continue;
            }
            if (value >= low) {
                expected.add((int) value);
            }

            int last = digits.charAt(digits.length() - 1) - '0';
            if (last > 0) {
                pending.addLast(digits + (last - 1));
            }
            if (last < 9) {
                pending.addLast(digits + (last + 1));
            }
        }
        expected.sort(Integer::compareTo);
        return expected;
    }

    private static boolean isSteppingNumber(int number) {
        if (number < 10) {
            return true;
        }
        String digits = Integer.toString(number);
        for (int i = 1; i < digits.length(); i++) {
            if (Math.abs(digits.charAt(i) - digits.charAt(i - 1)) != 1) {
                return false;
            }
        }
        return true;
    }

    private static void assertSortedUniqueAndStepping(List<Integer> result) {
        for (int i = 0; i < result.size(); i++) {
            assertTrue(result.get(i) >= 0);
            assertTrue(isSteppingNumber(result.get(i)), "not stepping: " + result.get(i));
            if (i > 0) {
                assertTrue(result.get(i) > result.get(i - 1), "not strictly sorted: " + result);
            }
        }
    }
}
