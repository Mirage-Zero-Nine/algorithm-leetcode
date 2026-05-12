package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaximumGap_164Test {
    private final MaximumGap_164 solution = new MaximumGap_164();

    @Test
    void testBasic() {
        assertEquals(3, solution.maximumGap(new int[]{3, 6, 9, 1}));
    }

    @Test
    void testNoGap() {
        assertEquals(0, solution.maximumGap(new int[]{10}));
    }

    @Test
    void testTwoElements() {
        assertEquals(5, solution.maximumGap(new int[]{1, 6}));
    }

    @Test
    void testLargeGap() {
        assertEquals(5, solution.maximumGap(new int[]{1, 10, 5}));
    }

    @Test
    void testConsecutive() {
        assertEquals(1, solution.maximumGap(new int[]{1, 2, 3, 4, 5}));
    }
}
