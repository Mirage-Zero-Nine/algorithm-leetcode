package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IncreasingTriplet334Test {

    private final IncreasingTriplet_334 test = new IncreasingTriplet_334();

    @Test
    public void testHappyCases() {
        assertTrue(test.increasingTriplet(new int[]{1, 2, 3, 4, 5}));
        assertTrue(test.increasingTriplet(new int[]{2, 1, 5, 0, 4, 6}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.increasingTriplet(new int[]{5, 4, 3, 2, 1}));
        assertFalse(test.increasingTriplet(new int[]{1, 2}));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.increasingTriplet(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3}));
    }
}
