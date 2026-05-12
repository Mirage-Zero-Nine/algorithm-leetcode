package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaximumProduct_628Test {
    private final MaximumProduct_628 solution = new MaximumProduct_628();

    @Test
    void testBasic() {
        assertEquals(6, solution.maximumProduct(new int[]{1, 2, 3}));
    }

    @Test
    void testWithNegatives() {
        assertEquals(24, solution.maximumProduct(new int[]{1, 2, 3, 4}));
    }

    @Test
    void testTwoNegatives() {
        assertEquals(300, solution.maximumProduct(new int[]{-10, -10, 1, 3, 2}));
    }

    @Test
    void testAllNegative() {
        assertEquals(-6, solution.maximumProduct(new int[]{-1, -2, -3}));
    }

    @Test
    void testMixed() {
        assertEquals(150, solution.maximumProduct(new int[]{-10, -5, 1, 2, 3}));
    }
}
