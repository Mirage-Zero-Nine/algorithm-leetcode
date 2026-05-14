package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class NumSmallerByFrequency_1170Test {

    private final NumSmallerByFrequency_1170 test = new NumSmallerByFrequency_1170();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{1}, test.numSmallerByFrequency(new String[]{"cbd"}, new String[]{"zaaaz"}));
        assertArrayEquals(new int[]{1, 2}, test.numSmallerByFrequency(new String[]{"bbb", "cc"}, new String[]{"a", "aa", "aaa", "aaaa"}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{0}, test.numSmallerByFrequency(new String[]{"z"}, new String[]{"a"}));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{3, 2, 1, 0},
            test.numSmallerByFrequency(new String[]{"a", "aa", "aaa", "aaaa"}, new String[]{"aa", "aaa", "aaaa"}));
    }

    @Test
    public void testAllQueriesHigherOrEqual() {
        assertArrayEquals(new int[]{0, 0},
            test.numSmallerByFrequency(new String[]{"aaaa", "zzzz"}, new String[]{"a", "bb"}));
    }

    @Test
    public void testAllQueriesLower() {
        assertArrayEquals(new int[]{3, 3},
            test.numSmallerByFrequency(new String[]{"a", "b"}, new String[]{"cc", "ddd", "eeee"}));
    }

    @Test
    public void testMixedScores() {
        assertArrayEquals(new int[]{2, 1, 1},
            test.numSmallerByFrequency(new String[]{"ab", "aab", "aaab"}, new String[]{"aaaa", "b", "cc"}));
    }

    @Test
    public void testSingleLetterWords() {
        assertArrayEquals(new int[]{0, 0, 0},
            test.numSmallerByFrequency(new String[]{"a", "m", "z"}, new String[]{"b", "c"}));
    }

    @Test
    public void testFrequencyFromSmallestCharacterOnly() {
        assertArrayEquals(new int[]{2},
            test.numSmallerByFrequency(new String[]{"dcce"}, new String[]{"aaaa", "bbb"}));
    }

    @Test
    public void testEmptyQueryArray() {
        assertArrayEquals(new int[]{},
            test.numSmallerByFrequency(new String[]{}, new String[]{"a", "aa"}));
    }

    @Test
    public void testGiantCase() {
        String[] queries = new String[500];
        String[] words = new String[500];
        for (int i = 0; i < 500; i++) {
            queries[i] = "a";
            words[i] = "aa";
        }
        int[] expected = new int[500];
        java.util.Arrays.fill(expected, 500);
        assertArrayEquals(expected, test.numSmallerByFrequency(queries, words));
    }
}
