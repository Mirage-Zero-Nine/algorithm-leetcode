package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Reverse7Test {

    private final Reverse_7 test = new Reverse_7();

    @Test
    public void testHappyCases() {
        assertEquals(321, test.reverse(123));
        assertEquals(-321, test.reverse(-123));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.reverse(0));
        assertEquals(0, test.reverse(1534236469));
    }

    @Test
    public void testLargeCase() {
        assertEquals(0L, (long) test.reverse(1534236469) == 0 ? 0 : test.reverse(1534236469));
        assertEquals(21, test.reverse(120));
    }
}
