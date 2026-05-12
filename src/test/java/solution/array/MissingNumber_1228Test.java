package solution.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MissingNumber_1228Test {
    private final MissingNumber_1228 solution = new MissingNumber_1228();

    @Test
    void testBasic() {
        assertEquals(9, solution.missingNumber(new int[]{5, 7, 11, 13}));
    }

    @Test
    void testNegative() {
        assertEquals(-3, solution.missingNumber(new int[]{-3, -5, -7}));
    }

    @Test
    void testTwoElements() {
        assertEquals(1, solution.missingNumber(new int[]{1, 5}));
    }

    @Test
    void testConsecutive() {
        assertEquals(1, solution.missingNumber(new int[]{1, 2, 3, 4}));
    }

    @Test
    void testLargeGap() {
        assertEquals(0, solution.missingNumber(new int[]{0, 20}));
    }
}
