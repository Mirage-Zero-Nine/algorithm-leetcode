package solution.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanArrange1497Test {

    private final CanArrange_1497 test = new CanArrange_1497();

    @Test
    public void testHappyCases() {
        assertTrue(test.canArrange(new int[]{1, 2, 3, 4, 5, 10, 6, 7, 8, 9}, 5));
        assertTrue(test.canArrange(new int[]{1, 2, 3, 4, 5, 6}, 7));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.canArrange(new int[]{1, 2, 3, 4, 5, 6}, 10));
        assertTrue(test.canArrange(new int[]{-1, 1}, 2));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.canArrange(new int[]{2, 4, 6, 8, 10, 12}, 2));
        assertTrue(test.canArrange(new int[]{1, 3, 5, 7, 9, 11}, 4));
    }
}
