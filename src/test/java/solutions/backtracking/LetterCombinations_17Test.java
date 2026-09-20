package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LetterCombinations_17Test {
    private final LetterCombinations_17 solution = new LetterCombinations_17();

    @Test
    void testBasic() {
        List<String> result = solution.letterCombinations("23");
        assertEquals(9, result.size());
    }

    @Test
    void testSingleDigit() {
        List<String> result = solution.letterCombinations("2");
        assertEquals(3, result.size());
    }

    @Test
    void testEmpty() {
        List<String> result = solution.letterCombinations("");
        assertEquals(0, result.size());
    }

    @Test
    void testThreeDigits() {
        List<String> result = solution.letterCombinations("234");
        assertEquals(27, result.size());
    }

    @Test
    void testWithSeven() {
        List<String> result = solution.letterCombinations("7");
        assertEquals(4, result.size());
    }

    @Test
    void testWithNine() {
        List<String> result = solution.letterCombinations("9");
        assertEquals(4, result.size());
    }

    @Test
    void testContainsExpectedCombination() {
        List<String> result = solution.letterCombinations("23");
        assertTrue(result.contains("ad"));
        assertTrue(result.contains("cf"));
    }

    @Test
    void testDigitWithZero() {
        // '0' has no letters, should return empty
        List<String> result = solution.letterCombinations("20");
        assertEquals(0, result.size());
    }

    @Test
    void testDigitWithOne() {
        // '1' has no letters, should return empty
        List<String> result = solution.letterCombinations("12");
        assertEquals(0, result.size());
    }

    @Test
    void testFourDigits() {
        // 2(3) * 3(3) * 4(3) * 5(3) = 81
        List<String> result = solution.letterCombinations("2345");
        assertEquals(81, result.size());
    }

    @Test
    void testSevenAndNine() {
        // 7(4) * 9(4) = 16
        List<String> result = solution.letterCombinations("79");
        assertEquals(16, result.size());
    }

    @Test
    void testGiantInput() {
        // "2222222" -> 3^7 = 2187
        List<String> result = solution.letterCombinations("2222222");
        assertEquals(2187, result.size());
    }

    @Test
    void testSingleDigitTwoExactContent() {
        Set<String> expected = Set.of("a", "b", "c");
        Set<String> result = new HashSet<>(solution.letterCombinations("2"));
        assertEquals(expected, result);
    }

    @Test
    void testSevenExactContent() {
        Set<String> expected = Set.of("p", "q", "r", "s");
        Set<String> result = new HashSet<>(solution.letterCombinations("7"));
        assertEquals(expected, result);
    }

    @Test
    void testNineExactContent() {
        Set<String> expected = Set.of("w", "x", "y", "z");
        Set<String> result = new HashSet<>(solution.letterCombinations("9"));
        assertEquals(expected, result);
    }

    @Test
    void testTwoThreeExactContent() {
        Set<String> expected = Set.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
        Set<String> result = new HashSet<>(solution.letterCombinations("23"));
        assertEquals(expected, result);
    }

    @Test
    void testAllDigitsSize() {
        // "23456789" -> 3*3*3*3*3*4*3*4 = 11664
        List<String> result = solution.letterCombinations("23456789");
        assertEquals(11664, result.size());
    }

    @Test
    void testAllResultsHaveCorrectLength() {
        String digits = "2379";
        List<String> result = solution.letterCombinations(digits);
        for (String s : result) {
            assertEquals(digits.length(), s.length());
        }
    }

    @Test
    void testAllResultsUnique() {
        List<String> result = solution.letterCombinations("23456789");
        assertEquals(result.size(), new HashSet<>(result).size());
    }

    @Test
    void testProductPropertyForMultipleInputs() {
        Map<Character, Integer> letterCount = Map.of(
                '2', 3, '3', 3, '4', 3, '5', 3, '6', 3, '7', 4, '8', 3, '9', 4);
        String[] inputs = {"23", "79", "234", "789", "2379"};
        for (String digits : inputs) {
            int expectedSize = 1;
            for (char c : digits.toCharArray()) {
                expectedSize *= letterCount.get(c);
            }
            assertEquals(expectedSize, solution.letterCombinations(digits).size(),
                    "Failed for digits: " + digits);
        }
    }

    @Test
    void testIndependentOracleAcrossRepresentativeInputs() {
        String[] inputs = {
                "2", "3", "7", "9", "22", "27", "72", "79", "89", "99",
                "234", "279", "777", "789", "923", "2345", "5678", "7272",
                "7939", "9999"
        };
        for (String digits : inputs) {
            assertMatchesOracle(digits);
        }
    }

    @Test
    void testExhaustiveValidInputsThroughLengthThree() {
        // This independently checks every 2-9 input of lengths 1, 2, and 3 (584 cases).
        for (int length = 1; length <= 3; length++) {
            assertAllDigitStrings("", length);
        }
    }

    @Test
    void testMaximumFourDigitInputsWithFourLetterMappings() {
        assertMatchesOracle("7777");
        assertMatchesOracle("9999");
        assertMatchesOracle("7979");
        assertMatchesOracle("9797");
    }

    @Test
    void testInvalidDigitsReturnNoCombinations() {
        assertEquals(List.of(), new LetterCombinations_17().letterCombinations("0"));
        assertEquals(List.of(), new LetterCombinations_17().letterCombinations("1"));
        assertEquals(List.of(), new LetterCombinations_17().letterCombinations("201"));
        assertEquals(List.of(), new LetterCombinations_17().letterCombinations("912"));
    }

    @Test
    void testEverySingleDigitMappingExactContent() {
        Map<String, Set<String>> expectedByDigit = Map.of(
                "2", Set.of("a", "b", "c"),
                "3", Set.of("d", "e", "f"),
                "4", Set.of("g", "h", "i"),
                "5", Set.of("j", "k", "l"),
                "6", Set.of("m", "n", "o"),
                "7", Set.of("p", "q", "r", "s"),
                "8", Set.of("t", "u", "v"),
                "9", Set.of("w", "x", "y", "z"));

        for (Map.Entry<String, Set<String>> entry : expectedByDigit.entrySet()) {
            assertEquals(entry.getValue(), new HashSet<>(solution.letterCombinations(entry.getKey())),
                    "wrong mapping for digit " + entry.getKey());
        }
    }

    @Test
    void testMaximumOfficialLengthWithMixedThreeAndFourLetterDigits() {
        assertMatchesOracle("2799");
        assertMatchesOracle("7779");
        assertMatchesOracle("2347");
    }

    @Test
    void testRepeatedCallsDoNotShareTraversalState() {
        List<String> first = solution.letterCombinations("23");
        Set<String> firstSnapshot = new HashSet<>(first);

        List<String> second = solution.letterCombinations("7");

        assertEquals(Set.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"),
                firstSnapshot);
        assertEquals(Set.of("p", "q", "r", "s"), new HashSet<>(second));
        assertEquals(firstSnapshot, new HashSet<>(first), "a later call changed an earlier result");
    }

    @Test
    void testReturnedListMutationDoesNotAffectLaterCalls() {
        List<String> discarded = solution.letterCombinations("23");
        discarded.clear();
        discarded.add("not-a-phone-combination");

        List<String> regenerated = solution.letterCombinations("23");
        assertEquals(new HashSet<>(oracle("23")), new HashSet<>(regenerated));
    }

    @Test
    void testEmptyResultsAreFreshAndMutable() {
        List<String> first = solution.letterCombinations("");
        first.add("caller-owned");

        List<String> second = solution.letterCombinations("");
        assertEquals(List.of(), second);
    }

    private void assertAllDigitStrings(String prefix, int remainingLength) {
        if (remainingLength == 0) {
            assertMatchesOracle(prefix);
            return;
        }
        for (char digit = '2'; digit <= '9'; digit++) {
            assertAllDigitStrings(prefix + digit, remainingLength - 1);
        }
    }

    /**
     * Builds the expected Cartesian product iteratively instead of sharing the solution's
     * recursive traversal, then checks both set equality and absence of duplicate outputs.
     */
    private void assertMatchesOracle(String digits) {
        List<String> expected = oracle(digits);
        List<String> actual = new LetterCombinations_17().letterCombinations(digits);

        assertEquals(expected.size(), actual.size(), "wrong count for " + digits);
        assertEquals(new HashSet<>(expected), new HashSet<>(actual), "wrong combinations for " + digits);
        assertEquals(actual.size(), new HashSet<>(actual).size(), "duplicate combination for " + digits);
    }

    private List<String> oracle(String digits) {
        Map<Character, String> mapping = Map.of(
                '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
                '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");
        List<String> combinations = new ArrayList<>();
        combinations.add("");
        for (char digit : digits.toCharArray()) {
            List<String> next = new ArrayList<>();
            for (String prefix : combinations) {
                for (char letter : mapping.get(digit).toCharArray()) {
                    next.add(prefix + letter);
                }
            }
            combinations = next;
        }
        return combinations;
    }
}
