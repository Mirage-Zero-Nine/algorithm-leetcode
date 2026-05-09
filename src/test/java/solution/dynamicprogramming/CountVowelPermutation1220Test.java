package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountVowelPermutation1220Test {

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
}
