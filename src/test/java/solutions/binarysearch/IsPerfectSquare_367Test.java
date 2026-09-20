package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

public class IsPerfectSquare_367Test {

    private final IsPerfectSquare_367 test = new IsPerfectSquare_367();

    @Test
    public void testHappyCases() {
        assertTrue(test.isPerfectSquare(16));
        assertTrue(test.isPerfectSquare(1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isPerfectSquare(14));
        assertTrue(test.isPerfectSquare(4));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isPerfectSquare(2147395600));
        assertFalse(test.isPerfectSquare(2147483647));
    }

    @Test
    public void testTwoIsNotPerfectSquare() {
        assertFalse(test.isPerfectSquare(2));
    }

    @Test
    public void testThreeIsNotPerfectSquare() {
        assertFalse(test.isPerfectSquare(3));
    }

    @Test
    public void testNineIsPerfectSquare() {
        assertTrue(test.isPerfectSquare(9));
    }

    @Test
    public void testFifteenIsNotPerfectSquare() {
        assertFalse(test.isPerfectSquare(15));
    }

    @Test
    public void testLargeNonSquareNearPerfectSquare() {
        assertFalse(test.isPerfectSquare(2147395601));
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("boundaryAndRepresentativeCases")
    public void testBoundaryAndRepresentativeCases(int number, boolean expected) {
        if (expected) {
            assertTrue(test.isPerfectSquare(number), "Expected a square: " + number);
        } else {
            assertFalse(test.isPerfectSquare(number), "Expected a non-square: " + number);
        }
    }

    private static Stream<Arguments> boundaryAndRepresentativeCases() {
        return Stream.of(
                Arguments.of(5, false),
                Arguments.of(6, false),
                Arguments.of(8, false),
                Arguments.of(10, false),
                Arguments.of(24, false),
                Arguments.of(26, false),
                Arguments.of(48, false),
                Arguments.of(50, false),
                Arguments.of(80, false),
                Arguments.of(99, false),
                Arguments.of(100, true),
                Arguments.of(101, false),
                Arguments.of(120, false),
                Arguments.of(121, true),
                Arguments.of(122, false),
                Arguments.of(9999, false),
                Arguments.of(10000, true),
                Arguments.of(10001, false),
                Arguments.of(999_999, false),
                Arguments.of(1_000_000, true),
                Arguments.of(1_000_001, false),
                Arguments.of(2_147_395_599, false),
                Arguments.of(2_147_395_600, true),
                Arguments.of(2_147_395_601, false),
                Arguments.of(Integer.MAX_VALUE, false));
    }

    @ParameterizedTest(name = "small number {0}")
    @ValueSource(ints = {7, 11, 12, 13, 17, 18, 19, 20, 21, 22, 23, 27, 28,
            29, 30, 31, 32, 33, 34, 35, 37, 38, 39, 40, 41, 42, 43, 44, 45,
            46, 47})
    public void testSmallNonSquares(int number) {
        assertFalse(test.isPerfectSquare(number));
    }

    @Test
    public void testSquaresAtPowersOfTwoAndTheirNeighbors() {
        for (int exponent = 0; exponent <= 15; exponent++) {
            int root = 1 << exponent;
            int square = root * root;
            assertTrue(test.isPerfectSquare(square), "root=" + root);
            if (square > 1) {
                assertFalse(test.isPerfectSquare(square - 1), "below root=" + root);
            }
            assertFalse(test.isPerfectSquare(square + 1), "above root=" + root);
        }
    }

    @Test
    public void testSquaresWithRootsAtSearchRangeTransitions() {
        int[] roots = {2, 3, 4, 5, 7, 8, 15, 16, 31, 32, 127, 128,
                255, 256, 511, 512, 999, 1000, 1001};
        for (int root : roots) {
            int square = root * root;
            assertTrue(test.isPerfectSquare(square), "root=" + root);
            assertFalse(test.isPerfectSquare(square - 1), "below root=" + root);
            assertFalse(test.isPerfectSquare(square + 1), "above root=" + root);
        }
    }

    @Test
    public void testSeededValuesAgainstIndependentOracle() {
        Random random = new Random(367L);
        IntStream.range(0, 2_000).forEach(index -> {
            int number = 1 + random.nextInt(Integer.MAX_VALUE);
            assertEqualsWithMessage(exactSquareOracle(number), test.isPerfectSquare(number), number);
        });
    }

    @Test
    public void testNearMaximumRootSquares() {
        int[] roots = {46_330, 46_331, 46_332, 46_333, 46_334, 46_335,
                46_336, 46_337, 46_338, 46_339, 46_340};
        for (int root : roots) {
            int square = root * root;
            assertTrue(test.isPerfectSquare(square), "root=" + root);
            assertFalse(test.isPerfectSquare(square - 1), "below root=" + root);
            if (square < Integer.MAX_VALUE) {
                assertFalse(test.isPerfectSquare(square + 1), "above root=" + root);
            }
        }
    }

    @Test
    public void testLargestIntAndSignedMultiplicationBoundaries() {
        assertFalse(test.isPerfectSquare(Integer.MAX_VALUE));
        assertTrue(test.isPerfectSquare(2_147_395_600));
        assertFalse(test.isPerfectSquare(2_147_395_599));
        assertFalse(test.isPerfectSquare(2_147_395_601));
        assertFalse(test.isPerfectSquare(1_073_741_823));
        assertTrue(test.isPerfectSquare(1_073_741_824));
        assertFalse(test.isPerfectSquare(1_073_741_825));
    }

    @Test
    public void testRepeatedCallsRemainIndependent() {
        assertTrue(test.isPerfectSquare(46_340 * 46_340));
        assertFalse(test.isPerfectSquare(46_340 * 46_340 - 1));
        assertTrue(test.isPerfectSquare(1));
        assertFalse(test.isPerfectSquare(2));
        assertTrue(test.isPerfectSquare(100));
        assertFalse(test.isPerfectSquare(101));
    }

    @Test
    public void testEveryRootThroughTheMaximumRepresentableRoot() {
        for (int root = 1; root <= 46_340; root++) {
            int square = root * root;
            assertTrue(test.isPerfectSquare(square), "root=" + root);
        }
    }

    @Test
    public void testExhaustivePracticalRange() {
        final int upperBound = 1_000_000;
        boolean[] perfectSquares = new boolean[upperBound + 1];
        for (int root = 1; root * root <= upperBound; root++) {
            perfectSquares[root * root] = true;
        }

        for (int num = 1; num <= upperBound; num++) {
            if (perfectSquares[num]) {
                assertTrue(test.isPerfectSquare(num), "Expected a perfect square: " + num);
            } else {
                assertFalse(test.isPerfectSquare(num), "Expected a non-square: " + num);
            }
        }
    }
    @Test
    public void testEveryLargeRepresentableSquareAndImmediateNeighbors() {
        for (int root = 1001; root <= 46340; root++) {
            int square = root * root;
            assertTrue(test.isPerfectSquare(square), "root=" + root);
            assertFalse(test.isPerfectSquare(square - 1), "below root=" + root);
            assertFalse(test.isPerfectSquare(square + 1), "above root=" + root);
        }
    }

    @Test
    public void testSignedMultiplicationBoundary() {
        assertFalse(test.isPerfectSquare(1073741823));
        assertTrue(test.isPerfectSquare(1073741824));
        assertFalse(test.isPerfectSquare(1073741825));
    }

    @Test
    public void testLargestTwoDigitRootsAndTheirMidpoints() {
        assertTrue(test.isPerfectSquare(9801));
        assertFalse(test.isPerfectSquare(9900));
        assertTrue(test.isPerfectSquare(10000));
    }

    private static boolean exactSquareOracle(int number) {
        long root = (long) Math.sqrt(number);
        while ((root + 1) * (root + 1) <= number) {
            root++;
        }
        while (root * root > number) {
            root--;
        }
        return root * root == number;
    }

    private static void assertEqualsWithMessage(boolean expected, boolean actual, int number) {
        if (expected) {
            assertTrue(actual, "Expected a perfect square: " + number);
        } else {
            assertFalse(actual, "Expected a non-square: " + number);
        }
    }
}
