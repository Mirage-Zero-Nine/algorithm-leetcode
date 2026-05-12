package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClimbStairs70Test {

    private final ClimbStairs_70 test = new ClimbStairs_70();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.climbStairs(2));
        assertEquals(3, test.climbStairs(3));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.climbStairs(1));
        assertEquals(5, test.climbStairs(4));
    }

    @Test
    public void testLargeCase() {
        assertEquals(89, test.climbStairs(10));
    }
}
