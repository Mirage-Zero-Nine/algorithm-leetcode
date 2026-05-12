package solution.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanDivideIntoSubsequences1121Test {

    private final CanDivideIntoSubsequences_1121 test = new CanDivideIntoSubsequences_1121();

    @Test
    public void testHappyCases() {
        assertTrue(test.canDivideIntoSubsequences(new int[]{1, 2, 2, 3, 3, 4, 4}, 3));
        assertFalse(test.canDivideIntoSubsequences(new int[]{5, 6, 6, 7, 8}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.canDivideIntoSubsequences(new int[]{1, 2, 3}, 3));
        assertFalse(test.canDivideIntoSubsequences(new int[]{1, 1, 1}, 2));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.canDivideIntoSubsequences(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 2));
    }
}
