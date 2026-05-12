package solution.bitmanipulation;

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
}
