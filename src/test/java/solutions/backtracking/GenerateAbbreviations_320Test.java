package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenerateAbbreviations_320Test {
    /** Check the recursive approach against an independent bit-mask oracle. */
    private void assertExact(String word) {
        List<String> actual = new GenerateAbbreviations_320().generateAbbreviations(word);
        Set<String> expected = oracle(word);
        // Order is unspecified, but every mask must produce exactly one result.
        assertEquals(expected, new HashSet<>(actual), word);
        assertEquals(expected.size(), actual.size(), "duplicate abbreviation: " + word);
        assertEquals(1 << word.length(), actual.size(), "one result per mask: " + word);
    }

    /** Enumerates runs independently of the recursive implementation. */
    private Set<String> oracle(String word) {
        Set<String> expected = new HashSet<>();
        for (int mask = 0; mask < (1 << word.length()); mask++) {
            StringBuilder abbreviation = new StringBuilder();
            int position = 0;
            while (position < word.length()) {
                if ((mask & (1 << position)) != 0) {
                    int end = position;
                    while (end < word.length() && (mask & (1 << end)) != 0) {
                        end++;
                    }
                    abbreviation.append(end - position);
                    position = end;
                } else {
                    abbreviation.append(word.charAt(position++));
                }
            }
            expected.add(abbreviation.toString());
        }
        return expected;
    }

    /**
     * Independently parses one output and verifies that it consumes the source exactly once.
     * A digit run denotes one positive-length, contiguous abbreviated substring.
     */
    private void assertWellFormed(String word, String abbreviation) {
        int sourcePosition = 0;
        int abbreviationPosition = 0;
        while (abbreviationPosition < abbreviation.length()) {
            char token = abbreviation.charAt(abbreviationPosition);
            if (Character.isDigit(token)) {
                assertTrue(token != '0', "zero-length abbreviation: " + abbreviation);
                int numberStart = abbreviationPosition;
                while (abbreviationPosition < abbreviation.length()
                        && Character.isDigit(abbreviation.charAt(abbreviationPosition))) {
                    abbreviationPosition++;
                }
                int count = Integer.parseInt(abbreviation.substring(numberStart, abbreviationPosition));
                assertTrue(count > 0, "abbreviation length must be positive: " + abbreviation);
                assertTrue(sourcePosition + count <= word.length(),
                        "abbreviation consumes beyond source: " + abbreviation);
                sourcePosition += count;
            } else {
                assertTrue(sourcePosition < word.length(), "literal consumes beyond source: " + abbreviation);
                assertEquals(word.charAt(sourcePosition), token,
                        "literal differs from source in " + abbreviation);
                sourcePosition++;
                abbreviationPosition++;
            }
        }
        assertEquals(word.length(), sourcePosition, "abbreviation does not consume source: " + abbreviation);
    }

    @Test
    void testEmpty() {
        assertExact("");
    }

    @Test
    void testSingleChar() {
        assertExact("a");
    }

    @Test
    void testTwoChars() {
        assertExact("ab");
    }

    @Test
    void testThreeChars() {
        assertExact("abc");
    }

    @Test
    void testWord() {
        assertExact("word");
    }

    @Test
    void testFiveChars() {
        assertExact("abcde");
    }

    @Test
    void testRepeatedLetters() {
        assertExact("aaaa");
    }

    @Test
    void testRepeatedRuns() {
        assertExact("aabbaa");
    }

    @Test
    void testAlternatingLetters() {
        assertExact("ababab");
    }

    @Test
    void testNonAdjacentRepeatedLetters() {
        assertExact("abacaba");
    }

    @Test
    void testSeveralAdjacentRuns() {
        assertExact("abbac");
    }

    @Test
    void testKnownPartialAbbreviations() {
        Set<String> actual = new HashSet<>(new GenerateAbbreviations_320().generateAbbreviations("word"));
        assertTrue(actual.containsAll(Set.of("word", "4", "w3", "3d", "1o1d", "2r1")));
    }

    @Test
    void testOriginalWordIsPresent() {
        assertExact("hello");
        Set<String> actual = new HashSet<>(new GenerateAbbreviations_320().generateAbbreviations("hello"));
        assertTrue(actual.contains("hello"));
    }

    @Test
    void testAllCharactersAbbreviated() {
        Set<String> actual = new HashSet<>(new GenerateAbbreviations_320().generateAbbreviations("hello"));
        assertTrue(actual.contains("5"));
    }

    @Test
    void testNoZeroLengthAbbreviationIsEmitted() {
        assertExact("abcdef");
        Set<String> actual = new HashSet<>(new GenerateAbbreviations_320().generateAbbreviations("abcdef"));
        assertFalse(actual.contains("0"));
    }

    @Test
    void testNoDuplicateResultsForRepeatedLetters() {
        assertExact("aaaaaa");
        List<String> actual = new GenerateAbbreviations_320().generateAbbreviations("aaaaaa");
        assertEquals(actual.size(), new HashSet<>(actual).size());
    }

    @Test
    void testFreshInstanceStateAcrossCalls() {
        GenerateAbbreviations_320 solution = new GenerateAbbreviations_320();
        assertEquals(8, solution.generateAbbreviations("abc").size());
        assertEquals(2, solution.generateAbbreviations("x").size());
        assertEquals(16, solution.generateAbbreviations("wxyz").size());
    }

    @Test
    void testRepeatedCallsRemainStable() {
        GenerateAbbreviations_320 solution = new GenerateAbbreviations_320();
        Set<String> expectedA = oracle("repeat");
        Set<String> expectedB = oracle("x");
        List<String> first = solution.generateAbbreviations("repeat");
        List<String> middle = solution.generateAbbreviations("x");
        List<String> last = solution.generateAbbreviations("repeat");
        assertEquals(expectedA, new HashSet<>(first));
        assertEquals(expectedB, new HashSet<>(middle));
        assertEquals(expectedA, new HashSet<>(last));
        assertEquals(expectedA.size(), first.size());
        assertEquals(expectedB.size(), middle.size());
        assertEquals(expectedA.size(), last.size());
    }

    @Test
    void testSevenCharacters() {
        assertExact("abcdefg");
    }

    @Test
    void testEightCharacters() {
        assertExact("abcdefgh");
    }

    @Test
    void testTenCharacters() {
        assertExact("abcdefghij");
    }

    @Test
    void testMaximumLeetCodeLength() {
        // LeetCode permits length <= 15; 2^15 results remains a bounded test.
        assertExact("abcdefghijklmno");
    }

    @Test
    void testLeadingAndTrailingRuns() {
        assertExact("aaabbb");
    }

    @Test
    void testExhaustiveBinaryWordsThroughLengthSix() {
        // Exhaustive small inputs catch state-transition errors independently of hand-picked words.
        for (int length = 0; length <= 6; length++) {
            for (int mask = 0; mask < (1 << length); mask++) {
                StringBuilder word = new StringBuilder(length);
                for (int position = 0; position < length; position++) {
                    word.append((mask & (1 << position)) == 0 ? 'a' : 'b');
                }
                assertExact(word.toString());
            }
        }
    }

    @Test
    void testEveryGeneratedAbbreviationIsWellFormed() {
        String word = "abcdefghijklmno";
        for (String abbreviation : new GenerateAbbreviations_320().generateAbbreviations(word)) {
            assertWellFormed(word, abbreviation);
        }
    }

    @Test
    void testDecimalRunLengthsAtMaximumWordSize() {
        Set<String> actual = new HashSet<>(
                new GenerateAbbreviations_320().generateAbbreviations("abcdefghijklmno"));
        assertTrue(actual.contains("15"));
        assertTrue(actual.contains("10klmno"));
        assertTrue(actual.contains("9jklmno"));
        assertFalse(actual.contains("0"));
    }

    @Test
    void testInvalidAdjacentOrOverlappingFormsAreAbsent() {
        Set<String> actual = new HashSet<>(new GenerateAbbreviations_320().generateAbbreviations("abcde"));
        // Adjacent abbreviated runs must be merged, and overlapping runs cannot be represented.
        assertFalse(actual.contains("23"));
        assertFalse(actual.contains("22de"));
        assertFalse(actual.contains("11cde"));
        assertFalse(actual.contains("0bcde"));
    }

    @Test
    void testReturnedListMutationDoesNotLeakIntoLaterCalls() {
        GenerateAbbreviations_320 solution = new GenerateAbbreviations_320();
        List<String> first = solution.generateAbbreviations("abc");
        first.clear();
        assertExact("abc");
        assertExact("xy");
    }
}
