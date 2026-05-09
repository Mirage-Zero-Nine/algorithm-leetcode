package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestPalindromeSubseq516Test {

    private final LongestPalindromeSubseq_516 test = new LongestPalindromeSubseq_516();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.longestPalindromeSubseq("bbbab"));
        assertEquals(2, test.longestPalindromeSubseq("cbbd"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestPalindromeSubseq(null));
        assertEquals(1, test.longestPalindromeSubseq("a"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(28, test.longestPalindromeSubseq("euazbipzncptldueeuechubrcourfpftcebikrxhybkymgvldvzpxwsnyqzzlrygqptugmgwdlodgeyspvpqhcmqjjxfroqsyshgpqt"));
    }
}
