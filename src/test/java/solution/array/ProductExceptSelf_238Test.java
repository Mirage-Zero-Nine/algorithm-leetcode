package solution.array;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductExceptSelf_238Test {
    private final ProductExceptSelf_238 solution = new ProductExceptSelf_238();

    @Test
    void testBasic() {
        assertArrayEquals(new int[]{24, 12, 8, 6}, solution.productExceptSelf(new int[]{1, 2, 3, 4}));
    }

    @Test
    void testWithZero() {
        assertArrayEquals(new int[]{0, 0, 9, 0, 0}, solution.productExceptSelf(new int[]{-1, 1, 0, -3, 3}));
    }

    @Test
    void testTwoElements() {
        assertArrayEquals(new int[]{2, 1}, solution.productExceptSelf(new int[]{1, 2}));
    }

    @Test
    void testAllOnes() {
        assertArrayEquals(new int[]{1, 1, 1}, solution.productExceptSelf(new int[]{1, 1, 1}));
    }

    @Test
    void testNegatives() {
        assertArrayEquals(new int[]{-6, -1}, solution.productExceptSelf(new int[]{-1, -6}));
    }

    @Test
    void testWithTwoZeros() {
        assertArrayEquals(new int[]{0, 0, 0, 0}, solution.productExceptSelf(new int[]{0, 0, 1, 2}));
    }

    @Test
    void testSingleZero() {
        assertArrayEquals(new int[]{0, 0, 0, 30, 0}, solution.productExceptSelf(new int[]{1, 2, 3, 0, 5}));
    }

    @Test
    void testAllNegatives() {
        assertArrayEquals(new int[]{6, 3, 2}, solution.productExceptSelf(new int[]{-1, -2, -3}));
    }

    @Test
    void testLargeValues() {
        assertArrayEquals(new int[]{200, 100}, solution.productExceptSelf(new int[]{100, 200}));
    }

    @Test
    void testGiantArray() {
        int n = 1000;
        int[] nums = new int[n];
        java.util.Arrays.fill(nums, 1);
        nums[0] = 2;
        int[] expected = new int[n];
        java.util.Arrays.fill(expected, 2);
        expected[0] = 1;
        assertArrayEquals(expected, solution.productExceptSelf(nums));
    }

    // --- NEW TESTS ---

    @Test
    void testSingleElement() {
        // Single element: product of "all others" is 1 (empty product)
        assertArrayEquals(new int[]{1}, solution.productExceptSelf(new int[]{42}));
    }

    @Test
    void testAllNegativesEvenCount() {
        // Even number of negatives: mixed signs
        assertArrayEquals(new int[]{-24, 12, -8, 6}, solution.productExceptSelf(new int[]{-1, 2, -3, 4}));
    }

    @Test
    void testMixedPositiveNegative() {
        // [-2, 3, -4, 5] -> products: 3*-4*5=-60, -2*-4*5=40, -2*3*5=-30, -2*3*-4=24
        assertArrayEquals(new int[]{-60, 40, -30, 24}, solution.productExceptSelf(new int[]{-2, 3, -4, 5}));
    }

    @Test
    void testThreeZeros() {
        assertArrayEquals(new int[]{0, 0, 0, 0, 0}, solution.productExceptSelf(new int[]{0, 0, 0, 1, 2}));
    }

    @Test
    void testLargeProductOverflow() {
        // 100000^3 overflows int; implementation uses int so overflow is expected behavior
        int[] nums = {100000, 100000, 100000, 100000};
        int[] result = solution.productExceptSelf(nums);
        // Verify consistency: each element should equal 100000^3 (with overflow)
        int cube = 100000 * 100000 * 100000; // overflows, but deterministic
        assertArrayEquals(new int[]{cube, cube, cube, cube}, result);
    }

    @Test
    void testSizeTwo_NegativePositive() {
        // [a, b] -> [b, a]
        assertArrayEquals(new int[]{7, -3}, solution.productExceptSelf(new int[]{-3, 7}));
    }

    @Test
    void testAllSameNonOne() {
        // [5, 5, 5, 5] -> each gets 5^3 = 125
        assertArrayEquals(new int[]{125, 125, 125, 125}, solution.productExceptSelf(new int[]{5, 5, 5, 5}));
    }

    @Test
    void testLargeArrayAllOnes() {
        int n = 2000;
        int[] nums = new int[n];
        java.util.Arrays.fill(nums, 1);
        int[] expected = new int[n];
        java.util.Arrays.fill(expected, 1);
        assertArrayEquals(expected, solution.productExceptSelf(nums));
    }

    @Test
    void testRandomPropertyBruteForce() {
        // Property test: compare with brute-force O(n^2) for random arrays
        Random rng = new Random(42L);
        for (int trial = 0; trial < 20; trial++) {
            int n = rng.nextInt(10) + 2;
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = rng.nextInt(21) - 10; // range [-10, 10]
            }
            int[] expected = new int[n];
            for (int i = 0; i < n; i++) {
                int prod = 1;
                for (int j = 0; j < n; j++) {
                    if (j != i) prod *= nums[j];
                }
                expected[i] = prod;
            }
            assertArrayEquals(expected, solution.productExceptSelf(nums),
                    "Failed for input: " + java.util.Arrays.toString(nums));
        }
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> edgeCaseProvider() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(new int[]{0, 1}, new int[]{1, 0}),
                org.junit.jupiter.params.provider.Arguments.of(new int[]{1, 0}, new int[]{0, 1}),
                org.junit.jupiter.params.provider.Arguments.of(new int[]{-1, -1, -1, -1}, new int[]{-1, -1, -1, -1}),
                org.junit.jupiter.params.provider.Arguments.of(new int[]{2, 3, 0, 5}, new int[]{0, 0, 30, 0})
        );
    }

    @ParameterizedTest
    @MethodSource("edgeCaseProvider")
    void testEdgeCasesParameterized(int[] input, int[] expected) {
        assertArrayEquals(expected, solution.productExceptSelf(input));
    }
}
