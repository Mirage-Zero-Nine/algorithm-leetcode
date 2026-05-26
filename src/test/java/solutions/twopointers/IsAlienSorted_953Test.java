package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class IsAlienSorted_953Test {

    private final IsAlienSorted_953 test = new IsAlienSorted_953();

    @Test
    public void testHappyCases() {
        assertTrue(test.isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        assertFalse(test.isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
        assertTrue(test.isAlienSorted(new String[]{"a"}, "abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isAlienSorted(new String[]{"kuvp", "q"}, "ngxlkthsjuoqcpavbfdermiywz"));
    }

    @Test
    public void testAdditionalHappyCases() {
        assertTrue(test.isAlienSorted(new String[]{"app", "apple"}, "abcdefghijklmnopqrstuvwxyz"));
        assertTrue(test.isAlienSorted(new String[]{"aa", "ab", "b"}, "abcdefghijklmnopqrstuvwxyz"));
        assertTrue(test.isAlienSorted(new String[]{"z", "zx"}, "zyxwvutsrqponmlkjihgfedcba"));
    }

    @Test
    public void testAdditionalNegativeAndEdgeCases() {
        assertFalse(test.isAlienSorted(new String[]{"zoo", "ant"}, "abcdefghijklmnopqrstuvwxyz"));
        assertTrue(test.isAlienSorted(new String[]{}, "abcdefghijklmnopqrstuvwxyz"));
        assertTrue(test.isAlienSorted(new String[]{"same", "same"}, "abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testAdditionalGiantCase() {
        assertTrue(test.isAlienSorted(
            new String[]{"aaa", "aab", "aac", "aad", "aae", "aaf", "aag", "aah", "aai", "aaj"},
            "abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testReversedOrder() {
        assertTrue(test.isAlienSorted(new String[]{"z", "y", "x"}, "zyxwvutsrqponmlkjihgfedcba"));
        assertFalse(test.isAlienSorted(new String[]{"a", "b", "c"}, "zyxwvutsrqponmlkjihgfedcba"));
    }

    @Test
    public void testSingleCharWords() {
        assertTrue(test.isAlienSorted(new String[]{"a", "b"}, "abcdefghijklmnopqrstuvwxyz"));
        assertFalse(test.isAlienSorted(new String[]{"b", "a"}, "abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testAllSameWords() {
        assertTrue(test.isAlienSorted(new String[]{"abc", "abc", "abc"}, "abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testGiantWordList() {
        String[] words = new String[100];
        for (int i = 0; i < 100; i++) {
            words[i] = "a".repeat(i + 1);
        }
        assertTrue(test.isAlienSorted(words, "abcdefghijklmnopqrstuvwxyz"));
    }

    // --- NEW TESTS ---

    @Test
    public void testSingleWord_alwaysTrue() {
        assertTrue(test.isAlienSorted(new String[]{"onlyone"}, "zyxwvutsrqponmlkjihgfedcba"));
    }

    @Test
    public void testTwoEqualWords_true() {
        assertTrue(test.isAlienSorted(new String[]{"equal", "equal"}, "qwertyuiopasdfghjklzxcvbnm"));
    }

    @Test
    public void testWordVsPrefix_longerFirst_false() {
        // "apple" before "app" violates order since longer word comes first
        assertFalse(test.isAlienSorted(new String[]{"apple", "app"}, "qwertyuiopasdfghjklzxcvbnm"));
    }

    @Test
    public void testPrefixFirst_true() {
        // "app" before "apple" is valid in any order
        assertTrue(test.isAlienSorted(new String[]{"app", "apple"}, "zyxwvutsrqponmlkjihgfedcba"));
    }

    @Test
    public void testFullReversedOrder_sortedDescending() {
        String reversed = "zyxwvutsrqponmlkjihgfedcba";
        // In reversed order, z < y < x < ... < a
        assertTrue(test.isAlienSorted(new String[]{"zoo", "yak", "xray", "ant"}, reversed));
        assertFalse(test.isAlienSorted(new String[]{"ant", "zoo"}, reversed));
    }

    @Test
    public void testEmptyWordInList() {
        // Empty string is prefix of everything, so it should come first
        assertTrue(test.isAlienSorted(new String[]{"", "a"}, "abcdefghijklmnopqrstuvwxyz"));
        assertFalse(test.isAlienSorted(new String[]{"a", ""}, "abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testRepeatedIdenticalWords_multipleEntries() {
        assertTrue(test.isAlienSorted(new String[]{"xyz", "xyz", "xyz", "xyz", "xyz"}, "zyxwvutsrqponmlkjihgfedcba"));
    }

    @Test
    public void testWordsDifferOnlyAtLastChar() {
        // "abc" vs "abd" — depends on order of c vs d
        String standard = "abcdefghijklmnopqrstuvwxyz";
        assertTrue(test.isAlienSorted(new String[]{"abc", "abd"}, standard));
        assertFalse(test.isAlienSorted(new String[]{"abd", "abc"}, standard));
        // In reversed order, d < c
        String reversed = "zyxwvutsrqponmlkjihgfedcba";
        assertTrue(test.isAlienSorted(new String[]{"abd", "abc"}, reversed));
    }

    @Test
    public void testLongWords_1000chars() {
        String base = "a".repeat(999);
        String word1 = base + "a";
        String word2 = base + "b";
        assertTrue(test.isAlienSorted(new String[]{word1, word2}, "abcdefghijklmnopqrstuvwxyz"));
        assertFalse(test.isAlienSorted(new String[]{word2, word1}, "abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testManyWords_100plus_preSortedInCustomOrder() {
        String order = "zyxwvutsrqponmlkjihgfedcba";
        int[] mapping = new int[26];
        for (int i = 0; i < 26; i++) {
            mapping[order.charAt(i) - 'a'] = i;
        }
        // Generate 150 random words and sort them according to custom order
        Random rng = new Random(12345L);
        String[] words = new String[150];
        for (int i = 0; i < 150; i++) {
            int len = rng.nextInt(5) + 1;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                sb.append((char) ('a' + rng.nextInt(26)));
            }
            words[i] = sb.toString();
        }
        Arrays.sort(words, (a, b) -> {
            for (int i = 0; i < Math.min(a.length(), b.length()); i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    return mapping[a.charAt(i) - 'a'] - mapping[b.charAt(i) - 'a'];
                }
            }
            return a.length() - b.length();
        });
        assertTrue(test.isAlienSorted(words, order));
    }

    @Test
    public void testProperty_standardOrderMatchesLexicographic() {
        String standard = "abcdefghijklmnopqrstuvwxyz";
        String[] words = {"alpha", "beta", "gamma", "omega", "zeta"};
        Arrays.sort(words);
        assertTrue(test.isAlienSorted(words, standard));
    }
}
