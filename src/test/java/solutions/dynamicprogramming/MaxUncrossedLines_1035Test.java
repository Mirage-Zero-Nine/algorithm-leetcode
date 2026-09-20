package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MaxUncrossedLines_1035Test {

    private final MaxUncrossedLines_1035 test = new MaxUncrossedLines_1035();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.maxUncrossedLines(new int[]{1, 4, 2}, new int[]{1, 2, 4}));
        assertEquals(3, test.maxUncrossedLines(new int[]{2, 5, 1, 2, 5}, new int[]{10, 5, 2, 1, 5, 2}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxUncrossedLines(new int[]{1, 2, 3}, new int[]{4, 5, 6}));
        assertEquals(1, test.maxUncrossedLines(new int[]{1}, new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.maxUncrossedLines(new int[]{1, 3, 7, 1, 7, 5}, new int[]{1, 9, 2, 5, 1}));
    }

    @Test
    public void testIdenticalArrays() {
        assertEquals(5, test.maxUncrossedLines(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testReversedArrays() {
        assertEquals(1, test.maxUncrossedLines(new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testSingleElementNoMatch() {
        assertEquals(0, test.maxUncrossedLines(new int[]{1}, new int[]{2}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.maxUncrossedLines(new int[]{}, new int[]{1, 2, 3}));
    }

    @Test
    public void testAllSameElements() {
        assertEquals(3, test.maxUncrossedLines(new int[]{1, 1, 1}, new int[]{1, 1, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] a = new int[500];
        int[] b = new int[500];
        for (int i = 0; i < 500; i++) { a[i] = i; b[i] = i; }
        assertEquals(500, test.maxUncrossedLines(a, b));
    }

    @Test
    public void testPartialOverlap() {
        assertEquals(3, test.maxUncrossedLines(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 6, 7}));
    }

    @ParameterizedTest(name = "LCS case {0}")
    @CsvSource({
            "'1;2;3', '3;2;1', 1", "'1;2;1', '1;1', 2",
            "'1;3;5;7', '2;4;6;8', 0", "'1;2;3;2', '2;1;2;3', 3",
            "'4;4;4', '4;4', 2", "'0;-1;2', '-1;0;2', 2",
            "'1;2', '1;2;3;4', 2", "'9', '8;9;10', 1",
            "'2;2;1', '2;1;2', 2", "'5;6;7', '7;6;5', 1"
    })
    public void testAdditionalOrderingAndDuplicateCases(String left, String right, int expected) {
        assertEquals(expected, test.maxUncrossedLines(parse(left), parse(right)));
    }

    private static int[] parse(String encoded) {
        String[] values = encoded.split(";");
        int[] result = new int[values.length];
        for (int i = 0; i < values.length; i++) result[i] = Integer.parseInt(values[i]);
        return result;
    }
}
