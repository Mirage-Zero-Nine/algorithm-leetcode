package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestCommonSubsequence1143Test {

    private final LongestCommonSubsequence_1143 test = new LongestCommonSubsequence_1143();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.longestCommonSubsequence("abcde", "ace"));
        assertEquals(3, test.longestCommonSubsequence("abc", "abc"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestCommonSubsequence("abc", "def"));
        assertEquals(0, test.longestCommonSubsequence("", "abc"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.longestCommonSubsequence("abcdefgh", "acdfgh"));
    }
}
