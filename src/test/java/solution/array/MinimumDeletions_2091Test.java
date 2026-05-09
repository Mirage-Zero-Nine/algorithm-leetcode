package solution.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinimumDeletions_2091Test {
    private final MinimumDeletions_2091 solution = new MinimumDeletions_2091();

    @Test
    void testBasic() {
        assertEquals(5, solution.minimumDeletions(new int[]{2, 10, 7, 5, 4, 1, 8, 6}));
    }

    @Test
    void testMinMaxAtEnds() {
        assertEquals(3, solution.minimumDeletions(new int[]{0, -4, 19, 1, 8, -2, -3, 5}));
    }

    @Test
    void testTwoElements() {
        assertEquals(2, solution.minimumDeletions(new int[]{1, 2}));
    }

    @Test
    void testAllSame() {
        assertEquals(1, solution.minimumDeletions(new int[]{5, 5, 5, 5}));
    }

    @Test
    void testLargeArray() {
        assertEquals(2, solution.minimumDeletions(new int[]{-1, -2, -3, -4, -5}));
    }
}
