package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindNumOfValidWords_1178Test {
    private final FindNumOfValidWords_1178 solver = new FindNumOfValidWords_1178();

    @Test public void testBasic() {
        String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};
        assertEquals(List.of(1, 1, 3, 2, 4, 0), solver.findNumOfValidWords(words, puzzles));
    }

    @Test public void testSinglePuzzle() {
        String[] words = {"apple"};
        String[] puzzles = {"applexy"};
        assertEquals(List.of(1), solver.findNumOfValidWords(words, puzzles));
    }

    @Test public void testNoMatch() {
        String[] words = {"xyz"};
        String[] puzzles = {"abcdefg"};
        assertEquals(List.of(0), solver.findNumOfValidWords(words, puzzles));
    }

    @Test public void testMissingFirstLetter() {
        // word contains only letters in puzzle, but not the first letter of puzzle
        String[] words = {"bcd"};
        String[] puzzles = {"abcdefg"};
        assertEquals(List.of(0), solver.findNumOfValidWords(words, puzzles));
    }

    @Test public void testMultipleWords() {
        String[] words = {"cab", "bca", "abc"};
        String[] puzzles = {"abcdefg"};
        assertEquals(List.of(3), solver.findNumOfValidWords(words, puzzles));
    }

    @Test public void testWordWithRepeatedChars() {
        String[] words = {"aaaa"};
        String[] puzzles = {"abcdefg"};
        assertEquals(List.of(1), solver.findNumOfValidWords(words, puzzles));
    }

    @Test public void testEmptyWords() {
        String[] words = {};
        String[] puzzles = {"abcdefg"};
        assertEquals(List.of(0), solver.findNumOfValidWords(words, puzzles));
    }

    @Test public void testWordExceedsLettersInPuzzle() {
        // word has letter 'z' not in puzzle
        String[] words = {"az"};
        String[] puzzles = {"abcdefg"};
        assertEquals(List.of(0), solver.findNumOfValidWords(words, puzzles));
    }

    @Test public void testMultiplePuzzlesMultipleWords() {
        String[] words = {"ab", "ac", "ad", "bc", "bd"};
        String[] puzzles = {"abcdefg", "bcdefga"};
        // puzzle "abcdefg": first letter 'a', valid words containing 'a' and subset of puzzle: ab, ac, ad -> 3
        // puzzle "bcdefga": first letter 'b', valid words containing 'b' and subset of puzzle: ab, bc, bd -> 3
        assertEquals(List.of(3, 3), solver.findNumOfValidWords(words, puzzles));
    }

    @Test public void testSingleCharWord() {
        String[] words = {"a", "b", "c"};
        String[] puzzles = {"abcdefg"};
        // first letter of puzzle is 'a', only "a" contains 'a'
        assertEquals(List.of(1), solver.findNumOfValidWords(words, puzzles));
    }

    @Test public void testGiantCase() {
        // large number of words and puzzles
        String[] words = new String[1000];
        for (int i = 0; i < 1000; i++) {
            words[i] = "abcdefg";
        }
        String[] puzzles = new String[100];
        for (int i = 0; i < 100; i++) {
            puzzles[i] = "abcdefg";
        }
        List<Integer> result = solver.findNumOfValidWords(words, puzzles);
        assertEquals(100, result.size());
        for (int count : result) {
            assertEquals(1000, count);
        }
    }

    @Test public void testEveryRequiredPuzzleLetter() {
        String[] words = {"aaa", "bbb", "ccc", "abc", "abcdefg", "xyz"};
        assertEquals(java.util.List.of(3, 3, 3, 1, 1, 1, 1), solver.findNumOfValidWords(words,
                new String[]{"abcdefg", "bcdefga", "cdefgab", "defgabc", "efgabcd", "fgabcde", "gabcdef"}));
    }

    @Test public void testSeededWordsAgainstCharacterMembershipOracle() {
        java.util.Random random = new java.util.Random(11780906L);
        for (int sample = 0; sample < 40; sample++) {
            String[] words = new String[50];
            for (int i = 0; i < words.length; i++) {
                StringBuilder word = new StringBuilder();
                for (int j = 0, length = 4 + random.nextInt(8); j < length; j++)
                    word.append((char) ('a' + random.nextInt(10)));
                words[i] = word.toString();
            }
            String[] puzzles = {"abcdefg", "bcdefgh", "cdefghi", "defghij", "jabcdef"};
            java.util.List<Integer> expected = new java.util.ArrayList<>();
            for (String puzzle : puzzles) {
                int count = 0;
                for (String word : words) {
                    boolean valid = word.indexOf(puzzle.charAt(0)) >= 0;
                    for (char letter : word.toCharArray()) valid &= puzzle.indexOf(letter) >= 0;
                    if (valid) count++;
                }
                expected.add(count);
            }
            assertEquals(expected, solver.findNumOfValidWords(words, puzzles));
        }
    }

    @Test public void testDuplicatesCountSeparatelyAndZIsIncluded() {
        assertEquals(java.util.List.of(3, 0), solver.findNumOfValidWords(
                new String[]{"zzzz", "zzzz", "zabc", "abcd"},
                new String[]{"zabcdef", "yabcdef"}));
    }

    @Test public void testOfficialSecondExample() {
        assertEquals(List.of(0, 1, 3, 2, 0), solver.findNumOfValidWords(
                new String[]{"apple", "pleas", "please"},
                new String[]{"aelwxyz", "aelpxyz", "aelpsxy", "saelpxy", "xaelpsy"}));
    }

    @Test public void testWordMustContainPuzzleFirstLetterEvenWhenItIsARepeatedLetter() {
        assertEquals(List.of(2, 3, 4), solver.findNumOfValidWords(
                new String[]{"bbbb", "cccc", "dddd", "bcde", "acde", "abcd"},
                new String[]{"abcdeff", "bcdefga", "cdefgab"}));
    }

    @Test public void testWordsUsingAllSevenPuzzleLetters() {
        String[] words = {"abcdefg", "gfedcba", "aaaaabbbbcccccdddddeeeeefffffggggg", "abcdef", "bcdefg"};
        assertEquals(List.of(4, 5, 5, 5, 5, 5, 4), solver.findNumOfValidWords(words,
                new String[]{"abcdefg", "bcdefga", "cdefgab", "defgabc", "efgabcd", "fgabcde", "gabcdef"}));
    }

    @Test public void testWordsWithSeveralLettersOutsidePuzzleAreRejected() {
        assertEquals(List.of(1, 0, 2), solver.findNumOfValidWords(
                new String[]{"aaaa", "aeh", "abz", "zaaa", "axyz"},
                new String[]{"abcdefg", "bcdefga", "zabcdef"}));
    }

    @Test public void testDuplicatePuzzlesPreserveOrderAndReturnIndependentCounts() {
        String[] words = {"aaaa", "abbb", "bccd", "cdef", "zzzz"};
        String[] puzzles = {"abcdefg", "zabcdef", "abcdefg", "cdefgab", "zabcdef"};
        assertEquals(expectedByDirectMembership(words, puzzles), solver.findNumOfValidWords(words, puzzles));
    }

    @Test public void testRepeatedPuzzleCharactersAreHandledBySetMembership() {
        assertEquals(List.of(1, 3, 1), solver.findNumOfValidWords(
                new String[]{"aaaa", "abbb", "accc", "bbbb", "cccc", "zzzz"},
                new String[]{"aaaaaaa", "aabbbcc", "zzzzzzz"}));
    }

    @Test public void testEveryAlphabetBitBoundary() {
        String[] words = {"aaaa", "bbbb", "mmmm", "nnnn", "yyyy", "zzzz", "azaz", "yzyz", "abcdefghijklmnopqrstuvwxyz"};
        String[] puzzles = {"abcdefg", "mnopqrs", "xyzabcd", "zabcdef", "yabcdef"};
        assertEquals(expectedByDirectMembership(words, puzzles), solver.findNumOfValidWords(words, puzzles));
    }

    @Test public void testSingleLetterPuzzlesUseOnlyWordsContainingThatLetter() {
        assertEquals(List.of(2, 1, 1, 0), solver.findNumOfValidWords(
                new String[]{"a", "aa", "ab", "b", "ba", "abc", "c"},
                new String[]{"a", "b", "c", "z"}));
    }

    @Test public void testEmptyPuzzleArrayReturnsEmptyResult() {
        assertEquals(List.of(), solver.findNumOfValidWords(new String[]{"aaaa", "bbbb"}, new String[]{}));
    }

    @Test public void testInputsAreNotMutated() {
        String[] words = {"able", "bake", "cable", "zebra"};
        String[] puzzles = {"abcdefg", "zabcdef", "bcdefga"};
        String[] originalWords = words.clone();
        String[] originalPuzzles = puzzles.clone();
        List<Integer> expected = expectedByDirectMembership(words, puzzles);

        assertEquals(expected, solver.findNumOfValidWords(words, puzzles));
        assertArrayEquals(originalWords, words);
        assertArrayEquals(originalPuzzles, puzzles);
    }

    @Test public void testRepeatedCallsDoNotRetainPreviousMasksOrResults() {
        String[] firstWords = {"aaaa", "abbb"};
        String[] firstPuzzles = {"abcdefg"};
        String[] secondWords = {"zzzz", "zabc"};
        String[] secondPuzzles = {"zabcdef", "abcdefg"};

        assertEquals(List.of(2), solver.findNumOfValidWords(firstWords, firstPuzzles));
        assertEquals(List.of(2, 0), solver.findNumOfValidWords(secondWords, secondPuzzles));
        assertEquals(List.of(2), solver.findNumOfValidWords(firstWords, firstPuzzles));
    }

    @Test public void testMaximumWordLengthAndRepeatedCharacters() {
        String[] words = {"a".repeat(50), "a".repeat(49) + "g", "a".repeat(49) + "z", "b".repeat(50)};
        String[] puzzles = {"abcdefg", "zabcdef"};
        assertEquals(List.of(2, 1), solver.findNumOfValidWords(words, puzzles));
    }

    @Test public void testMaximumWordCountWithOnePuzzle() {
        String[] words = new String[100_000];
        Arrays.fill(words, "abcdefg");
        assertEquals(List.of(100_000), solver.findNumOfValidWords(words, new String[]{"abcdefg"}));
    }

    @Test public void testMaximumPuzzleCountWithOneWord() {
        String[] puzzles = new String[10_000];
        Arrays.fill(puzzles, "zabcdef");
        assertEquals(repeatedOnes(10_000), solver.findNumOfValidWords(new String[]{"zzzz"}, puzzles));
    }

    @Test public void testAllNonemptySubsetsOfSevenLettersAgainstDirectOracle() {
        String[] words = new String[127];
        for (int mask = 1; mask < 128; mask++) {
            words[mask - 1] = lettersForMask(mask, 'a');
        }
        String[] puzzles = {"abcdefg", "bcdefga", "cdefgab", "defgabc", "efgabcd", "fgabcde", "gabcdef"};
        assertEquals(expectedByDirectMembership(words, puzzles), solver.findNumOfValidWords(words, puzzles));
    }

    @Test public void testWordsWithNoPuzzleFirstLetterAreAllRejected() {
        String[] words = {"bbbb", "cccc", "dddd", "efgh", "xyzxyz", "bcdefg"};
        assertEquals(List.of(0, 0, 0), solver.findNumOfValidWords(words,
                new String[]{"aefghij", "zaefghi", "yaefghz"}));
    }

    @Test public void testZeroAndOneBitWordsAreCountedAccordingToTheirFirstLetter() {
        assertEquals(List.of(2, 2, 1), solver.findNumOfValidWords(
                new String[]{"a", "aa", "b", "bb", "c", "abc", "def"},
                new String[]{"a", "b", "c"}));
    }

    @Test public void testSeededRandomCasesAgainstIndependentMembershipOracle() {
        Random random = new Random(1178_2026L);
        for (int sample = 0; sample < 200; sample++) {
            String[] words = new String[15 + random.nextInt(36)];
            for (int i = 0; i < words.length; i++) {
                int length = 1 + random.nextInt(15);
                StringBuilder word = new StringBuilder(length);
                for (int j = 0; j < length; j++) {
                    word.append((char) ('a' + random.nextInt(26)));
                }
                words[i] = word.toString();
            }

            String[] puzzles = new String[1 + random.nextInt(12)];
            for (int i = 0; i < puzzles.length; i++) {
                StringBuilder puzzle = new StringBuilder();
                int length = 1 + random.nextInt(9);
                for (int j = 0; j < length; j++) {
                    puzzle.append((char) ('a' + random.nextInt(26)));
                }
                puzzles[i] = puzzle.toString();
            }
            assertEquals(expectedByDirectMembership(words, puzzles), solver.findNumOfValidWords(words, puzzles),
                    "seed " + sample);
        }
    }

    private static List<Integer> expectedByDirectMembership(String[] words, String[] puzzles) {
        List<Integer> expected = new ArrayList<>(puzzles.length);
        for (String puzzle : puzzles) {
            int count = 0;
            for (String word : words) {
                if (word.indexOf(puzzle.charAt(0)) < 0) {
                    continue;
                }
                boolean usesOnlyPuzzleLetters = true;
                for (int i = 0; i < word.length(); i++) {
                    if (puzzle.indexOf(word.charAt(i)) < 0) {
                        usesOnlyPuzzleLetters = false;
                        break;
                    }
                }
                if (usesOnlyPuzzleLetters) {
                    count++;
                }
            }
            expected.add(count);
        }
        return expected;
    }

    private static String lettersForMask(int mask, char firstLetter) {
        StringBuilder word = new StringBuilder();
        for (int bit = 0; bit < 7; bit++) {
            if ((mask & (1 << bit)) != 0) {
                word.append((char) (firstLetter + bit));
            }
        }
        return word.toString();
    }

    private static List<Integer> repeatedOnes(int size) {
        List<Integer> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(1);
        }
        return result;
    }
}
