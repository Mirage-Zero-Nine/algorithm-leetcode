package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Tribonacci_1137Test {

    private final Tribonacci_1137 test = new Tribonacci_1137();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.tribonacci(4));
        assertEquals(7, test.tribonacci(5));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.tribonacci(0));
        assertEquals(1, test.tribonacci(1));
        assertEquals(1, test.tribonacci(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(149, test.tribonacci(10));
    }

    @Test
    public void testN3() {
        assertEquals(2, test.tribonacci(3));
    }

    @Test
    public void testN6() {
        assertEquals(13, test.tribonacci(6));
    }

    @Test
    public void testN7() {
        assertEquals(24, test.tribonacci(7));
    }

    @Test
    public void testN8() {
        assertEquals(44, test.tribonacci(8));
    }

    @Test
    public void testN15() {
        assertEquals(3136, test.tribonacci(15));
    }

    @Test
    public void testN20() {
        assertEquals(66012, test.tribonacci(20));
    }

    @Test
    public void testGiantN37() {
        assertEquals(2082876103, test.tribonacci(37));
    }
}
