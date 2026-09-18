package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneratePalindromes_267Test {
    private final GeneratePalindromes_267 solution = new GeneratePalindromes_267();

    @Test
    void testBasic() {
        assertExact("aabb");
    }

    @Test
    void testSingleChar() {
        assertExact("a");
    }

    @Test
    void testNoSolution() {
        assertExact("abc");
    }

    @Test
    void testThreeSame() {
        assertExact("aaa");
    }

    @Test
    void testMultipleSolutions() {
        assertExact("aabbcc");
    }

    @Test
    void testTwoCharsNoPalindrome() {
        assertExact("ab");
    }

    @Test
    void testTwoCharsSame() {
        assertExact("aa");
    }

    @Test
    void testOddLengthPalindrome() {
        assertExact("aab");
    }

    @Test
    void testAllResultsArePalindromes() {
        assertExact("aabbcc");
    }

    @Test
    void testNoDuplicatesInResult() {
        assertExact("aabbcc");
    }

    @Test
    void testGiantInput() {
        assertExact("aabbccdd");
    }

    @Test
    void testOnePairAndOneCenter() {
        assertExact("baa");
    }

    @Test
    void testTwoPairsWithCenter() {
        assertExact("aabbc");
    }

    @Test
    void testSeveralRepeatedPairs() {
        assertExact("aabbccdde");
    }

    @Test
    void testInputOrderDoesNotAffectSet() {
        assertExact("dccbbaaccdd");
    }

    @Test
    void testAllCharactersSameEven() {
        assertExact("aaaaaa");
    }

    @Test
    void testAllCharactersSameOdd() {
        assertExact("aaaaaaa");
    }

    @Test
    void testMultipleOddFrequencies() {
        assertExact("aabbcde");
    }

    @Test
    void testFourOddFrequencies() {
        assertExact("aabbcdef");
    }

    @Test
    void testNoPairs() {
        assertExact("abcdef");
    }

    @Test
    void testSinglePairWithDifferentCenter() {
        assertExact("zza");
    }

    @Test
    void testUnevenPairCounts() {
        assertExact("aaaabbbbcc");
    }

    @Test
    void testMaximumLengthWithEightDistinctPairs() {
        // 16 characters is the LeetCode maximum; 8! outputs remain manageable.
        assertCountAndValidity("aabbccddeeffgghh", 40320);
    }

    @Test
    void testMaximumLengthWithRepeatedPairs() {
        assertCountAndValidity("aaaabbbbccccdddd", 2520);
    }

    @Test
    void testMaximumLengthImpossible() {
        assertExact("abcdefghijklmnop");
    }

    @Test
    void testResultsUseEveryInputCharacter() {
        assertExact("aabbcdd");
    }

    @Test
    void testCenterIsTheOnlyOddCharacter() {
        List<String> result = solution.generatePalindromes("aaccb");
        assertEquals(Set.of("acbca", "cabac"), new HashSet<>(result));
        assertEquals(2, result.size());
        assertEveryResultIsValid("aaccb", new HashSet<>(result));
        for (String palindrome : result) {
            assertEquals('b', palindrome.charAt(palindrome.length() / 2));
        }
    }

    private void assertExact(String input) {
        Set<String> expected = independentHalfArrangements(input);
        List<String> actualList = solution.generatePalindromes(input);
        Set<String> actual = new HashSet<>(actualList);
        assertEquals(expected, actual, "Unexpected palindromes for " + input);
        assertEquals(expected.size(), actualList.size(), "Duplicate output for " + input);
        assertEveryResultIsValid(input, actual);
    }

    private void assertCountAndValidity(String input, int expectedCount) {
        List<String> actualList = solution.generatePalindromes(input);
        Set<String> actual = new HashSet<>(actualList);
        assertEquals(expectedCount, actualList.size(), "Unexpected count for " + input);
        assertEquals(expectedCount, actual.size(), "Duplicate output for " + input);
        assertEveryResultIsValid(input, actual);
    }

    private void assertEveryResultIsValid(String input, Set<String> results) {
        Map<Character, Integer> expectedCounts = counts(input);
        for (String palindrome : results) {
            assertEquals(input.length(), palindrome.length());
            assertEquals(palindrome, new StringBuilder(palindrome).reverse().toString());
            assertEquals(expectedCounts, counts(palindrome), "Wrong character multiset: " + palindrome);
        }
    }

    /** Independent lexicographic half-permutation oracle, separate from recursive solution choices. */
    private Set<String> independentHalfArrangements(String input) {
        Map<Character, Integer> counts = counts(input);
        StringBuilder half = new StringBuilder();
        char center = 0;
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            if ((entry.getValue() & 1) == 1) {
                if (center != 0) {
                    return Set.of();
                }
                center = entry.getKey();
            }
            half.append(String.valueOf(entry.getKey()).repeat(entry.getValue() / 2));
        }

        char[] permutation = half.toString().toCharArray();
        Arrays.sort(permutation);
        Set<String> expected = new HashSet<>();
        do {
            String left = new String(permutation);
            String right = new StringBuilder(left).reverse().toString();
            expected.add(left + (center == 0 ? "" : center) + right);
        } while (nextPermutation(permutation));
        return expected;
    }

    private boolean nextPermutation(char[] values) {
        int i = values.length - 2;
        while (i >= 0 && values[i] >= values[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }
        int j = values.length - 1;
        while (values[j] <= values[i]) {
            j--;
        }
        char temp = values[i];
        values[i] = values[j];
        values[j] = temp;
        for (int left = i + 1, right = values.length - 1; left < right; left++, right--) {
            temp = values[left];
            values[left] = values[right];
            values[right] = temp;
        }
        return true;
    }

    private Map<Character, Integer> counts(String value) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char character : value.toCharArray()) {
            counts.merge(character, 1, Integer::sum);
        }
        return counts;
    }
}
