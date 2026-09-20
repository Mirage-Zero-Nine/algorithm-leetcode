package solutions.twopointers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LongestMountain_845Test {
    private final LongestMountain_845 solution = new LongestMountain_845();

    @Test
    void testBasic() {
        assertEquals(5, solution.longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}));
    }

    @Test
    void testNoMountain() {
        assertEquals(0, solution.longestMountain(new int[]{2, 2, 2}));
    }

    @Test
    void testOnlyIncreasing() {
        assertEquals(0, solution.longestMountain(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testOnlyDecreasing() {
        assertEquals(0, solution.longestMountain(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    void testMultipleMountains() {
        assertEquals(5, solution.longestMountain(new int[]{0, 1, 2, 3, 2, 2, 0, 1, 2, 1}));
    }

    @Test
    void testMinimalMountain() {
        assertEquals(3, solution.longestMountain(new int[]{1, 2, 1}));
    }

    @Test
    void testTwoElements() {
        assertEquals(0, solution.longestMountain(new int[]{1, 2}));
    }

    @Test
    void testEmptyArray() {
        assertEquals(0, solution.longestMountain(new int[]{}));
    }

    @Test
    void testSingleElement() {
        assertEquals(0, solution.longestMountain(new int[]{5}));
    }

    @Test
    void testPlateau() {
        assertEquals(0, solution.longestMountain(new int[]{1, 2, 2, 1}));
    }

    @Test
    void testEntireArrayMountain() {
        assertEquals(7, solution.longestMountain(new int[]{1, 2, 3, 4, 3, 2, 1}));
    }

    @Test
    void testGiantArray() {
        int[] arr = new int[10001];
        for (int i = 0; i <= 5000; i++) arr[i] = i;
        for (int i = 5001; i <= 10000; i++) arr[i] = 10000 - i;
        assertEquals(10001, solution.longestMountain(arr));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("additionalMountains")
    void testAdditionalContractCases(String name, int[] input, int expected) {
        assertEquals(expected, solution.longestMountain(input), name);
    }

    private static Stream<Arguments> additionalMountains() {
        int[] maximumLegalMountain = new int[1000];
        for (int i = 0; i <= 499; i++) {
            maximumLegalMountain[i] = i;
        }
        maximumLegalMountain[500] = 1000;
        for (int i = 501; i < maximumLegalMountain.length; i++) {
            maximumLegalMountain[i] = 1500 - i;
        }

        return Stream.of(
                Arguments.of("mountain starts after descent", new int[]{9, 8, 7, 8, 9, 10, 9, 8}, 6),
                Arguments.of("later mountain is longer", new int[]{1, 3, 2, 4, 3, 2}, 4),
                Arguments.of("equal length neighboring mountains", new int[]{1, 2, 3, 2, 3, 2, 1}, 4),
                Arguments.of("plateau at the peak", new int[]{1, 2, 2, 1, 0}, 0),
                Arguments.of("valley without an ascent and descent", new int[]{3, 2, 1, 2, 3}, 0),
                Arguments.of("negative values", new int[]{-5, -3, -4}, 3),
                Arguments.of("integer extremes", new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE, 0, -1}, 5),
                Arguments.of("mountain after a short mountain", new int[]{1, 2, 1, 2, 1, 0}, 4),
                Arguments.of("separated minimal mountains", new int[]{1, 0, 1, 0, 1}, 3),
                Arguments.of("maximum legal input length", maximumLegalMountain, 1000),
                Arguments.of("peak at the first possible index", new int[]{0, 2, 1, 0, -1}, 5),
                Arguments.of("peak at the last possible index", new int[]{4, 3, 2, 1, 3}, 0)
        );
    }

    @Test
    void testDoesNotMutateInputAndSupportsReuse() {
        int[] input = {2, 5, 4, 3, 1};
        int[] original = input.clone();

        assertEquals(5, solution.longestMountain(input));
        assertEquals(5, solution.longestMountain(input));
        org.junit.jupiter.api.Assertions.assertArrayEquals(original, input);
    }
}
