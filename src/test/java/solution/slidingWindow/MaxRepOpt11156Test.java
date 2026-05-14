package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxRepOpt11156Test {

    private final MaxRepOpt1_1156 test = new MaxRepOpt1_1156();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.maxRepOpt1_1156("aaabaaa"));
        assertEquals(4, test.maxRepOpt1_1156("aaabbaaa"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.maxRepOpt1_1156("a"));
        assertEquals(1, test.maxRepOpt1_1156("ab"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.maxRepOpt1_1156("aababaab"));
    }

    @Test
    public void testAllSameChar() {
        assertEquals(5, test.maxRepOpt1_1156("aaaaa"));
        assertEquals(3, test.maxRepOpt1_1156("bbb"));
    }

    @Test
    public void testTwoDistinctChars() {
        assertEquals(3, test.maxRepOpt1_1156("ababa"));
        assertEquals(2, test.maxRepOpt1_1156("abab"));
    }

    @Test
    public void testSwapFromEnd() {
        assertEquals(5, test.maxRepOpt1_1156("aaabaa"));
    }

    @Test
    public void testEmptyString() {
        assertEquals(0, test.maxRepOpt1_1156(""));
    }

    @Test
    public void testSingleSwapNeeded() {
        assertEquals(6, test.maxRepOpt1_1156("aabaaaa"));
    }

    @Test
    public void testMultipleDistinctChars() {
        assertEquals(3, test.maxRepOpt1_1156("abcaaa"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5000; i++) {
            sb.append('a');
        }
        sb.setCharAt(2500, 'b');
        assertEquals(4999, test.maxRepOpt1_1156(sb.toString()));
    }
}
