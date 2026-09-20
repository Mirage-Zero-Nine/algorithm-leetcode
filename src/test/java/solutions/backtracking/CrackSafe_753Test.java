package solutions.backtracking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.HashSet;
import java.util.stream.Stream;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CrackSafe_753Test {
    private final CrackSafe_753 solution = new CrackSafe_753();

    private void assertValidDeBruijnSequence(String result, int n, int k) {
        assertNotNull(result);
        assertEquals((int) Math.pow(k, n) + n - 1, result.length());

        Set<String> seen = new HashSet<>();
        for (int i = 0; i <= result.length() - n; i++) {
            String window = result.substring(i, i + n);
            assertTrue(window.chars().allMatch(ch -> ch >= '0' && ch < '0' + k));
            assertTrue(seen.add(window), "Repeated password window: " + window);
        }

        // The minimum-length requirement gives exactly k^n windows.  Comparing
        // with an independently generated set proves that no legal password
        // is missing, rather than only checking the number of distinct windows.
        assertEquals(allCodes(n, k), seen);
    }

    private static Set<String> allCodes(int n, int k) {
        Set<String> expected = new HashSet<>();
        collectCodes(n, k, new StringBuilder(), expected);
        return expected;
    }

    private static void collectCodes(int remaining, int k, StringBuilder prefix, Set<String> expected) {
        if (remaining == 0) {
            expected.add(prefix.toString());
            return;
        }
        for (int digit = 0; digit < k; digit++) {
            prefix.append(digit);
            collectCodes(remaining - 1, k, prefix, expected);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    @Test
    void testBasic() {
        String result = solution.crackSafe(1, 2);
        assertValidDeBruijnSequence(result, 1, 2);
    }

    @Test
    void testTwoTwo() {
        String result = solution.crackSafe(2, 2);
        assertValidDeBruijnSequence(result, 2, 2);
    }

    @Test
    void testOneOne() {
        String result = solution.crackSafe(1, 1);
        assertValidDeBruijnSequence(result, 1, 1);
    }

    @Test
    void testTwoThree() {
        String result = solution.crackSafe(2, 3);
        assertValidDeBruijnSequence(result, 2, 3);
    }

    @Test
    void testThreeTwo() {
        String result = solution.crackSafe(3, 2);
        assertValidDeBruijnSequence(result, 3, 2);
    }

    @Test
    void testOneThree() {
        String result = solution.crackSafe(1, 3);
        assertValidDeBruijnSequence(result, 1, 3);
    }

    @Test
    void testOneFour() {
        String result = solution.crackSafe(1, 4);
        assertValidDeBruijnSequence(result, 1, 4);
    }

    @Test
    void testTwoFour() {
        String result = solution.crackSafe(2, 4);
        assertValidDeBruijnSequence(result, 2, 4);
    }

    @Test
    void testResultNotNull() {
        String result = solution.crackSafe(1, 1);
        assertNotNull(result);
    }

    @Test
    void testOneOneContainsZero() {
        String result = solution.crackSafe(1, 1);
        assertTrue(result.contains("0"));
    }

    @Test
    void testSingleSymbolPasswordIsDeterministicForEveryLength() {
        assertEquals("0000", solution.crackSafe(4, 1));
    }

    @Test
    void testSingleSymbolThreeDigitPassword() {
        assertEquals("000", solution.crackSafe(3, 1));
    }

    @Test
    void testSingleDigitMaximumAlphabet() {
        assertValidDeBruijnSequence(solution.crackSafe(1, 10), 1, 10);
    }

    @Test
    void testTwoDigitMaximumAlphabet() {
        assertValidDeBruijnSequence(solution.crackSafe(2, 10), 2, 10);
    }

    @Test
    void testThreeDigitMaximumAlphabet() {
        assertValidDeBruijnSequence(solution.crackSafe(3, 10), 3, 10);
    }

    @Test
    void testRepeatedCallsOnOneInstanceDoNotShareState() {
        assertValidDeBruijnSequence(solution.crackSafe(2, 3), 2, 3);
        assertValidDeBruijnSequence(solution.crackSafe(1, 2), 1, 2);
        assertValidDeBruijnSequence(solution.crackSafe(3, 2), 3, 2);
    }

    @Test
    void testRepeatedSameInputProducesIndependentResults() {
        String first = solution.crackSafe(2, 4);
        String second = solution.crackSafe(2, 4);
        assertValidDeBruijnSequence(first, 2, 4);
        assertValidDeBruijnSequence(second, 2, 4);
    }

    @Test
    void testMaximumProductBoundary() {
        // The official upper product bound is k^n = 4096, reached by (4, 8).
        assertValidDeBruijnSequence(solution.crackSafe(4, 8), 4, 8);
    }

    @Test
    void testGiantCase() {
        // n=4, k=2 -> length = 2^4 + 3 = 19
        String result = solution.crackSafe(4, 2);
        assertValidDeBruijnSequence(result, 4, 2);
    }

    /**
     * Exercise every parameter pair allowed by the problem's constraints.  The
     * output is intentionally checked by its De Bruijn properties rather than
     * against one string, because the problem accepts any minimum-length
     * sequence and the backtracking order is not part of the contract.
     */
    @ParameterizedTest(name = "n={0}, k={1}")
    @MethodSource("validInputs")
    void testEveryValidInput(int n, int k) {
        assertValidDeBruijnSequence(new CrackSafe_753().crackSafe(n, k), n, k);
    }

    private static Stream<Arguments> validInputs() {
        return Stream.of(
                // n = 1: all k from 1 through 10
                Arguments.of(1, 1), Arguments.of(1, 2), Arguments.of(1, 3),
                Arguments.of(1, 4), Arguments.of(1, 5), Arguments.of(1, 6),
                Arguments.of(1, 7), Arguments.of(1, 8), Arguments.of(1, 9),
                Arguments.of(1, 10),
                // n = 2: all k from 1 through 10
                Arguments.of(2, 1), Arguments.of(2, 2), Arguments.of(2, 3),
                Arguments.of(2, 4), Arguments.of(2, 5), Arguments.of(2, 6),
                Arguments.of(2, 7), Arguments.of(2, 8), Arguments.of(2, 9),
                Arguments.of(2, 10),
                // n = 3: all k from 1 through 10
                Arguments.of(3, 1), Arguments.of(3, 2), Arguments.of(3, 3),
                Arguments.of(3, 4), Arguments.of(3, 5), Arguments.of(3, 6),
                Arguments.of(3, 7), Arguments.of(3, 8), Arguments.of(3, 9),
                Arguments.of(3, 10),
                // n = 4: k <= 8 because k^n must be at most 4096
                Arguments.of(4, 1), Arguments.of(4, 2), Arguments.of(4, 3),
                Arguments.of(4, 4), Arguments.of(4, 5), Arguments.of(4, 6),
                Arguments.of(4, 7), Arguments.of(4, 8));
    }
}
