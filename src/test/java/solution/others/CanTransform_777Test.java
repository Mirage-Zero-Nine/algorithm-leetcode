package solution.others;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanTransform_777Test {

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

    @Test
    public void testEmptyX() {
        assertTrue(test.canTransform("", ""));
    }

    @Test
    public void testRCannotMoveLeft() {
        assertFalse(test.canTransform("XR", "RX"));
    }

    @Test
    public void testLCannotMoveRight() {
        assertFalse(test.canTransform("LX", "XL"));
    }

    @Test
    public void testRMovesRight() {
        assertTrue(test.canTransform("RX", "XR"));
    }

    @Test
    public void testLMovesLeft() {
        assertTrue(test.canTransform("XL", "LX"));
    }

    @Test
    public void testDifferentLengths() {
        assertFalse(test.canTransform("XL", "LXX"));
    }

    @Test
    public void testGiantCase() {
        String start = "R" + "X".repeat(1000) + "L";
        String end = "X".repeat(1000) + "R" + "L";
        assertTrue(test.canTransform(start, end));
    }
}
