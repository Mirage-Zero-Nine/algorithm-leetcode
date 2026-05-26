package solutions.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestDecomposition_1147Test {
    private final LongestDecomposition_1147 solver = new LongestDecomposition_1147();

    @Test public void testBasic() {
        // "ghiabcdefhelloadamhelloabcdefghi"
        // (ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi) => 7
        assertEquals(7, solver.longestDecomposition("ghiabcdefhelloadamhelloabcdefghi"));
    }

    @Test public void testMerryChristmas() {
        // "aaaa" decomposes into (a)(a)(a)(a) => 4
        assertEquals(4, solver.longestDecomposition("aaaa"));
    }

    @Test public void testSingle() {
        assertEquals(1, solver.longestDecomposition("a"));
    }

    @Test public void testRepeat() {
        // "aaa" decomposes into (a)(a)(a) => 3
        assertEquals(3, solver.longestDecomposition("volvo"));
    }

    @Test public void testComplexOdd() {
        assertEquals(11, solver.longestDecomposition("antaprezatepzapreanta"));
    }

    @Test public void testTwoChars() {
        assertEquals(2, solver.longestDecomposition("aa"));
    }

    @Test public void testTwoDifferent() {
        assertEquals(1, solver.longestDecomposition("ab"));
    }

    @Test public void testPalindrome() {
        // "abcba" -> (a)(b)(c)(b)(a) => 5
        assertEquals(5, solver.longestDecomposition("abcba"));
    }

    @Test public void testNoDecomposition() {
        // "abcdef" -> no matching prefix/suffix pairs, just 1 chunk
        assertEquals(1, solver.longestDecomposition("abcdef"));
    }

    @Test public void testAllSameChars() {
        // "aaaaa" -> (a)(a)(a)(a)(a) => 5
        assertEquals(5, solver.longestDecomposition("aaaaa"));
    }

    @Test public void testGiant() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) sb.append("ab");
        int result = solver.longestDecomposition(sb.toString());
        assertEquals(500, result);
    }
}
