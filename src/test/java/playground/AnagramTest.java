package playground;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AnagramTest {
    @ParameterizedTest
    @MethodSource("cases")
    void returnsAllUniqueAnagrams(String input, int expectedCount) {
        List<String> actual = new Anagram(input).findAnagram();
        assertEquals(expectedCount, actual.size());
        assertEquals(expectedCount, new HashSet<>(actual).size());
        for (String value : actual) {
            assertEquals(input.length(), value.length());
            assertTrue(sameLetters(input, value));
        }
    }

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of("ab", 2), Arguments.of("abc", 6), Arguments.of("aab", 3),
                Arguments.of("aaa", 1), Arguments.of("aabb", 6), Arguments.of("abcd", 24),
                Arguments.of("abca", 12), Arguments.of("zzxy", 12), Arguments.of("12", 2),
                Arguments.of("Aab", 6), Arguments.of("a!a", 3), Arguments.of(" ", 0),
                Arguments.of("", 0), Arguments.of("x", 0), Arguments.of("aba", 3),
                Arguments.of("aabc", 12), Arguments.of("112", 3), Arguments.of("!?", 2),
                Arguments.of("aabbcc", 90), Arguments.of("abcde", 120), Arguments.of("aabcde", 360)
        );
    }

    private static boolean sameLetters(String left, String right) {
        int[] counts = new int[Character.MAX_VALUE + 1];
        for (int i = 0; i < left.length(); i++) counts[left.charAt(i)]++;
        for (int i = 0; i < right.length(); i++) counts[right.charAt(i)]--;
        for (int count : counts) if (count != 0) return false;
        return true;
    }
}
