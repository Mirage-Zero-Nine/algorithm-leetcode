package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;

public class TwoSumLessThanK_1099Test {

    private final TwoSumLessThanK_1099 test = new TwoSumLessThanK_1099();

    @Test
    public void testHappyCases() {
        assertEquals(58, test.twoSumLessThanK(new int[]{34, 23, 1, 24, 75, 33, 54, 8}, 60));
        assertEquals(-1, test.twoSumLessThanK(new int[]{10, 20, 30}, 15));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.twoSumLessThanK(new int[]{10, 20, 30}, 5));
        assertEquals(2, test.twoSumLessThanK(new int[]{1, 1}, 3));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.twoSumLessThanK(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 10));
    }

    @Test
    public void testExactK() {
        // Sum equals K should not be included (must be strictly less)
        assertEquals(-1, test.twoSumLessThanK(new int[]{5, 5}, 10));
    }

    @Test
    public void testSumJustBelowK() {
        assertEquals(9, test.twoSumLessThanK(new int[]{4, 5}, 10));
    }

    @Test
    public void testAllSameElements() {
        assertEquals(4, test.twoSumLessThanK(new int[]{2, 2, 2, 2}, 5));
    }

    @Test
    public void testTwoElements() {
        assertEquals(3, test.twoSumLessThanK(new int[]{1, 2}, 5));
        assertEquals(-1, test.twoSumLessThanK(new int[]{3, 4}, 5));
    }

    @Test
    public void testLargeK() {
        assertEquals(199, test.twoSumLessThanK(new int[]{100, 99, 1, 2}, 200));
    }

    @Test
    public void testNoValidPair() {
        assertEquals(-1, test.twoSumLessThanK(new int[]{50, 60, 70}, 100));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = i + 1;
        }
        // Max pair sum < 2000: 999 + 1000 = 1999
        assertEquals(1999, test.twoSumLessThanK(arr, 2000));
    }

    @Test
    public void testRepeatedCallsUseFreshInputs() {
        assertEquals(8, test.twoSumLessThanK(new int[]{8, 1, 7}, 9));
        assertEquals(-3, test.twoSumLessThanK(new int[]{-10, 4, 7}, 0));
        assertEquals(199, test.twoSumLessThanK(new int[]{100, 99, 1}, 200));
    }

    @ParameterizedTest(name = "case {index}")
    @MethodSource("additionalCases")
    public void testAdditionalContractCases(int[] values, int k) {
        // Enumerating every pair is independent of the sorted two-pointer implementation.
        assertEquals(bruteForceExpected(values, k), test.twoSumLessThanK(values, k));
    }

    private static Stream<Arguments> additionalCases() {
        return Stream.of(
                Arguments.of(new int[]{}, 10),
                Arguments.of(new int[]{7}, 8),
                Arguments.of(new int[]{7, 7}, 14),
                Arguments.of(new int[]{7, 7}, 15),
                Arguments.of(new int[]{-8, -3, -5}, -7),
                Arguments.of(new int[]{-8, -3, -5}, -13),
                Arguments.of(new int[]{-8, 0, 4, 9}, 1),
                Arguments.of(new int[]{-8, 0, 4, 9}, 10),
                Arguments.of(new int[]{-10, -10, -10, -1}, -15),
                Arguments.of(new int[]{-10, -10, -10, -1}, -20),
                Arguments.of(new int[]{0, 0, 0, 1}, 1),
                Arguments.of(new int[]{0, 0, 0, 1}, 2),
                Arguments.of(new int[]{1, 2, 3, 4, 100}, 6),
                Arguments.of(new int[]{1, 2, 3, 4, 100}, 7),
                Arguments.of(new int[]{Integer.MIN_VALUE, 0, 1}, -1),
                Arguments.of(new int[]{Integer.MAX_VALUE, -1, -2}, Integer.MAX_VALUE),
                Arguments.of(new int[]{-1000, -1, 1000, 1001}, 1001),
                Arguments.of(new int[]{2, 2, 3, 3, 4, 4}, 7),
                Arguments.of(new int[]{2, 2, 3, 3, 4, 4}, 8),
                Arguments.of(new int[]{5, 1, 9, 2, 8, 3, 7, 4, 6}, 12),
                Arguments.of(new int[]{5, 1, 9, 2, 8, 3, 7, 4, 6}, 13),
                Arguments.of(new int[]{-5, 2, 2, 2, 10, 10}, 5),
                Arguments.of(new int[]{-5, 2, 2, 2, 10, 10}, 4),
                Arguments.of(new int[]{-20, -1, 0, 1, 20}, 0),
                Arguments.of(new int[]{-20, -1, 0, 1, 20}, 21));
    }

    private static int bruteForceExpected(int[] values, int k) {
        int best = 0;
        boolean found = false;
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                int sum = values[i] + values[j];
                if (sum < k && (!found || sum > best)) {
                    best = sum;
                    found = true;
                }
            }
        }
        return found ? best : -1;
    }
}
