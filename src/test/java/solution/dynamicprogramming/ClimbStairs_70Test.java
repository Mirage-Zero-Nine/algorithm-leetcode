package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClimbStairs_70Test {

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

    @Test
    public void testN5() {
        assertEquals(8, test.climbStairs(5));
    }

    @Test
    public void testN6() {
        assertEquals(13, test.climbStairs(6));
    }

    @Test
    public void testN7() {
        assertEquals(21, test.climbStairs(7));
    }

    @Test
    public void testN8() {
        assertEquals(34, test.climbStairs(8));
    }

    @Test
    public void testN9() {
        assertEquals(55, test.climbStairs(9));
    }

    @Test
    public void testN20() {
        assertEquals(10946, test.climbStairs(20));
    }

    @Test
    public void testGiantCase() {
        assertEquals(1346269, test.climbStairs(30));
    }
}
