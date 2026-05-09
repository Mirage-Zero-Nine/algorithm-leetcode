package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxVowels1456Test {

    private final MaxVowels_1456 test = new MaxVowels_1456();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.maxVowels("abciiidef", 3));
        assertEquals(2, test.maxVowels("aeiou", 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxVowels("", 1));
        assertEquals(0, test.maxVowels("bcd", 2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.maxVowels("leetcode", 3));
    }
}
