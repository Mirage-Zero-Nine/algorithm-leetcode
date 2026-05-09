package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GetPermutation60Test {

    private final GetPermutation_60 test = new GetPermutation_60();

    @Test
    public void testHappyCases() {
        assertEquals("213", test.getPermutation(3, 3));
        assertEquals("2314", test.getPermutation(4, 9));
    }

    @Test
    public void testEdgeCases() {
        assertEquals("1", test.getPermutation(1, 1));
        assertEquals("12", test.getPermutation(2, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals("2134", test.getPermutation(4, 7));
    }
}
