package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxProduct_318Test {
    private final MaxProduct_318 solver = new MaxProduct_318();

    @Test public void testBasic() {
        String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        assertEquals(16, solver.maxProduct(words));  // "abcw" * "xtfn" = 4 * 4 = 16
    }

    @Test public void testAllShare() {
        String[] words = {"a", "ab", "abc"};
        assertEquals(0, solver.maxProduct(words));
    }

    @Test public void testTwoDistinct() {
        String[] words = {"a", "aa", "aaa", "aaaa"};
        assertEquals(0, solver.maxProduct(words));
    }

    @Test public void testSimple() {
        String[] words = {"ab", "cd"};
        assertEquals(4, solver.maxProduct(words));
    }

    @Test public void testEmpty() {
        String[] words = {};
        assertEquals(0, solver.maxProduct(words));
    }

    @Test public void testSingleWord() {
        String[] words = {"hello"};
        assertEquals(0, solver.maxProduct(words));
    }

    @Test public void testLongerWords() {
        String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        assertEquals(16, solver.maxProduct(words));
    }

    @Test public void testExample2() {
        String[] words = {"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
        assertEquals(4, solver.maxProduct(words));  // "ab" and "cd" = 2*2
    }

    @Test public void testSingleLetterDisjointPair() {
        assertEquals(1, solver.maxProduct(new String[]{"a", "z"}));
    }

    @Test public void testNoDisjoint() {
        String[] words = {"abc", "bca", "cab"};
        assertEquals(0, solver.maxProduct(words));
    }

    @Test public void testAnagramsAndDuplicateMasks() {
        String[] words = {"ab", "ba", "cd", "dc", "aabb"};
        assertEquals(8, solver.maxProduct(words));
    }

    @Test public void testRepeatedLettersUseWordLengths() {
        String[] words = {"aabbcc", "ddeeff", "abcdef", "fefefe"};
        assertEquals(36, solver.maxProduct(words));
    }

    @Test public void testLongestPairBeatsShortDecoys() {
        String[] words = {"a", "bcdefghij", "klmnopqrst", "abcdefghijklmnopqrstuv"};
        assertEquals(90, solver.maxProduct(words));
    }

    @Test public void testAllAlphabetBitsSplitIntoTwoWords() {
        String[] words = {"abcdefghijklm", "nopqrstuvwxyz", "abcdefghijkl", "nopqrstuvwxyz"};
        assertEquals(169, solver.maxProduct(words));
    }

    @Test public void testWordsContainingEveryLetterCannotPair() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        assertEquals(0, solver.maxProduct(new String[]{alphabet, alphabet}));
    }

    @Test public void testGiantCase() {
        String[] words = new String[100];
        for (int i = 0; i < 50; i++) words[i] = "a".repeat(i + 1);
        for (int i = 50; i < 100; i++) words[i] = "b".repeat(i - 49);
        // max product = "a"*50 * "b"*50 = 50*50 = 2500
        assertEquals(2500, solver.maxProduct(words));
    }

    @Test public void testTwoLongDisjoint() {
        String[] words = {"abcdefghij", "klmnopqrst"};
        assertEquals(100, solver.maxProduct(words));
    }

    @Test public void testSeededWordsAgainstCharacterComparisonOracle() {
        java.util.Random random = new java.util.Random(3180906L);
        for (int sample = 0; sample < 100; sample++) {
            String[] words = new String[12];
            for (int i = 0; i < words.length; i++) {
                StringBuilder word = new StringBuilder();
                for (int j = 0, length = 1 + random.nextInt(12); j < length; j++)
                    word.append((char) ('a' + random.nextInt(26)));
                words[i] = word.toString();
            }
            assertEquals(directCharacterComparisonOracle(words), solver.maxProduct(words),
                    "seed=" + sample);
        }
    }

    @Test public void testHighAlphabetBitsAndRepeatedCharacters() {
        assertEquals(30, solver.maxProduct(new String[]{"zzzzz", "yyyyyy", "zyzyzyzy"}));
    }

    @Test public void testLongestDisjointWordsAtConstraintLength() {
        assertEquals(1000000, solver.maxProduct(new String[]{"a".repeat(1000), "z".repeat(1000), "az"}));
    }

    @Test public void testMaximumArrayAndWordLengthsWithinContract() {
        String[] words = new String[1000];
        String aWord = "a".repeat(1000);
        String bWord = "b".repeat(1000);
        for (int i = 0; i < words.length / 2; i++) {
            words[i] = aWord;
            words[i + words.length / 2] = bWord;
        }
        assertEquals(1_000_000, solver.maxProduct(words));
    }

    @Test public void testInputArrayAndWordsAreNotModified() {
        String[] words = {"abc", "defgh", "ij", "abcdefghi"};
        String[] original = words.clone();
        assertEquals(15, solver.maxProduct(words));
        assertEquals(java.util.Arrays.asList(original), java.util.Arrays.asList(words));
    }

    @Test public void testRepeatedCallsDoNotLeakState() {
        assertEquals(16, solver.maxProduct(new String[]{"abcd", "efgh"}));
        assertEquals(0, solver.maxProduct(new String[]{"a", "aa", "aaa"}));
        assertEquals(1, solver.maxProduct(new String[]{"x", "y"}));
    }

    @Test public void testCallerCanReuseArrayAfterInvocation() {
        String[] words = {"ab", "cd"};
        assertEquals(4, solver.maxProduct(words));
        words[0] = "a";
        words[1] = "bc";
        assertEquals(2, solver.maxProduct(words));
    }

    @Test public void testAllRepeatedSameLetterWordsHaveNoPair() {
        String[] words = {"z", "zz", "zzz", "zzzz", "zzzzz", "zzzzzz"};
        assertEquals(0, solver.maxProduct(words));
    }

    @Test public void testMixedShortAndLongMasksAgainstIndependentOracle() {
        String[] words = {"abcabc", "def", "ghijkl", "mnop", "aef", "qrstuvwx", "yz"};
        assertEquals(directCharacterComparisonOracle(words), solver.maxProduct(words));
    }

    @Test public void testEverySingleLetterMaskAgainstIndependentOracle() {
        String[] words = new String[26];
        for (int i = 0; i < words.length; i++) {
            words[i] = String.valueOf((char) ('a' + i));
        }
        assertEquals(1, solver.maxProduct(words));
    }

    @Test public void testExhaustiveSmallAlphabetWordsAgainstIndependentOracle() {
        java.util.List<String> generated = new java.util.ArrayList<>();
        for (int length = 1; length <= 4; length++) {
            appendWordsOverAlphabet(generated, new StringBuilder(), length, "abc");
        }
        String[] words = generated.toArray(String[]::new);
        assertEquals(directCharacterComparisonOracle(words), solver.maxProduct(words));
    }

    private static void appendWordsOverAlphabet(java.util.List<String> words, StringBuilder current,
                                                int remaining, String alphabet) {
        if (remaining == 0) {
            words.add(current.toString());
            return;
        }
        for (int i = 0; i < alphabet.length(); i++) {
            current.append(alphabet.charAt(i));
            appendWordsOverAlphabet(words, current, remaining - 1, alphabet);
            current.deleteCharAt(current.length() - 1);
        }
    }

    /**
     * Direct reference implementation that compares characters rather than using the production bitmask.
     * Keeping this oracle independent catches errors in bit positions, duplicate-letter handling, and ordering.
     */
    private static int directCharacterComparisonOracle(String[] words) {
        int maximum = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                boolean disjoint = true;
                for (int left = 0; left < words[i].length() && disjoint; left++) {
                    for (int right = 0; right < words[j].length(); right++) {
                        if (words[i].charAt(left) == words[j].charAt(right)) {
                            disjoint = false;
                            break;
                        }
                    }
                }
                if (disjoint) {
                    maximum = Math.max(maximum, words[i].length() * words[j].length());
                }
            }
        }
        return maximum;
    }
}
