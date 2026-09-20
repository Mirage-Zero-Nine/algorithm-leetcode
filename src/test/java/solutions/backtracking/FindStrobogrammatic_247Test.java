package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Contract and completeness tests for LeetCode 247. */
class FindStrobogrammatic_247Test {
    private static final Map<Character, Character> ROTATION = Map.of(
            '0', '0', '1', '1', '6', '9', '8', '8', '9', '6');
    private static final char[] DIGITS = {'0', '1', '6', '8', '9'};

    @Test
    void zeroUsesDocumentedEmptyNumberBaseCase() {
        assertEquals(List.of(""), new FindStrobogrammatic_247().findStrobogrammatic(0));
    }

    @Test void oneHasExactlyTheThreeAllowedCenters() { assertExact(1); }
    @Test void twoHasExactlyTheFourValidPairs() { assertExact(2); }
    @Test void threeHasAllCentersAndPairs() { assertExact(3); }
    @Test void fourHasAllValidTwoPairCombinations() { assertExact(4); }
    @Test void fiveHasAllValidCombinations() { assertExact(5); }
    @Test void sixHasAllValidCombinations() { assertExact(6); }
    @Test void sevenHasAllValidCombinations() { assertExact(7); }
    @Test void eightHasExpectedCardinalityAndValidity() { assertContract(8); }
    @Test void nineHasExpectedCardinalityAndValidity() { assertContract(9); }
    @Test void tenHasExpectedCardinalityAndValidity() { assertContract(10); }
    @Test void elevenHasExpectedCardinalityAndValidity() { assertContract(11); }
    @Test void twelveHasExpectedCardinalityAndValidity() { assertContract(12); }
    @Test void thirteenHasExpectedCardinalityAndValidity() { assertContract(13); }
    @Test void fourteenCoversMaximumLeetCodeConstraint() { assertContract(14); }

    @Test
    void noResultHasForbiddenLeadingZero() {
        for (int n = 2; n <= 10; n++) {
            for (String value : new FindStrobogrammatic_247().findStrobogrammatic(n)) {
                assertFalse(value.startsWith("0"), "leading zero for n=" + n + ": " + value);
            }
        }
    }

    @Test
    void everyResultIsUnchangedBy180DegreeRotation() {
        for (int n = 1; n <= 9; n++) {
            for (String value : new FindStrobogrammatic_247().findStrobogrammatic(n)) {
                assertEquals(value, rotate(value), "not strobogrammatic: " + value);
            }
        }
    }

    @Test
    void everyResultContainsOnlyRotatableDigits() {
        for (int n = 1; n <= 9; n++) {
            for (String value : new FindStrobogrammatic_247().findStrobogrammatic(n)) {
                for (char digit : value.toCharArray()) {
                    assertTrue(ROTATION.containsKey(digit), "unexpected digit in " + value);
                }
            }
        }
    }

    @Test
    void resultsAreUniqueAtSeveralEvenLengths() {
        for (int n : new int[]{2, 4, 6, 8, 10}) {
            List<String> values = new FindStrobogrammatic_247().findStrobogrammatic(n);
            assertEquals(values.size(), new HashSet<>(values).size(), "duplicate at n=" + n);
        }
    }

    @Test
    void resultsAreUniqueAtSeveralOddLengths() {
        for (int n : new int[]{1, 3, 5, 7, 9}) {
            List<String> values = new FindStrobogrammatic_247().findStrobogrammatic(n);
            assertEquals(values.size(), new HashSet<>(values).size(), "duplicate at n=" + n);
        }
    }

    @Test
    void oddLengthsUseOnlyThreeValidCenterDigits() {
        for (int n : new int[]{1, 3, 5, 9}) {
            for (String value : new FindStrobogrammatic_247().findStrobogrammatic(n)) {
                assertTrue("018".indexOf(value.charAt(n / 2)) >= 0, value);
            }
        }
    }

    @Test
    void evenLengthsHaveCorrectCentralPair() {
        for (int n : new int[]{2, 4, 8, 10}) {
            for (String value : new FindStrobogrammatic_247().findStrobogrammatic(n)) {
                assertEquals(n, value.length());
                assertEquals(value.charAt(n / 2 - 1), ROTATION.get(value.charAt(n / 2)), value);
            }
        }
    }

    @Test
    void repeatedCallsOnOneInstanceDoNotLeakState() {
        FindStrobogrammatic_247 solution = new FindStrobogrammatic_247();
        Set<String> first = new HashSet<>(solution.findStrobogrammatic(3));
        solution.findStrobogrammatic(8);
        assertEquals(first, new HashSet<>(solution.findStrobogrammatic(3)));
    }

    @Test
    void callsInDifferentOrderRemainIndependent() {
        FindStrobogrammatic_247 solution = new FindStrobogrammatic_247();
        Set<String> lengthFive = new HashSet<>(solution.findStrobogrammatic(5));
        solution.findStrobogrammatic(2);
        Set<String> lengthSeven = new HashSet<>(solution.findStrobogrammatic(7));
        assertEquals(lengthFive, new HashSet<>(solution.findStrobogrammatic(5)));
        assertEquals(60, lengthFive.size());
        assertEquals(300, lengthSeven.size());
    }

    @Test
    void eachLengthHasEveryAllowedNonzeroOuterPair() {
        Set<String> outerPairs = Set.of("11", "69", "88", "96");
        for (int n = 2; n <= 14; n++) {
            Set<String> actual = new HashSet<>(new FindStrobogrammatic_247().findStrobogrammatic(n));
            Set<String> observed = new HashSet<>();
            for (String value : actual) {
                observed.add(value.substring(0, 1) + value.substring(value.length() - 1));
            }
            assertEquals(outerPairs, observed, "missing outer pair at n=" + n);
        }
    }

    @Test
    void mutatingOneReturnedListDoesNotAffectLaterCalls() {
        FindStrobogrammatic_247 solution = new FindStrobogrammatic_247();
        List<String> first = solution.findStrobogrammatic(4);
        first.clear();
        assertEquals(bruteForceExpected(4), new HashSet<>(solution.findStrobogrammatic(4)));
    }

    @Test
    void outputIsNonNullForEveryValidLength() {
        for (int n = 1; n <= 14; n++) {
            assertNotNull(new FindStrobogrammatic_247().findStrobogrammatic(n));
        }
    }

    private static void assertExact(int n) {
        Set<String> actual = new HashSet<>(new FindStrobogrammatic_247().findStrobogrammatic(n));
        assertEquals(bruteForceExpected(n), actual, "wrong complete set for n=" + n);
    }

    private static void assertContract(int n) {
        List<String> values = new FindStrobogrammatic_247().findStrobogrammatic(n);
        assertEquals(expectedCount(n), values.size(), "wrong count for n=" + n);
        assertEquals(values.size(), new HashSet<>(values).size(), "duplicates for n=" + n);
        for (String value : values) {
            assertEquals(n, value.length());
            assertFalse(value.startsWith("0"));
            assertEquals(value, rotate(value));
        }
    }

    /* Independent oracle: enumerate all strings over the five rotatable digits and filter them. */
    private static Set<String> bruteForceExpected(int n) {
        Set<String> result = new HashSet<>();
        enumerate(new StringBuilder(), n, result);
        return result;
    }

    private static void enumerate(StringBuilder candidate, int n, Set<String> result) {
        if (candidate.length() == n) {
            if ((n <= 1 || candidate.charAt(0) != '0')
                    && candidate.toString().equals(rotate(candidate.toString()))) {
                result.add(candidate.toString());
            }
            return;
        }
        for (char digit : DIGITS) {
            candidate.append(digit);
            enumerate(candidate, n, result);
            candidate.deleteCharAt(candidate.length() - 1);
        }
    }

    private static String rotate(String value) {
        StringBuilder result = new StringBuilder(value.length());
        for (int i = value.length() - 1; i >= 0; i--) {
            result.append(ROTATION.get(value.charAt(i)));
        }
        return result.toString();
    }

    private static int expectedCount(int n) {
        if (n == 0) return 1;
        if (n == 1) return 3;
        int count = n % 2 == 0 ? 4 : 12;
        for (int i = 1; i < n / 2; i++) count *= 5;
        return count;
    }
}
