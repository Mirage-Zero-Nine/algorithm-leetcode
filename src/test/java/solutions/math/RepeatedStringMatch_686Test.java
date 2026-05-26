package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RepeatedStringMatch_686Test {

    private final RepeatedStringMatch_686 test = new RepeatedStringMatch_686();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.repeatedStringMatch("abcd", "cdabcdab"));
        assertEquals(2, test.repeatedStringMatch("a", "aa"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.repeatedStringMatch("abc", "wxyz"));
        assertEquals(1, test.repeatedStringMatch("abc", "abc"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.repeatedStringMatch("ab", "ababab"));
    }

    @Test
    public void testBIsSubstringOfA() {
        assertEquals(1, test.repeatedStringMatch("abcdef", "cd"));
    }

    @Test
    public void testBSpansExactlyTwo() {
        assertEquals(2, test.repeatedStringMatch("abc", "cab"));
    }

    @Test
    public void testSingleCharRepeat() {
        assertEquals(4, test.repeatedStringMatch("a", "aaaa"));
    }

    @Test
    public void testNoMatchPossible() {
        assertEquals(-1, test.repeatedStringMatch("ab", "ba" + "c"));
    }

    @Test
    public void testBEqualsA() {
        assertEquals(1, test.repeatedStringMatch("xyz", "xyz"));
    }

    @Test
    public void testBLongerNeedsMultipleRepeats() {
        assertEquals(4, test.repeatedStringMatch("ab", "abababab"));
    }

    @Test
    public void testGiantCase() {
        String a = "abc";
        String b = "abc".repeat(3334); // length 10002, needs ceil(10002/3) = 3334 repeats
        assertEquals(3334, test.repeatedStringMatch(a, b));
    }
}
