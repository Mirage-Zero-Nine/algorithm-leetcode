package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Sqrt_69Test {

    private final Sqrt_69 test = new Sqrt_69();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.sqrt(4));
        assertEquals(2, test.sqrt(8));
        assertEquals(3, test.sqrt(9));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.sqrt(0));
        assertEquals(1, test.sqrt(1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(100, test.sqrt(10000));
        assertEquals(46340, test.sqrt(2147395600));
    }

    @Test
    public void testPerfectSquares() {
        assertEquals(5, test.sqrt(25));
        assertEquals(10, test.sqrt(100));
        assertEquals(12, test.sqrt(144));
    }

    @Test
    public void testNonPerfectSquares() {
        assertEquals(3, test.sqrt(10));
        assertEquals(4, test.sqrt(20));
        assertEquals(7, test.sqrt(50));
    }

    @Test
    public void testSmallValues() {
        assertEquals(1, test.sqrt(2));
        assertEquals(1, test.sqrt(3));
    }

    @Test
    public void testIntMaxValue() {
        assertEquals(46340, test.sqrt(Integer.MAX_VALUE));
    }

    @Test
    public void testBinarySearchHappyCases() {
        assertEquals(2, test.binarySearch(4));
        assertEquals(3, test.binarySearch(9));
        assertEquals(2, test.binarySearch(8));
    }

    @Test
    public void testBinarySearchEdgeCases() {
        assertEquals(0, test.binarySearch(0));
        assertEquals(1, test.binarySearch(1));
    }

    @Test
    public void testBinarySearchLarge() {
        assertEquals(46340, test.binarySearch(Integer.MAX_VALUE));
        assertEquals(100, test.binarySearch(10000));
    }

    @Test
    public void testGiantConsistencyCheck() {
        int[] values = {999, 1024, 2048, 4096, 8192, 65536, 100000, 1000000};
        for (int v : values) {
            assertEquals(test.binarySearch(v), test.sqrt(v));
        }
    }

    /**
     * Iterable sweep: every input in [0, 100] must produce floor(sqrt(x))
     * for both implementations. Compared against {@link Math#sqrt(double)}
     * truncated to int as the trusted reference.
     */
    @ParameterizedTest(name = "sqrt({0}) = {1}")
    @MethodSource("zeroToHundred")
    public void testEveryValueFromZeroToOneHundred(int input, int expected) {
        assertEquals(expected, test.sqrt(input));
        assertEquals(expected, test.binarySearch(input));
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> zeroToHundred() {
        return IntStream.rangeClosed(0, 100)
                .mapToObj(i -> arguments(i, (int) Math.floor(Math.sqrt(i))));
    }

    /**
     * Boundary cases around perfect squares: n^2-1, n^2, n^2+1 should all
     * collapse to the correct floor. Catches off-by-one errors.
     */
    @ParameterizedTest(name = "around {0}^2")
    @CsvSource({
            "2", "3", "4", "5", "10", "31", "100", "316",
            "1000", "3162", "10000", "31622", "46340"
    })
    public void testPerfectSquareBoundaries(int n) {
        long sq = (long) n * n;
        if (sq - 1 >= 0 && sq - 1 <= Integer.MAX_VALUE) {
            assertEquals(n - 1, test.sqrt((int) (sq - 1)));
            assertEquals(n - 1, test.binarySearch((int) (sq - 1)));
        }
        if (sq <= Integer.MAX_VALUE) {
            assertEquals(n, test.sqrt((int) sq));
            assertEquals(n, test.binarySearch((int) sq));
        }
        if (sq + 1 <= Integer.MAX_VALUE) {
            assertEquals(n, test.sqrt((int) (sq + 1)));
            assertEquals(n, test.binarySearch((int) (sq + 1)));
        }
    }

    /**
     * Spot-check selected large powers-of-ten and near-overflow values to
     * guard against arithmetic-overflow regressions.
     */
    @ParameterizedTest(name = "sqrt({0}) = {1}")
    @CsvSource({
            "999, 31",
            "1000, 31",
            "1024, 32",
            "9999, 99",
            "10001, 100",
            "999999, 999",
            "1000000, 1000",
            "999999999, 31622",
            "2147395599, 46339",
            "2147395600, 46340",
            "2147395601, 46340",
            "2147483646, 46340",
            "2147483647, 46340"
    })
    public void testLargeValuesSpotCheck(int input, int expected) {
        assertEquals(expected, test.sqrt(input));
        assertEquals(expected, test.binarySearch(input));
    }
}
