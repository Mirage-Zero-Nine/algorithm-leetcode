package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class NumSmallerByFrequency1170Test {

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
}
