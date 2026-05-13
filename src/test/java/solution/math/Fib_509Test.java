package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Fib_509Test {

    private final Fib_509 test = new Fib_509();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.fib(1));
        assertEquals(1, test.fib(2));
        assertEquals(5, test.fib(5));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.fib(0));
        assertEquals(1, test.fib(1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(55, test.fib(10));
        assertEquals(144, test.fib(12));
    }

    @Test
    public void testFib3() {
        assertEquals(2, test.fib(3));
    }

    @Test
    public void testFib4() {
        assertEquals(3, test.fib(4));
    }

    @Test
    public void testFib6() {
        assertEquals(8, test.fib(6));
    }

    @Test
    public void testFib7() {
        assertEquals(13, test.fib(7));
    }

    @Test
    public void testFib8() {
        assertEquals(21, test.fib(8));
    }

    @Test
    public void testFib9() {
        assertEquals(34, test.fib(9));
    }

    @Test
    public void testFib15() {
        assertEquals(610, test.fib(15));
    }

    @Test
    public void testFib20Giant() {
        assertEquals(6765, test.fib(20));
    }
}
