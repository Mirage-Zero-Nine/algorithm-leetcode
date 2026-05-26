package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.List;

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
}
