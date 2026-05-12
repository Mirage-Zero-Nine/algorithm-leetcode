package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DinnerPlates1172Test {

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
}
