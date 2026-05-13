package solution.array;

import org.junit.jupiter.api.Test;
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
}
