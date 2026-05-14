package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountVowelPermutation_1220Test {

    private final CountVowelPermutation_1220 test = new CountVowelPermutation_1220();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.countVowelPermutation(1));
        assertEquals(10, test.countVowelPermutation(2));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(5, test.countVowelPermutation(1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(68, test.countVowelPermutation(5));
    }

    @Test
    public void testN3() {
        assertEquals(19, test.countVowelPermutation(3));
    }

    @Test
    public void testN4() {
        assertEquals(35, test.countVowelPermutation(4));
    }

    @Test
    public void testN10() {
        assertEquals(1739, test.countVowelPermutation(10));
    }

    @Test
    public void testN20() {
        assertEquals(1151090, test.countVowelPermutation(20));
    }

    @Test
    public void testN50() {
        assertEquals(227130014, test.countVowelPermutation(50));
    }

    @Test
    public void testN100() {
        assertEquals(173981881, test.countVowelPermutation(100));
    }

    @Test
    public void testGiantCase() {
        // n = 20000, just verify it returns a valid modular result
        int result = test.countVowelPermutation(20000);
        assertEquals(true, result >= 0 && result < 1_000_000_007);
    }
}
