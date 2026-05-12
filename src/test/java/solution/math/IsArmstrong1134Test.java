package solution.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsArmstrong1134Test {

    private final IsArmstrong_1134 test = new IsArmstrong_1134();

    @Test
    public void testHappyCases() {
        assertTrue(test.isArmstrong(153));
        assertTrue(test.isArmstrong(9));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isArmstrong(10));
        assertFalse(test.isArmstrong(100));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isArmstrong(1634));
        assertFalse(test.isArmstrong(1000));
    }
}
