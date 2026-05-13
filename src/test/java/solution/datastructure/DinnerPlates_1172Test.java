package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DinnerPlates_1172Test {

    @Test
    public void testHappyCases() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        dp.push(1); dp.push(2); dp.push(3); dp.push(4); dp.push(5);
        assertEquals(2, dp.popAtStack(0));
        dp.push(20); dp.push(21);
        assertEquals(20, dp.popAtStack(0));
        assertEquals(21, dp.popAtStack(2));
        assertEquals(5, dp.pop());
        assertEquals(4, dp.pop());
        assertEquals(3, dp.pop());
        assertEquals(1, dp.pop());
        assertEquals(-1, dp.pop());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(1);
        assertEquals(-1, dp.pop());
        assertEquals(-1, dp.popAtStack(0));
    }

    @Test
    public void testLargeCase() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(3);
        for (int i = 1; i <= 9; i++) dp.push(i);
        assertEquals(9, dp.pop());
        assertEquals(8, dp.pop());
    }

    @Test
    public void testPopAtMissingIndexReturnsNegativeOne() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        dp.push(1);
        assertEquals(-1, dp.popAtStack(3));
    }

    @Test
    public void testPushFillsLeftmostAvailableStack() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        dp.push(1);
        dp.push(2);
        dp.push(3);
        dp.push(4);
        assertEquals(2, dp.popAtStack(0));
        dp.push(5);
        assertEquals(5, dp.popAtStack(0));
    }

    @Test
    public void testPopReturnsRightmostNonEmptyStackValue() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        dp.push(1);
        dp.push(2);
        dp.push(3);
        assertEquals(3, dp.pop());
        assertEquals(2, dp.pop());
    }

    @Test
    public void testPopAtStackCanEmptyMiddleStack() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        dp.push(10);
        dp.push(11);
        dp.push(20);
        dp.push(21);
        assertEquals(21, dp.popAtStack(1));
        assertEquals(20, dp.popAtStack(1));
        assertEquals(-1, dp.popAtStack(1));
    }

    @Test
    public void testInterleavedPopAtAndPushAcrossSparseStacks() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(2);
        for (int i = 1; i <= 6; i++) {
            dp.push(i);
        }
        assertEquals(4, dp.popAtStack(1));
        assertEquals(6, dp.popAtStack(2));
        dp.push(7);
        dp.push(8);
        assertEquals(7, dp.popAtStack(1));
        assertEquals(8, dp.popAtStack(2));
    }

    @Test
    public void testRepeatedPopOnEmptyStructure() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(3);
        assertEquals(-1, dp.pop());
        assertEquals(-1, dp.pop());
        dp.push(1);
        assertEquals(1, dp.pop());
        assertEquals(-1, dp.pop());
    }

    @Test
    public void testGiantCase() {
        DinnerPlates_1172 dp = new DinnerPlates_1172(5);
        for (int i = 1; i <= 5000; i++) {
            dp.push(i);
        }
        for (int i = 5000; i >= 4501; i--) {
            assertEquals(i, dp.pop());
        }
        for (int i = 0; i < 200; i++) {
            assertEquals(-1, dp.popAtStack(2000 + i));
        }
    }
}
