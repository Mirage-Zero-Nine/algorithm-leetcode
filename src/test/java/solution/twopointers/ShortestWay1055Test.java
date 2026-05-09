package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShortestWay1055Test {

    private final ShortestWay_1055 test = new ShortestWay_1055();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.shortestWay("abc", "abcbc"));
        assertEquals(-1, test.shortestWay("abc", "acdbc"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(3, test.shortestWay("xyz", "xzyxz"));
        assertEquals(1, test.shortestWay("abc", "abc"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.shortestWay("ab", "ababababab"));
    }
}
