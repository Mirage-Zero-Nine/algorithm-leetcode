package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Fib509Test {

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
}
