package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/09/29 11:37
 * Created with IntelliJ IDEA
 */

public class CalculateTime_1165Test {

    private final CalculateTime_1165 solution = new CalculateTime_1165();

    // Test cases based on LeetCode examples
    @Test
    public void testExample1_StandardKeyboard() {
        String keyboard = "abcdefghijklmnopqrstuvwxyz";
        String word = "cba";
        int expected = 4; // 0->2 (2) + 2->1 (1) + 1->0 (1) = 4
        assertEquals(expected, solution.calculateTime(keyboard, word));
    }

    @Test
    public void testExample2_CustomKeyboard() {
        String keyboard = "pqrstuvwxyzabcdefghijklmno";
        String word = "leetcode";
        int expected = 73;
        assertEquals(expected, solution.calculateTime(keyboard, word));
    }

    // Edge cases
    @Test
    public void testSingleCharacter() {
        String keyboard = "abcdefghijklmnopqrstuvwxyz";
        String word = "a";
        int expected = 0; // Starting at position 0, 'a' is at position 0
        assertEquals(expected, solution.calculateTime(keyboard, word));
    }

    @Test
    public void testSingleCharacterNotAtStart() {
        String keyboard = "abcdefghijklmnopqrstuvwxyz";
        String word = "z";
        int expected = 25; // From position 0 to position 25
        assertEquals(expected, solution.calculateTime(keyboard, word));
    }

    @Test
    public void testRepeatingCharacters() {
        String keyboard = "abcdefghijklmnopqrstuvwxyz";
        String word = "aaa";
        int expected = 0; // 0->0 (0) + 0->0 (0) + 0->0 (0) = 0
        assertEquals(expected, solution.calculateTime(keyboard, word));
    }

    @Test
    public void testAlternatingCharacters() {
        String keyboard = "abcdefghijklmnopqrstuvwxyz";
        String word = "aba";
        int expected = 2; // 0->0 (0) + 0->1 (1) + 1->0 (1) = 2
        assertEquals(expected, solution.calculateTime(keyboard, word));
    }

    // Corner cases - null and invalid inputs
    @Test
    public void testNullKeyboard() {
        String keyboard = null;
        String word = "test";
        int expected = 0;
        assertEquals(expected, solution.calculateTime(keyboard, word));
    }

    @Test
    public void testNullWord() {
        String keyboard = "abcdefghijklmnopqrstuvwxyz";
        String word = null;
        int expected = 0;
        assertEquals(expected, solution.calculateTime(keyboard, word));
    }

    @Test
    public void testEmptyWord() {
        String keyboard = "abcdefghijklmnopqrstuvwxyz";
        String word = "";
        int expected = 0;
        assertEquals(expected, solution.calculateTime(keyboard, word));
    }

    @Test
    public void testInvalidKeyboardLength_Short() {
        String keyboard = "abcdef"; // Only 6 characters
        String word = "test";
        int expected = 0;
        assertEquals(expected, solution.calculateTime(keyboard, word));
    }

    @Test
    public void testInvalidKeyboardLength_Long() {
        String keyboard = "abcdefghijklmnopqrstuvwxyz123"; // 29 characters
        String word = "test";
        int expected = 0;
        assertEquals(expected, solution.calculateTime(keyboard, word));
    }

    // Boundary cases
    @Test
    public void testMaxDistance() {
        String keyboard = "abcdefghijklmnopqrstuvwxyz";
        String word = "za"; // From position 25 to position 0
        int expected = 25; // 0->25 (25) + 25->0 (25) = 50
        assertEquals(50, solution.calculateTime(keyboard, word));
    }

    @Test
    public void testLongWord() {
        String keyboard = "abcdefghijklmnopqrstuvwxyz";
        String word = "abcdefghijklmnopqrstuvwxyz"; // All 26 letters in order
        int expected = 25; // 0->1->2->...->25, total = 25
        assertEquals(expected, solution.calculateTime(keyboard, word));
    }

    @Test
    public void testReverseAlphabet() {
        String keyboard = "abcdefghijklmnopqrstuvwxyz";
        String word = "zyxwvutsrqponmlkjihgfedcba"; // Reverse order
        int expected = 50; // 0->25->24->23->...->0
        assertEquals(expected, solution.calculateTime(keyboard, word));
    }

    // Custom keyboard layout tests
    @Test
    public void testCustomKeyboardLayout() {
        String keyboard = "zyxwvutsrqponmlkjihgfedcba"; // Reverse alphabet
        String word = "abc";
        // 'a' at pos 25, 'b' at pos 24, 'c' at pos 23
        // 0->25 (25) + 25->24 (1) + 24->23 (1) = 27
        int expected = 27;
        assertEquals(expected, solution.calculateTime(keyboard, word));
    }
}
