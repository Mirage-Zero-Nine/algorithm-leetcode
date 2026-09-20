package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenerateParenthesis_22Test {
    private final GenerateParenthesis_22 solution = new GenerateParenthesis_22();

    @Test
    void testOne() {
        List<String> result = solution.generateParenthesis(1);
        assertEquals(1, result.size());
        assertTrue(result.contains("()"));
    }

    @Test
    void testThree() {
        List<String> result = solution.generateParenthesis(3);
        assertEquals(5, result.size());
    }

    @Test
    void testTwo() {
        List<String> result = solution.generateParenthesis(2);
        assertEquals(2, result.size());
    }

    @Test
    void testFour() {
        List<String> result = solution.generateParenthesis(4);
        assertEquals(14, result.size());
    }

    @Test
    void testZero() {
        List<String> result = solution.generateParenthesis(0);
        assertTrue(result.size() <= 1);
    }

    @Test
    void testZeroReturnsEmpty() {
        List<String> result = solution.generateParenthesis(0);
        assertEquals(0, result.size());
    }

    @Test
    void testFive() {
        // Catalan number C(5) = 42
        List<String> result = solution.generateParenthesis(5);
        assertEquals(42, result.size());
    }

    @Test
    void testSix() {
        // Catalan number C(6) = 132
        List<String> result = solution.generateParenthesis(6);
        assertEquals(132, result.size());
    }

    @Test
    void testAllResultsCorrectLength() {
        List<String> result = solution.generateParenthesis(4);
        for (String s : result) {
            assertEquals(8, s.length());
        }
    }

    @Test
    void testAllResultsBalanced() {
        List<String> result = solution.generateParenthesis(3);
        for (String s : result) {
            int count = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') count++;
                else count--;
                assertTrue(count >= 0, "Unbalanced: " + s);
            }
            assertEquals(0, count);
        }
    }

    @Test
    void testNoDuplicates() {
        List<String> result = solution.generateParenthesis(4);
        Set<String> set = new HashSet<>(result);
        assertEquals(result.size(), set.size());
    }

    @Test
    void testThreeContainsExpected() {
        List<String> result = solution.generateParenthesis(3);
        assertTrue(result.contains("((()))"));
        assertTrue(result.contains("(()())"));
        assertTrue(result.contains("(())()"));
        assertTrue(result.contains("()(())"));
        assertTrue(result.contains("()()()"));
    }

    @Test
    void testGiantCase() {
        // n=8 is the largest input in the LeetCode contract (Catalan number C(8) = 1430).
        List<String> result = solution.generateParenthesis(8);
        assertEquals(1430, result.size());
    }

    @Test
    void testOneExactContent() {
        List<String> result = solution.generateParenthesis(1);
        assertEquals(Set.of("()"), new HashSet<>(result));
    }

    @Test
    void testTwoExactContent() {
        List<String> result = solution.generateParenthesis(2);
        assertEquals(Set.of("(())", "()()"), new HashSet<>(result));
    }

    @Test
    void testEightCatalanCount() {
        // Catalan number C(8) = 1430
        List<String> result = solution.generateParenthesis(8);
        assertEquals(1430, result.size());
    }

    @Test
    void testEightAllValidAndCorrectLength() {
        List<String> result = solution.generateParenthesis(8);
        for (String s : result) {
            assertEquals(16, s.length());
            int count = 0;
            for (char c : s.toCharArray()) {
                count += (c == '(') ? 1 : -1;
                assertTrue(count >= 0, "Unbalanced: " + s);
            }
            assertEquals(0, count, "Not closed: " + s);
        }
    }

    @Test
    void testEightNoDuplicates() {
        List<String> result = solution.generateParenthesis(8);
        assertEquals(result.size(), new HashSet<>(result).size());
    }

    @Test
    void testAllResultsOnlyContainParenChars() {
        List<String> result = solution.generateParenthesis(5);
        for (String s : result) {
            assertTrue(s.matches("[()]+"), "Unexpected chars in: " + s);
        }
    }

    @Test
    void testCatalanSequenceProperty() {
        // Verify Catalan number sequence: 1, 1, 2, 5, 14, 42, 132
        int[] expected = {0, 1, 2, 5, 14, 42, 132};
        for (int n = 1; n <= 6; n++) {
            assertEquals(expected[n], solution.generateParenthesis(n).size(),
                    "Catalan mismatch for n=" + n);
        }
    }

    @Test
    void testEveryResultStartsWithOpenAndEndsWithClose() {
        for (int n = 1; n <= 5; n++) {
            for (String s : solution.generateParenthesis(n)) {
                assertEquals('(', s.charAt(0), "Must start with '(': " + s);
                assertEquals(')', s.charAt(s.length() - 1), "Must end with ')': " + s);
            }
        }
    }

    @Test
    void testExhaustiveIndependentOracleForEverySupportedN() {
        // Enumerate every 2n-character parenthesis string independently, then retain only
        // strings with exactly n opens and a non-negative prefix balance. This checks both
        // completeness and soundness without relying on the implementation's recursion.
        for (int n = 1; n <= 8; n++) {
            Set<String> expected = bruteForceWellFormedParentheses(n);
            List<String> actual = solution.generateParenthesis(n);
            assertEquals(expected, new HashSet<>(actual), "Mismatch for n=" + n);
            assertEquals(expected.size(), actual.size(), "Duplicate result for n=" + n);
        }
    }

    @Test
    void testNegativeNUsesTheSolutionClassEmptyResultGuard() {
        // LeetCode excludes negative n, but this implementation's recursion guards produce
        // an empty result; preserve and verify that defined extension behavior.
        assertTrue(solution.generateParenthesis(-1).isEmpty());
    }

    @Test
    void testRepeatedCallsDoNotShareMutableState() {
        List<String> first = solution.generateParenthesis(3);
        first.clear();
        assertEquals(Set.of("()"), new HashSet<>(solution.generateParenthesis(1)));
        assertEquals(5, solution.generateParenthesis(3).size());
    }

    @Test
    void testEverySupportedResultHasExactlyNOfEachCharacter() {
        for (int n = 1; n <= 8; n++) {
            for (String value : solution.generateParenthesis(n)) {
                assertEquals(n, value.chars().filter(c -> c == '(').count());
                assertEquals(n, value.chars().filter(c -> c == ')').count());
            }
        }
    }

    @Test
    void testCatalanCountsIncludeTheOfficialMaximum() {
        // C(7)=429 and C(8)=1430 are the two largest official result sets.
        int[] expected = {429, 1430};
        for (int n = 7; n <= 8; n++) {
            assertEquals(expected[n - 7], solution.generateParenthesis(n).size(),
                    "Catalan mismatch for n=" + n);
        }
    }

    @Test
    void testMaximumInputContainsBothCanonicalExtremes() {
        Set<String> result = new HashSet<>(solution.generateParenthesis(8));
        assertTrue(result.contains("(((((((())))))))"));
        assertTrue(result.contains("()()()()()()()()"));
    }

    @Test
    void testEveryOfficialInputHasNonEmptyCompleteResults() {
        for (int n = 1; n <= 8; n++) {
            List<String> result = solution.generateParenthesis(n);
            assertFalse(result.isEmpty(), "No combinations for n=" + n);
            assertEquals(bruteForceWellFormedParentheses(n), new HashSet<>(result),
                    "Incomplete result set for n=" + n);
        }
    }

    @Test
    void testEveryOfficialResultHasValidPrefixBalance() {
        for (int n = 1; n <= 8; n++) {
            for (String value : solution.generateParenthesis(n)) {
                int balance = 0;
                for (int index = 0; index < value.length(); index++) {
                    balance += value.charAt(index) == '(' ? 1 : -1;
                    assertTrue(balance >= 0,
                            "Negative prefix balance at index " + index + ": " + value);
                }
                assertEquals(0, balance, "Unclosed result: " + value);
            }
        }
    }

    @Test
    void testInvalidNegativeInputsUseTheImplementationDefinedEmptyResult() {
        assertTrue(solution.generateParenthesis(-2).isEmpty());
    }

    @Test
    void testEachInvocationReturnsAnIndependentMutableList() {
        List<String> first = solution.generateParenthesis(2);
        List<String> second = solution.generateParenthesis(2);
        assertNotSame(first, second);
        first.add("not-a-valid-result");
        assertEquals(Set.of("(())", "()()"), new HashSet<>(second));
        assertEquals(2, solution.generateParenthesis(2).size());
    }

    @Test
    void testChangingPairCountDoesNotReusePreviousResults() {
        List<String> eight = solution.generateParenthesis(8);
        List<String> one = solution.generateParenthesis(1);
        assertEquals(1430, eight.size());
        assertEquals(List.of("()"), one);
        assertEquals(5, solution.generateParenthesis(3).size());
    }

    @Test
    void testPositiveResultsContainNoNullOrEmptyStrings() {
        for (int n = 1; n <= 8; n++) {
            for (String value : solution.generateParenthesis(n)) {
                assertTrue(value != null && !value.isEmpty());
            }
        }
    }

    private Set<String> bruteForceWellFormedParentheses(int pairs) {
        int length = pairs * 2;
        Set<String> valid = new HashSet<>();
        for (int mask = 0; mask < (1 << length); mask++) {
            int opens = Integer.bitCount(mask);
            if (opens != pairs) {
                continue;
            }
            StringBuilder candidate = new StringBuilder(length);
            int balance = 0;
            boolean wellFormedPrefix = true;
            for (int index = 0; index < length; index++) {
                if ((mask & (1 << index)) != 0) {
                    candidate.append('(');
                    balance++;
                } else {
                    candidate.append(')');
                    balance--;
                }
                if (balance < 0) {
                    wellFormedPrefix = false;
                    break;
                }
            }
            if (wellFormedPrefix && balance == 0) {
                valid.add(candidate.toString());
            }
        }
        return valid;
    }
}
