package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link GroupAnagrams_49}.
 *
 * <p>The solution may return groups and group members in any order. Therefore,
 * the assertions normalize both expected and actual results before comparing
 * them. Normalization sorts each group and then sorts the groups themselves.
 * Unlike a {@code Set}, this preserves duplicate strings and verifies that no
 * input occurrence is lost.</p>
 */
class GroupAnagrams_49Test {

    private final GroupAnagrams_49 solution = new GroupAnagrams_49();

    @Test
    void groupsTheOfficialExample() {
        assertGroupsEqual(
                List.of(
                        List.of("eat", "tea", "ate"),
                        List.of("tan", "nat"),
                        List.of("bat")
                ),
                solution.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"})
        );
    }

    @Test
    void groupsSeveralPermutationsOfTheSameWord() {
        assertGroupsEqual(
                List.of(List.of("abc", "bca", "cab", "acb", "cba", "bac")),
                solution.groupAnagrams(new String[]{"abc", "bca", "cab", "acb", "cba", "bac"})
        );
    }

    @Test
    void keepsDifferentWordsWithDifferentLengthsSeparate() {
        assertGroupsEqual(
                List.of(
                        List.of("a"),
                        List.of("aa"),
                        List.of("aaa"),
                        List.of("ab", "ba"),
                        List.of("aab", "aba"),
                        List.of("abc", "cba")
                ),
                solution.groupAnagrams(new String[]{
                        "a", "aa", "aaa", "ab", "ba", "aab", "aba", "abc", "cba"
                })
        );
    }

    @Test
    void distinguishesStringsWithSimilarButDifferentFrequencies() {
        assertGroupsEqual(
                List.of(
                        List.of("aabb", "baba", "abba", "baab"),
                        List.of("abbb"),
                        List.of("aaab")
                ),
                solution.groupAnagrams(new String[]{
                        "aabb", "baba", "abba", "baab", "abbb", "aaab"
                })
        );
    }

    @Test
    void preservesDuplicateWordsAndDuplicateAnagrams() {
        assertGroupsEqual(
                List.of(
                        List.of("abc", "abc", "abc", "bca", "cab"),
                        List.of("listen", "silent", "enlist", "listen")
                ),
                solution.groupAnagrams(new String[]{
                        "abc", "listen", "abc", "bca", "silent", "cab", "enlist", "abc", "listen"
                })
        );
    }

    @Test
    void groupsEmptyStringsAndPreservesTheirOccurrences() {
        assertGroupsEqual(
                List.of(List.of("", "", "")),
                solution.groupAnagrams(new String[]{"", "", ""})
        );
    }

    @Test
    void handlesAStringContainingEveryLowercaseLetter() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String reversed = new StringBuilder(alphabet).reverse().toString();
        String rotated = alphabet.substring(7) + alphabet.substring(0, 7);

        assertGroupsEqual(
                List.of(List.of(alphabet, reversed, rotated)),
                solution.groupAnagrams(new String[]{alphabet, reversed, rotated})
        );
    }

    @Test
    void handlesSingleCharacterStrings() {
        assertGroupsEqual(
                List.of(
                        List.of("a", "a", "a"),
                        List.of("b", "b"),
                        List.of("z")
                ),
                solution.groupAnagrams(new String[]{"a", "b", "a", "z", "b", "a"})
        );
    }

    @Test
    void keepsAllUniqueStringsInSeparateGroups() {
        String[] input = {"abc", "def", "ghi", "abcd", "efgh", "xyz"};

        assertGroupsEqual(
                List.of(
                        List.of("abc"),
                        List.of("def"),
                        List.of("ghi"),
                        List.of("abcd"),
                        List.of("efgh"),
                        List.of("xyz")
                ),
                solution.groupAnagrams(input)
        );
    }

    @Test
    void handlesMultiDigitCharacterCounts() {
        String first = "a".repeat(12) + "b".repeat(3);
        String permutation = "b".repeat(3) + "a".repeat(12);
        String differentFrequency = "a".repeat(11) + "b".repeat(4);

        assertGroupsEqual(
                List.of(
                        List.of(first, permutation),
                        List.of(differentFrequency)
                ),
                solution.groupAnagrams(new String[]{first, permutation, differentFrequency})
        );
    }

    @Test
    void doesNotConfuseACharacterCountWithAnotherSignature() {
        // This catches signatures built with sb.append(i + 'a'). Since that
        // expression appends numeric code points, "ab" could collide with a
        // sufficiently long sequence of "a" characters.
        String manyAs = "a".repeat(1_981);

        assertGroupsEqual(
                List.of(List.of("ab"), List.of(manyAs)),
                solution.groupAnagrams(new String[]{manyAs, "ab"})
        );
    }

    @Test
    void handlesNullInput() {
        assertEquals(List.of(), solution.groupAnagrams(null));
    }

    @Test
    void handlesAnEmptyInputArray() {
        assertEquals(List.of(), solution.groupAnagrams(new String[0]));
    }

    @Test
    void returnsTheSingleInputWithoutChangingIt() {
        String[] input = {"hello"};
        String[] original = input.clone();

        assertGroupsEqual(List.of(List.of("hello")), solution.groupAnagrams(input));
        assertArrayEquals(original, input);
    }

    @Test
    void handlesTheMaximumNumberOfInputStrings() {
        // LeetCode 49 allows up to 10,000 input strings. This test verifies
        // that every occurrence is retained while several groups are formed.
        String[] input = new String[10_000];
        for (int i = 0; i < input.length; i++) {
            input[i] = switch (i % 4) {
                case 0 -> "listen";
                case 1 -> "silent";
                case 2 -> "enlist";
                default -> "google";
            };
        }

        List<List<String>> result = solution.groupAnagrams(input);

        assertEquals(2, result.size());
        assertEquals(7_500, findGroupContaining(result, "listen").size());
        assertEquals(2_500, findGroupContaining(result, "google").size());
        assertEquals(10_000, result.stream().mapToInt(List::size).sum());
    }

    private static void assertGroupsEqual(List<List<String>> expected, List<List<String>> actual) {
        assertEquals(normalizeGroups(expected), normalizeGroups(actual));
    }

    private static List<List<String>> normalizeGroups(List<List<String>> groups) {
        List<List<String>> normalized = new ArrayList<>();
        for (List<String> group : groups) {
            List<String> sortedGroup = new ArrayList<>(group);
            sortedGroup.sort(Comparator.naturalOrder());
            normalized.add(sortedGroup);
        }

        // The NUL separator cannot occur in the problem's lowercase-letter
        // input and also keeps empty strings unambiguous in the comparison key.
        normalized.sort(Comparator.comparing((List<String> group) -> String.join("\u0000", group)));
        return normalized;
    }

    private static List<String> findGroupContaining(List<List<String>> groups, String value) {
        return groups.stream()
                .filter(group -> group.contains(value))
                .findFirst()
                .orElseThrow(() -> new AssertionError("No group contains: " + value));
    }
}
