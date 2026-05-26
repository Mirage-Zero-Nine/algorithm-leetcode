package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestStrChain_1048Test {

    private final LongestStrChain_1048 test = new LongestStrChain_1048();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.longestStrChain(new String[]{"a", "b", "ba", "bca", "bda", "bdca"}));
        assertEquals(5, test.longestStrChain(new String[]{"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.longestStrChain(new String[]{"a"}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.longestStrChain(new String[]{"bdca", "bda", "ba", "a", "b"}));
    }

    @Test
    public void testHappySimpleChain() {
        assertEquals(3, test.longestStrChain(new String[]{"a", "ab", "abc"}));
    }

    @Test
    public void testHappyDisjointWords() {
        // No word is predecessor of another
        assertEquals(1, test.longestStrChain(new String[]{"abc", "def", "ghi"}));
    }

    @Test
    public void testNegativeNoChainPossible() {
        // All same length, no chain possible
        assertEquals(1, test.longestStrChain(new String[]{"ab", "cd", "ef", "gh"}));
    }

    @Test
    public void testEdgeTwoWordsChain() {
        assertEquals(2, test.longestStrChain(new String[]{"a", "ab"}));
    }

    @Test
    public void testEdgeTwoWordsNoChain() {
        assertEquals(1, test.longestStrChain(new String[]{"a", "bc"}));
    }

    @Test
    public void testHappyLongerChain() {
        assertEquals(4, test.longestStrChain(new String[]{"a", "ba", "bca", "bdca", "xyz"}));
    }

    @Test
    public void testEdgeMultiplePredecessors() {
        // "a" -> "ab" or "ba", "ab" -> "abc"
        assertEquals(3, test.longestStrChain(new String[]{"a", "ab", "ba", "abc", "bac"}));
    }

    @Test
    public void testGiantCase() {
        // Build a chain of length 16: "a", "aa", "aaa", ..., "aaaa...a" (16 a's)
        String[] words = new String[16];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append('a');
            words[i] = sb.toString();
        }
        assertEquals(16, test.longestStrChain(words));
    }
}
