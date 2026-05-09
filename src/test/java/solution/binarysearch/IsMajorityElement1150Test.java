package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsMajorityElement1150Test {

    private final IsMajorityElement_1150 test = new IsMajorityElement_1150();

    @Test
    public void testHappyCases() {
        assertTrue(test.isMajorityElement(new int[]{2, 4, 5, 5, 5, 5, 5, 6, 6}, 5));
        assertFalse(test.isMajorityElement(new int[]{10, 100, 101, 101}, 101));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.isMajorityElement(new int[]{1}, 1));
        assertFalse(test.isMajorityElement(new int[]{1, 2, 3}, 2));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isMajorityElement(new int[]{1, 1, 1, 1, 1, 2, 3, 4, 5}, 1));
    }
}
