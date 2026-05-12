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
}
