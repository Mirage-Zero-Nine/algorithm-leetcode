package solution.others;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanTransform777Test {

    private final CanTransform_777 test = new CanTransform_777();

    @Test
    public void testHappyCases() {
        assertTrue(test.canTransform("RXXLRXRXL", "XRLXXRRLX"));
        assertFalse(test.canTransform("X", "L"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.canTransform("LLR", "RRL"));
        assertTrue(test.canTransform("X", "X"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.canTransform("RXXLRXRXL", "XRLXXRRLX"));
    }
}
