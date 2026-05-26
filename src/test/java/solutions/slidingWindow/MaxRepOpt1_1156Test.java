package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;

public class MaxRepOpt1_1156Test {

    private final MaxRepOpt1_1156 solution = new MaxRepOpt1_1156();

    @Test
    public void testHappy_example1() {
        assertEquals(3, solution.maxRepOpt1_1156("ababa"));
    }

    @Test
    public void testHappy_example2() {
        assertEquals(6, solution.maxRepOpt1_1156("aaabaaa"));
    }

    @Test
    public void testHappy_example3() {
        assertEquals(4, solution.maxRepOpt1_1156("aaabbaaa"));
    }

    @Test
    public void testHappy_example4() {
        assertEquals(5, solution.maxRepOpt1_1156("aaaaa"));
    }

    @Test
    public void testHappy_swapToExtend() {
        assertEquals(5, solution.maxRepOpt1_1156("aabaaab"));
    }

    @Test
    public void testNegative_allDifferent() {
        assertEquals(1, solution.maxRepOpt1_1156("abcde"));
    }

    @Test
    public void testEdge_emptyString() {
        assertEquals(0, solution.maxRepOpt1_1156(""));
    }

    @Test
    public void testEdge_singleChar() {
        assertEquals(1, solution.maxRepOpt1_1156("a"));
    }

    @Test
    public void testEdge_twoSameChars() {
        assertEquals(2, solution.maxRepOpt1_1156("aa"));
    }

    @Test
    public void testEdge_twoDifferentChars() {
        assertEquals(1, solution.maxRepOpt1_1156("ab"));
    }

    @Test
    public void testGiant() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20000; i++) {
            sb.append('a');
        }
        sb.setCharAt(10000, 'b');
        // 19999 a's total, swap b->a limited by count of a's = 19999
        assertEquals(19999, solution.maxRepOpt1_1156(sb.toString()));
    }

    @Test
    public void testAllSame_noSwapNeeded() {
        assertEquals(7, solution.maxRepOpt1_1156("bbbbbbb"));
    }

    @Test
    public void testAlternating_ababab() {
        // swap one b->a: e.g. "aaabab", best window = 3
        assertEquals(3, solution.maxRepOpt1_1156("ababab"));
    }

    @Test
    public void testSwapBridgesTwoSegments() {
        // "aabaa" -> 4 a's total, window starting at 0: aa+skip b+aa = 4 (limited by one gap)
        assertEquals(4, solution.maxRepOpt1_1156("aabaa"));
    }

    @Test
    public void testOnlyTwoDistinctChars_longRun() {
        // "aaabbaaa" -> best for 'a' is 4 (3+swap one b, limited by gap of 2)
        assertEquals(4, solution.maxRepOpt1_1156("aaabbaaa"));
    }

    @Test
    public void testThreeChars_mixedPattern() {
        // "abcaaa" -> 'a' count=4, window from idx 3: aaa, can't extend past end = 3; from idx 0: a+skip b = 2. Best = 3
        assertEquals(3, solution.maxRepOpt1_1156("abcaaa"));
    }

    @Test
    public void testLongString_seed42() {
        Random rng = new Random(42L);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append((char) ('a' + rng.nextInt(3))); // only a, b, c
        }
        String s = sb.toString();
        int result = solution.maxRepOpt1_1156(s);
        assertTrue(result > 0 && result <= s.length(),
                "Result " + result + " should be in [1, " + s.length() + "]");
    }

    @Test
    public void testProperty_resultNeverExceedsLength() {
        String[] inputs = {"", "a", "ab", "aab", "aaaa", "abcabc", "zzzzz"};
        for (String input : inputs) {
            int result = solution.maxRepOpt1_1156(input);
            assertTrue(result <= input.length(),
                    "Result " + result + " exceeds length " + input.length() + " for input '" + input + "'");
        }
    }

    @Test
    public void testSingleDifferentCharAtEnd() {
        // "aaaab" -> 4 a's + swap b->a? count of a = 4, so window = 4 (can't extend beyond count)
        assertEquals(4, solution.maxRepOpt1_1156("aaaab"));
    }

    @Test
    public void testSingleDifferentCharAtStart() {
        // "baaaa" -> start from idx1, window of 4 a's, count=4, so 4
        assertEquals(4, solution.maxRepOpt1_1156("baaaa"));
    }
}
